# ================================================================
# SCRIPT PARA EXECUTAR SEPPHI TEAM F1 2025
# ================================================================

$JAVA_PATH = "C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\java.exe"
$PROJECT_PATH = "G:\PROJETOS\SepphiTeamF1-main"

Write-Host ""
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host "    SEPPHI TEAM F1 - SIMULADOR DE TEMPORADA 2025" -ForegroundColor Cyan
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ""

# Mudar para o diretório do projeto
Set-Location $PROJECT_PATH

# Executar a aplicação
Write-Host "[*] Iniciando aplicação..." -ForegroundColor Yellow
Write-Host ""

$CP = "bin;lib\postgresql-42.7.1.jar"
& $JAVA_PATH -cp $CP Main.Main

Write-Host ""
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host "    Aplicação finalizada" -ForegroundColor Cyan
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ""

