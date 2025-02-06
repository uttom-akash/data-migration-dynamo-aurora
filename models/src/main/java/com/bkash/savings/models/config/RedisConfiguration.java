package com.bkash.savings.models.config;

import com.bkash.savings.models.properties.PlatformProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import software.amazon.awssdk.services.ssm.SsmClient;

import java.time.Duration;

@Configuration
@ComponentScan(basePackages = "com.bkash.savings")
@RequiredArgsConstructor
@Slf4j
@DependsOn("ssmClient")
public class RedisConfiguration {

    private final PlatformProperties platformProperties;

    @Bean
    @Primary
    public RedisConnectionFactory redisConnectionFactory(SsmClient ssmClient) {
        log.info("RedisConfiguration|redisConnectionFactory:: Creating RedisConnectionFactory bean");
        var response = ssmClient.getParameter(
                builder -> builder.name(platformProperties.getRedis().getPasswordSsmPath())
                        .withDecryption(true));
        var password = response.parameter().value();

        var configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(platformProperties.getRedis().getHost());
        configuration.setPassword(password);
        configuration.setPort(platformProperties.getRedis().getPort());
        configuration.setDatabase(platformProperties.getRedis().getDatabase());

        var clientConfiguration = platformProperties.getRedis().getUseSsl() ?
                LettuceClientConfiguration.builder()
                        .commandTimeout(Duration.ofMillis(platformProperties.getRedis().getConnectTimeout()))
                        .useSsl()
                        .startTls()
                        .build() :
                LettuceClientConfiguration.builder()
                        .commandTimeout(Duration.ofMillis(platformProperties.getRedis().getConnectTimeout()))
                        .build();

        return new LettuceConnectionFactory(configuration, clientConfiguration);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }


    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use Jackson for JSON serialization
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(new ObjectMapper());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        return template;
    }
}
