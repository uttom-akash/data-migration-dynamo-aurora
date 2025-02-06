package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.rpp;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
@DynamoDBDocument
public class RppSubscriptionInfo {

    private String requestId;

    /**
     * yyyy-MM-dd format
     */
    private String requestDate;

    private String cycleStartDate;

    private Long subscriptionId;

    public static RppSubscriptionInfo of(String requestId, String requestDate, String cycleStartDate, Long subscriptinId) {
        return new RppSubscriptionInfo()
                .setRequestId(requestId)
                .setRequestDate(requestDate)
                .setCycleStartDate(cycleStartDate)
                .setSubscriptionId(subscriptinId);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean hasSubscriptionId() {
        return Objects.nonNull(subscriptionId);
    }

    @DynamoDBIgnore
    @JsonIgnore
    public boolean isIncomplete() {
        return !hasSubscriptionId();
    }
}
