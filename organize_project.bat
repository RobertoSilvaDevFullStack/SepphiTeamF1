@echo off
cd /d G:\PROJETOS\SepphiTeamF1-main

REM Criar pastas de organização
mkdir docs 2>nul
mkdir scripts 2>nul
mkdir database 2>nul
mkdir tests 2>nul

echo [+] Pastas criadas com sucesso!

REM Mover arquivos de documentação para a pasta docs
move CHECKLIST.md docs\ >nul 2>&1
move DATABASE_GUIDE.md docs\ >nul 2>&1
move EXECUTIVE_SUMMARY.md docs\ >nul 2>&1
move IMPLEMENTATION_SUMMARY.md docs\ >nul 2>&1
move QUICKSTART.md docs\ >nul 2>&1

echo [+] Arquivos de documentação movidos para /docs

REM Mover scripts de execução e teste para a pasta scripts
move run.bat scripts\ >nul 2>&1
move run_app.bat scripts\ >nul 2>&1
move run_app.ps1 scripts\ >nul 2>&1
move simulate_race.bat scripts\ >nul 2>&1
move simulate_race.ps1 scripts\ >nul 2>&1
move download_driver.bat scripts\ >nul 2>&1
move test_connection.bat scripts\ >nul 2>&1
move test_all.bat scripts\ >nul 2>&1
move test_project.bat scripts\ >nul 2>&1
move test_project.ps1 scripts\ >nul 2>&1

echo [+] Scripts movidos para /scripts

REM Mover arquivos de banco de dados para a pasta database
move database_schema.sql database\ >nul 2>&1
move clean_database.sql database\ >nul 2>&1

echo [+] Arquivos de banco de dados movidos para /database

echo.
echo [OK] Reorganizacao concluida com sucesso!
echo.
pause

