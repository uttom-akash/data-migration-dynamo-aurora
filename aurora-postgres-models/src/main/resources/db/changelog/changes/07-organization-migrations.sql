-- liquibase formatted sql

-- changeset sadman.exabyting:1735462708033-1
CREATE TABLE localized_name
(
    id                 UUID       NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    language           VARCHAR(2) NOT NULL,
    value              TEXT        NOT NULL,
    CONSTRAINT pk_localized_name PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462708033-2
CREATE TABLE organization
(
    id                                    UUID         NOT NULL,
    version                               INTEGER,
    created_date                          TIMESTAMP WITH TIME ZONE,
    last_modified_date                    TIMESTAMP WITH TIME ZONE,
    organization_code                     VARCHAR(20)  NOT NULL,
    logo                                  TEXT,
    address                               TEXT,
    merchant_wallet_no                    VARCHAR(20)  NOT NULL,
    min_subscription_days_to_cancellation SMALLINT     NOT NULL,
    merchant_short_code                   VARCHAR(20)  NOT NULL,
    organization_operator                 VARCHAR(255) NOT NULL,
    cps_api_parameters                    VARCHAR(255),
    active_inactive_date                  TIMESTAMP WITH TIME ZONE,
    organization_domain                   VARCHAR(255) NOT NULL,
    api_end_point                         VARCHAR(255) NOT NULL,
    org_disbursement_id                   VARCHAR(255) NOT NULL,
    private_key_store_password            VARCHAR(255) NOT NULL,
    private_cert_alias_name               VARCHAR(255) NOT NULL,
    private_cert_path                     VARCHAR(255) NOT NULL,
    terms_and_conditions_url              TEXT         NOT NULL,
    terms_and_conditions_url_bn           TEXT         NOT NULL,
    active                                BOOLEAN      NOT NULL,
    organization_type                     VARCHAR(20)  NOT NULL,
    updated_version                       BOOLEAN,
    new_feature_enabled                   BOOLEAN      NOT NULL,
    v_2_enabled                           BOOLEAN      NOT NULL,
    CONSTRAINT pk_organization PRIMARY KEY (id)
);

create table organization_localized_names (
        localized_names_id uuid not null unique,
        organization_entity_id uuid not null
    );


-- changeset sadman.exabyting:1735462708033-3
ALTER TABLE localized_name
    ADD CONSTRAINT uc_localized_name_language UNIQUE (language);

-- changeset sadman.exabyting:1735462708033-4
ALTER TABLE organization
    ADD CONSTRAINT uc_organization_org_disbursement_id UNIQUE (org_disbursement_id);

-- changeset sadman.exabyting:1735462708033-5
ALTER TABLE organization
    ADD CONSTRAINT uc_organization_organization_code UNIQUE (organization_code);

-- changeset sadman.exabyting:1736482954407-1
ALTER TABLE localized_name DROP CONSTRAINT uc_localized_name_language;

-- changeset sadman.exabyting:1736853893029-4
ALTER TABLE localized_name
    ADD organization_id UUID;

-- changeset sadman.exabyting:1736853893029-5
ALTER TABLE localized_name
    ADD CONSTRAINT FK_LOCALIZED_NAME_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organization (id);

-- changeset sadman.exabyting:1736853893029-7
DROP TABLE organization_localized_names CASCADE;
