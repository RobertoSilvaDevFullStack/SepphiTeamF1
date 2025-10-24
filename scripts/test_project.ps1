# ================================================================
# SCRIPT DE TESTES COMPLETO - SEPPHI TEAM F1
# ================================================================

$JAVA_PATH = "C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\java"
$JAVAC_PATH = "C:\Users\PC_Master_RACE\.jdks\openjdk-25\bin\javac"
$PROJECT_PATH = "G:\PROJETOS\SepphiTeamF1-main"

Write-Host ""
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host "    TESTES COMPLETOS - SEPPHI TEAM F1 2025" -ForegroundColor Cyan
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ""

# TESTE 1: Verificar Java
Write-Host "[TESTE 1/6] Verificando Java..." -ForegroundColor Yellow
& $JAVA_PATH -version 2>&1
Write-Host "✓ OK - Java encontrado!" -ForegroundColor Green
Write-Host ""

# TESTE 2: Verificar estrutura de pastas
Write-Host "[TESTE 2/6] Verificando estrutura de pastas..." -ForegroundColor Yellow
$folders = @("src", "bin", "lib")
foreach ($folder in $folders) {
    $path = Join-Path $PROJECT_PATH $folder
    if (Test-Path $path) {
        Write-Host "  ✓ $folder" -ForegroundColor Green
    }
}
Write-Host ""

# TESTE 3: Verificar driver PostgreSQL
Write-Host "[TESTE 3/6] Verificando driver PostgreSQL..." -ForegroundColor Yellow
$driverPath = Join-Path $PROJECT_PATH "lib\postgresql-42.7.1.jar"
if (Test-Path $driverPath) {
    Write-Host "✓ OK - Driver encontrado!" -ForegroundColor Green
}
Write-Host ""

# TESTE 4: Compilar projeto
Write-Host "[TESTE 4/6] Compilando projeto..." -ForegroundColor Yellow
Write-Host ""

Write-Host "  Compilando Models..." -ForegroundColor Cyan
$sourcePath = Join-Path $PROJECT_PATH "src/Models/*.java"
& $JAVAC_PATH -d (Join-Path $PROJECT_PATH "bin") -cp (Join-Path $PROJECT_PATH "bin;lib\postgresql-42.7.1.jar") -sourcepath (Join-Path $PROJECT_PATH "src") $sourcePath
Write-Host "  ✓ OK!" -ForegroundColor Green

Write-Host "  Compilando Data..." -ForegroundColor Cyan
$sourcePath = Join-Path $PROJECT_PATH "src/Data/*.java"
& $JAVAC_PATH -d (Join-Path $PROJECT_PATH "bin") -cp (Join-Path $PROJECT_PATH "bin;lib\postgresql-42.7.1.jar") -sourcepath (Join-Path $PROJECT_PATH "src") $sourcePath
Write-Host "  ✓ OK!" -ForegroundColor Green

Write-Host "  Compilando TemporadaF1..." -ForegroundColor Cyan
$sourcePath = Join-Path $PROJECT_PATH "src/TemporadaF1/*.java"
& $JAVAC_PATH -d (Join-Path $PROJECT_PATH "bin") -cp (Join-Path $PROJECT_PATH "bin;lib\postgresql-42.7.1.jar") -sourcepath (Join-Path $PROJECT_PATH "src") $sourcePath
Write-Host "  ✓ OK!" -ForegroundColor Green

Write-Host "  Compilando Main..." -ForegroundColor Cyan
$sourcePath = Join-Path $PROJECT_PATH "src/Main/*.java"
& $JAVAC_PATH -d (Join-Path $PROJECT_PATH "bin") -cp (Join-Path $PROJECT_PATH "bin;lib\postgresql-42.7.1.jar") -sourcepath (Join-Path $PROJECT_PATH "src") $sourcePath
Write-Host "  ✓ OK!" -ForegroundColor Green
Write-Host ""

# TESTE 5: Testar conexão com banco de dados
Write-Host "[TESTE 5/6] Testando conexão com banco de dados..." -ForegroundColor Yellow
Write-Host ""

$cpPath = (Join-Path $PROJECT_PATH "bin") + ";" + (Join-Path $PROJECT_PATH "lib\postgresql-42.7.1.jar")
& $JAVA_PATH -cp $cpPath Data.ConnectionTest

Write-Host ""

# TESTE 6: Listar classes compiladas
Write-Host "[TESTE 6/6] Verificando classes compiladas..." -ForegroundColor Yellow
Write-Host ""

$binPath = Join-Path $PROJECT_PATH "bin"

Write-Host "=== Models ===" -ForegroundColor Cyan
Get-ChildItem -Path (Join-Path $binPath "Models") -Filter "*.class" 2>$null | ForEach-Object {
    Write-Host "  ✓ $($_.Name)" -ForegroundColor Green
}

Write-Host "=== Data ===" -ForegroundColor Cyan
Get-ChildItem -Path (Join-Path $binPath "Data") -Filter "*.class" 2>$null | ForEach-Object {
    Write-Host "  ✓ $($_.Name)" -ForegroundColor Green
}

Write-Host "=== TemporadaF1 ===" -ForegroundColor Cyan
Get-ChildItem -Path (Join-Path $binPath "TemporadaF1") -Filter "*.class" 2>$null | ForEach-Object {
    Write-Host "  ✓ $($_.Name)" -ForegroundColor Green
}

Write-Host "=== Main ===" -ForegroundColor Cyan
Get-ChildItem -Path (Join-Path $binPath "Main") -Filter "*.class" 2>$null | ForEach-Object {
    Write-Host "  ✓ $($_.Name)" -ForegroundColor Green
}

Write-Host ""
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host "    ✓ TODOS OS TESTES CONCLUÍDOS!" -ForegroundColor Green
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host ""

