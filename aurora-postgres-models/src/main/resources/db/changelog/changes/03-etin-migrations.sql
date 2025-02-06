-- liquibase formatted sql

-- changeset sadman.exabyting:1735462579185-1
CREATE TABLE etin
(
    id                 UUID         NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    wallet_id          VARCHAR(20)  NOT NULL,
    etin_number        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_etin PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462579185-2
ALTER TABLE etin
    ADD CONSTRAINT uc_etin_wallet_id UNIQUE (wallet_id);

