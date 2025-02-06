package com.bkash.savings.migrator_dynamodb_pg.migrators.transaction;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.paymenthistory.PaymentEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryProductManagement;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemorySavingsAccountManagement;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionMigrationStep extends MigrationStep {

    private final PaymentHistoryReader paymentHistoryReader;
    private final TransactionProcessor dpsTransactionProcessor;
    private final TransactionWriter dpsTransactionWriter;
    private final InMemorySavingsAccountManagement inMemorySavingsAccountManagement;
    private final InMemoryProductManagement inMemoryProductManagement;

    public TransactionMigrationStep(ResourcePartitioner resourcePartitioner, PaymentHistoryReader paymentHistoryReader, TransactionProcessor dpsTransactionProcessor, TransactionWriter dpsTransactionWriter, MigrationConfig migrationConfig, InMemorySavingsAccountManagement inMemorySavingsAccountManagement, InMemoryProductManagement inMemoryProductManagement) {
        super(resourcePartitioner, migrationConfig);
        this.paymentHistoryReader = paymentHistoryReader;
        this.dpsTransactionProcessor = dpsTransactionProcessor;
        this.dpsTransactionWriter = dpsTransactionWriter;
        this.inMemorySavingsAccountManagement = inMemorySavingsAccountManagement;
        this.inMemoryProductManagement = inMemoryProductManagement;
    }

    @Bean
    public Step createTransactionMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var transactionStepConfig = migrationConfig.getTransactionStepConfig();

        var step = new StepBuilder(StepNames.TRANSACTION_STEP, jobRepository)
                .<PaymentEntity, TransactionEntity>chunk(10, transactionManager)
                .listener(inMemoryProductManagement)
                .listener(inMemorySavingsAccountManagement)
                .reader(paymentHistoryReader)
                .processor(dpsTransactionProcessor)
                .writer(dpsTransactionWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.TRANSACTION_STEP, step, jobRepository, transactionStepConfig);
//
//                new StepBuilder(stepName, jobRepository)
//                .partitioner(stepName, resourcePartitioner)
//                .step(step)
//                .gridSize(4)
//                .taskExecutor(taskExecutor())
//                .build();
    }
}
