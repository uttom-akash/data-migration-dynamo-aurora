-- liquibase formatted sql

-- changeset sadman.exabyting:1735462485809-1
CREATE TABLE savings_account
(
    id                                      UUID         NOT NULL,
    version                                 INTEGER,
    created_date                            TIMESTAMP WITH TIME ZONE,
    last_modified_date                      TIMESTAMP WITH TIME ZONE,
    wallet_id                               VARCHAR(20)  NOT NULL,
    savings_id                              VARCHAR(36)  NOT NULL,
    savings_type                            VARCHAR(10)  NOT NULL,
    fi_account_id                           VARCHAR(50),
    fi_account_no                           VARCHAR(255),
    purpose                                 VARCHAR(255) NOT NULL,
    status                                  VARCHAR(20)  NOT NULL,
    current_state                           VARCHAR(100) NOT NULL,
    opening_date                            TIMESTAMP WITH TIME ZONE,
    start_date                              TIMESTAMP WITH TIME ZONE,
    start_short_date                        date,
    end_date                                TIMESTAMP WITH TIME ZONE,
    maturity_date                           TIMESTAMP WITH TIME ZONE,
    maturity_short_date                     date,
    fi_start_date_str                       VARCHAR(255),
    fi_end_date_str                         VARCHAR(255),
    fi_maturity_date_str                    VARCHAR(255),
    cancellation_requested_by               VARCHAR(10),
    cancel_request_time                     TIMESTAMP WITH TIME ZONE,
    cancel_reason                           TEXT,
    correlation_id                          VARCHAR(255),
    cancellation_date                       TIMESTAMP WITH TIME ZONE,
    cycle_start_date                        date         NOT NULL,
    onboarding_type                         VARCHAR(20)  NOT NULL,
    receivable_amount                       DECIMAL,
    maturity_amount                         DECIMAL      NOT NULL,
    instalment_percentage                   DOUBLE PRECISION,
    disbursement_time                       TIMESTAMP WITH TIME ZONE,
    effective_tenure_count                  SMALLINT,
    product_code                            VARCHAR(255) NOT NULL,
    nominee_id                              UUID         NOT NULL,
    organization_code                       VARCHAR(255) NOT NULL,
    fi_status                               VARCHAR(30),
    assisted_savings_id                     UUID,
    first_trx_originator_conversation_id    VARCHAR(255),
    first_trx_id                            VARCHAR(255),
    first_trx_date_time                     TIMESTAMP WITH TIME ZONE,
    disbursement_trx_id                     VARCHAR(255),
    disbursement_originator_conversation_id VARCHAR(255),
    disbursement_date_time                  TIMESTAMP WITH TIME ZONE,
    CONSTRAINT pk_savings_account PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462485809-2
ALTER TABLE savings_account
    ADD CONSTRAINT uc_savings_account_wallet_id_savings_id UNIQUE (wallet_id, savings_id);

-- changeset sadman.exabyting:1735462485809-3
CREATE INDEX idx_savings_account_fi_account_id ON savings_account (fi_account_id);

-- changeset sadman.exabyting:1735462485809-4
CREATE INDEX idx_savings_account_fi_account_no ON savings_account (fi_account_no);

-- changeset sadman.exabyting:1735462485809-5
CREATE INDEX idx_savings_account_start_short_date_current_state ON savings_account (start_short_date, current_state);

-- changeset sadman.exabyting:1735462485809-6
CREATE INDEX idx_savings_account_wallet_id ON savings_account (wallet_id);

-- changeset sadman.exabyting:1736225049943-1
ALTER TABLE savings_account
    ALTER COLUMN maturity_amount DROP NOT NULL;

-- changeset mahadi.hasan:1736225049943-2
ALTER TABLE savings_account
    ADD auto_deduction_time VARCHAR(255);

-- changeset mahadi.hasan:1736225049943-3
ALTER TABLE savings_account
    ADD disbursement_amount DECIMAL;

ALTER TABLE savings_account
DROP
COLUMN disbursement_time;

-- changeset sadman.exabyting:1737534770924-3
ALTER TABLE savings_account
    ADD rps_id UUID;

-- changeset sadman.exabyting:1738143649231-5
ALTER TABLE savings_account
    ADD CONSTRAINT uc_savings_account_wallet_id_fi_account_id_fi_account_no UNIQUE (wallet_id, fi_account_id,
                                                                                    fi_account_no);