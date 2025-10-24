@echo off
setlocal enabledelayedexpansion
color 0A
cd /d G:\PROJETOS\SepphiTeamF1-main

echo.
echo ================================================================
echo    TESTES COMPLETOS - SEPPHI TEAM F1 2025
echo ================================================================
echo.

REM TESTE 1: Verificar Java
echo [TESTE 1/6] Verificando Java...
C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\java -version 2>&1
if %errorlevel% equ 0 (
    echo OK - Java encontrado!
) else (
    echo ERROR: Java nao encontrado!
    pause
    exit /b 1
)
echo.

REM TESTE 2: Verificar estrutura
echo [TESTE 2/6] Verificando estrutura de pastas...
if exist "src" echo  - src OK
if exist "bin" echo  - bin OK
if exist "lib" echo  - lib OK
echo.

REM TESTE 3: Verificar driver
echo [TESTE 3/6] Verificando driver PostgreSQL...
if exist "lib\postgresql-42.7.1.jar" (
    echo  - Driver encontrado!
) else (
    echo ERROR: Driver nao encontrado!
    pause
    exit /b 1
)
echo.

REM TESTE 4: Compilar
echo [TESTE 4/6] Compilando projeto...
echo.

echo  - Compilando Models...
C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src\Models\*.java
if %errorlevel% equ 0 (
    echo    OK!
) else (
    echo    ERROR!
    pause
    exit /b 1
)

echo  - Compilando Data...
C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src\Data\*.java
if %errorlevel% equ 0 (
    echo    OK!
) else (
    echo    ERROR!
    pause
    exit /b 1
)

echo  - Compilando TemporadaF1...
C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src\TemporadaF1\*.java
if %errorlevel% equ 0 (
    echo    OK!
) else (
    echo    ERROR!
    pause
    exit /b 1
)

echo  - Compilando Main...
C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src\Main\*.java
if %errorlevel% equ 0 (
    echo    OK!
) else (
    echo    ERROR!
    pause
    exit /b 1
)
echo.

REM TESTE 5: Testar conexao com banco
echo [TESTE 5/6] Testando conexao com banco de dados...
echo.
C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\java -cp "bin;lib\postgresql-42.7.1.jar" Data.ConnectionTest
echo.

REM TESTE 6: Verificar classes
echo [TESTE 6/6] Verificando classes compiladas...
echo.
echo === MODELS ===
dir /b bin\Models\*.class 2>nul
echo.
echo === DATA ===
dir /b bin\Data\*.class 2>nul
echo.
echo === TEMPORADA F1 ===
dir /b bin\TemporadaF1\*.class 2>nul
echo.
echo === MAIN ===
dir /b bin\Main\*.class 2>nul
echo.

echo ================================================================
echo    TESTES CONCLUIDOS COM SUCESSO!
echo ================================================================
echo.
pause

