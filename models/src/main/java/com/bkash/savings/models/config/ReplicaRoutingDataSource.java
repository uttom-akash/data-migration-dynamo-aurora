package com.bkash.savings.models.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class ReplicaRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("ReplicaRoutingDataSource|determineCurrentLookupKey:: routing to {}",
                DatabaseContextHolder.getReplicaType());
        return DatabaseContextHolder.getReplicaType();
    }
}