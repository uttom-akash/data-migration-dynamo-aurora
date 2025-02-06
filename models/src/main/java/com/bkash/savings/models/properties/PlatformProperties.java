package com.bkash.savings.models.properties;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "platform")
@Validated
public class PlatformProperties {
    @Valid
    @NestedConfigurationProperty
    private Cache cache = new Cache();
    @Valid
    @NestedConfigurationProperty
    private Async async = new Async();
    @Valid
    @NestedConfigurationProperty
    private DataSource datasource = new DataSource();
    @Valid
    @NestedConfigurationProperty
    private Redis redis = new Redis();
}
