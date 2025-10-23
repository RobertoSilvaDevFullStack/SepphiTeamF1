package Data;

import Models.*;
import java.util.List;

public class DatabaseInitializer {

    private DatabaseManager dbManager;

    public DatabaseInitializer(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * Inicializa o banco de dados com dados padrão das equipes da F1 2025
     */
    public void initializeDefaultData() {
        System.out.println("\n🏁 Iniciando população do banco de dados com equipes F1 2025...\n");

        // Red Bull Racing
        initializeRedBull();

        // Ferrari
        initializeFerrari();

        // Mercedes
        initializeMercedes();

        // McLaren
        initializeMcLaren();

        // Aston Martin
        initializeAstonMartin();

        System.out.println("\n✓ Banco de dados inicializado com sucesso!\n");
    }

    private void initializeRedBull() {
        Team redBull = new Team("Red Bull Racing", "Áustria");
        int teamId = dbManager.getTeamDAO().insertTeam(redBull);
        redBull.setId(teamId);

        // Chefe
        TeamBoss boss = new TeamBoss("Christian Horner", 51, 8000000);
        boss.setTeamId(teamId);

        // Pilotos
        Driver max = new Driver("Max Verstappen", 27, 50000000, 1, 0, 0);
        max.setTeamId(teamId);
        int maxId = dbManager.getDriverDAO().insertDriver(max, teamId);
        max.setId(maxId);

        Driver yuki = new Driver("Yuki Tsunoda", 25, 5000000, 22, 0, 0);
        yuki.setTeamId(teamId);
        int yukiId = dbManager.getDriverDAO().insertDriver(yuki, teamId);
        yuki.setId(yukiId);

        // Piloto Reserva
        Driver reserve = new Driver("Ayumu Iwasa", 23, 500000, 40, 0, 0);
        reserve.setTeamId(teamId);
        // Inserir como reserva seria necessário marcar no banco após inserção

        // Engenheiros
        Engineer eng1 = new Engineer("Gianpiero Lambiase", 55, 5000000, "Engenheiro de Pista", 20);
        eng1.setTeamId(teamId);
        dbManager.getEngineerDAO().insertEngineer(eng1, teamId);

        Engineer eng2 = new Engineer("Pierre Gasé", 48, 3500000, "Engenheiro de Aerodinâmica", 15);
        eng2.setTeamId(teamId);
        dbManager.getEngineerDAO().insertEngineer(eng2, teamId);

        // Carros
        Car car1 = new Car("RB21", 1050, 0.92);
        car1.setTeamId(teamId);
        int car1Id = dbManager.getCarDAO().insertCar(car1, teamId);
        dbManager.getCarDAO().assignCarToDriver(car1Id, maxId);

        Car car2 = new Car("RB21", 1050, 0.92);
        car2.setTeamId(teamId);
        int car2Id = dbManager.getCarDAO().insertCar(car2, teamId);
        dbManager.getCarDAO().assignCarToDriver(car2Id, yukiId);

        System.out.println("✓ Red Bull Racing carregada com sucesso");
    }

    private void initializeFerrari() {
        Team ferrari = new Team("Ferrari", "Itália");
        int teamId = dbManager.getTeamDAO().insertTeam(ferrari);
        ferrari.setId(teamId);

        // Chefe
        TeamBoss boss = new TeamBoss("Frédéric Vasseur", 56, 7500000);
        boss.setTeamId(teamId);

        // Pilotos
        Driver charles = new Driver("Charles Leclerc", 27, 32000000, 16, 0, 0);
        charles.setTeamId(teamId);
        int charlesId = dbManager.getDriverDAO().insertDriver(charles, teamId);
        charles.setId(charlesId);

        Driver lewis = new Driver("Lewis Hamilton", 40, 45000000, 44, 0, 0);
        lewis.setTeamId(teamId);
        int lewisId = dbManager.getDriverDAO().insertDriver(lewis, teamId);
        lewis.setId(lewisId);

        // Engenheiros
        Engineer eng1 = new Engineer("Jock Clear", 58, 4500000, "Engenheiro de Pista", 25);
        eng1.setTeamId(teamId);
        dbManager.getEngineerDAO().insertEngineer(eng1, teamId);

        Engineer eng2 = new Engineer("Dirk de Beer", 52, 3000000, "Engenheiro de Aerodinâmica", 18);
        eng2.setTeamId(teamId);
        dbManager.getEngineerDAO().insertEngineer(eng2, teamId);

        // Carros
        Car car1 = new Car("SF-25", 1040, 0.91);
        car1.setTeamId(teamId);
        int car1Id = dbManager.getCarDAO().insertCar(car1, teamId);
        dbManager.getCarDAO().assignCarToDriver(car1Id, charlesId);

        Car car2 = new Car("SF-25", 1040, 0.91);
        car2.setTeamId(teamId);
        int car2Id = dbManager.getCarDAO().insertCar(car2, teamId);
        dbManager.getCarDAO().assignCarToDriver(car2Id, lewisId);

        System.out.println("✓ Ferrari carregada com sucesso");
    }

    private void initializeMercedes() {
        Team mercedes = new Team("Mercedes-AMG", "Alemanha");
        int teamId = dbManager.getTeamDAO().insertTeam(mercedes);
        mercedes.setId(teamId);

        // Chefe
        TeamBoss boss = new TeamBoss("Toto Wolff", 52, 8500000);
        boss.setTeamId(teamId);

        // Pilotos
        Driver george = new Driver("George Russell", 27, 20000000, 63, 0, 0);
        george.setTeamId(teamId);
        int georgeId = dbManager.getDriverDAO().insertDriver(george, teamId);
        george.setId(georgeId);

        Driver kimi = new Driver("Andrea Kimi Antonelli", 19, 2000000, 12, 0, 0);
        kimi.setTeamId(teamId);
        int kimiId = dbManager.getDriverDAO().insertDriver(kimi, teamId);
        kimi.setId(kimiId);

        // Engenheiros
        Engineer eng1 = new Engineer("Andrew Shovlin", 54, 5000000, "Engenheiro de Pista", 22);
        eng1.setTeamId(teamId);
        dbManager.getEngineerDAO().insertEngineer(eng1, teamId);

        // Carros
        Car car1 = new Car("W16", 1035, 0.89);
        car1.setTeamId(teamId);
        int car1Id = dbManager.getCarDAO().insertCar(car1, teamId);
        dbManager.getCarDAO().assignCarToDriver(car1Id, georgeId);

        Car car2 = new Car("W16", 1035, 0.89);
        car2.setTeamId(teamId);
        int car2Id = dbManager.getCarDAO().insertCar(car2, teamId);
        dbManager.getCarDAO().assignCarToDriver(car2Id, kimiId);

        System.out.println("✓ Mercedes-AMG carregada com sucesso");
    }

    private void initializeMcLaren() {
        Team mclaren = new Team("McLaren", "Reino Unido");
        int teamId = dbManager.getTeamDAO().insertTeam(mclaren);
        mclaren.setId(teamId);

        // Chefe
        TeamBoss boss = new TeamBoss("Andrea Stella", 53, 6000000);
        boss.setTeamId(teamId);

        // Pilotos
        Driver lando = new Driver("Lando Norris", 25, 15000000, 4, 0, 0);
        lando.setTeamId(teamId);
        int landoId = dbManager.getDriverDAO().insertDriver(lando, teamId);
        lando.setId(landoId);

        Driver oscar = new Driver("Oscar Piastri", 23, 8000000, 81, 0, 0);
        oscar.setTeamId(teamId);
        int oscarId = dbManager.getDriverDAO().insertDriver(oscar, teamId);
        oscar.setId(oscarId);

        // Engenheiros
        Engineer eng1 = new Engineer("Rob Marshall", 52, 4000000, "Engenheiro de Pista", 18);
        eng1.setTeamId(teamId);
        dbManager.getEngineerDAO().insertEngineer(eng1, teamId);

        // Carros
        Car car1 = new Car("MCL38", 1045, 0.90);
        car1.setTeamId(teamId);
        int car1Id = dbManager.getCarDAO().insertCar(car1, teamId);
        dbManager.getCarDAO().assignCarToDriver(car1Id, landoId);

        Car car2 = new Car("MCL38", 1045, 0.90);
        car2.setTeamId(teamId);
        int car2Id = dbManager.getCarDAO().insertCar(car2, teamId);
        dbManager.getCarDAO().assignCarToDriver(car2Id, oscarId);

        System.out.println("✓ McLaren carregada com sucesso");
    }

    private void initializeAstonMartin() {
        Team aston = new Team("Aston Martin", "Reino Unido");
        int teamId = dbManager.getTeamDAO().insertTeam(aston);
        aston.setId(teamId);

        // Chefe
        TeamBoss boss = new TeamBoss("Mike Krack", 51, 5500000);
        boss.setTeamId(teamId);

        // Pilotos
        Driver fernando = new Driver("Fernando Alonso", 43, 25000000, 14, 0, 0);
        fernando.setTeamId(teamId);
        int fernandoId = dbManager.getDriverDAO().insertDriver(fernando, teamId);
        fernando.setId(fernandoId);

        Driver lance = new Driver("Lance Stroll", 25, 12000000, 18, 0, 0);
        lance.setTeamId(teamId);
        int lanceId = dbManager.getDriverDAO().insertDriver(lance, teamId);
        lance.setId(lanceId);

        // Carros
        Car car1 = new Car("AMR25", 1038, 0.89);
        car1.setTeamId(teamId);
        int car1Id = dbManager.getCarDAO().insertCar(car1, teamId);
        dbManager.getCarDAO().assignCarToDriver(car1Id, fernandoId);

        Car car2 = new Car("AMR25", 1038, 0.89);
        car2.setTeamId(teamId);
        int car2Id = dbManager.getCarDAO().insertCar(car2, teamId);
        dbManager.getCarDAO().assignCarToDriver(car2Id, lanceId);

        System.out.println("✓ Aston Martin carregada com sucesso");
    }

    /**
     * Inicializa o calendário de corridas F1 2025
     */
    public void initializeRaceCalendar() {
        System.out.println("\n🏁 Inicializando calendário de corridas...\n");

        String[][] calendario = {
            {"GP do Bahrein", "Bahrein", "🇧🇭"},
            {"GP da Arabia Saudita", "Arabia Saudita", "🇸🇦"},
            {"GP da Australia", "Australia", "🇦🇺"},
            {"GP do Japao", "Japao", "🇯🇵"},
            {"GP da China", "China", "🇨🇳"},
            {"GP de Miami", "Estados Unidos", "🇺🇸"},
            {"GP da Emilia-Romagna", "Italia", "🇮🇹"},
            {"GP de Monaco", "Monaco", "🇲🇨"},
            {"GP do Canada", "Canada", "🇨🇦"},
            {"GP da Espanha", "Espanha", "🇪🇸"},
            {"GP da Austria", "Austria", "🇦🇹"},
            {"GP da Gra-Bretanha", "Reino Unido", "🇬🇧"},
            {"GP da Hungria", "Hungria", "🇭🇺"},
            {"GP da Belgica", "Belgica", "🇧🇪"},
            {"GP dos Paises Baixos", "Holanda", "🇳🇱"},
            {"GP da Italia", "Italia", "🇮🇹"},
            {"GP do Azerbaijao", "Azerbaijao", "🇦🇿"},
            {"GP de Singapura", "Singapura", "🇸🇬"},
            {"GP dos Estados Unidos", "Estados Unidos", "🇺🇸"},
            {"GP do Mexico", "Mexico", "🇲🇽"},
            {"GP de Sao Paulo", "Brasil", "🇧🇷"},
            {"GP de Las Vegas", "Estados Unidos", "🇺🇸"},
            {"GP do Catar", "Catar", "🇶🇦"},
            {"GP de Abu Dhabi", "Emirados Arabes Unidos", "🇦🇪"}
        };

        for (int i = 0; i < calendario.length; i++) {
            Race race = new Race(calendario[i][0], calendario[i][1], calendario[i][2]);
            race.setRoundNumber(i + 1);
            dbManager.getRaceDAO().insertRace(race);
        }

        System.out.println("✓ " + calendario.length + " corridas carregadas com sucesso!\n");
    }

    /**
     * Inicializa as classificações das equipes no campeonato
     */
    public void initializeChampionshipStandings() {
        System.out.println("\n🏆 Inicializando classificações do campeonato...\n");

        List<Team> teams = dbManager.getTeamDAO().getAllTeams();
        for (Team team : teams) {
            dbManager.getChampionshipStandingDAO().upsertStanding(team.getId(), 0, 0, 2025);
        }

        System.out.println("✓ Classificações inicializadas com sucesso!\n");
    }
}

