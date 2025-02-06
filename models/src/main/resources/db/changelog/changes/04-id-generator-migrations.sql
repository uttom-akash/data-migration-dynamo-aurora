-- liquibase formatted sql

-- changeset sadman.exabyting:1735462602333-1
CREATE TABLE id_generator
(
    id                 UUID         NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    pk                 VARCHAR(255) NOT NULL,
    sk                 VARCHAR(255) NOT NULL,
    value              BIGINT,
    CONSTRAINT pk_id_generator PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462602333-2
ALTER TABLE id_generator
    ADD CONSTRAINT uc_id_generator_sk_pk UNIQUE (sk, pk);

-- changeset sadman.exabyting:1735462602333-3
CREATE INDEX idx_id_generator_pk ON id_generator (pk);

