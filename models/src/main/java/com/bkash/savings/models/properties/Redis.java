package com.bkash.savings.models.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Redis {
    @NotNull
    private String host;
    @NotNull
    private Integer port;
    private String password;
    @NotNull
    private Boolean useSsl;
    @NotNull
    private Long connectTimeout;
    @NotNull
    private String passwordSsmPath;
    @NotNull
    private Integer database;
}
