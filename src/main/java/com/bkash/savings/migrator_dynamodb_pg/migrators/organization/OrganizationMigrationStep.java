package com.bkash.savings.migrator_dynamodb_pg.migrators.organization;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.organization.OrganizationEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class OrganizationMigrationStep extends MigrationStep {

    private final OrganizationReader organizationReader;
    private final OrganizationProcessor organizationProcessor;
    private final OrganizationWriter organizationWriter;

    public OrganizationMigrationStep(MigrationConfig migrationConfig, OrganizationReader organizationReader, OrganizationProcessor organizationProcessor, OrganizationWriter organizationWriter, ResourcePartitioner resourcePartitioner) {
        super(resourcePartitioner, migrationConfig);

        this.organizationReader = organizationReader;
        this.organizationProcessor = organizationProcessor;
        this.organizationWriter = organizationWriter;
    }

    @Bean
    public Step createOrganizationMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var organizationStepConfig = migrationConfig.getOrganizationStepConfig();

        var step = new StepBuilder(StepNames.OrgnizationStep, jobRepository)
                .<OrganizationEntity, com.bkash.savings.models.postgres.organization.OrganizationEntity>chunk(organizationStepConfig.getChunkSize(), transactionManager)
                .reader(organizationReader)
                .processor(organizationProcessor)
                .writer(organizationWriter)
                .transactionManager(transactionManager)
                .build();

        return createPartitionedStep(StepNames.OrgnizationStep, step, jobRepository, organizationStepConfig);
    }
}
