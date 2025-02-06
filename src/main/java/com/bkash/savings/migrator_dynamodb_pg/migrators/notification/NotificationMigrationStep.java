package com.bkash.savings.migrator_dynamodb_pg.migrators.notification;

import com.bkash.savings.migrator_dynamodb_pg.configs.MigrationConfig;
import com.bkash.savings.migrator_dynamodb_pg.configs.StepNames;
import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.notification.NotificationEntity;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.MigrationStep;
import com.bkash.savings.migrator_dynamodb_pg.migrators.common.ResourcePartitioner;
import com.bkash.savings.migrator_dynamodb_pg.migrators.nominee.NomineeWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class NotificationMigrationStep extends MigrationStep {

    private final NotificationReader notificationReader;
    private final NotificationProcessor notificationProcessor;
    private final NotificationWriter notificationWriter;

    public NotificationMigrationStep(MigrationConfig migrationConfig, NotificationReader notificationReader, NotificationProcessor notificationProcessor, NotificationWriter notificationWriter, ResourcePartitioner resourcePartitioner) {
        super(resourcePartitioner, migrationConfig);
        this.notificationReader = notificationReader;
        this.notificationProcessor = notificationProcessor;
        this.notificationWriter = notificationWriter;
    }


    @Bean
    public Step createNotificationMigration(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

        var notificationStepConfig = migrationConfig.getNotificationStepConfig();

        var step = new StepBuilder(StepNames.NOTIFICATION_STEP, jobRepository)
                .<NotificationEntity, com.bkash.savings.models.postgres.notification.NotificationEntity>chunk(notificationStepConfig.getChunkSize(), transactionManager)
                .reader(notificationReader)
                .processor(notificationProcessor)
                .writer(notificationWriter)
                .transactionManager(transactionManager)
                .build();


        return createPartitionedStep(StepNames.NOTIFICATION_STEP, step, jobRepository, notificationStepConfig);
    }
}
