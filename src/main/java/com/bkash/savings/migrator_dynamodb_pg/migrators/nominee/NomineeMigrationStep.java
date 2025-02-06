package com.bkash.savings.migrator_dynamodb_pg.migrators.nominee;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.nominee.NomineeEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class NomineeMigrationStep extends MigrationStep {

    private final NomineeReader nomineeReader;
    private final NomineeProcessor nomineeProcessor;
    private final NomineeWriter nomineeWriter;

    public NomineeMigrationStep(MigrationConfig migrationConfig, NomineeReader nomineeReader, NomineeProcessor nomineeProcessor, NomineeWriter nomineeWriter, ResourcePartitioner resourcePartitioner) {
        super(resourcePartitioner, migrationConfig);
        this.nomineeReader = nomineeReader;
        this.nomineeProcessor = nomineeProcessor;
        this.nomineeWriter = nomineeWriter;
    }

    @Bean
    public Step createNomineeMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var nomineeStepConfig = migrationConfig.getNomineeStepConfig();

        var step = new StepBuilder(StepNames.NOMINEE_STEP, jobRepository)
                .<NomineeEntity, com.bkash.savings.models.postgres.nominee.NomineeEntity>chunk(nomineeStepConfig.getChunkSize(), transactionManager)
                .reader(nomineeReader)
                .processor(nomineeProcessor)
                .writer(nomineeWriter)
                .transactionManager(transactionManager)
                .build();

        return createPartitionedStep(StepNames.AssistedSavings, step, jobRepository, nomineeStepConfig);
    }
}
