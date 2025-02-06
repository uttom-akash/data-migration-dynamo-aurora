-- liquibase formatted sql

-- changeset sadman.exabyting:dev-1.1.1 context:faker
insert into organization(id, version, created_date, last_modified_date, organization_code, logo, address,
                         merchant_wallet_no, min_subscription_days_to_cancellation, merchant_short_code,
                         organization_operator, cps_api_parameters, active_inactive_date, organization_domain,
                         api_end_point, org_disbursement_id, private_key_store_password, private_cert_alias_name,
                         private_cert_path, terms_and_conditions_url, terms_and_conditions_url_bn, active,
                         organization_type, updated_version, new_feature_enabled, v_2_enabled)
VALUES ('01940354-5a46-71c6-772b-42e97889df45', 0, now(), now(),
        'IDLC', 'www.example.com/ldlc-logo.png', 'Dhaka, BD',
        '0171111111111', 3, 'IDLCSC',
        'param 1', 'param 2', now(),
        'http://localhost:8092', 'MFSServiceApi/api/V2/',
        '123456', '2ju32hxQf0QzDUpFrbd56n8qfrB9THCl',
        'sit-savings-selfsigned', 'jks/sit-savings-keystore.pfx',
        'www.example.com/idlc-term-condition.pdf', 'www.example.com/idlc-term-condition.pdf',
        true, 'REGULAR', true, true, true);

-- changeset sadman.exabyting:dev-1.2.1 context:faker        
insert
into localized_name
(created_date, language, last_modified_date, value, version, id, organization_id)
values (now(), 'en', now(), 'IDLC Finance Ltd', 0,
        '0194036b-ed93-70de-8410-ef72dd54e985', '01940354-5a46-71c6-772b-42e97889df45');


-- changeset sadman.exabyting:dev-2 context:faker
insert into product_types(deleted, id, version, created_date, last_modified_date, type)
values (false, '0194036b-ed93-70de-8410-ef72dd54e825', 0, now(), now(),
        'Monthly Savings');

-- changeset sadman.exabyting:dev-3 context:faker
insert into product (id, version, created_date, last_modified_date, product_id, product_type, savings_type, interest,
                     tenure, term, auto_renewal, active_from, de_active_from, amount, total_payout,
                     total_interest_earned, organization_code)
values ('01940373-fe6e-7e3b-8cf8-c2db6173c2fd', 0, now(), now(),
        'DPS_129', 'Monthly Savings', 'DPS', 9.0, 24, 'MONTHLY',
        false, now(), null, 500.00, 13080.0,
        1080.0, 'IDLC');

-- changeset sadman.exabyting:dev-4 context:faker
begin;
insert into availability(id, version, created_date, last_modified_date, option)
values ('0194038a-36de-723b-ad6c-04e3208b5a15', 0, now(), now(), 'CAPP');

insert into product_availability(availability_id, product_id)
values ('0194038a-36de-723b-ad6c-04e3208b5a15', '01940373-fe6e-7e3b-8cf8-c2db6173c2fd');
commit;

-- changeset sadman.exabyting:dev-5 context:faker
insert into settings(id, version, created_date, last_modified_date, key)
values ('0194038e-c36b-711f-b14d-5a6b56fd92c2', 0, now(),
        now(), 'idlc-api-paths-v2');

-- changeset sadman.exabyting:dev-6 context:faker
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('01940393-4d9c-7219-b6ce-2999bce6ec0d', 0, now(), now(),
        'send-kyc', 'OpeningRequest', '0194038e-c36b-711f-b14d-5a6b56fd92c2');

-- changeset sadman.exabyting:dev-7.1 context:faker
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('019415fd-b7ef-78a3-a4eb-77a1bd897e97', 0, now(), now(),
        'create-account', 'OpenAccount', '0194038e-c36b-711f-b14d-5a6b56fd92c2');


-- changeset sadman.exabyting:dev-7.2 context:faker      
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('019415fd-b7ef-78a3-a4eb-77a1bd888e97', 0, now(), now(),
        'push-transactions', 'pushBulkTransactions', '0194038e-c36b-711f-b14d-5a6b56fd92c2');


