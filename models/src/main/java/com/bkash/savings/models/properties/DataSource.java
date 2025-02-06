package com.bkash.savings.models.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ToString
public class DataSource {
    @Valid
    @NestedConfigurationProperty
    public Replica writeReplica = new Replica();

    @Valid
    @NestedConfigurationProperty
    public Replica readReplica = new Replica();

    @Getter
    @Setter
    public static class Replica {
        @NotNull
        private String url;
        @NotNull
        private String username;
        private String password;
        @NotNull
        private Integer initialPoolSize;
        @NotNull
        private Integer maximumPoolSize;
        @NotNull
        private Integer minimumIdle;
        @NotNull
        private Long idleTimeout;
        @NotNull
        private Long maximumLifetime;
        @NotNull
        private Long connectionTimeout;
        @NotNull
        private Boolean autoCommit;
        @NotNull
        private String poolName;
        @NotNull
        private Long initializationFailTimeout;
        @NotNull
        private Long leakDetectionThreshold;
        @NotNull
        private String connectionTestQuery;
        @NotNull
        private String passwordSsmKey;
    }
}