@echo off
REM Script para baixar driver PostgreSQL JDBC

echo.
echo ============================================================
echo     Baixando driver PostgreSQL JDBC
echo ============================================================
echo.

set LIB_DIR=G:\PROJETOS\SepphiTeamF1-main\lib
set DRIVER_URL=https://jdbc.postgresql.org/download/postgresql-42.7.1.jar
set DRIVER_FILE=%LIB_DIR%\postgresql-42.7.1.jar

REM Verificar se já existe
if exist "%DRIVER_FILE%" (
    echo Driver ja existe em: %DRIVER_FILE%
    exit /b 0
)

echo Criando pasta lib...
if not exist "%LIB_DIR%" mkdir "%LIB_DIR%"

echo.
echo Baixando driver PostgreSQL JDBC...
echo URL: %DRIVER_URL%
echo.

REM Usar PowerShell para baixar (mais confiável)
powershell -Command "Invoke-WebRequest -Uri '%DRIVER_URL%' -OutFile '%DRIVER_FILE%'"

if exist "%DRIVER_FILE%" (
    echo.
    echo ============================================================
    echo OK! Driver baixado com sucesso!
    echo Arquivo: %DRIVER_FILE%
    echo ============================================================
) else (
    echo.
    echo ERRO ao baixar driver!
    echo Por favor, baixe manualmente de: %DRIVER_URL%
    echo E coloque em: %LIB_DIR%
    echo.
    pause
    exit /b 1
)

pause

