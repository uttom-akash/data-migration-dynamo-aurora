-- changeset mahadi.hasan:14-1
CREATE TABLE unknown_transaction_entity
(
    id                    UUID                     NOT NULL,
    version               INTEGER,
    created_date          TIMESTAMP WITH TIME ZONE,
    last_modified_date    TIMESTAMP WITH TIME ZONE,
    fi_account_id         VARCHAR(255)             NOT NULL,
    org_short_code        VARCHAR(255)             NOT NULL,
    cps_trx_id            VARCHAR(255)             NOT NULL,
    cps_trx_date          TIMESTAMP WITH TIME ZONE NOT NULL,
    fi_transaction_type   VARCHAR(20),
    fi_transaction_status VARCHAR(20),
    fi_trx_date           TIMESTAMP WITH TIME ZONE,
    fi_trx_id             VARCHAR(255),
    reference_cps_trx_id  VARCHAR(255),
    CONSTRAINT pk_unknown_transaction_entity PRIMARY KEY (id)
);

ALTER TABLE unknown_transaction_entity
    ADD CONSTRAINT uc_unknown_transaction_entity_cps_trx UNIQUE (cps_trx_id);