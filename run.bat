@echo off
REM Script para compilar e executar a aplicação F1
REM Configurar encoding UTF-8 para o console
chcp 65001 >nul

echo.
echo ============================================================
echo     Compilando e Iniciando Simulacao F1 2025
echo ============================================================
echo.

set PROJ_DIR=G:\PROJETOS\SepphiTeamF1-main
set SRC_DIR=%PROJ_DIR%\src
set BIN_DIR=%PROJ_DIR%\bin
set LIB_DIR=%PROJ_DIR%\lib
set JAVA_PATH=C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin

echo Usando Java em: %JAVA_PATH%
echo.

REM Criar diretório bin se não existir
if not exist %BIN_DIR% mkdir %BIN_DIR%

REM Compilar Models
echo [1/4] Compilando Models...
"%JAVA_PATH%\javac" -d %BIN_DIR% -sourcepath %SRC_DIR% %SRC_DIR%\Models\*.java
if errorlevel 1 (
    echo ERRO ao compilar Models!
    pause
    exit /b 1
)
echo OK!

echo.
echo [2/4] Compilando Data...
"%JAVA_PATH%\javac" -d %BIN_DIR% -sourcepath %SRC_DIR% -cp "%LIB_DIR%\postgresql-42.7.1.jar" %SRC_DIR%\Data\*.java
if errorlevel 1 (
    echo ERRO ao compilar Data!
    pause
    exit /b 1
)
echo OK!

echo.
echo [3/4] Compilando TemporadaF1...
"%JAVA_PATH%\javac" -d %BIN_DIR% -sourcepath %SRC_DIR% -cp "%LIB_DIR%\postgresql-42.7.1.jar" %SRC_DIR%\TemporadaF1\*.java
if errorlevel 1 (
    echo ERRO ao compilar TemporadaF1!
    pause
    exit /b 1
)
echo OK!

echo.
echo [4/4] Compilando Main...
"%JAVA_PATH%\javac" -d %BIN_DIR% -sourcepath %SRC_DIR% -cp "%LIB_DIR%\postgresql-42.7.1.jar" %SRC_DIR%\Main\*.java
if errorlevel 1 (
    echo ERRO ao compilar Main!
    pause
    exit /b 1
)
echo OK!

echo.
echo ============================================================
echo Iniciando aplicacao...
echo ============================================================
echo.

REM Executar aplicação
"%JAVA_PATH%\java" -cp "%BIN_DIR%;%LIB_DIR%\postgresql-42.7.1.jar" Main.Main

echo.
echo Aplicacao finalizada.
pause

