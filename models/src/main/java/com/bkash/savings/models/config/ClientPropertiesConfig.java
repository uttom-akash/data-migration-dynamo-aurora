package com.bkash.savings.models.config;

import com.bkash.savings.models.properties.ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ClientProperties.class})
public class ClientPropertiesConfig {
}
