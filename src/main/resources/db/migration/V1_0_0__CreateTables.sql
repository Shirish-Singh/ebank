CREATE TABLE IF NOT EXISTS "public"."bank_user"
(
    id        UUID NOT NULL PRIMARY KEY,
    name      TEXT,
    last_name TEXT,
    email     TEXT,
    mobile    varchar(20),
    status    varchar(20)
);

CREATE TABLE IF NOT EXISTS "public"."bank_account"
(
    id           UUID NOT NULL PRIMARY KEY,
    iban         varchar(50),
    account_type varchar(255),
    balance      decimal,
    bank_user_id UUID NOT NULL REFERENCES "public"."bank_user" (id) UNIQUE
);

CREATE TABLE IF NOT EXISTS "public"."transaction_details"
(
    id                     UUID NOT NULL PRIMARY KEY,
    src_bank_account_id    UUID,
    target_bank_account_id UUID,
    tx_amount              decimal,
    memo                   varchar(255)
);