-- changeset mahadi.hasan:dev-8.1 context:faker
INSERT INTO savings_account (id,
                             version,
                             created_date,
                             last_modified_date,
                             wallet_id,
                             savings_id,
                             savings_type,
                             fi_account_id,
                             fi_account_no,
                             purpose,
                             status,
                             current_state,
                             opening_date,
                             start_date,
                             start_short_date,
                             end_date,
                             maturity_date,
                             maturity_short_date,
                             fi_start_date_str,
                             fi_end_date_str,
                             fi_maturity_date_str,
                             cancellation_requested_by,
                             cancel_request_time,
                             cancel_reason,
                             correlation_id,
                             cancellation_date,
                             cycle_start_date,
                             onboarding_type,
                             receivable_amount,
                             maturity_amount,
                             instalment_percentage,
                             effective_tenure_count,
                             product_code,
                             nominee_id,
                             organization_code,
                             fi_status,
                             assisted_savings_id,
                             first_trx_originator_conversation_id,
                             first_trx_id,
                             first_trx_date_time,
                             disbursement_trx_id,
                             disbursement_originator_conversation_id,
                             disbursement_date_time,
                             auto_deduction_time)
VALUES (gen_random_uuid(), -- Replace with your UUID generation method if necessary
        1,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        '017000000000', -- Example wallet_id
        '2024061800001', -- Example savings_id
        'DPS', -- Example savings_type
        'FI1234567890', -- Example fi_account_id
        '123456789', -- Example fi_account_no
        'Saving for future goals', -- Example purpose
        'ACTIVE', -- Example status
        'CPS_PAYMENT_PENDING', -- Example current_state
        CURRENT_TIMESTAMP - INTERVAL '1 DAY', -- Example opening_date
        CURRENT_TIMESTAMP, -- Example start_date
        '2024-06-18', -- Example start_short_date
        CURRENT_TIMESTAMP + INTERVAL '12 MONTH', -- Example end_date
        CURRENT_TIMESTAMP + INTERVAL '12 MONTH', -- Example maturity_date
        CURRENT_DATE + INTERVAL '12 MONTH', -- Example maturity_short_date
        '2024-01-01', -- Example fi_start_date_str
        '2024-12-31', -- Example fi_end_date_str
        '2025-06-18', -- Example fi_maturity_date_str
        NULL, -- Example cancellation_requested_by
        NULL, -- Example cancel_request_time
        NULL, -- Example cancel_reason
        'COR12345', -- Example correlation_id
        NULL, -- Example cancellation_date
        '2024-07-18', -- Example cycle_start_date
        'SELF', -- Example onboarding_type
        5000.00, -- Example receivable_amount
        6000.00, -- Example maturity_amount
        10.0, -- Example instalment_percentage
        12, -- Example effective_tenure_count
        'DPS_129', -- Example product_code
        '01940354-5a46-71c6-882b-42e97889df45', -- Example nominee_id
        'IDLC', -- Example organization_code
        'PENDING', -- Example fi_status
        NULL, -- Example assisted_savings_id
        'ORIG001', -- Example first_trx_originator_conversation_id
        'TRX001', -- Example first_trx_id
        CURRENT_TIMESTAMP, -- Example first_trx_date_time
        'TRXDIS001', -- Example disbursement_trx_id
        'ORIGDIS001', -- Example disbursement_originator_conversation_id
        CURRENT_TIMESTAMP, -- Example disbursement_date_time
        '18th Every Month');

-- changeset mahadi.hasan:dev-9 context:faker
INSERT INTO nominee (id,
                     version,
                     created_date,
                     last_modified_date,
                     wallet_id,
                     nid_number,
                     dob,
                     relation,
                     last_used_time)
VALUES ('01940354-5a46-71c6-882b-42e97889df45',
        1,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        '01533211460',
        '1234567890123',
        '1990-01-01',
        'SPOUSE',
        CURRENT_TIMESTAMP);

-- changeset mahadi.hasan:dev-10 context:faker
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174000', 0, now(), now(),
        'saving-details', 'AccountDetails', '0194038e-c36b-711f-b14d-5a6b56fd92c2');

