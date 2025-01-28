package org.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;

@Configuration
//@EnableBatchProcessing
public class BatchConfiguration {


//    public FlatFileItemReader<InputData> reader(@Value("#{stepExecutionContext[start]}") Integer start, @Value("#{stepExecutionContext[end]}") Integer end) {
//
//        System.out.println("reading file from "+start+ " to "+end);
//
//        FlatFileItemReader<InputData> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("step1/input.csv"));
//        reader.setLineMapper(new DefaultLineMapper<InputData>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames("id", "name", "age");
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                setTargetType(InputData.class);
//            }});
//        }});
//        return reader;
//    }

    @Bean
    @StepScope
    public FlatFileItemReader<InputData> reader(@Value("#{stepExecutionContext[start]}") Integer start, @Value("#{stepExecutionContext[end]}") Integer end) {

        FlatFileItemReader<InputData> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("step1/input.csv"));

        // Set line mapper and other configurations
        reader.setLineMapper(new DefaultLineMapper<InputData>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "name", "age");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(InputData.class);
            }});
        }});

        // Debugging lines to see if it's correctly configured
        System.out.println("Reading from line " + start + " to " + end);

        reader.setLinesToSkip(start - 1);  // Skip lines up to the start
        reader.setMaxItemCount(end - start + 1);  // Limit to the number of items in the partition

        return reader;
    }


    // Writing output file
    @Bean
    @StepScope
    public FlatFileItemWriter<OutputData> writer(@Value("#{stepExecutionContext[start]}") Integer start, @Value("#{stepExecutionContext[end]}") Integer end) {
        FlatFileItemWriter<OutputData> writer = new FlatFileItemWriter<>();

        // Specify FileSystemResource for output file
        writer.setResource(new FileSystemResource("/home/akash/Dev/java-practice/data-migration-dynamoddb-aurorapg/src/main/resources/step1/output-"+start+"-"+end+".csv"));
        writer.setHeaderCallback(writerHeader -> writerHeader.write("id,name,age,status"));
        writer.setLineAggregator(item -> String.join(",",
                String.valueOf(item.getId()),
                item.getName(),
                String.valueOf(item.getAge()),
                item.getStatus()
        ));

        return writer;
    }

    // Processor with console log for debugging
    @Bean
    public ItemProcessor<InputData, OutputData> processor() {
        return input -> {
            System.out.println("Processing name : " + input.getName());
            return new OutputData(input.getId(), input.getName(), input.getAge(), "Processed");
        };
    }

    // Defining step with chunk-oriented processing
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(4); // Number of threads for parallel processing
        return taskExecutor;
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<InputData, OutputData>chunk(10, transactionManager)
                .reader(reader(null, null))
                .processor(processor())
                .writer(writer(null,null))
                //.taskExecutor(taskExecutor())
                .transactionManager(transactionManager) // Ensuring transaction management
                .build();
    }

    // Partitioner to divide data into chunks for parallel processing
    @Bean
    public Partitioner partitioner() {
        return new ResourcePartitioner();
    }

    // Define step with partitioned execution
    @Bean
    public Step partitionedStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("partitionedStep", jobRepository)
                .partitioner("step1", partitioner()) // Link the partitioner
                .step(step1(jobRepository, transactionManager))  // Reusing the existing step
                .gridSize(3)  // Number of partitions
                .taskExecutor(taskExecutor())  // Parallel execution
                .build();
    }

    // Defining job with the step
    @Bean
    public Job importUserJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("importUserJob", jobRepository)
                .start(partitionedStep(jobRepository, transactionManager))
                .build();
    }

    // Running the job manually after application starts
    @Bean
    public CommandLineRunner run(JobLauncher jobLauncher, Job importUserJob) {
        return args -> {
            System.out.println("Running job...");
            jobLauncher.run(importUserJob, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
        };
    }
}
