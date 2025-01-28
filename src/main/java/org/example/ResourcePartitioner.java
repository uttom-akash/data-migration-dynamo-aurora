package org.example;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class ResourcePartitioner implements Partitioner {

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> partitionMap = new HashMap<>(gridSize);
        int totalRecords = 40; // Total number of records in the input file
        int recordsPerPartition = totalRecords / gridSize; // 10 records per partition

        for (int i = 0; i < gridSize; i++) {
            ExecutionContext context = new ExecutionContext();
            context.putInt("start", i * recordsPerPartition);
            context.putInt("end", (i + 1) * recordsPerPartition - 1);
            partitionMap.put("partition" + i, context);
        }

        return partitionMap;
    }
}