package org.migration.migrators.base;

import org.migration.configs.MigrationConfig;
import org.migration.configs.StepConfig;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

public class MigrationStep {

    protected final MigrationConfig migrationConfig;
    private final ResourcePartitioner resourcePartitioner;

    public MigrationStep(ResourcePartitioner resourcePartitioner, MigrationConfig migrationConfig) {
        this.resourcePartitioner = resourcePartitioner;
        this.migrationConfig = migrationConfig;
    }

    protected TaskExecutor taskExecutor(int numberOfThreads) {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(numberOfThreads);
        return taskExecutor;
    }

    protected Step createPartitionedStep(String stepName, Step step, JobRepository jobRepository, StepConfig stepConfig) {
        return new StepBuilder(stepName, jobRepository)
                .partitioner(stepName, resourcePartitioner)
                .step(step)
                .gridSize(stepConfig.getNumberOfPartitions())
                .taskExecutor(taskExecutor(stepConfig.getNumberOfThreads()))
                .build();
    }
}
