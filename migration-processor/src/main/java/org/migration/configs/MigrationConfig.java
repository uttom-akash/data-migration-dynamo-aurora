package org.migration.configs;

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
    private StepConfig nomineeStepConfig;
    private StepConfig transactionStepConfig;
}



