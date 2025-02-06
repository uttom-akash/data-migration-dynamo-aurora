package com.bkash.savings.models.properties;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@ConfigurationProperties(prefix = "clients")
@Validated
@ToString
public class ClientProperties {

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Bank fi = new Bank();

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Fintech cps = new Fintech();

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Gql gql = new Gql();

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Rpp rpp = new Rpp();

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Notification notification = new Notification();
}
