package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.notification;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "notification")
public class NotificationEntity {

    @DynamoDBHashKey
    private String event;

    private int expiryTime;

    private String title;

    private String logoUrl;

    private String imageUrl;

    private String headerText;

    private String headerDetails;

    private String detailsText;

    private String actionText;


    @DynamoDBAttribute
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @DynamoDBAttribute
    public int getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(int expiryTime) {
        this.expiryTime = expiryTime;
    }

    @DynamoDBAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @DynamoDBAttribute
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @DynamoDBAttribute
    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    @DynamoDBAttribute
    public String getHeaderDetails() {
        return headerDetails;
    }

    public void setHeaderDetails(String headerDetails) {
        this.headerDetails = headerDetails;
    }

    @DynamoDBAttribute
    public String getDetailsText() {
        return detailsText;
    }

    public void setDetailsText(String detailsText) {
        this.detailsText = detailsText;
    }

    @DynamoDBAttribute
    public String getActionText() {
        return actionText;
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }
}
