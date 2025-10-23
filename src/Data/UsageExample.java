package Data;

/**
 * Exemplo de como usar o novo sistema de persistência de dados
 * Este arquivo demonstra todos os padrões de uso
 */
public class UsageExample {

    public static void main(String[] args) {
        // ==================================
        // 1. INICIALIZAR O BANCO DE DADOS
        // ==================================

        DatabaseManager dbManager = new DatabaseManager(
            "dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com",
            "bdf1",
            "bdf1",
            "fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr"
        );

        // Testar conexão
        if (!dbManager.testConnection()) {
            System.out.println("❌ Falha ao conectar ao banco!");
            return;
        }

        // ==================================
        // 2. POPULAR O BANCO (primeira vez)
        // ==================================

        DatabaseInitializer initializer = new DatabaseInitializer(dbManager);

        // Descomente apenas na primeira execução:
        // initializer.initializeDefaultData();
        // initializer.initializeRaceCalendar();
        // initializer.initializeChampionshipStandings();

        // ==================================
        // 3. EXEMPLOS DE OPERAÇÕES
        // ==================================

        exampleListAllTeams(dbManager);
        exampleListDriversByTeam(dbManager);
        exampleGetTeamClassification(dbManager);
        exampleInsertRaceResult(dbManager);
        exampleRegisterSubstitution(dbManager);
        exampleUpdateDriverPoints(dbManager);
    }

    /**
     * Exemplo 1: Listar todas as equipes
     */
    private static void exampleListAllTeams(DatabaseManager dbManager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXEMPLO 1: Listar todas as equipes");
        System.out.println("=".repeat(60));

        var teams = dbManager.getTeamDAO().getAllTeams();
        System.out.println("\n🏁 Equipes F1 2025:\n");

        for (var team : teams) {
            System.out.println("  • " + team.getName() + " (" + team.getCitizenship() + ")");
        }
    }

    /**
     * Exemplo 2: Listar pilotos de uma equipe
     */
    private static void exampleListDriversByTeam(DatabaseManager dbManager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXEMPLO 2: Listar pilotos de uma equipe");
        System.out.println("=".repeat(60));

        // Buscar primeira equipe
        var teams = dbManager.getTeamDAO().getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("Nenhuma equipe encontrada!");
            return;
        }

        var team = teams.get(0);
        var drivers = dbManager.getDriverDAO().getDriversByTeam(team.getId());

        System.out.println("\n👥 Pilotos de " + team.getName() + ":\n");

