package com.bkash.savings.migrator_dynamodb_pg.migrators.product_type;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.settings.SettingsEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import com.bkash.savings.migrator_dynamodb_pg.migrators.settings.SettingsReader;
import com.bkash.savings.models.postgres.product.ProductType;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class ProductTypeMigrationStep extends MigrationStep {

    private final SettingsReader settingsReader;
    private final ProductTypeProcessor settingsProcessor;
    private final ProductTypeWriter settingsWriter;

    public ProductTypeMigrationStep(MigrationConfig migrationConfig, ResourcePartitioner resourcePartitioner, SettingsReader settingsReader, ProductTypeProcessor settingsProcessor, ProductTypeWriter settingsWriter) {
        super(resourcePartitioner, migrationConfig);
        this.settingsReader = settingsReader;
        this.settingsProcessor = settingsProcessor;
        this.settingsWriter = settingsWriter;
    }

    @Bean
    public Step createProductTypeMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var setSettingsStepConfig = migrationConfig.getSettingsStepConfig();

        var step = new StepBuilder(StepNames.Settings, jobRepository)
                .<SettingsEntity, ProductType>chunk(setSettingsStepConfig.getChunkSize(), transactionManager)
                .reader(settingsReader)
                .processor(settingsProcessor)
                .writer(settingsWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.Settings, step, jobRepository, setSettingsStepConfig);
    }
}
