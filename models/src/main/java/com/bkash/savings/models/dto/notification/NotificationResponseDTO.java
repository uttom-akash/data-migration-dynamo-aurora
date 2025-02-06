package com.bkash.savings.models.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {

    private String event;

    private String title;

    private String headerText;

    private String headerDetails;

    private String detailsText;

    private int expiryTime;

    private String logoUrl;

    private String imageUrl;

    private String actionText;
}
