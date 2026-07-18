-- liquibase formatted sql

-- changeset uttom.akash:1752800000000-1
CREATE TABLE product
(
    id                 UUID         NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    product_id         VARCHAR(64)  NOT NULL,
    name               VARCHAR(255) NOT NULL,
    category           VARCHAR(64),
    price              NUMERIC(19, 2),
    stock_quantity     INTEGER,
    listed_date        TIMESTAMP WITH TIME ZONE,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- changeset uttom.akash:1752800000000-2
ALTER TABLE product
    ADD CONSTRAINT uc_product_product_id UNIQUE (product_id);
