package Main;

import Data.DatabaseManager;
import Data.DatabaseInitializer;
import TemporadaF1.SimulacaoCorrida;
import java.util.Scanner;

/**
 * Classe Main atualizada para usar o novo sistema de persistência de dados
 * Esta é a entrada principal do programa
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║       🏁 SEPPHI TEAM F1 - SIMULADOR DE TEMPORADA 2025 🏁     ║");
        System.out.println("║                    Versão 2.0 (Com Banco de Dados)          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // ==================================================================
        // 1. INICIALIZAR CONEXÃO COM BANCO DE DADOS
        // ==================================================================
        System.out.println("⏳ Conectando ao banco de dados...\n");

        DatabaseManager dbManager = new DatabaseManager(
            "dpg-d3rcb58gjchc73cpjjdg-a.oregon-postgres.render.com",
            "bdf1",
            "bdf1",
            "fYQe1oWVq7RkbtnA9qKMQP5ZI8AfI9yr"
        );

        // Testar conexão
        if (!dbManager.testConnection()) {
            System.out.println("\n❌ ERRO: Não foi possível conectar ao banco de dados!");
            System.out.println("Verifique suas credenciais e a conexão com a internet.\n");
            return;
        }

        System.out.println("\n✅ Conexão com banco de dados estabelecida com sucesso!\n");

        // ==================================================================
        // 2. PERGUNTAR SE DESEJA POPULAR O BANCO (primeira execução)
        // ==================================================================

        Scanner scanner = new Scanner(System.in);
        boolean shouldInitialize = false;

        System.out.println("🤔 É a primeira vez que você executa este programa?");
        System.out.println("   (O banco será populado com equipes F1, pilotos, etc)");
        System.out.print("\n   Deseja popular o banco agora? (s/n): ");

        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("s") || response.equals("sim")) {
            shouldInitialize = true;
        }

        if (shouldInitialize) {
            // Limpar banco antes de popular
            dbManager.clearDatabase();
            initializeDatabase(dbManager);
        }

        // ==================================================================
        // 3. INICIAR SIMULAÇÃO
        // ==================================================================

        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 Iniciando simulação da temporada F1 2025...\n");

        try {
            SimulacaoCorrida simulacao = new SimulacaoCorrida(dbManager);
            simulacao.menuPrincipal();
        } catch (Exception e) {
            System.out.println("\n❌ Erro durante a simulação: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("👋 Obrigado por usar Sepphi Team F1!");
        System.out.println("   Seus dados foram salvos no banco de dados.\n");

        scanner.close();
    }

    /**
     * Inicializa o banco de dados com dados padrão
     */
    private static void initializeDatabase(DatabaseManager dbManager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔄 POPULANDO O BANCO DE DADOS");
        System.out.println("=".repeat(60));

        try {
            DatabaseInitializer initializer = new DatabaseInitializer(dbManager);

            // Carregar equipes, pilotos e engenheiros
            System.out.println("\n📥 Carregando equipes F1 2025...");
            initializer.initializeDefaultData();

            // Carregar calendário de corridas
            System.out.println("\n📅 Carregando calendário de corridas...");
            initializer.initializeRaceCalendar();

            // Inicializar classificações
            System.out.println("\n📊 Inicializando classificações...");
            initializer.initializeChampionshipStandings();

            System.out.println("\n✅ Banco de dados populado com sucesso!");
            System.out.println("   • 5 Equipes F1");
            System.out.println("   • 10 Pilotos principais + Reservas");
            System.out.println("   • 20 Engenheiros técnicos");
            System.out.println("   • 10 Carros");
            System.out.println("   • 24 Corridas do calendário");
            System.out.println("   • Classificações inicializadas");

        } catch (Exception e) {
            System.out.println("\n❌ Erro ao popular banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

