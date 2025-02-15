package org.migration.migrators.transaction;


import org.dynamo.models.paymentlog.PaymentLog;
import org.migration.configs.MigrationConfig;
import org.migration.configs.StepNames;
import org.migration.in_memory_dataset.InMemoryDepositAccountManagement;
import org.migration.migrators.base.MigrationStep;
import org.migration.migrators.base.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionMigrationStep extends MigrationStep {

    private final PaymentLogReader paymentHistoryReader;
    private final TransactionProcessor dpsTransactionProcessor;
    private final TransactionWriter dpsTransactionWriter;
    private final InMemoryDepositAccountManagement inMemorySavingsAccountManagement;
    private final InMemoryDepositAccountManagement inMemoryDepositAccountManagement;

    public TransactionMigrationStep(ResourcePartitioner resourcePartitioner,
                                    PaymentLogReader paymentHistoryReader,
                                    TransactionProcessor dpsTransactionProcessor,
                                    TransactionWriter dpsTransactionWriter,
                                    MigrationConfig migrationConfig,
                                    InMemoryDepositAccountManagement inMemorySavingsAccountManagement, InMemoryDepositAccountManagement inMemoryDepositAccountManagement) {
        super(resourcePartitioner, migrationConfig);
        this.paymentHistoryReader = paymentHistoryReader;
        this.dpsTransactionProcessor = dpsTransactionProcessor;
        this.dpsTransactionWriter = dpsTransactionWriter;
        this.inMemorySavingsAccountManagement = inMemorySavingsAccountManagement;
        this.inMemoryDepositAccountManagement = inMemoryDepositAccountManagement;
    }

    @Bean
    public Step createTransactionMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var transactionStepConfig = migrationConfig.getTransactionStepConfig();

        var step = new StepBuilder(StepNames.TRANSACTION_STEP, jobRepository)
                .<PaymentLog, TransactionEntity>chunk(transactionStepConfig.getChunkSize(), transactionManager)
                .listener(inMemorySavingsAccountManagement)
                .listener(inMemoryDepositAccountManagement)
                .reader(paymentHistoryReader)
                .processor(dpsTransactionProcessor)
                .writer(dpsTransactionWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.TRANSACTION_STEP, step, jobRepository, transactionStepConfig);
    }
}
