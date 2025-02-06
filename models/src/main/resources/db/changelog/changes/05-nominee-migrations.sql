-- liquibase formatted sql

-- changeset sadman.exabyting:1735462626884-1
CREATE TABLE nominee
(
    id                 UUID        NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    wallet_id          VARCHAR(16) NOT NULL,
    nid_number         VARCHAR(50) NOT NULL,
    dob                date        NOT NULL,
    relation           VARCHAR(8)  NOT NULL,
    last_used_time     TIMESTAMP WITH TIME ZONE,
    CONSTRAINT pk_nominee PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462626884-2
ALTER TABLE nominee
    ADD CONSTRAINT uc_nominee_id_wallet_id UNIQUE (id, wallet_id);

-- changeset sadman.exabyting:1735462626884-3
ALTER TABLE nominee
    ADD CONSTRAINT uc_nominee_wallet_id_nid_number UNIQUE (wallet_id, nid_number);

-- changeset sadman.exabyting:1735462626884-4
CREATE INDEX idx_nominee_wallet_id ON nominee (wallet_id);

