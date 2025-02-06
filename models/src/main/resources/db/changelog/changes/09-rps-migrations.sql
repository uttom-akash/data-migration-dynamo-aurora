-- liquibase formatted sql

-- changeset sadman.exabyting:1735462783556-1
CREATE TABLE rps
(
    id                  UUID        NOT NULL,
    version             INTEGER,
    created_date        TIMESTAMP WITH TIME ZONE,
    last_modified_date  TIMESTAMP WITH TIME ZONE,
    savings_id          VARCHAR(36) NOT NULL,
    rps_id              VARCHAR(36) NOT NULL,
    amount              DECIMAL     NOT NULL,
    cycle_start_date    date        NOT NULL,
    cycle_end_date      date        NOT NULL,
    activation_date     date        NOT NULL,
    organization_type   VARCHAR(20) NOT NULL,
    wallet_id           VARCHAR(20) NOT NULL,
    fi_account_id       VARCHAR(50) NOT NULL,
    rps_status          VARCHAR(40) NOT NULL,
    term                VARCHAR(10) NOT NULL,
    organization_code   VARCHAR(20) NOT NULL,
    merchant_short_code VARCHAR(20) NOT NULL,
    cancel_attempted   BOOLEAN     NOT NULL,
    tasks_remaining     INTEGER     NOT NULL,
    CONSTRAINT pk_rps PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462783556-2
CREATE TABLE rps_task
(
    id                 UUID        NOT NULL,
    version            INTEGER,
    created_date       TIMESTAMP WITH TIME ZONE,
    last_modified_date TIMESTAMP WITH TIME ZONE,
    rps_id             VARCHAR     NOT NULL,
    rpp_sub_req_id     VARCHAR(40) NOT NULL,
    task_id            VARCHAR(40) NOT NULL,
    status             VARCHAR(40) NOT NULL,
    q_status           VARCHAR(40) NOT NULL,
    start_date         date        NOT NULL,
    end_date           date        NOT NULL,
    termination_date   date,
    rpp_sub_id         VARCHAR(40),
    last_req_time      date,
    CONSTRAINT pk_rps_task PRIMARY KEY (id)
);

-- changeset sadman.exabyting:1735462783556-3
ALTER TABLE rps
    ADD CONSTRAINT uc_rps_id UNIQUE (rps_id);

-- changeset sadman.exabyting:1735462783556-4
ALTER TABLE rps_task
    ADD CONSTRAINT uc_rps_task_id UNIQUE (task_id);

-- changeset sadman.exabyting:1735462783556-5
ALTER TABLE rps_task
    ADD CONSTRAINT FK_RPS_TASK_ON_RPS FOREIGN KEY (rps_id) REFERENCES rps (rps_id);

-- changeset sadman.exabyting:1738143649231-2
ALTER TABLE rps_task DROP COLUMN rpp_sub_id;

-- changeset sadman.exabyting:1738143649231-3
ALTER TABLE rps_task
    ADD rpp_sub_id BIGINT;
