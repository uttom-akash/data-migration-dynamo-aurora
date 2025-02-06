package com.bkash.savings.models.properties;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bank {
    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Version v1 = new Version();
    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Version v2 = new Version();
    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Api api = new Api();
    @Valid
    @NotNull
    @NestedConfigurationProperty
    private Ssl ssl = new Ssl();
    @Valid
    @NotNull
    @NestedConfigurationProperty
    private SsmParameterConfig ssm = new SsmParameterConfig();

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private SignaturePropertiesConfig signature = new SignaturePropertiesConfig();

    @Getter
    @Setter
    @ToString
    public static class Version {
        @Valid
        @NotBlank
        public String encryptionAlgorithm;
    }

    @Getter
    @Setter
    @ToString
    public static class Api {
        @Valid
        @NotNull
        private Integer timeout;
    }

    @Getter
    @Setter
    @ToString
    public static class Ssl {
        @Valid
        @NotNull
        @NestedConfigurationProperty
        private SslCertificate certificate = new SslCertificate();
    }

    @Getter
    @Setter
    @ToString
    public static class SslCertificate {
        @NotBlank
        private String bucket;
        @Valid
        @NotNull
        @NestedConfigurationProperty
        private KeyStore keystore = new KeyStore();
        @Valid
        @NotNull
        @NestedConfigurationProperty
        private TrustStore truststore = new TrustStore();
    }

    @Getter
    @Setter
    @ToString
    public static class KeyStore {
        @NotBlank
        private String path;
        @NotBlank
        private String password;
    }

    @Getter
    @Setter
    @ToString
    public static class TrustStore {
        @NotBlank
        private String prefix;
        @NotBlank
        private String suffix;
    }

    @Getter
    @Setter
    @ToString
    public static class SsmParameterConfig {
        @NotBlank
        private String project;
        @NotBlank
        private String profile;
        @NotBlank
        private String region;
        
        private String endpoint;
    }

    @Getter
    @Setter
    @ToString
    public static class SignaturePropertiesConfig {
        @NotBlank
        private String algorithm;
        @NotNull
        @NestedConfigurationProperty
        private Certificate certificate;

        @Getter
        @Setter
        @ToString
        public static class Certificate {
            @NotNull
            private String bucket;
            @NotNull
            private String keystorePath;
            @NotNull
            private String keystorePass;
            @NotNull
            private String keystoreAlias;
            @NotNull
            private String publicPathPrefix;
        }
    }
}