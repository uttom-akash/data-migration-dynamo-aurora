package org.migration;


import org.migration.migrators.product.ProductMigrationStep;
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
public class MigrationJobConfiguration {

    private final ProductMigrationStep productMigrationStep;

    public MigrationJobConfiguration(ProductMigrationStep productMigrationStep) {
        this.productMigrationStep = productMigrationStep;
    }

    @Bean
    public Job migrationJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("Migration-Job", jobRepository)
                .start(productMigrationStep.createProductMigration(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public CommandLineRunner run(JobLauncher jobLauncher, Job migrationJob) {

        return args -> {
            jobLauncher.run(
                    migrationJob,
                    new JobParametersBuilder()
                            .addLong("time",
                                    System.currentTimeMillis())
                            .toJobParameters());
        };
    }
}
