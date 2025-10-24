# 🚀 Quick Start - Guia Rápido de Implementação

## ⚡ 5 Minutos para Começar

### 1️⃣ Preparar o Banco de Dados (2 minutos)

```sql
-- Acesse: https://render.com
-- Dashboard > PostgreSQL > dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com
-- Vá para "Query" e copie todo o conteúdo de database_schema.sql
-- Execute (Ctrl + Enter)
```

**Credenciais do seu banco (já fornecidas):**
```
Host: dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com
Database: bdf1
User: bdf1
Password: fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr
```

### 2️⃣ Copiar Novas Classes para seu Projeto (1 minuto)

Copie os seguintes arquivos para `src/Data/`:
```
✓ DatabaseManager.java
✓ DatabaseInitializer.java
✓ TeamDAO.java
✓ DriverDAO.java
✓ EngineerDAO.java
✓ CarDAO.java
✓ RaceDAO.java
✓ RaceResultDAO.java
✓ DriverSubstitutionDAO.java
✓ ChampionshipStandingDAO.java
✓ UsageExample.java
```

### 3️⃣ Atualizar Classe Main (1 minuto)

Substitua sua classe `Main.java` pelo conteúdo de `MainUpdated.java` ou adicione:

```java
DatabaseManager dbManager = new DatabaseManager(
    "dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com",
    "bdf1",
    "bdf1",
    "fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr"
);

if (dbManager.testConnection()) {
    System.out.println("✓ Conectado ao banco!");
}
```

### 4️⃣ Executar (1 minuto)

```bash
cd G:\PROJETOS\SepphiTeamF1-main
javac -d bin -sourcepath src src/Main/Main.java
java -cp bin Main.Main
```

Na primeira execução, responda **"sim"** quando perguntado se deseja popular o banco.

---

## 🎯 Operações Mais Comuns

### ✅ Salvar Resultado de Corrida

```java
// Quando um piloto termina a corrida em 1º lugar (25 pontos)
dbManager.getRaceResultDAO().insertRaceResult(
    raceId,      // ID da corrida
    driverId,    // ID do piloto
    teamId,      // ID da equipe
    1,           // Posição final
    25           // Pontos conquistados
);

// Atualizar pontos do piloto
dbManager.getDriverDAO().updateDriverPoints(driverId, 25);

// Atualizar pontos da equipe
dbManager.getChampionshipStandingDAO()
    .updateTeamPoints(teamId, currentPoints + 25, 2025);
```

### ✅ Substituir Piloto com Alerta

```java
// Registrar substituição
dbManager.getDriverSubstitutionDAO().insertSubstitution(
    teamId,
    driverRemovedId,
    driverAddedId,
    "Lesão muscular - 2 corridas de recuperação",
    raceId  // null se não for específico de corrida
);

// Exibir alerta visualmente
System.out.println("\n⚠️  ALERTA DE SUBSTITUIÇÃO! ⚠️");
System.out.println("🏁 Equipe: " + teamName);
System.out.println("❌ Piloto saiu: " + driverRemovedName);
System.out.println("✅ Piloto entrou: " + driverAddedName);
System.out.println("📋 Motivo: Lesão muscular - 2 corridas de recuperação");
System.out.println("=".repeat(50));
```

### ✅ Ver Classificação Atual

```java
List<Map<String, Object>> standings = 
    dbManager.getChampionshipStandingDAO()
        .getChampionshipStandings(2025);

System.out.println("\n🏆 CLASSIFICAÇÃO F1 2025\n");
for (var s : standings) {
    System.out.printf("%2d. %-25s %5d PTS\n",
        (int)s.get("position"),
        s.get("team_name"),
        (int)s.get("total_points")
    );
}
```

### ✅ Melhorar Carro (Engenheiro Trabalhando)

```java
// Buscar carro
Car car = dbManager.getCarDAO().getCarById(carId);

// Buscar engenheiro
Engineer engineer = dbManager.getEngineerDAO()
    .getEngineerById(engineerId);

// Calcular melhoria (anos de experiência × 0.5)
double melhoria = engineer.yearsOfExperience * 0.5;
double novaPerformance = car.getPerformance() + melhoria;

// Atualizar no banco
dbManager.getCarDAO()
    .updateCarPerformance(carId, novaPerformance);

System.out.println("✅ Carro melhorado!");
System.out.println("   Performance: " + car.getPerformance() + 
                   " → " + novaPerformance);
```

