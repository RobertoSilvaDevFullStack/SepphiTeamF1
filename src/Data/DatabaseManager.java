package Data;

import java.sql.Connection;

public class DatabaseManager {

    private ConnectDB connectDB;
    private TeamDAO teamDAO;
    private DriverDAO driverDAO;
    private EngineerDAO engineerDAO;
    private CarDAO carDAO;
    private RaceDAO raceDAO;
    private RaceResultDAO raceResultDAO;
    private DriverSubstitutionDAO driverSubstitutionDAO;
    private ChampionshipStandingDAO championshipStandingDAO;

    public DatabaseManager(String hostname, String database, String user, String password) {
        try {
            this.connectDB = new ConnectDB(hostname, user, password, database);
            this.teamDAO = new TeamDAO(connectDB);
            this.driverDAO = new DriverDAO(connectDB);
            this.engineerDAO = new EngineerDAO(connectDB);
            this.carDAO = new CarDAO(connectDB);
            this.raceDAO = new RaceDAO(connectDB);
            this.raceResultDAO = new RaceResultDAO(connectDB);
            this.driverSubstitutionDAO = new DriverSubstitutionDAO(connectDB);
            this.championshipStandingDAO = new ChampionshipStandingDAO(connectDB);
            System.out.println("✓ Database Manager inicializado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar Database Manager: " + e.getMessage());
        }
    }

    // Getters para todos os DAOs
    public TeamDAO getTeamDAO() {
        return teamDAO;
    }

    public DriverDAO getDriverDAO() {
        return driverDAO;
    }

    public EngineerDAO getEngineerDAO() {
        return engineerDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public RaceDAO getRaceDAO() {
        return raceDAO;
    }

    public RaceResultDAO getRaceResultDAO() {
        return raceResultDAO;
    }

    public DriverSubstitutionDAO getDriverSubstitutionDAO() {
        return driverSubstitutionDAO;
    }

    public ChampionshipStandingDAO getChampionshipStandingDAO() {
        return championshipStandingDAO;
    }

    public ConnectDB getConnectDB() {
        return connectDB;
    }

    // Método para testar conexão
    public boolean testConnection() {
        try {
            Connection conn = connectDB.getConnection();
            if (conn != null) {
                conn.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao testar conexão: " + e.getMessage());
        }
        return false;
    }

    // Método para limpar todas as tabelas
    public void clearDatabase() {
        try (Connection conn = connectDB.getConnection();
             var stmt = conn.createStatement()) {

            System.out.println("🗑️  Limpando banco de dados...");

            // Deletar em ordem de dependência (sem desabilitar triggers)
            stmt.executeUpdate("DELETE FROM driver_substitutions");
            stmt.executeUpdate("DELETE FROM race_results");
            stmt.executeUpdate("DELETE FROM cars");
            stmt.executeUpdate("DELETE FROM engineers");
            stmt.executeUpdate("DELETE FROM drivers");
            stmt.executeUpdate("DELETE FROM team_bosses");
            stmt.executeUpdate("DELETE FROM championship_standings");
            stmt.executeUpdate("DELETE FROM races");
            stmt.executeUpdate("DELETE FROM teams");

            System.out.println("✓ Banco de dados limpo com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao limpar banco de dados: " + e.getMessage());
        }
    }
}
