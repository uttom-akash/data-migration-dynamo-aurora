package com.bkash.savings.migrator_dynamodb_pg.migrators.settings;


import com.bkash.savings.migrator_dynamodb_pg.migrators.common.PostgresWriter;
import com.bkash.savings.models.postgres.settings.SettingsEntity;
import com.bkash.savings.models.postgres.settings.SettingsValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SettingsWriter extends PostgresWriter implements ItemWriter<SettingsEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void write(Chunk<? extends SettingsEntity> items) throws Exception {

        for (SettingsEntity settingsEntity : items.getItems()) {
            SettingsEntity existingEntity = (SettingsEntity) entityManager.createQuery(
                            "SELECT e FROM SettingsEntity e WHERE e.key = :key")
                    .setParameter("key", settingsEntity.getKey())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);


            merge(settingsEntity, existingEntity);

            for (var settingValue : settingsEntity.getSettingsValues()){

                SettingsValue existingValue = (SettingsValue) entityManager.createQuery(
                                "SELECT e FROM SettingsValue e" +
                                        " WHERE e.key = :key and e.settingsId = :settings_id")
                        .setParameter("key", settingValue.getKey())
                        .setParameter("settings_id", settingsEntity.getId())
                        .getResultStream()
                        .findFirst()
                        .orElse(null);

                merge(settingValue, existingValue);
            }
        }

        entityManager.flush();

        System.out.println("Writing DpsTransactionEntity");
        System.out.println(items.getItems());
    }

    public class SettingsValueEx extends com.bkash.savings.models.postgres.settings.SettingsValue{
        public String settingsId;
    }
}