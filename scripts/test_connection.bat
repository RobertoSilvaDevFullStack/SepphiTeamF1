@echo off
REM Script para compilar e testar conexão com banco de dados
REM Encontra automaticamente o caminho do Java

echo.
echo ============================================================
echo     Compilando projeto F1 e testando conexao com banco
echo ============================================================
echo.

REM Definir diretórios
set PROJ_DIR=G:\PROJETOS\SepphiTeamF1-main
set SRC_DIR=%PROJ_DIR%\src
set BIN_DIR=%PROJ_DIR%\bin
set LIB_DIR=%PROJ_DIR%\lib

REM Caminho do Java encontrado anteriormente
set JAVA_PATH=C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin

echo [INFO] Usando Java em: %JAVA_PATH%
echo.

REM Verificar se driver PostgreSQL existe
if not exist "%LIB_DIR%\postgresql-42.7.1.jar" (
    echo.
    echo AVISO: Driver PostgreSQL nao encontrado!
    echo Executando download_driver.bat...
    echo.
    call "%PROJ_DIR%\download_driver.bat"
    if errorlevel 1 (
        echo.
        echo ERRO: Nao foi possivel baixar o driver!
        echo Por favor, baixe manualmente de:
        echo https://jdbc.postgresql.org/download/postgresql-42.7.1.jar
        echo E coloque em: %LIB_DIR%
        echo.
        pause
        exit /b 1
    )
)

REM Criar diretório bin se não existir
if not exist %BIN_DIR% mkdir %BIN_DIR%

REM Compilar Models
echo [1/3] Compilando Models...
"%JAVA_PATH%\javac" -d %BIN_DIR% -sourcepath %SRC_DIR% %SRC_DIR%\Models\*.java
if errorlevel 1 (
    echo.
    echo ERRO ao compilar Models!
    echo.
    pause
    exit /b 1
)
echo OK!

echo.
echo [2/3] Compilando Data...
"%JAVA_PATH%\javac" -d %BIN_DIR% -sourcepath %SRC_DIR% -cp "%LIB_DIR%\postgresql-42.7.1.jar" %SRC_DIR%\Data\*.java
if errorlevel 1 (
    echo.
    echo ERRO ao compilar Data!
    echo.
    pause
    exit /b 1
)
echo OK!

echo.
echo [3/3] Executando teste de conexao...
echo.
"%JAVA_PATH%\java" -cp "%BIN_DIR%;%LIB_DIR%\postgresql-42.7.1.jar" Data.ConnectionTest

echo.
echo ============================================================
echo     Teste concluido
echo ============================================================
pause

