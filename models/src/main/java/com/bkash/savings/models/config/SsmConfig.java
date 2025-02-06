package com.bkash.savings.models.config;

import java.net.URI;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bkash.savings.models.properties.ClientProperties;
import com.bkash.savings.models.util.ProfileConstants;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;

@Configuration
@Slf4j
public class SsmConfig {

    @Bean
    public SsmClient ssmClient(ClientProperties properties, AwsCredentialsProvider awsCredentialsProvider) {
        log.info("SsmConfig|ssmClient:: Creating SsmClient bean with {}", properties.getFi().getSsm());
        if (ProfileConstants.PROFILE_DEV.equals(properties.getFi().getSsm().getProfile())) {
            return SsmClient.builder()
                    .region(Region.of(Objects.requireNonNull(properties.getFi().getSsm().getRegion())))
                    .endpointOverride(URI.create(properties.getFi().getSsm().getEndpoint()))
                    .credentialsProvider(awsCredentialsProvider)
                    .build();
        }
        
        var client = SsmClient.builder().build();
        Region region = client.serviceClientConfiguration().region();
        log.info("SsmConfig|ssmClient:: SSM Region: {}", region.toString());
        return client;
    }
}
