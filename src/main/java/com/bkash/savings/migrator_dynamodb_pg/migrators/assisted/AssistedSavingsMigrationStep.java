package com.bkash.savings.migrator_dynamodb_pg.migrators.assisted;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.assisted.AssistedSavingsEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryNomineeManagement;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AssistedSavingsMigrationStep extends MigrationStep {

    private final AssistedSavingsReader assistedSavingsReader;
    private final AssistedSavingsProcessor assistedSavingsProcessor;
    private final AssistedSavingsWriter assistedSavingsWriter;
    private final InMemoryNomineeManagement inMemoryNomineeManagement;

    public AssistedSavingsMigrationStep(ResourcePartitioner resourcePartitioner,
                                        AssistedSavingsReader assistedSavingsReader,
                                        AssistedSavingsProcessor assistedSavingsProcessor,
                                        AssistedSavingsWriter assistedSavingsWriter,
                                        InMemoryNomineeManagement inMemoryNomineeManagement, MigrationConfig migrationConfig) {
        super(resourcePartitioner, migrationConfig);

        this.assistedSavingsReader = assistedSavingsReader;
        this.assistedSavingsProcessor = assistedSavingsProcessor;
        this.assistedSavingsWriter = assistedSavingsWriter;
        this.inMemoryNomineeManagement = inMemoryNomineeManagement;
    }


    @Bean
    public Step createAssistedSavingsMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var assistedSavingsStepConfig = migrationConfig.getAssistedSavingsStepConfig();

        var step = new StepBuilder(StepNames.AssistedSavings, jobRepository)
                .<AssistedSavingsEntity, com.bkash.savings.models.postgres.account.AssistedSavingsEntity>chunk(assistedSavingsStepConfig.getChunkSize(), transactionManager)
                .listener(inMemoryNomineeManagement)
                .reader(assistedSavingsReader)
                .processor(assistedSavingsProcessor)
                .writer(assistedSavingsWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.AssistedSavings, step, jobRepository, assistedSavingsStepConfig);
    }
}
