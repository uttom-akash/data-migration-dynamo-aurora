package org.migration.migrators.base;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;

import java.util.Iterator;
import java.util.Map;


@StepScope
public abstract class DynamoDbReader<EntityType> implements ItemReader<EntityType> {

    private final AmazonDynamoDB dynamoDbClient;
    private Integer segment;
    private Integer totalSegments;
    private Iterator<EntityType> iterator;
    private Map<String, AttributeValue> lastEvaluatedKey;
    private boolean fetchMore;
    private String tableName;
    @Value("${resource.prefix}")
    private String appProfile;
    @Value("${spring.application.name}")
    private String appName;

    public DynamoDbReader(AmazonDynamoDB dynamoDbClient, String tableName) {
        this.dynamoDbClient = dynamoDbClient;
        this.lastEvaluatedKey = null;
        fetchMore = true;
        this.tableName = tableName;
    }

    @Value("#{stepExecutionContext['segment']}")
    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    @Value("#{stepExecutionContext['totalSegments']}")
    public void setTotalSegments(Integer totalSegments) {
        this.totalSegments = totalSegments;
    }

    protected abstract EntityType marshallIntoObject(Map<String, AttributeValue> item);

    @Override
    public EntityType read() throws Exception {
        if (iterator == null || !iterator.hasNext()) {
            if (!fetchMore) return null;
            fetchNextSegment();
        }
        return iterator != null && iterator.hasNext() ? iterator.next() : null;
    }

    private void fetchNextSegment() {

        ScanRequest scanRequest = new ScanRequest()
                .withTableName(getTableName())
                .withSegment(segment)
                .withTotalSegments(totalSegments)
                .withExclusiveStartKey(lastEvaluatedKey);

        ScanResult result = dynamoDbClient.scan(scanRequest);

        lastEvaluatedKey = result.getLastEvaluatedKey();

        if (lastEvaluatedKey == null) fetchMore = false;

        var entities = result
                .getItems()
                .stream()
                .map(this::marshallIntoObject) // Convert each item to PaymentEntity
                .toList();

        iterator = entities.iterator();
    }

    private String getTableName() {
        return new StringBuilder(appProfile).append("-").append(appName).append(tableName).toString();
    }
}