-- changeset mahadi.hasan:dev-11 context:faker
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174001', 0, now(), now(),
        'maturity-details', 'MaturityDetail', '0194038e-c36b-711f-b14d-5a6b56fd92c2');

-- changeset mahadi.hasan:dev-12 context:faker
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174002', 0, now(), now(),
        'cancel-summary', 'CancelSummary', '0194038e-c36b-711f-b14d-5a6b56fd92c2');

-- changeset mahadi.hasan:dev-13 context:faker
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174003', 0, now(), now(),
        'installment-plan', 'InstallmentPlan', '0194038e-c36b-711f-b14d-5a6b56fd92c2');


-- changeset sadman.exabyting:dev-14 context:faker
-- api-end-point for v1
insert into settings(id, version, created_date, last_modified_date, key)
values ('0194038e-c36b-711f-b14d-5a6b56fd92c1', 0, now(),
        now(), 'idlc-api-paths');

-- v1
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174004', 0, now(), now(),
        'installment-plan', 'InstallmentPlan', '0194038e-c36b-711f-b14d-5a6b56fd92c1');

-- v1
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174005', 0, now(), now(),
        'saving-details', 'AccountDetails', '0194038e-c36b-711f-b14d-5a6b56fd92c1');

-- v1
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174006', 0, now(), now(),
        'maturity-details', 'MaturityDetail', '0194038e-c36b-711f-b14d-5a6b56fd92c1');
-- v1
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174007', 0, now(), now(),
        'cancel-summary', 'CancelSummary', '0194038e-c36b-711f-b14d-5a6b56fd92c1');

-- changeset mahadi.hasan:dev-15 context:faker
-- v2
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174008', 0, now(), now(),
        'get-batch-details', 'get-batch-details', '0194038e-c36b-711f-b14d-5a6b56fd92c2');
        
-- changeset mahmudul.hasan:dev-16 context:faker
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174778', 0, now(), now(),
        'query-transactions', 'queryTransactions', '0194038e-c36b-711f-b14d-5a6b56fd92c2');


-- changeset mahmudul.hasan:dev-17 context:faker
-- anomaly type data insertion
INSERT INTO dps_transaction_anomaly_type (id, dps_transaction_anomaly_type, description)
VALUES (gen_random_uuid(), 'TRANSACTION_TYPE_MISMATCH', 'The transaction type does not match the expected type.');

INSERT INTO dps_transaction_anomaly_type (id, dps_transaction_anomaly_type, description)
VALUES (gen_random_uuid(), 'FI_ACCOUNT_ID_MISMATCH', 'The financial institution account ID does not match.');

INSERT INTO dps_transaction_anomaly_type (id, dps_transaction_anomaly_type, description)
VALUES (gen_random_uuid(), 'FI_ACCOUNT_NO_MISMATCH', 'The financial institution account number does not match.');

INSERT INTO dps_transaction_anomaly_type (id, dps_transaction_anomaly_type, description)
VALUES (gen_random_uuid(), 'BK_TRANSACTION_DATE_MISMATCH', 'The transaction date does not match the expected date.');

INSERT INTO dps_transaction_anomaly_type (id, dps_transaction_anomaly_type, description)
VALUES (gen_random_uuid(), 'AMOUNT_MISMATCH', 'The transaction amount does not match the expected amount.');


-- changeset mahadi.hasan:dev-18 context:faker
-- v1
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174101', 0, now(), now(),
        'nominee-update', 'NomineeUpdate', '0194038e-c36b-711f-b14d-5a6b56fd92c1');
-- v2
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174102', 0, now(), now(),
        'nominee-update', 'NomineeUpdate', '0194038e-c36b-711f-b14d-5a6b56fd92c2');


-- changeset mahadi.hasan:dev-19 context:faker
-- v1
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174103', 0, now(), now(),
        'refund-confirmation', 'refundConfirmation', '0194038e-c36b-711f-b14d-5a6b56fd92c1');
-- v2
insert into settings_values(id, version, created_date, last_modified_date, key, text, settings_id)
values ('123e4567-e89b-12d3-a456-426614174104', 0, now(), now(),
        'refund-confirmation', 'refundConfirmation', '0194038e-c36b-711f-b14d-5a6b56fd92c2');
       