@echo off
setlocal enabledelayedexpansion
color 0A
cd /d G:\PROJETOS\SepphiTeamF1-main

echo.
echo ================================================================
echo    EXECUTANDO SEPPHI TEAM F1 2025
echo ================================================================
echo.

set JAVA_PATH=C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\java

REM Verificar se Java existe
if not exist "%JAVA_PATH%" (
    echo ERROR: Java nao encontrado em %JAVA_PATH%
    pause
    exit /b 1
)

REM Executar aplicacao
echo [*] Iniciando aplicacao...
echo.

"%JAVA_PATH%" -cp "bin;lib\postgresql-42.7.1.jar" Main.Main

echo.
echo ================================================================
echo    Aplicacao finalizada
echo ================================================================
echo.
pause

