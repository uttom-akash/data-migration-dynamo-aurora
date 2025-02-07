package org.migration.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {
    @Value("${dynamodb.endpoint-url}")
    private String endpointUrl;

    @Value("${dynamodb.endpoint-region}")
    private String endpointRegion;

    @Value("${dynamodb.access-key}")
    private String accessKey;

    @Value("${dynamodb.access-secret-key}")
    private String accessSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AmazonDynamoDBClientBuilder.EndpointConfiguration(
                        endpointUrl, endpointRegion)) // LocalStack's DynamoDB endpoint
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, accessSecretKey))) // Dummy credentials for LocalStack
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }
}


