package org.migration.migrators.deposit_nominee;


import org.aurora.postgres.deposit_nominee.DepositNominee;
import org.dynamo.models.dps_nominee.Nominee;
import org.migration.configs.MigrationConfig;
import org.migration.configs.StepNames;
import org.migration.migrators.base.MigrationStep;
import org.migration.migrators.base.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DepositNomineeMigrationStep extends MigrationStep {

    private final DepositNomineeReader nomineeReader;
    private final DepositNomineeProcessor nomineeProcessor;
    private final DepositNomineeWriter nomineeWriter;

    public DepositNomineeMigrationStep(MigrationConfig migrationConfig,
                                       DepositNomineeReader nomineeReader,
                                       DepositNomineeProcessor nomineeProcessor,
                                       DepositNomineeWriter nomineeWriter,
                                       ResourcePartitioner resourcePartitioner) {
        super(resourcePartitioner, migrationConfig);
        this.nomineeReader = nomineeReader;
        this.nomineeProcessor = nomineeProcessor;
        this.nomineeWriter = nomineeWriter;
    }

    @Bean
    public Step createNomineeMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var nomineeStepConfig = migrationConfig.getNomineeStepConfig();

        var step = new StepBuilder(StepNames.NOMINEE_STEP, jobRepository)
                .<Nominee, DepositNominee>chunk(nomineeStepConfig.getChunkSize(), transactionManager)
                .reader(nomineeReader)
                .processor(nomineeProcessor)
                .writer(nomineeWriter)
                .transactionManager(transactionManager)
                .build();

        return createPartitionedStep(StepNames.NOMINEE_STEP, step, jobRepository, nomineeStepConfig);
    }
}
