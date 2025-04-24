-- setup.sql

BEGIN
   -- Drop tables if they exist
   BEGIN EXECUTE IMMEDIATE 'DROP TABLE manual_transaction CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
   BEGIN EXECUTE IMMEDIATE 'DROP TABLE product_cosif CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;
   BEGIN EXECUTE IMMEDIATE 'DROP TABLE product CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN NULL; END;

   -- Create Product table
   EXECUTE IMMEDIATE '
       CREATE TABLE product (
           product_code CHAR(4) PRIMARY KEY NOT NULL,
           product_desc VARCHAR2(255),
           status CHAR(1)
       )';

   -- Create Product_Cosif table
   EXECUTE IMMEDIATE '
       CREATE TABLE product_cosif (
           product_code CHAR(4) NOT NULL,
           cosif_code CHAR(11) NOT NULL PRIMARY KEY,
           classification_code CHAR(6),
           status CHAR(1)
       )';

   -- Create Manual_Transaction table
   EXECUTE IMMEDIATE '
       CREATE TABLE manual_transaction (
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

   -- Insert sample data
   EXECUTE IMMEDIATE 'INSERT INTO product VALUES (''P001'', ''Product 1 Description'', ''A'')';
   EXECUTE IMMEDIATE 'INSERT INTO product VALUES (''P002'', ''Product 2 Description'', ''A'')';
   EXECUTE IMMEDIATE 'INSERT INTO product VALUES (''P003'', ''Product 3 Description'', ''B'')';

   EXECUTE IMMEDIATE 'INSERT INTO product_cosif VALUES (''P001'', ''COSIF123456'', ''CL001'', ''A'')';
   EXECUTE IMMEDIATE 'INSERT INTO product_cosif VALUES (''P002'', ''COSIF123457'', ''CL002'', ''A'')';

   EXECUTE IMMEDIATE '
       INSERT INTO manual_transaction (
           month, year, entry_number, product_code, cosif_code,
           description, transaction_date, user_code, amount
       ) VALUES (
           4, 2025, 1, ''P001'', ''COSIF123456'', ''Transaction 1'',
           TO_TIMESTAMP(''2025-04-17 10:00:00'', ''YYYY-MM-DD HH24:MI:SS''), ''TEST'', 100.00
       )';

   EXECUTE IMMEDIATE '
       INSERT INTO manual_transaction (
           month, year, entry_number, product_code, cosif_code,
           description, transaction_date, user_code, amount
       ) VALUES (
           4, 2025, 2, ''P002'', ''COSIF123457'', ''Transaction 2'',
           TO_TIMESTAMP(''2025-04-17 10:30:00'', ''YYYY-MM-DD HH24:MI:SS''), ''TEST'', 150.00
       )';

   COMMIT;
END;
/
-- === Final Selects to display data ===
PROMPT
PROMPT =============================
PROMPT ✅ TABLE: PRODUCT
PROMPT =============================
COLUMN PRODUCT_CODE FORMAT A10
COLUMN PRODUCT_DESC FORMAT A30
COLUMN STATUS FORMAT A6

SELECT PRODUCT_CODE, PRODUCT_DESC, STATUS FROM PRODUCT;

PROMPT
PROMPT =============================
PROMPT ✅ TABLE: PRODUCT_COSIF
PROMPT =============================
COLUMN PRODUCT_CODE FORMAT A10
COLUMN COSIF_CODE FORMAT A12
COLUMN CLASSIFICATION_CODE FORMAT A18
COLUMN STATUS FORMAT A6

SELECT PRODUCT_CODE, COSIF_CODE, CLASSIFICATION_CODE, STATUS FROM PRODUCT_COSIF;

PROMPT
PROMPT =============================
PROMPT ✅ TABLE: MANUAL_TRANSACTION
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
FROM MANUAL_TRANSACTION;
