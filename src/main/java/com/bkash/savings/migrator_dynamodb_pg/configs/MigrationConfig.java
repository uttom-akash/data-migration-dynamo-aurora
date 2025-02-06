package com.bkash.savings.migrator_dynamodb_pg.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "migration")
@Getter
@Setter
public class MigrationConfig {
    private StepConfig savingsAccountStepConfig;
    private StepConfig assistedSavingsStepConfig;
    private StepConfig eTinStepConfig;
    private StepConfig nomineeStepConfig;
    private StepConfig notificationStepConfig;
    private StepConfig organizationStepConfig;
    private StepConfig productStepConfig;
    private StepConfig settingsStepConfig;
    private StepConfig transactionStepConfig;
}



