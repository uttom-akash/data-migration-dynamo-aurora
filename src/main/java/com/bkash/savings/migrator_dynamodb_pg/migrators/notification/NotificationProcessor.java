package com.bkash.savings.migrator_dynamodb_pg.migrators.notification;

import com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.notification.NotificationEntity;
import com.bkash.savings.migrator_dynamodb_pg.mappers.DateConversion;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NotificationProcessor implements ItemProcessor<NotificationEntity, com.bkash.savings.models.postgres.notification.NotificationEntity> {

    @Override
    public com.bkash.savings.models.postgres.notification.NotificationEntity process(NotificationEntity notification) throws Exception {
         return com.bkash.savings.models.postgres.notification.NotificationEntity.builder()
                .event(notification.getEvent())
                .expiryTime(notification.getExpiryTime())
                .title(notification.getTitle())
                .logoUrl(notification.getLogoUrl() == null ? "" : notification.getLogoUrl())
                .imageUrl(notification.getImageUrl() == null ? "" : notification.getImageUrl())
                .headerText(notification.getHeaderText())
                .headerDetails(notification.getHeaderDetails() == null ? "" : notification.getHeaderDetails())
                .detailsText(notification.getDetailsText())
                .actionText(DateConversion.getOrEmptyString(notification.getActionText()).substring(0,
                        Math.min(DateConversion.getOrEmptyString(notification.getActionText()).length()-1,
                        29)))
                .build();
    }
}






