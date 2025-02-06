-- changeset mahadi.hasan:1736225049947-1
CREATE TABLE account_details_sync
(
    id                       UUID         NOT NULL,
    version                  INTEGER,
    created_date             TIMESTAMP WITH TIME ZONE,
    last_modified_date       TIMESTAMP WITH TIME ZONE,
    wallet_id                VARCHAR(20)  NOT NULL,
    savings_id               VARCHAR(36)  NOT NULL,
    organization_code        VARCHAR(255) NOT NULL,
    fi_account_id            VARCHAR(50)  NOT NULL,
    status                   SMALLINT     NOT NULL,
    current_amount           DECIMAL,
    current_deposited_amount DECIMAL,
    current_interest_amount  DECIMAL,
    last_sync_time           TIMESTAMP WITH TIME ZONE,
    maturity_amount          DOUBLE PRECISION,
    maturity_date            TIMESTAMP WITHOUT TIME ZONE,
    tax_amount               DOUBLE PRECISION,
    excise_duty              DOUBLE PRECISION,
    total_instalment_no      INTEGER,
    interest_rate            DOUBLE PRECISION,
    instalment_percentage    DOUBLE PRECISION,
    miss_payment             BOOLEAN,
    product_type             VARCHAR(255),
    total_deposit_amount     DOUBLE PRECISION,
    total_interest_earning   DOUBLE PRECISION,
    CONSTRAINT pk_account_details_sync PRIMARY KEY (id)
);

ALTER TABLE account_details_sync
    ADD CONSTRAINT uc_account_details_sync_organization_code_fi_account_id UNIQUE (organization_code, fi_account_id);

ALTER TABLE account_details_sync
    ADD CONSTRAINT uc_account_details_sync_wallet_id_savings_id UNIQUE (wallet_id, savings_id);