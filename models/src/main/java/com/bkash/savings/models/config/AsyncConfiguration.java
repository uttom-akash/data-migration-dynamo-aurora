package com.bkash.savings.models.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.bkash.savings.models.properties.PlatformProperties;

@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.bkash.savings")
public class AsyncConfiguration {
    @Value("${spring.threads.virtual.enabled:false}")
    private Boolean isVirtualThreadEnabled;

    @Bean
    public Executor platformAsyncExecutor(PlatformProperties properties) {
        if (isVirtualThreadEnabled) {
            return new VirtualThreadTaskExecutor(properties.getAsync().getThreadNamePrefix());
        }
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getAsync().getCorePoolSize());
        executor.setMaxPoolSize(properties.getAsync().getMaxPoolSize());
        executor.setQueueCapacity(properties.getAsync().getQueueCapacity());
        executor.setThreadNamePrefix(properties.getAsync().getThreadNamePrefix());
        executor.setAllowCoreThreadTimeOut(properties.getAsync().getAllowCoreThreadTimeOut());
        executor.setKeepAliveSeconds(properties.getAsync().getKeepAliveSeconds());
        executor.initialize();
        return executor;
    }
}
