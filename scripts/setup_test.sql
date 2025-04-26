-- C:\dev\projects\product-service\scripts\setup_test.sql

-- Drop existing tables only if they exist
BEGIN
    FOR tbl IN (SELECT table_name
                FROM all_tables
                WHERE owner = 'TEST_USER' AND table_name IN ('PRODUCT', 'PRODUCT_COSIF', 'MANUAL_TRANSACTION')) 
    LOOP
        EXECUTE IMMEDIATE 'DROP TABLE test_user.' || tbl.table_name || ' CASCADE CONSTRAINTS';
        DBMS_OUTPUT.PUT_LINE('Dropped table ' || tbl.table_name);
    END LOOP;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('No tables to drop or error occurred.');
END;
/

-- Create PRODUCT table
DECLARE
    table_exists INTEGER;
BEGIN
    SELECT COUNT(*) INTO table_exists FROM all_tables WHERE table_name = 'PRODUCT' AND owner = 'TEST_USER';
    IF table_exists = 0 THEN
        EXECUTE IMMEDIATE '
            CREATE TABLE test_user.product (
                product_code CHAR(4) PRIMARY KEY NOT NULL,
                product_desc VARCHAR2(255),
                status CHAR(1)
            )';
        DBMS_OUTPUT.PUT_LINE('Table "product" created.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table "product" already exists.');
    END IF;
END;
/

-- Create PRODUCT_COSIF table
DECLARE
    table_exists INTEGER;
BEGIN
    SELECT COUNT(*) INTO table_exists FROM all_tables WHERE table_name = 'PRODUCT_COSIF' AND owner = 'TEST_USER';
    IF table_exists = 0 THEN
        EXECUTE IMMEDIATE '
            CREATE TABLE test_user.product_cosif (
                product_code CHAR(4) NOT NULL,
                cosif_code CHAR(11) NOT NULL PRIMARY KEY,
                classification_code CHAR(6),
                status CHAR(1)
            )';
        DBMS_OUTPUT.PUT_LINE('Table "product_cosif" created.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table "product_cosif" already exists.');
    END IF;
END;
/

-- Create MANUAL_TRANSACTION table
DECLARE
    table_exists INTEGER;
BEGIN
    SELECT COUNT(*) INTO table_exists FROM all_tables WHERE table_name = 'MANUAL_TRANSACTION' AND owner = 'TEST_USER';
    IF table_exists = 0 THEN
        EXECUTE IMMEDIATE '
            CREATE TABLE test_user.manual_transaction (
                month NUMERIC(2) NOT NULL,
                year NUMERIC(4) NOT NULL,
                entry_number NUMERIC(3) NOT NULL,
                product_code CHAR(4) NOT NULL,
                cosif_code CHAR(11) NOT NULL,
                description VARCHAR2(50) NOT NULL,
                transaction_date TIMESTAMP NOT NULL,
                user_code VARCHAR2(15) NOT NULL,
                amount NUMERIC(16,2) NOT NULL,
                PRIMARY KEY (month, year, entry_number)
            )';
        DBMS_OUTPUT.PUT_LINE('Table "manual_transaction" created.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table "manual_transaction" already exists.');
    END IF;
END;
/

-- Insert test data only if PRODUCT table is empty
DECLARE
    row_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO row_count FROM test_user.product;
    IF row_count = 0 THEN
        INSERT INTO test_user.product VALUES ('P001', 'Investment Fund', 'A');
        INSERT INTO test_user.product VALUES ('P002', 'Savings Account', 'A');
        INSERT INTO test_user.product VALUES ('P003', 'Mortgage Loan', 'I');
        INSERT INTO test_user.product VALUES ('P004', 'Auto Consortium', 'I');
		
        DBMS_OUTPUT.PUT_LINE('Test data inserted into "product".');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Test data already present in "product".');
    END IF;
END;
/

-- Insert test data only if PRODUCT_COSIF table is empty
DECLARE
    row_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO row_count FROM test_user.product_cosif;
    IF row_count = 0 THEN
        INSERT INTO test_user.product_cosif (product_code, cosif_code, classification_code, status)
        VALUES ('P001', 'COSIF123456', 'CL001', 'A');
        INSERT INTO test_user.product_cosif (product_code, cosif_code, classification_code, status)
        VALUES ('P002', 'COSIF123457', 'CL002', 'A');
        INSERT INTO test_user.product_cosif (product_code, cosif_code, classification_code, status)
        VALUES ('P003', 'COSIF123458', 'CL003', 'I');
        INSERT INTO test_user.product_cosif (product_code, cosif_code, classification_code, status)
        VALUES ('P004', 'COSIF123459', 'CL004', 'I');
        DBMS_OUTPUT.PUT_LINE('Test data inserted into "product_cosif".');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Test data already present in "product_cosif".');
    END IF;
END;
/

-- Insert test data only if MANUAL_TRANSACTION table is empty
DECLARE
    row_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO row_count FROM test_user.manual_transaction;
    IF row_count = 0 THEN
        INSERT INTO test_user.manual_transaction (
            month, year, entry_number, product_code, cosif_code, description, transaction_date, user_code, amount
        ) VALUES (
            4, 2025, 1, 'P001', 'COSIF123456', 'Investment Fund Transaction', TO_TIMESTAMP('2025-04-17 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'TEST', 1000.00
        );
        INSERT INTO test_user.manual_transaction (
            month, year, entry_number, product_code, cosif_code, description, transaction_date, user_code, amount
        ) VALUES (
            4, 2025, 2, 'P002', 'COSIF123457', 'Savings Account Transaction', TO_TIMESTAMP('2025-04-17 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'TEST', 2000.00
        );
        DBMS_OUTPUT.PUT_LINE('Test data inserted into "manual_transaction".');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Test data already present in "manual_transaction".');
    END IF;
END;
/

-- === Final Selects to display data ===
PROMPT
PROMPT =============================
PROMPT  TABLE: test_user.PRODUCT
PROMPT =============================
COLUMN PRODUCT_CODE FORMAT A10
COLUMN PRODUCT_DESC FORMAT A30
COLUMN STATUS FORMAT A6

SELECT PRODUCT_CODE, PRODUCT_DESC, STATUS FROM test_user.PRODUCT;

PROMPT
PROMPT =============================
PROMPT  TABLE: test_user.PRODUCT_COSIF
PROMPT =============================
COLUMN PRODUCT_CODE FORMAT A10
COLUMN COSIF_CODE FORMAT A12
COLUMN CLASSIFICATION_CODE FORMAT A18
COLUMN STATUS FORMAT A6

SELECT PRODUCT_CODE, COSIF_CODE, CLASSIFICATION_CODE, STATUS FROM test_user.PRODUCT_COSIF;

PROMPT
PROMPT =============================
PROMPT  TABLE: test_user.MANUAL_TRANSACTION
PROMPT =============================
COLUMN PRODUCT_CODE FORMAT A12
COLUMN COSIF_CODE FORMAT A12
COLUMN DESCRIPTION FORMAT A25
COLUMN TRANSACTION_DATE FORMAT A22
COLUMN USER_CODE FORMAT A15
COLUMN AMOUNT FORMAT 999,999,990.00

SELECT
  MONTH,
  YEAR,
  ENTRY_NUMBER,
  PRODUCT_CODE,
  COSIF_CODE,
  DESCRIPTION,
  TRANSACTION_DATE,
  USER_CODE,
  AMOUNT
FROM test_user.MANUAL_TRANSACTION;