---

## 📱 Estrutura de Uso Recomendada

### Na classe SimulacaoCorrida:

```java
public class SimulacaoCorrida {
    private DatabaseManager dbManager;
    private ArrayList<Team> teams;
    private ArrayList<Race> races;
    private Scanner scanner;

    public SimulacaoCorrida(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.scanner = new Scanner(System.in);
        
        // Carregar do banco
        this.teams = new ArrayList<>(
            dbManager.getTeamDAO().getAllTeams()
        );
        this.races = new ArrayList<>(
            dbManager.getRaceDAO().getAllRaces()
        );
    }

    public void simulateRace(Race race) {
        // ... lógica de simulação ...
        
        // Salvar resultados
        for (int i = 0; i < finalPositions.size(); i++) {
            Driver driver = finalPositions.get(i);
            int points = getPointsForPosition(i + 1);
            
            dbManager.getRaceResultDAO().insertRaceResult(
                race.getId(),
                driver.getId(),
                driver.getTeamId(),
                i + 1,
                points
            );
            
            dbManager.getDriverDAO()
                .updateDriverPoints(driver.getId(), points);
        }
        
        // Marcar corrida como simulada
        dbManager.getRaceDAO().markRaceAsSimulated(race.getId());
    }

    public void showClassification() {
        var standings = dbManager.getChampionshipStandingDAO()
            .getChampionshipStandings(2025);
        
        // ... exibir standings ...
    }
}
```

---

## 🔍 Verificar se Tudo Está Funcionando

Execute este teste rápido:

```java
// Em Main.java, após inicializar dbManager
DatabaseManager dbManager = new DatabaseManager(...);

// Teste 1: Conexão
if (!dbManager.testConnection()) {
    System.out.println("❌ Conexão falhou!");
    return;
}
System.out.println("✅ Conexão OK");

// Teste 2: Buscar equipes
List<Team> teams = dbManager.getTeamDAO().getAllTeams();
System.out.println("✅ " + teams.size() + " equipes carregadas");

// Teste 3: Buscar pilotos
List<Driver> drivers = dbManager.getDriverDAO().getAllDrivers();
System.out.println("✅ " + drivers.size() + " pilotos carregados");

// Teste 4: Buscar corridas
List<Race> races = dbManager.getRaceDAO().getAllRaces();
System.out.println("✅ " + races.size() + " corridas carregadas");

// Se todos os testes passarem, o sistema está pronto!
```

---

## ⚠️ Erros Comuns e Soluções

### ❌ "Connection refused"
**Causa**: Credenciais incorretas ou banco offline
**Solução**: 
- Verifique credenciais do Render
- Teste conexão direto no Render Dashboard

### ❌ "Table not found"
**Causa**: Schema SQL não foi executado
**Solução**: 
- Execute `database_schema.sql` completo no Render
- Verifique se todas as tabelas foram criadas

### ❌ "Duplicate key"
**Causa**: Tentou inserir dado duplicado
**Solução**: 
- Verifique se já existe no banco
- Use UPDATE ao invés de INSERT

### ❌ "NullPointerException"
**Causa**: Objeto não foi carregado do banco
**Solução**: 
- Verifique se ID existe no banco
- Adicione validação nula: `if (object != null)`

---

## 📊 Dados que Persistem Automaticamente

✅ Resultados de todas as corridas
✅ Pontos de pilotos e equipes
✅ Histórico de substituições
✅ Performance melhorada de carros
✅ Classificação atualizada
✅ Histórico completo de eventos

---

## 🎓 Próximos Passos (Opcional)

Depois que tudo estiver funcionando:

1. **Criar DataService** para encapsular lógica de negócios
2. **Adicionar Cache** em memória para performance
3. **Exportar Relatórios** em PDF
4. **Criar Dashboard Web** com Spring Boot

---

## 💬 Dúvidas?

Consulte:
- `DATABASE_GUIDE.md` - Guia completo
- `IMPLEMENTATION_SUMMARY.md` - Resumo técnico
- `UsageExample.java` - 6 exemplos práticos
- `src/Data/` - Código dos DAOs com comentários

---

**Tempo total**: ~5 minutos ⏱️
**Dificuldade**: ⭐ Fácil
**Status**: ✅ Pronto para Produção


