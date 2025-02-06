-- liquibase formatted sql

-- changeset sadman.exabyting:1735462438353-1
CREATE TABLE assisted_savings
(
    id                      UUID                     NOT NULL,
    version                 INTEGER,
    created_date            TIMESTAMP WITH TIME ZONE,
    last_modified_date      TIMESTAMP WITH TIME ZONE,
    requester_wallet_id     VARCHAR(20)              NOT NULL,
    customer_wallet_id      VARCHAR(20)              NOT NULL,
    savings_type            VARCHAR(5)               NOT NULL,
    product_code            VARCHAR(255)             NOT NULL,
    nominee_id              UUID                     NOT NULL,
    status                  VARCHAR(50)              NOT NULL,
    assisted_requester_type VARCHAR(20)              NOT NULL,
    requester_name          VARCHAR(255)             NOT NULL,
    organization_code       VARCHAR(255)             NOT NULL,
    cycle_start_date        TIMESTAMP WITH TIME ZONE NOT NULL,
    reported_by_customer    BOOLEAN,
    first_deposit_info_id   UUID,
    earning_received_date   TIMESTAMP WITH TIME ZONE,
    commission_amount       DECIMAL,
    maturity_date           TIMESTAMP WITH TIME ZONE NOT NULL,
    message                 OID,
    purpose                 VARCHAR(255),
    effective_tenure_count  SMALLINT,
    CONSTRAINT pk_assisted_savings PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462438353-2
CREATE INDEX idx_assisted_savings_requester_wallet_id ON assisted_savings (requester_wallet_id);

-- changeset sadman.exabyting:1735462438353-3
CREATE INDEX idx_assisted_savings_requester_wallet_id_created_date ON assisted_savings (requester_wallet_id, created_date);

-- changeset sadman.exabyting:1735462438353-4
CREATE INDEX idx_assisted_savings_requester_wallet_id_organization_code ON assisted_savings (requester_wallet_id, organization_code);

-- changeset sadman.exabyting:1735462438353-5
CREATE INDEX idx_assisted_savings_requester_wallet_id_savings_type ON assisted_savings (requester_wallet_id, savings_type);

-- changeset sadman.exabyting:1735462438353-6
CREATE INDEX idx_assisted_savings_requester_wallet_id_status ON assisted_savings (requester_wallet_id, status);

-- changeset sadman.exabyting:1736590707544-1
ALTER TABLE assisted_savings
    ADD first_deposit_id VARCHAR(255);

-- changeset sadman.exabyting:1736590707544-2
ALTER TABLE assisted_savings DROP COLUMN first_deposit_info_id;

-- changeset sadman.exabyting:1736594323499-1
ALTER TABLE assisted_savings
    DROP COLUMN cycle_start_date;

-- changeset sadman.exabyting:1736594323499-2
ALTER TABLE assisted_savings
    ADD cycle_start_date date NOT NULL;

-- changeset sadman.exabyting:1736665319870-1
ALTER TABLE assisted_savings
    ADD request_id VARCHAR(254);

-- changeset sadman.exabyting:1736665319870-2
ALTER TABLE assisted_savings
    ALTER COLUMN request_id SET NOT NULL;

-- changeset sadman.exabyting:1736665319870-3
CREATE INDEX idx_assisted_savings_request_id_requester_wallet_id ON assisted_savings (request_id, requester_wallet_id);

-- changeset sadman.exabyting:1736853893029-1
ALTER TABLE assisted_savings
    DROP COLUMN message;

-- changeset sadman.exabyting:1736853893029-2
ALTER TABLE assisted_savings
    ADD message TEXT;

-- changeset sadman.exabyting:1736853893029-3
ALTER TABLE assisted_savings
    ALTER COLUMN request_id TYPE VARCHAR(255) USING (request_id::VARCHAR(255));
