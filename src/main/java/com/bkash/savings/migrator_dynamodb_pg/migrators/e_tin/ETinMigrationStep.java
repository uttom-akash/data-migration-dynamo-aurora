package com.bkash.savings.migrator_dynamodb_pg.migrators.e_tin;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.etin.EtinEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ETinMigrationStep extends MigrationStep {

    private final ETinReader etinReader;
    private final EtinProcessor etinProcessor;
    private final ETinWriter etinWriter;

    public ETinMigrationStep(MigrationConfig migrationConfig, ResourcePartitioner resourcePartitioner, ETinReader etinReader, EtinProcessor etinProcessor, ETinWriter etinWriter) {
        super(resourcePartitioner, migrationConfig);
        this.etinReader = etinReader;
        this.etinProcessor = etinProcessor;
        this.etinWriter = etinWriter;
    }

    @Bean
    public Step createETinMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        Todo-akash : Depends on nominee
        var eTinStepConfig = migrationConfig.getETinStepConfig();

        var step = new StepBuilder(StepNames.ETIN_STEP, jobRepository)
                .<EtinEntity, com.bkash.savings.models.postgres.etin.ETINEntity>chunk(eTinStepConfig.getChunkSize(), transactionManager)
                .reader(etinReader)
                .processor(etinProcessor)
                .writer(etinWriter)
                .transactionManager(transactionManager)
                .build();

        return createPartitionedStep(StepNames.ETIN_STEP, step, jobRepository, eTinStepConfig);
    }
}
