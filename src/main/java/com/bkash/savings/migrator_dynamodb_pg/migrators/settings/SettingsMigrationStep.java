package com.bkash.savings.migrator_dynamodb_pg.migrators.settings;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings.SettingsEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SettingsMigrationStep extends MigrationStep {

    private final SettingsReader settingsReader;
    private final SettingsProcessor settingsProcessor;
    private final SettingsWriter settingsWriter;

    public SettingsMigrationStep(MigrationConfig migrationConfig, ResourcePartitioner resourcePartitioner, SettingsReader settingsReader, SettingsProcessor settingsProcessor, SettingsWriter settingsWriter) {
        super(resourcePartitioner, migrationConfig);

        this.settingsReader = settingsReader;
        this.settingsProcessor = settingsProcessor;
        this.settingsWriter = settingsWriter;
    }

    @Bean
    public Step createSettingsMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var setSettingsStepConfig = migrationConfig.getSettingsStepConfig();

        var step = new StepBuilder(StepNames.Settings, jobRepository)
                .<SettingsEntity, com.bkash.savings.models.postgres.settings.SettingsEntity>chunk(setSettingsStepConfig.getChunkSize(), transactionManager)
                .reader(settingsReader)
                .processor(settingsProcessor)
                .writer(settingsWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.Settings, step, jobRepository, setSettingsStepConfig);
    }
}
