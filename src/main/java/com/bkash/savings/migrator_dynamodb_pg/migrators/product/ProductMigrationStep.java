package com.bkash.savings.migrator_dynamodb_pg.migrators.product;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.product.ProductSchemeEntity;
import com.bkash.savings.migrator_dynamodb_pg.in_memory_data.InMemoryAvailabilityManagement;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProductMigrationStep extends MigrationStep {


    private final ProductReader productReader;
    private final ProductProcessor productProcessor;
    private final ProductWriter productWriter;
    private final InMemoryAvailabilityManagement inMemoryAvailabilityManagement;

    public ProductMigrationStep(MigrationConfig migrationConfig, ProductReader productReader, ProductProcessor productProcessor, ProductWriter productWriter, ResourcePartitioner resourcePartitioner, InMemoryAvailabilityManagement inMemoryAvailabilityManagement) {
        super(resourcePartitioner, migrationConfig);
        this.productReader = productReader;
        this.productProcessor = productProcessor;
        this.productWriter = productWriter;
        this.inMemoryAvailabilityManagement = inMemoryAvailabilityManagement;
    }

    @Bean
    public Step createProductMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var productStepConfig = migrationConfig.getProductStepConfig();

        var step = new StepBuilder(StepNames.Product, jobRepository)
                .<ProductSchemeEntity, com.bkash.savings.models.postgres.product.ProductEntity>chunk(productStepConfig.getChunkSize(), transactionManager)
                .listener(inMemoryAvailabilityManagement)
                .reader(productReader)
                .processor(productProcessor)
                .writer(productWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.Product, step, jobRepository, productStepConfig);
    }
}
