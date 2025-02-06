package com.bkash.savings.migrator_dynamodb_pg.migrators.account;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.account.SavingsAccountEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryAssistedSavingsManagement;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SavingsAccountMigrationStep extends MigrationStep {

    private final SavingsAccountReader savingsAccountReader;
    private final SavingsAccountProcessor savingsAccountProcessor;
    private final SavingsAccountWriter savingsAccountWriter;
    private final InMemoryAssistedSavingsManagement inMemoryAssistedSavingsManagement;

    public SavingsAccountMigrationStep(SavingsAccountReader savingsAccountReader,
                                       SavingsAccountProcessor savingsAccountProcessor,
                                       SavingsAccountWriter savingsAccountWriter,
                                       AmazonDynamoDB amazonDynamoDB,
                                       ResourcePartitioner resourcePartitioner,
                                       InMemoryAssistedSavingsManagement inMemoryAssistedSavingsManagement,
                                       MigrationConfig migrationConfig) {
        super(resourcePartitioner, migrationConfig);

        this.savingsAccountReader = savingsAccountReader;
        this.savingsAccountProcessor = savingsAccountProcessor;
        this.savingsAccountWriter = savingsAccountWriter;
        this.inMemoryAssistedSavingsManagement = inMemoryAssistedSavingsManagement;
    }

    @Bean
    public Step createSavingsAccountMigration(JobRepository jobRepository,
                                              PlatformTransactionManager transactionManager) {

//        Todo-akash : Depends on nominee, assisted savings
        var savingsAccountStepConfig = migrationConfig.getSavingsAccountStepConfig();

        var step = new StepBuilder(StepNames.SAVINGS_ACCOUNT, jobRepository)
                .<SavingsAccountEntity, com.bkash.savings.models.postgres.account.SavingsAccountEntity>chunk(savingsAccountStepConfig.getChunkSize(),
                        transactionManager)
                .listener(inMemoryAssistedSavingsManagement)
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
