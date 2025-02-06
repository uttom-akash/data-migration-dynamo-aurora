package com.bkash.savings.models.properties;


import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "rps")
@Validated
public class RpsProperties {
    @Valid
    private RedisProperties redis = new RedisProperties();
    @Valid
    private SchedulerProperties scheduler = new SchedulerProperties();

    @Valid
    private OffsetProperties offset = new OffsetProperties();

    @Getter
    @Setter
    public class RedisProperties {
        @NotNull
        @Positive
        private Integer maxLockAttempt;
        @NotNull
        @Positive
        private Integer expireTime;

        @NotNull
        @Positive
        private Integer lockAttemptThreadSleep;


    }

    @Getter
    @Setter
    public class SchedulerProperties {
        @NotNull
        @Positive
        private Integer taskAttemptPeriod;
        @NotNull
        @Positive
        private Integer syncAttemptPeriod;
    }

    @Getter
    @Setter
    public class OffsetProperties {
        @NotNull
        private Integer endDateMonthly;
        @NotNull
        private Integer endDateWeekly;
        @NotNull
        private Integer terminationDate;
    }
}
