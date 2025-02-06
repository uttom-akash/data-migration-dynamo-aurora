-- liquibase formatted sql

-- changeset sadman.exabyting:1735463268078-1
CREATE TABLE availability
(
    id                 UUID        NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    option             VARCHAR(10) NOT NULL,
    CONSTRAINT pk_availability PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735463268078-2
CREATE TABLE product
(
    id                    UUID             NOT NULL,
    version               INTEGER,
    created_date          TIMESTAMP WITH TIME ZONE,
    last_modified_date    TIMESTAMP WITH TIME ZONE,
    product_id            VARCHAR(20)      NOT NULL,
    product_type          VARCHAR(255)     NOT NULL,
    savings_type          VARCHAR(6)       NOT NULL,
    interest              DOUBLE PRECISION NOT NULL,
    tenure                SMALLINT         NOT NULL,
    term                  VARCHAR(10)      NOT NULL,
    auto_renewal          BOOLEAN,
    active_from           date             NOT NULL,
    de_active_from        date,
    amount                DECIMAL          NOT NULL,
    total_payout          DECIMAL          NOT NULL,
    total_interest_earned DECIMAL          NOT NULL,
    organization_code     VARCHAR(255)     NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735463268078-3
CREATE TABLE product_availability
(
    availability_id UUID NOT NULL,
    product_id      UUID NOT NULL
);

-- changeset sadman.exabyting:1735463268078-4
CREATE TABLE product_types
(
    deleted            BOOLEAN      NOT NULL,
    id                 UUID         NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    type               VARCHAR(255) NOT NULL,
    CONSTRAINT pk_product_types PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735463268078-5
ALTER TABLE availability
    ADD CONSTRAINT uc_availability_option UNIQUE (option);

-- changeset sadman.exabyting:1735463268078-6
ALTER TABLE product
    ADD CONSTRAINT uc_product_product_id UNIQUE (product_id);

-- changeset sadman.exabyting:1735463268078-7
ALTER TABLE product_types
    ADD CONSTRAINT uc_product_types_type UNIQUE (type);

-- changeset sadman.exabyting:1735463268078-8
ALTER TABLE product_availability
    ADD CONSTRAINT fk_proava_on_availability FOREIGN KEY (availability_id) REFERENCES availability (id);

-- changeset sadman.exabyting:1735463268078-9
ALTER TABLE product_availability
    ADD CONSTRAINT fk_proava_on_product_entity FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE product
    ADD auto_renewal_mode VARCHAR(255);;

ALTER TABLE product
    DROP COLUMN auto_renewal;

ALTER TABLE product
    ADD auto_renewal VARCHAR(255);