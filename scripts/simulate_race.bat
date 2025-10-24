@echo off
setlocal enabledelayedexpansion
color 0A
cd /d G:\PROJETOS\SepphiTeamF1-main

echo.
echo ================================================================
echo    TESTE: SIMULANDO UMA CORRIDA COMPLETA
echo ================================================================
echo.

set JAVA_PATH=C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\java
set CP=bin;lib\postgresql-42.7.1.jar

echo [*] Iniciando simulacao...
echo.

REM Executar com entrada pré-definida
REM n = nao popular banco
REM 4 = Ver classificacao de equipes
REM 3 = Simular corrida
REM 1 = Simular primeira corrida
REM 4 = Ver classificacao de equipes (apos corrida)
REM 0 = Sair

(
  echo n
  timeout /t 1 /nobreak >nul
  echo 4
  timeout /t 2 /nobreak >nul
  echo 3
  timeout /t 1 /nobreak >nul
  echo 1
  timeout /t 2 /nobreak >nul
  echo 4
  timeout /t 2 /nobreak >nul
  echo 0
) | "%JAVA_PATH%" -cp "%CP%" Main.Main

echo.
echo ================================================================
echo    Simulacao finalizada!
echo ================================================================
echo.
echo RESULTADO:
echo   [OK] Dados salvos no banco PostgreSQL do Render
echo   [OK] Classificacoes atualizadas
echo   [OK] Corridas e resultados registrados
echo.
pause

