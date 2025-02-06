package org.migration;


import org.migration.migrators.deposit_account.DepositAccountMigrationStep;
import org.migration.migrators.deposit_nominee.DepositNomineeMigrationStep;
import org.migration.migrators.transaction.TransactionMigrationStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfiguration {

    private final DepositNomineeMigrationStep nomineeMigrationStep;
    private final DepositAccountMigrationStep savingsAccountMigrationStep;
    private final TransactionMigrationStep transactionMigrationStep;

    public JobConfiguration(DepositNomineeMigrationStep nomineeMigrationStep,
                            DepositAccountMigrationStep savingsAccountMigrationStep,
                            TransactionMigrationStep dpsTransactionMigrationStep) {
        this.nomineeMigrationStep = nomineeMigrationStep;
        this.savingsAccountMigrationStep = savingsAccountMigrationStep;
        this.transactionMigrationStep = dpsTransactionMigrationStep;
    }

    @Bean
    public Job migrationJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("Migration-Job", jobRepository)
                .start(nomineeMigrationStep.createNomineeMigration(jobRepository, transactionManager))
                .next(savingsAccountMigrationStep.createSavingsAccountMigration(jobRepository, transactionManager))
                .next(transactionMigrationStep.createTransactionMigration(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public CommandLineRunner run(JobLauncher jobLauncher, Job migrationJob) {

        return args -> {
            System.out.println("Running job...");
            jobLauncher.run(
                    migrationJob,
                    new JobParametersBuilder()
                            .addLong("time",
                                    System.currentTimeMillis())
                            .toJobParameters());
        };
    }
}
