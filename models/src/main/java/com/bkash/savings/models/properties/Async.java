package com.bkash.savings.models.properties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Async {
    @NotNull
    @Min(value = 10)
    private Integer corePoolSize;
    @NotNull
    @Min(value = 10)
    private Integer maxPoolSize;
    @NotNull
    @Min(value = 0)
    private Integer queueCapacity;
    @NotNull
    private String threadNamePrefix;
    @NotNull
    private Boolean allowCoreThreadTimeOut;
    @NotNull
    private Integer keepAliveSeconds;
}
