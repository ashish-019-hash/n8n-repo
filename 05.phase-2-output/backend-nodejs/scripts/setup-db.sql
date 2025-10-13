

\c carddemo;


DROP TABLE IF EXISTS daily_transactions CASCADE;
DROP TABLE IF EXISTS disclosure_groups CASCADE;
DROP TABLE IF EXISTS transaction_category_balances CASCADE;
DROP TABLE IF EXISTS transaction_categories CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS transaction_types CASCADE;
DROP TABLE IF EXISTS card_xrefs CASCADE;
DROP TABLE IF EXISTS cards CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users (
    sec_usr_id VARCHAR(8) PRIMARY KEY,
    sec_usr_fname VARCHAR(20) NOT NULL,
    sec_usr_lname VARCHAR(20) NOT NULL,
    sec_usr_pwd VARCHAR(100) NOT NULL,
    sec_usr_type VARCHAR(1) NOT NULL CHECK (sec_usr_type IN ('A', 'U')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS customers (
    cust_id BIGINT PRIMARY KEY,
    cust_first_name VARCHAR(25) NOT NULL,
    cust_middle_name VARCHAR(25),
    cust_last_name VARCHAR(25) NOT NULL,
    cust_addr_line_1 VARCHAR(50),
    cust_addr_line_2 VARCHAR(50),
    cust_addr_line_3 VARCHAR(50),
    cust_addr_state_cd VARCHAR(2),
    cust_addr_country_cd VARCHAR(3),
    cust_addr_zip VARCHAR(10),
    cust_phone_num_1 VARCHAR(15),
    cust_phone_num_2 VARCHAR(15),
    cust_ssn BIGINT NOT NULL,
    cust_govt_issued_id VARCHAR(20),
    cust_dob_yyyy_mm_dd VARCHAR(10),
    cust_eft_account_id VARCHAR(10),
    cust_pri_card_holder_ind VARCHAR(1),
    cust_fico_credit_score INTEGER CHECK (cust_fico_credit_score BETWEEN 300 AND 850),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS accounts (
    acct_id BIGINT PRIMARY KEY,
    acct_active_status VARCHAR(1) NOT NULL CHECK (acct_active_status IN ('A', 'C')),
    acct_curr_bal DECIMAL(12, 2) NOT NULL DEFAULT 0,
    acct_credit_limit DECIMAL(12, 2) NOT NULL,
    acct_cash_credit_limit DECIMAL(12, 2),
    acct_open_date VARCHAR(10),
    acct_expiraion_date VARCHAR(10),
    acct_reissue_date VARCHAR(10),
    acct_curr_cyc_credit DECIMAL(12, 2),
    acct_curr_cyc_debit DECIMAL(12, 2),
    acct_addr_zip VARCHAR(10),
    acct_group_id VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cards (
    card_num VARCHAR(16) PRIMARY KEY,
    card_acct_id BIGINT NOT NULL,
    card_cvv_cd INTEGER NOT NULL,
    card_embossed_name VARCHAR(50) NOT NULL,
    card_expiraion_date VARCHAR(10) NOT NULL,
    card_active_status VARCHAR(1) NOT NULL CHECK (card_active_status IN ('A', 'C', 'S')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (card_acct_id) REFERENCES accounts(acct_id)
);

CREATE TABLE IF NOT EXISTS transaction_types (
    tran_type VARCHAR(2) PRIMARY KEY,
    tran_type_desc VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS transaction_categories (
    id SERIAL PRIMARY KEY,
    tran_type_cd VARCHAR(2) NOT NULL,
    tran_cat_cd INTEGER NOT NULL,
    tran_cat_type_desc VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS transactions (
    tran_id VARCHAR(16) PRIMARY KEY,
    tran_type_cd VARCHAR(2) NOT NULL,
    tran_cat_cd INTEGER NOT NULL,
    tran_source VARCHAR(10) NOT NULL,
    tran_desc VARCHAR(100) NOT NULL,
    tran_amt DECIMAL(11, 2) NOT NULL,
    tran_merchant_id BIGINT,
    tran_merchant_name VARCHAR(50),
    tran_merchant_city VARCHAR(50),
    tran_merchant_zip VARCHAR(10),
    tran_card_num VARCHAR(16) NOT NULL,
    tran_orig_ts VARCHAR(26),
    tran_proc_ts VARCHAR(26),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tran_card_num) REFERENCES cards(card_num),
    FOREIGN KEY (tran_type_cd) REFERENCES transaction_types(tran_type)
);

CREATE TABLE IF NOT EXISTS card_xrefs (
    xref_card_num VARCHAR(16) PRIMARY KEY,
    xref_cust_id BIGINT NOT NULL,
    xref_acct_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (xref_card_num) REFERENCES cards(card_num),
    FOREIGN KEY (xref_cust_id) REFERENCES customers(cust_id),
    FOREIGN KEY (xref_acct_id) REFERENCES accounts(acct_id)
);

CREATE TABLE IF NOT EXISTS transaction_category_balances (
    id SERIAL PRIMARY KEY,
    trancat_acct_id BIGINT NOT NULL,
    trancat_type_cd VARCHAR(2) NOT NULL,
    trancat_cd INTEGER NOT NULL,
    tran_cat_bal DECIMAL(11, 2) NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (trancat_acct_id) REFERENCES accounts(acct_id)
);

CREATE TABLE IF NOT EXISTS disclosure_groups (
    id SERIAL PRIMARY KEY,
    dis_acct_group_id VARCHAR(10) NOT NULL,
    dis_tran_type_cd VARCHAR(2) NOT NULL,
    dis_tran_cat_cd INTEGER NOT NULL,
    dis_int_rate DECIMAL(6, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS daily_transactions (
    dalytran_id VARCHAR(16) PRIMARY KEY,
    dalytran_type_cd VARCHAR(2) NOT NULL,
    dalytran_cat_cd INTEGER NOT NULL,
    dalytran_source VARCHAR(10) NOT NULL,
    dalytran_desc VARCHAR(100) NOT NULL,
    dalytran_amt DECIMAL(11, 2) NOT NULL,
    dalytran_merchant_id BIGINT,
    dalytran_merchant_name VARCHAR(50),
    dalytran_merchant_city VARCHAR(50),
    dalytran_merchant_zip VARCHAR(10),
    dalytran_card_num VARCHAR(16) NOT NULL,
    dalytran_orig_ts VARCHAR(26),
    dalytran_proc_ts VARCHAR(26),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_cards_acct_id ON cards(card_acct_id);
CREATE INDEX idx_transactions_card_num ON transactions(tran_card_num);
CREATE INDEX idx_transactions_type ON transactions(tran_type_cd);
CREATE INDEX idx_card_xrefs_cust_id ON card_xrefs(xref_cust_id);
CREATE INDEX idx_card_xrefs_acct_id ON card_xrefs(xref_acct_id);
CREATE INDEX idx_tran_cat_bal_acct_id ON transaction_category_balances(trancat_acct_id);

COMMENT ON DATABASE carddemo IS 'CardDemo Credit Card Management System - Modernized from COBOL';
