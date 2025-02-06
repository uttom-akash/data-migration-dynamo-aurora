-- liquibase formatted sql

-- changeset sadman.exabyting:1735462647222-1
CREATE TABLE notification
(
    id                 UUID         NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    event              VARCHAR(30)  NOT NULL,
    expiry_time        INTEGER      NOT NULL,
    title              VARCHAR(40)  NOT NULL,
    logo_url           TEXT         NOT NULL,
    image_url          TEXT         NOT NULL,
    header_text        VARCHAR(100) NOT NULL,
    header_details     VARCHAR(150) NOT NULL,
    details_text       TEXT         NOT NULL,
    action_text        VARCHAR(30)  NOT NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462647222-2
ALTER TABLE notification
    ADD CONSTRAINT uc_notification_event UNIQUE (event);

