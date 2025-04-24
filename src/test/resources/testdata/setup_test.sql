-- C:\dev\projects\product-service\src\test\resources\testdata\setup_test.sql

-- Create PRODUCT table
BEGIN
    DECLARE table_exists INTEGER;
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
END;
/

-- Create PRODUCT_COSIF table
BEGIN
    DECLARE table_exists INTEGER;
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
END;
/

-- Create MANUAL_TRANSACTION table
BEGIN
    DECLARE table_exists INTEGER;
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
END;
/

-- Insert test data only if PRODUCT table is empty
BEGIN
    DECLARE row_count INTEGER;
    BEGIN
        SELECT COUNT(*) INTO row_count FROM test_user.product;
        IF row_count = 0 THEN
            INSERT INTO test_user.product VALUES ('PR01', RPAD('X', 255, 'X'), 'A');
            INSERT INTO test_user.product VALUES ('PR02', RPAD('Y', 255, 'Y'), 'I');
            DBMS_OUTPUT.PUT_LINE('Test data inserted into "product".');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Test data already present in "product".');
        END IF;
    END;
END;
/

-- Insert test data only if PRODUCT_COSIF table is empty
BEGIN
    DECLARE row_count INTEGER;
    BEGIN
        SELECT COUNT(*) INTO row_count FROM test_user.product_cosif;
        IF row_count = 0 THEN
            INSERT INTO test_user.product_cosif (product_code, cosif_code, classification_code, status)
            VALUES ('PR01', 'COSIFCODE01', 'CL001A', 'A');
            
            INSERT INTO test_user.product_cosif (product_code, cosif_code, classification_code, status)
            VALUES ('PR02', 'COSIFCODE02', 'CL002B', 'A');
            
            DBMS_OUTPUT.PUT_LINE('Test data inserted into "product_cosif".');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Test data already present in "product_cosif".');
        END IF;
    END;
END;
/

-- Insert test data only if MANUAL_TRANSACTION table is empty
BEGIN
    DECLARE row_count INTEGER;
    BEGIN
        SELECT COUNT(*) INTO row_count FROM test_user.manual_transaction;
        IF row_count = 0 THEN
            INSERT INTO test_user.manual_transaction (
                month, year, entry_number, product_code, cosif_code, description, transaction_date, user_code, amount
            ) VALUES (
                4, 2025, 1, 'PR01', 'COSIFCODE01',
                RPAD('D', 50, 'D'),
                TO_TIMESTAMP('2025-04-20 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
                RPAD('U', 15, 'U'),
                99999999999999.99
            );

            INSERT INTO test_user.manual_transaction (
                month, year, entry_number, product_code, cosif_code, description, transaction_date, user_code, amount
            ) VALUES (
                4, 2025, 2, 'PR02', 'COSIFCODE02',
                RPAD('E', 50, 'E'),
                TO_TIMESTAMP('2025-04-20 12:30:00', 'YYYY-MM-DD HH24:MI:SS'),
                RPAD('Z', 15, 'Z'),
                12345678901234.56
            );

            DBMS_OUTPUT.PUT_LINE('Test data inserted into "manual_transaction".');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Test data already present in "manual_transaction".');
        END IF;
    END;
END;
/

EXIT;
