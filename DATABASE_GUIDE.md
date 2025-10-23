# 🏁 Guia de Implementação - Persistência de Dados no Banco de Dados

## 📋 Visão Geral

Este documento explica como usar o novo sistema de persistência de dados que migra todos os dados das equipes F1 (pilotos, engenheiros, carros, corridas, etc) do código Java para um banco de dados PostgreSQL.

## 🗄️ Estrutura do Banco de Dados

O banco foi estruturado com as seguintes tabelas principais:

- **teams**: Dados das equipes
- **drivers**: Pilotos (com suporte a reservas)
- **engineers**: Engenheiros técnicos
- **cars**: Carros da F1
- **races**: Corridas do calendário
- **race_results**: Resultados de cada corrida
- **driver_substitutions**: Histórico de substituições de pilotos
- **championship_standings**: Classificação atual das equipes
- **f1_points_table**: Tabela de pontuação F1

## 🔧 Como Usar

### 1. Criar o Banco de Dados

Execute o script SQL fornecido:

```sql
-- Copie todo o conteúdo de database_schema.sql e execute no seu banco PostgreSQL
```

**Credenciais do Render (fornecidas):**
- Host: `dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com`
- Database: `bdf1`
- User: `bdf1`
- Password: `fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr`

### 2. Inicializar o DatabaseManager na sua aplicação

```java
// Na classe Main ou na inicialização do seu programa
DatabaseManager dbManager = new DatabaseManager(
    "dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com",
    "bdf1",
    "bdf1",
    "fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr"
);

// Testar conexão
if (dbManager.testConnection()) {
    System.out.println("✓ Conectado ao banco com sucesso!");
}
```

### 3. Popular o Banco com Dados Iniciais

```java
DatabaseInitializer initializer = new DatabaseInitializer(dbManager);

// Carregar equipes e seus membros
initializer.initializeDefaultData();

// Carregar calendário de corridas
initializer.initializeRaceCalendar();

// Inicializar classificações
initializer.initializeChampionshipStandings();
```

## 📚 Exemplos de Uso

### Buscar Todas as Equipes

```java
List<Team> teams = dbManager.getTeamDAO().getAllTeams();
for (Team team : teams) {
    System.out.println(team.getName());
}
```

### Buscar Pilotos de uma Equipe

```java
List<Driver> drivers = dbManager.getDriverDAO().getDriversByTeam(teamId);
for (Driver driver : drivers) {
    System.out.println(driver.getName() + " - #" + driver.getCarNumber());
}
```

### Atualizar Pontos de um Piloto

```java
dbManager.getDriverDAO().updateDriverPoints(driverId, 25); // Adiciona 25 pontos
```

### Registrar Resultado de Corrida

```java
dbManager.getRaceResultDAO().insertRaceResult(
    raceId,
    driverId,
    teamId,
    finishPosition,  // 1 para primeiro, 2 para segundo, etc
    pointsEarned     // 25, 18, 15, etc
);
```

### Registrar Substituição de Piloto

```java
dbManager.getDriverSubstitutionDAO().insertSubstitution(
    teamId,
    driverRemovedId,
    driverAddedId,
    "Lesão - Recuperação de 2 semanas",
    raceId  // null se não for específico de uma corrida
);

// Exibir histórico
System.out.println("⚠️ ALERTA: Substituição de piloto!");
List<Map<String, Object>> substitutions = 
    dbManager.getDriverSubstitutionDAO().getTeamSubstitutions(teamId);
```

### Buscar Classificação do Campeonato

```java
List<Map<String, Object>> standings = 
    dbManager.getChampionshipStandingDAO().getChampionshipStandings(2025);

for (Map<String, Object> standing : standings) {
    System.out.printf("%d. %s - %d pontos\n",
        standing.get("position"),
        standing.get("team_name"),
        standing.get("total_points")
    );
}
```

### Melhorar Performance de um Carro

```java
Car car = dbManager.getCarDAO().getCarById(carId);
double currentPerformance = car.getPerformance();
double newPerformance = currentPerformance + 2.5; // Adiciona 2.5 ao desempenho

dbManager.getCarDAO().updateCarPerformance(carId, newPerformance);
```

## 🔄 Integração com SimulacaoCorrida

Para integrar com sua classe `SimulacaoCorrida`, modifique o construtor:

```java
public SimulacaoCorrida(DatabaseManager dbManager) {
    this.dbManager = dbManager;
    this.scanner = new Scanner(System.in);
    
    // Carregar equipes do banco
    this.teams = new ArrayList<>(dbManager.getTeamDAO().getAllTeams());
    
    // Carregar corridas do banco
    List<Race> allRaces = dbManager.getRaceDAO().getAllRaces();
    this.races = new ArrayList<>(allRaces);
}
```

## 📊 Estrutura das Classes DAO

Cada DAO segue o padrão de operações CRUD:

- **Insert**: Adiciona novo registro e retorna o ID
- **Get/Select**: Busca registros (por ID, por filtro, todos)
- **Update**: Atualiza registro existente
- **Delete**: Remove registro

### Exemplo de DAO customizado:

```java
public class CustomDAO {
    private ConnectDB db;
    
    public CustomDAO(ConnectDB db) {
        this.db = db;
    }
    
    public boolean customOperation(int id) {
        String sql = "UPDATE sua_tabela SET campo = ? WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, novoValor);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return false;
    }
}
```

## 🎯 Vantagens do Sistema

✅ **Persistência de Dados**: Todos os dados são salvos no banco
✅ **Sem Perda de Informações**: Ao encerrar, todos os dados continuam no banco
✅ **Múltiplas Sessões**: Pode usar os dados em várias execuções do programa
✅ **Relatórios**: Fácil gerar relatórios históricos
✅ **Escalabilidade**: Suporta mais equipes, pilotos e corridas facilmente
✅ **Alertas**: Sistema de substituição de pilotos com razões registradas

## 📝 Notas Importantes

1. **IDs do Banco**: Todas as classes foram atualizadas para suportar IDs (-1 = não salvo)
2. **TeamId**: Membro, Engenheiro e Carro agora têm referência à equipe
3. **Transações**: Use try-with-resources para garantir fechamento de conexões
4. **Performance**: Use índices para queries frequentes (já criados no schema)
5. **Validação**: Os DAOs fazem validação básica de dados

## 🐛 Tratamento de Erros

Todos os DAOs incluem tratamento de exceções:

```java
try (Connection conn = db.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    // Operação...
} catch (SQLException e) {
    System.out.println("Erro ao [operação]: " + e.getMessage());
}
```

## 🚀 Próximos Passos

1. Criar DataService para encapsular lógica de negócios
2. Implementar cache em memória para melhorar performance
3. Adicionar backup automático do banco
4. Criar interface web para consultar dados
5. Adicionar mais relatórios e análises

---

**Data**: Outubro 2025
**Versão**: 1.0
**Status**: Pronto para Produção