        for (var driver : drivers) {
            System.out.printf("  #%d - %s (Salário: $%.2f M)\n",
                driver.getCarNumber(),
                driver.getName(),
                driver.getWage() / 1_000_000.0
            );
        }
    }

    /**
     * Exemplo 3: Ver classificação do campeonato
     */
    private static void exampleGetTeamClassification(DatabaseManager dbManager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXEMPLO 3: Classificação do Campeonato");
        System.out.println("=".repeat(60));

        var standings = dbManager.getChampionshipStandingDAO()
            .getChampionshipStandings(2025);

        System.out.println("\n🏆 Classificação F1 2025:\n");
        System.out.println("POS | TIME                    | PONTOS | CORRIDAS");
        System.out.println("-".repeat(55));

        for (var standing : standings) {
            System.out.printf("%2d  | %-23s | %6d | %8d\n",
                standing.get("position"),
                standing.get("team_name"),
                standing.get("total_points"),
                standing.get("races_completed")
            );
        }
    }

    /**
     * Exemplo 4: Inserir resultado de corrida
     */
    private static void exampleInsertRaceResult(DatabaseManager dbManager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXEMPLO 4: Registrar resultado de corrida");
        System.out.println("=".repeat(60));

        // Buscar primeira corrida não simulada
        var races = dbManager.getRaceDAO().getUnsimulatedRaces();
        var drivers = dbManager.getDriverDAO().getAllDrivers();

        if (races.isEmpty() || drivers.isEmpty()) {
            System.out.println("Dados insuficientes para exemplo!");
            return;
        }

        var race = races.get(0);
        var driver = drivers.get(0);

        // Inserir resultado (1º lugar = 25 pontos)
        int resultId = dbManager.getRaceResultDAO().insertRaceResult(
            race.getId(),
            driver.getId(),
            driver.getTeamId(),
            1,      // 1º lugar
            25      // 25 pontos
        );

        System.out.println("\n✓ Resultado de corrida registrado!");
        System.out.println("  Corrida: " + race.getName());
        System.out.println("  Piloto: " + driver.getName() + " (#" + driver.getCarNumber() + ")");
        System.out.println("  Posição: 1º");
        System.out.println("  Pontos: 25");

        // Marcar corrida como simulada
        dbManager.getRaceDAO().markRaceAsSimulated(race.getId());
        System.out.println("✓ Corrida marcada como simulada!");
    }

    /**
     * Exemplo 5: Registrar substituição de piloto com alerta
     */
    private static void exampleRegisterSubstitution(DatabaseManager dbManager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXEMPLO 5: Registrar substituição de piloto");
        System.out.println("=".repeat(60));

        var teams = dbManager.getTeamDAO().getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("Nenhuma equipe encontrada!");
            return;
        }

        var team = teams.get(0);
        var drivers = dbManager.getDriverDAO().getDriversByTeam(team.getId());
        var reserves = dbManager.getDriverDAO().getReserveDrivers(team.getId());

        if (drivers.size() < 2 || reserves.isEmpty()) {
            System.out.println("Dados insuficientes para substituição!");
            return;
        }

        var driverOut = drivers.get(0);
        var driverIn = reserves.get(0);

        // Registrar substituição
        int subId = dbManager.getDriverSubstitutionDAO().insertSubstitution(
            team.getId(),
            driverOut.getId(),
            driverIn.getId(),
            "Lesão muscular - Indisponível por 2 corridas",
            null  // null = não específico de uma corrida
        );

        // ALERTA COM EMOJI
        System.out.println("\n⚠️  ALERTA DE SUBSTITUIÇÃO DE PILOTO! ⚠️");
        System.out.println("=".repeat(60));
        System.out.println("🏁 Equipe: " + team.getName());
        System.out.println("❌ Piloto removido: " + driverOut.getName() + " (#" + driverOut.getCarNumber() + ")");
        System.out.println("✅ Piloto adicionado: " + driverIn.getName() + " (#" + driverIn.getCarNumber() + ")");
        System.out.println("📋 Motivo: Lesão muscular - Indisponível por 2 corridas");
        System.out.println("⏰ Data: " + java.time.LocalDateTime.now());
        System.out.println("=".repeat(60));

        // Listar histórico de substituições
        var substitutions = dbManager.getDriverSubstitutionDAO()
            .getTeamSubstitutions(team.getId());

        if (!substitutions.isEmpty()) {
            System.out.println("\n📜 Histórico de substituições da " + team.getName() + ":");
            for (var sub : substitutions) {
                System.out.printf("  • %s → %s (Motivo: %s)\n",
                    sub.get("driver_removed_name"),
                    sub.get("driver_added_name"),
                    sub.get("reason")
                );
            }
        }
    }

    /**
     * Exemplo 6: Atualizar pontos de um piloto
     */
    private static void exampleUpdateDriverPoints(DatabaseManager dbManager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXEMPLO 6: Atualizar pontos de piloto");
        System.out.println("=".repeat(60));

        var drivers = dbManager.getDriverDAO().getAllDrivers();
        if (drivers.isEmpty()) {
            System.out.println("Nenhum piloto encontrado!");
            return;
        }

        var driver = drivers.get(0);
        System.out.println("\n📊 Piloto: " + driver.getName());
        System.out.println("Pontos antes: " + driver.getPointsOfSeason());

        // Atualizar pontos (adicionar 25)
        dbManager.getDriverDAO().updateDriverPoints(driver.getId(), 25);

        // Buscar novamente para ver atualização
        var updatedDriver = dbManager.getDriverDAO().getDriverById(driver.getId());
        System.out.println("Pontos depois: " + updatedDriver.getPointsOfSeason());
        System.out.println("✓ Pontos atualizados com sucesso!");
    }
}

