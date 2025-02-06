package com.bkash.savings.models.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
public class Notification {
    @NotNull
    private Long timeout;

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Url url = new Url();

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Type type = new Type();

    @Getter
    @Setter
    public static class Url {
        @NotBlank
        private String customerApp;
        @NotBlank
        private String agentApp;
    }

    @Getter
    @Setter
    public static class Type {
        @NotBlank
        private String user;
        @NotBlank
        private String promo;
    }
}
