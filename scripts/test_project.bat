@echo off
REM ================================================================
REM SCRIPT DE TESTES COMPLETO - SEPPHI TEAM F1
REM ================================================================

setlocal enabledelayedexpansion

color 0A
cls

echo.
echo ================================================================
echo    TESTES COMPLETOS - SEPPHI TEAM F1 2025
echo ================================================================
echo.

REM ================================================================
REM TESTE 1: Verificar Java
REM ================================================================
echo [TESTE 1/6] Verificando Java...
java -version 2>&1
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Java nao encontrado!
    echo.
    goto END
)
echo OK - Java encontrado!
echo.

REM ================================================================
REM TESTE 2: Verificar estrutura de pastas
REM ================================================================
echo [TESTE 2/6] Verificando estrutura de pastas...
if not exist "src" (
    echo ERROR: Pasta src nao encontrada!
    goto END
)
if not exist "bin" (
    echo ERROR: Pasta bin nao encontrada!
    goto END
)
if not exist "lib" (
    echo ERROR: Pasta lib nao encontrada!
    goto END
)
echo OK - Estrutura correta!
echo.

REM ================================================================
REM TESTE 3: Verificar driver PostgreSQL
REM ================================================================
echo [TESTE 3/6] Verificando driver PostgreSQL...
if not exist "lib\postgresql-42.7.1.jar" (
    echo ERROR: Driver PostgreSQL nao encontrado!
    echo Execute: download_driver.bat
    goto END
)
echo OK - Driver encontrado!
echo.

REM ================================================================
REM TESTE 4: Compilar projeto
REM ================================================================
echo [TESTE 4/6] Compilando projeto...
echo.

echo  - Compilando Models...
cd /d G:\PROJETOS\SepphiTeamF1-main
javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src/Models/*.java 2>&1 | findstr /V "^$"
if %errorlevel% neq 0 (
    echo ERROR ao compilar Models!
    goto END
)
echo OK!
echo.

echo  - Compilando Data...
javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src/Data/*.java 2>&1 | findstr /V "^$"
if %errorlevel% neq 0 (
    echo ERROR ao compilar Data!
    goto END
)
echo OK!
echo.

echo  - Compilando TemporadaF1...
javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src/TemporadaF1/*.java 2>&1 | findstr /V "^$"
if %errorlevel% neq 0 (
    echo ERROR ao compilar TemporadaF1!
    goto END
)
echo OK!
echo.

echo  - Compilando Main...
javac -d bin -cp "bin;lib\postgresql-42.7.1.jar" -sourcepath src src/Main/*.java 2>&1 | findstr /V "^$"
if %errorlevel% neq 0 (
    echo ERROR ao compilar Main!
    goto END
)
echo OK!
echo.

REM ================================================================
REM TESTE 5: Testar conexão com banco de dados
REM ================================================================
echo [TESTE 5/6] Testando conexao com banco de dados...
echo.
java -cp "bin;lib\postgresql-42.7.1.jar" Data.ConnectionTest 2>&1
echo.

REM ================================================================
REM TESTE 6: Listar classes compiladas
REM ================================================================
echo [TESTE 6/6] Verificando classes compiladas...
echo.
echo === MODELOS ===
dir /b bin\Models\*.class 2>nul || echo Nenhum arquivo encontrado
echo.
echo === DATA (DAOs) ===
dir /b bin\Data\*.class 2>nul || echo Nenhum arquivo encontrado
echo.
echo === TEMPORADA F1 ===
dir /b bin\TemporadaF1\*.class 2>nul || echo Nenhum arquivo encontrado
echo.
echo === MAIN ===
dir /b bin\Main\*.class 2>nul || echo Nenhum arquivo encontrado
echo.

REM ================================================================
REM RESULTADO FINAL
REM ================================================================
:END
echo.
echo ================================================================
echo    TESTES CONCLUIDOS
echo ================================================================
echo.
pause

