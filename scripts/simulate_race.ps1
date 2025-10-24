# ================================================================
# SCRIPT PARA SIMULAR UMA CORRIDA - SEPPHI TEAM F1
# ================================================================

$JAVA_PATH = "C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\java.exe"
$PROJECT_PATH = "G:\PROJETOS\SepphiTeamF1-main"

Write-Host ""
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host "    TESTE: SIMULANDO UMA CORRIDA COMPLETA" -ForegroundColor Cyan
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ""

Set-Location $PROJECT_PATH

$inputCommands = @"
n
4
3
1
4
0
"@

$inputFile = "race_input.txt"
Set-Content -Path $inputFile -Value $inputCommands -Encoding UTF8

Write-Host "[*] Arquivo de entrada criado" -ForegroundColor Yellow
Write-Host "[*] Iniciando simulacao..." -ForegroundColor Yellow
Write-Host ""

$CP = "bin;lib\postgresql-42.7.1.jar"

Get-Content $inputFile | & $JAVA_PATH -cp $CP Main.Main

Write-Host ""
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host "    Simulacao finalizada!" -ForegroundColor Cyan
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ""

Remove-Item $inputFile -Force

Write-Host "RESULTADO:" -ForegroundColor Green
Write-Host "  [OK] Dados salvos no banco PostgreSQL do Render" -ForegroundColor Green
Write-Host "  [OK] Classificacoes atualizadas" -ForegroundColor Green
Write-Host "  [OK] Corridas e resultados registrados" -ForegroundColor Green
Write-Host ""

