package com.bkash.savings.migrator_dynamodb_pg;

import com.bkash.savings.migrator_dynamodb_pg.migrators.account.SavingsAccountMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.assisted.AssistedSavingsMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.e_tin.ETinMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.nominee.NomineeMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.notification.NotificationMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.organization.OrganizationMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.product.ProductMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.product_type.ProductTypeMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.settings.SettingsMigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.transaction.TransactionMigrationStep;
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

    private final NomineeMigrationStep nomineeMigrationStep;
    private final AssistedSavingsMigrationStep assistedSavingsAccountMigrationStep;
    private final SavingsAccountMigrationStep savingsAccountMigrationStep;
    private final TransactionMigrationStep transactionMigrationStep;
    private final ProductMigrationStep productMigrationStep;
    private final OrganizationMigrationStep organizationMigrationStep;
    private final NotificationMigrationStep notificationMigrationStep;
    private final SettingsMigrationStep settingsMigrationStep;
    private final ETinMigrationStep eTinMigrationStep;
    private final ProductTypeMigrationStep productTypeMigrationStep;

    public JobConfiguration(NomineeMigrationStep nomineeMigrationStep, AssistedSavingsMigrationStep assistedSavingsAccountMigrationStep, SavingsAccountMigrationStep savingsAccountMigrationStep, TransactionMigrationStep dpsTransactionMigrationStep, ProductMigrationStep productMigrationStep, OrganizationMigrationStep organizationMigrationStep, NotificationMigrationStep notificationMigrationStep, SettingsMigrationStep settingsMigrationStep, ETinMigrationStep eTinMigrationStep, ProductTypeMigrationStep productTypeMigrationStep) {
        this.nomineeMigrationStep = nomineeMigrationStep;
        this.assistedSavingsAccountMigrationStep = assistedSavingsAccountMigrationStep;
        this.savingsAccountMigrationStep = savingsAccountMigrationStep;
        this.transactionMigrationStep = dpsTransactionMigrationStep;
        this.productMigrationStep = productMigrationStep;
        this.organizationMigrationStep = organizationMigrationStep;
        this.notificationMigrationStep = notificationMigrationStep;
        this.settingsMigrationStep = settingsMigrationStep;
        this.eTinMigrationStep = eTinMigrationStep;
        this.productTypeMigrationStep = productTypeMigrationStep;
    }

    @Bean
    public Job migrationJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("Migration-Job", jobRepository)
                .start(productTypeMigrationStep.createProductTypeMigration(jobRepository,transactionManager))
                .next(productMigrationStep.createProductMigration(jobRepository, transactionManager))
                .next(eTinMigrationStep.createETinMigration(jobRepository, transactionManager))
                .next(organizationMigrationStep.createOrganizationMigration(jobRepository, transactionManager))
                .next(notificationMigrationStep.createNotificationMigration(jobRepository, transactionManager))
                .next(settingsMigrationStep.createSettingsMigration(jobRepository, transactionManager))
                .next(nomineeMigrationStep.createNomineeMigration(jobRepository, transactionManager))
                .next(assistedSavingsAccountMigrationStep.createAssistedSavingsMigration(jobRepository, transactionManager))
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
