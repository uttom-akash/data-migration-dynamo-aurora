package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common;

public interface NewRelicService {
    void sendRelicEvent(String body, String invokeType, String serviceNameField, String eventName);
}
