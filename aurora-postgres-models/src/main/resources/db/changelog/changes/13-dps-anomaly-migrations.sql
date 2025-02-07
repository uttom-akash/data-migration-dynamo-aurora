-- changeset mahadi.hasan:13-1
CREATE TABLE dps_anomaly_transaction
(
    id                    UUID                     NOT NULL,
    version               INTEGER,
    created_date          TIMESTAMP WITH TIME ZONE,
    last_modified_date    TIMESTAMP WITH TIME ZONE,
    fi_account_id         VARCHAR(255)             NOT NULL,
    org_code              VARCHAR(255)             NOT NULL,
    cps_trx_id            VARCHAR(255)             NOT NULL,
    cps_trx_date          TIMESTAMP WITH TIME ZONE NOT NULL,
    fi_account_no         VARCHAR(255),
    fi_transaction_type   VARCHAR(20),
    fi_transaction_status VARCHAR(20),
    fi_trx_date           TIMESTAMP WITH TIME ZONE,
    fi_trx_id             VARCHAR(255),
    reference_cps_trx_id  VARCHAR(255),
    remarks               VARCHAR(255),
    CONSTRAINT pk_dps_anomaly_transaction PRIMARY KEY (id)
);

CREATE TABLE dps_transaction_anomaly_type
(
    id                           UUID         NOT NULL,
    version                      INTEGER,
    created_date                 TIMESTAMP WITH TIME ZONE,
    last_modified_date           TIMESTAMP WITH TIME ZONE,
    dps_transaction_anomaly_type VARCHAR(255) NOT NULL,
    description                  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_dps_transaction_anomaly_type PRIMARY KEY (id)
);

CREATE TABLE dps_anomaly_transaction_anomaly_type_association
(
    anomaly_transaction_id UUID NOT NULL,
    anomaly_type_id        UUID NOT NULL
);

ALTER TABLE dps_anomaly_transaction
    ADD CONSTRAINT uc_dps_anomaly_transaction_cpstrxid UNIQUE (cps_trx_id);

ALTER TABLE dps_anomaly_transaction_anomaly_type_association
    ADD CONSTRAINT fk_dpsanotraanotypass_on_dps_anomaly_transaction_entity FOREIGN KEY (anomaly_transaction_id) REFERENCES dps_anomaly_transaction (id);

ALTER TABLE dps_anomaly_transaction_anomaly_type_association
    ADD CONSTRAINT fk_dpsanotraanotypass_on_dps_transaction_anomaly_type_entity FOREIGN KEY (anomaly_type_id) REFERENCES dps_transaction_anomaly_type (id);



ALTER TABLE dps_transaction_anomaly_type
    ADD CONSTRAINT uc_dps_transaction_anomaly_type_dps_transaction_anomaly_type UNIQUE (dps_transaction_anomaly_type);

