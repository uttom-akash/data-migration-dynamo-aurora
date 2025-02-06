package com.bkash.savings.models.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequestDTO {

    @NotBlank(message = "Event cannot be empty")
    private String event;

    @Positive(message = "Must be a positive integer")
    private int expiryTime;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Please provide a valid logo URL")
    private String logoUrl;

    private String imageUrl;

    @NotBlank(message = "Header Text cannot be empty")
    private String headerText;

    private String headerDetails;

    @NotBlank(message = "Details Text cannot be empty")
    private String detailsText;

    private String actionText;


    public void trim() {
        this.event = this.event.trim();
        this.title = this.title.trim();
        this.logoUrl = this.logoUrl.trim();
        this.headerText = this.headerText.trim();
        this.detailsText = this.detailsText.trim();
        if (this.headerDetails != null) {
            this.headerDetails = this.headerDetails.trim();
        }
        if (this.imageUrl != null) {
            this.imageUrl = this.imageUrl.trim();
        }
        if (this.actionText != null) {
            this.actionText = this.actionText.trim();
        }
    }
}
