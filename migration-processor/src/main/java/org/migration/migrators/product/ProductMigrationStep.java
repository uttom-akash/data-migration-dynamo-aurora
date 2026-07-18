package org.migration.migrators.product;

import org.aurora.postgres.product.Product;
import org.dynamo.models.product.ProductEntity;
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
public class ProductMigrationStep extends MigrationStep {

    private final ProductReader productReader;
    private final ProductProcessor productProcessor;
    private final ProductWriter productWriter;

    public ProductMigrationStep(ProductReader productReader,
                                ProductProcessor productProcessor,
                                ProductWriter productWriter,
                                ResourcePartitioner resourcePartitioner,
                                MigrationConfig migrationConfig) {
        super(resourcePartitioner, migrationConfig);
        this.productReader = productReader;
        this.productProcessor = productProcessor;
        this.productWriter = productWriter;
    }

    @Bean
    public Step createProductMigration(JobRepository jobRepository,
                                       PlatformTransactionManager transactionManager) {

        var productStepConfig = migrationConfig.getProductStepConfig();

        var step = new StepBuilder(StepNames.PRODUCT_STEP, jobRepository)
                .<ProductEntity, Product>chunk(productStepConfig.getChunkSize(), transactionManager)
                .reader(productReader)
                .processor(productProcessor)
                .writer(productWriter)
                .transactionManager(transactionManager)
                .build();

        return createPartitionedStep(StepNames.PRODUCT_STEP, step, jobRepository, productStepConfig);
    }
}
