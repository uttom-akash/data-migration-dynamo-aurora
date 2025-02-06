package com.bkash.savings.migrator_dynamodb_pg.configs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StepConfig {
    private int numberOfPartitions;
    private int chunkSize;
    private int numberOfThreads;
}
