-- liquibase formatted sql

-- changeset sadman.exabyting:1735462838795-1
CREATE TABLE cps_transaction
(
    id                         UUID                     NOT NULL,
    version                    INTEGER,
    created_date               TIMESTAMP WITH TIME ZONE,
    last_modified_date         TIMESTAMP WITH TIME ZONE,
    savings_id                 VARCHAR(255)             NOT NULL,
    amount                     DECIMAL                  NOT NULL,
    cps_trx_status             VARCHAR(255)             NOT NULL,
    originator_conversation_id VARCHAR(255)             NOT NULL,
    correlation_id             VARCHAR(255)             NOT NULL,
    trx_channel                VARCHAR(5)               NOT NULL,
    trx_type                   VARCHAR(100)             NOT NULL,
    trx_id                     VARCHAR(255),
    fi_account_id              VARCHAR(255)             NOT NULL,
    fi_account_number          VARCHAR(255),
    wallet_number              VARCHAR(20),
    receiver                   VARCHAR(255),
    trx_date                   TIMESTAMP WITH TIME ZONE,
    trx_due_date               date,
    request_initiated_time     TIMESTAMP WITH TIME ZONE NOT NULL,
    transaction_source         VARCHAR(10)              NOT NULL,
    org_short_code             VARCHAR(255)             NOT NULL,
    CONSTRAINT pk_cps_transaction PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462838795-2
CREATE TABLE dps_transaction
(
    id                   UUID                     NOT NULL,
    version              INTEGER,
    created_date         TIMESTAMP WITH TIME ZONE,
    last_modified_date   TIMESTAMP WITH TIME ZONE,
    savings_id           VARCHAR(255)             NOT NULL,
    cps_trx_id           VARCHAR(255)             NOT NULL,
    cps_trx_date         TIMESTAMP WITH TIME ZONE NOT NULL,
    amount               DECIMAL                  NOT NULL,
    trx_status           VARCHAR(20)              NOT NULL,
    due_date             date                     NOT NULL,
    bk_plan_no           SMALLINT                 NOT NULL,
    fi_plan_no           SMALLINT,
    fi_trx_date          TIMESTAMP WITH TIME ZONE,
    fi_trx_id            VARCHAR(255),
    fi_account_id        VARCHAR(255)             NOT NULL,
    fi_account_number    VARCHAR(255),
    reference_cps_trx_id VARCHAR(255),
    trx_type             VARCHAR(20)              NOT NULL,
    org_short_code       VARCHAR(255)             NOT NULL,
    CONSTRAINT pk_dps_transaction PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462838795-3
ALTER TABLE cps_transaction
    ADD CONSTRAINT uc_cps_transaction_trx_id UNIQUE (trx_id);

-- changeset sadman.exabyting:1735462838795-4
ALTER TABLE dps_transaction
    ADD CONSTRAINT uc_dps_transaction_cps_trx UNIQUE (cps_trx_id);

-- changeset sadman.exabyting:1735462838795-5
CREATE INDEX idx_cps_transaction_trx_date ON cps_transaction (trx_date);

-- changeset mahadi.hasan:1735462838795-6
ALTER TABLE cps_transaction
    ADD reference_cps_trx_id VARCHAR(255);

-- changeset mahadi.hasan:1735462838795-7
ALTER TABLE dps_transaction
    ADD CONSTRAINT uc_dps_transaction_orgCode_fiTrxId UNIQUE (org_short_code, fi_trx_id);

-- changeset mahadi.hasan:1735462838795-8
ALTER TABLE dps_transaction
    ADD fi_trx_status VARCHAR(20);

-- changeset mahadi.hasan:1735462838795-9
ALTER TABLE dps_transaction
    ADD reference_cps_conversation_id VARCHAR(255);

-- changeset mahadi.hasan:1735462838795-10
ALTER TABLE dps_transaction
    ADD transaction_source VARCHAR(10);

ALTER TABLE dps_transaction
    ADD rpp_payment_id BIGINT;

ALTER TABLE dps_transaction
    ADD remarks VARCHAR(255);

ALTER TABLE dps_transaction
    ADD reference_cps_trx_date TIMESTAMP WITH TIME ZONE;

-- changeset sadman.exabyting:1738143649231-1
ALTER TABLE dps_transaction
    ALTER COLUMN fi_trx_status SET NOT NULL;

-- changeset sadman.exabyting:1738143649231-4
ALTER TABLE dps_transaction
    ALTER COLUMN transaction_source SET NOT NULL;