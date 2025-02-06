package org.migration.migrators.deposit_account;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.aurora.postgres.deposit_account.DepositAccount;
import org.dynamo.models.dps_account.DpsAccountEntity;
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
public class DepositAccountMigrationStep extends MigrationStep {

    private final DepositAccountReader savingsAccountReader;
    private final DepositAccountProcessor savingsAccountProcessor;
    private final DepositAccountWriter savingsAccountWriter;

    public DepositAccountMigrationStep(DepositAccountReader savingsAccountReader,
                                       DepositAccountProcessor savingsAccountProcessor,
                                       DepositAccountWriter savingsAccountWriter,
                                       AmazonDynamoDB amazonDynamoDB,
                                       ResourcePartitioner resourcePartitioner,
                                       MigrationConfig migrationConfig) {
        super(resourcePartitioner, migrationConfig);

        this.savingsAccountReader = savingsAccountReader;
        this.savingsAccountProcessor = savingsAccountProcessor;
        this.savingsAccountWriter = savingsAccountWriter;
    }

    @Bean
    public Step createSavingsAccountMigration(JobRepository jobRepository,
                                              PlatformTransactionManager transactionManager) {

//        Todo-akash : Depends on nominee, assisted savings
        var savingsAccountStepConfig = migrationConfig.getSavingsAccountStepConfig();

        var step = new StepBuilder(StepNames.SAVINGS_ACCOUNT, jobRepository)
                .<DpsAccountEntity, DepositAccount>chunk(savingsAccountStepConfig.getChunkSize(),
                        transactionManager)

                .reader(savingsAccountReader)
                .processor(savingsAccountProcessor)
                .writer(savingsAccountWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.SAVINGS_ACCOUNT,
                step,
                jobRepository,
                savingsAccountStepConfig);
    }
}
