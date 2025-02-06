package com.bkash.savings.models.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rpp {

    @NotNull
    @Valid
    private Rpp.RppProxy proxy = new RppProxy();

    @Getter
    @Setter
    @ToString
    public static class RppProxy {

        @Valid
        @NotNull
        private Integer timeout;

        @Valid
        @NotBlank
        private String url;

        @Valid
        @NotBlank
        private String version;

        @Valid
        @NotBlank
        private String channelId;

        @Valid
        @NotNull
        private Integer regularServiceId;

        @Valid
        @NotNull
        private Integer islamicServiceId;
    }
}
