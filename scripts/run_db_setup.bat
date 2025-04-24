:: run_db_setup.bat
@echo off
SETLOCAL

:: === Configuration ===
set DB_CONN=system/1234@//localhost:1521/XEPDB1
set SETUP_SQL=%~dp0setup.sql

:: === Check for setup.sql ===
if not exist "%SETUP_SQL%" (
    echo [ERROR] Missing setup.sql at:
    echo %SETUP_SQL%
    pause
    exit /b 1
)

:: === Run Setup ===
echo Running database setup...
echo.

(
    echo SET SERVEROUTPUT ON
    echo SET FEEDBACK OFF
    echo SET VERIFY OFF
    echo @"%SETUP_SQL%"
    echo EXIT
) | sqlplus -L %DB_CONN%

:: === Check Exit Code ===
if %ERRORLEVEL% equ 0 (
    echo.
    echo [SUCCESS] Setup completed successfully.
) else (
    echo.
    echo [ERROR] Setup failed with code %ERRORLEVEL%.
)

pause
