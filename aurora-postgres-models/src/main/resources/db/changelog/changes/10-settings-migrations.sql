-- liquibase formatted sql

-- changeset sadman.exabyting:1735462804929-1
CREATE TABLE settings
(
    id                 UUID         NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    key                VARCHAR(255) NOT NULL,
    CONSTRAINT pk_settings PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462804929-2
CREATE TABLE settings_values
(
    id                 UUID         NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    key                VARCHAR(255) NOT NULL,
    text               VARCHAR(255) NOT NULL,
    settings_id        UUID,
    CONSTRAINT pk_settings_values PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462804929-3
ALTER TABLE settings
    ADD CONSTRAINT uc_settings_key UNIQUE (key);

-- changeset sadman.exabyting:1735462804929-4
ALTER TABLE settings_values
    ADD CONSTRAINT FK_SETTINGS_VALUES_ON_SETTINGS FOREIGN KEY (settings_id) REFERENCES settings (id);

