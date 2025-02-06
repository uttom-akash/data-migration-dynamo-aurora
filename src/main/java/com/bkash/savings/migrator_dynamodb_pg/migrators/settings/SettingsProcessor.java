package com.bkash.savings.migrator_dynamodb_pg.migrators.settings;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings.SettingsEntity;
import com.bkash.savings.models.postgres.settings.SettingsValue;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class SettingsProcessor implements ItemProcessor<SettingsEntity, com.bkash.savings.models.postgres.settings.SettingsEntity> {

    @Override
    public com.bkash.savings.models.postgres.settings.SettingsEntity process(SettingsEntity entity) throws Exception {

        var settingValues = new ArrayList<SettingsValue>();

        for (var value : entity.getValues()) {
            settingValues.add(SettingsValue.builder()
                    .key(value.getKey())
                    .text(value.getText())
                    .build());
        }

        return com.bkash.savings.models.postgres.settings.SettingsEntity.builder()
                .key(entity.getKey())
                .settingsValues(settingValues)
                .build();
    }
}






