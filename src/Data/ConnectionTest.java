package Data;

import java.sql.Connection;

/**
 * Classe para testar a conexão com o banco de dados PostgreSQL
 */
public class ConnectionTest {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           🧪 TESTE DE CONEXÃO - PostgreSQL Render          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Credenciais do Render
        String hostname = "dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com";
        String database = "bdf1";
        String user = "bdf1";
        String password = "fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr";

        System.out.println("📋 Informações de Conexão:");
        System.out.println("   Host: " + hostname);
        System.out.println("   Database: " + database);
        System.out.println("   User: " + user);
        System.out.println("   Password: ✓ (configurada)\n");

        // Criar DatabaseManager
        System.out.println("⏳ Conectando ao banco de dados...\n");
        DatabaseManager dbManager = new DatabaseManager(hostname, database, user, password);

        // Testar conexão
        if (dbManager.testConnection()) {
            System.out.println("✅ CONEXÃO ESTABELECIDA COM SUCESSO!\n");

            // Testar se as tabelas existem
            System.out.println("🔍 Verificando tabelas do banco...\n");
            testTables(dbManager);

        } else {
            System.out.println("❌ FALHA NA CONEXÃO!");
            System.out.println("\nVerifique:");
            System.out.println("  1. Se o banco PostgreSQL está online no Render");
            System.out.println("  2. Se as credenciais estão corretas");
            System.out.println("  3. Se você tem conexão com a internet");
            System.out.println("  4. Se o database_schema.sql foi executado\n");
        }
    }

    private static void testTables(DatabaseManager dbManager) {
        try {
            // Teste 1: Buscar equipes
            var teams = dbManager.getTeamDAO().getAllTeams();
            System.out.println("  ✓ Tabela 'teams' - " + teams.size() + " equipes encontradas");

            // Teste 2: Buscar pilotos
            var drivers = dbManager.getDriverDAO().getAllDrivers();
            System.out.println("  ✓ Tabela 'drivers' - " + drivers.size() + " pilotos encontrados");

            // Teste 3: Buscar engenheiros
            var engineers = dbManager.getEngineerDAO().getAllEngineers();
            System.out.println("  ✓ Tabela 'engineers' - " + engineers.size() + " engenheiros encontrados");

            // Teste 4: Buscar carros
            var cars = dbManager.getCarDAO().getAllCars();
            System.out.println("  ✓ Tabela 'cars' - " + cars.size() + " carros encontrados");

            // Teste 5: Buscar corridas
            var races = dbManager.getRaceDAO().getAllRaces();
            System.out.println("  ✓ Tabela 'races' - " + races.size() + " corridas encontradas");

            System.out.println("\n" + "=".repeat(60));
            System.out.println("✅ TODOS OS TESTES PASSARAM COM SUCESSO!");
            System.out.println("=".repeat(60));

            System.out.println("\n📊 Resumo dos Dados no Banco:");
            System.out.println("  • Equipes: " + teams.size());
            System.out.println("  • Pilotos: " + drivers.size());
            System.out.println("  • Engenheiros: " + engineers.size());
            System.out.println("  • Carros: " + cars.size());
            System.out.println("  • Corridas: " + races.size());

            if (teams.size() > 0) {
                System.out.println("\n🏁 Equipes carregadas:");
                for (var team : teams) {
                    System.out.println("   - " + team.getName() + " (" + team.getCitizenship() + ")");
                }
            }

            System.out.println("\n" + "=".repeat(60));
            System.out.println("✨ Seu projeto está pronto para usar!");
            System.out.println("=".repeat(60));

        } catch (Exception e) {
            System.out.println("\n❌ Erro ao testar tabelas:");
            System.out.println("   " + e.getMessage());
            e.printStackTrace();
        }
    }
}

