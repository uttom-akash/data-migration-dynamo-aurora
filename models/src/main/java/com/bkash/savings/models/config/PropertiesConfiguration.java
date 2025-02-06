package com.bkash.savings.models.config;

import com.bkash.savings.models.properties.PlatformProperties;
import com.bkash.savings.models.properties.RpsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({PlatformProperties.class, RpsProperties.class})
public class PropertiesConfiguration {
}
