:: C:\dev\projects\product-service\scripts\run_db_setup_test.bat
@echo off
SETLOCAL

:: === Configuration ===
set DB_CONN=test_user/test_password@//localhost:1521/XEPDB1
set SETUP_SQL=%~dp0setup_test.sql

:: === Check for setup_test.sql ===
if not exist "%SETUP_SQL%" (
    echo [ERROR] Missing setup_test.sql at:
    echo %SETUP_SQL%
    pause
    exit /b 1
)

:: === Run Setup ===
echo Running database setup for test...
echo.

:: Run the SQL script using SQLPlus, suppress extra SQL> prompts
(
    echo SET SERVEROUTPUT ON
    echo SET FEEDBACK OFF
    echo SET VERIFY OFF
    echo SET ECHO OFF
    echo @%SETUP_SQL%
    echo EXIT
) | sqlplus -L %DB_CONN%

:: === Check Exit Code ===
if %ERRORLEVEL% equ 0 (
    echo.
    echo [SUCCESS] Test database setup completed successfully.
) else (
    echo.
    echo [ERROR] Test database setup failed with code %ERRORLEVEL%.
)

pause
