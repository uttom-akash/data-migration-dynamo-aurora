package com.bkash.savings.models.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cache {
    @NotNull
    private Integer timeToLiveSeconds = 60;
    @NotNull
    private Long maxEntries = 100L;
    @NotNull
    private Long sizeInMB = 100L;
}