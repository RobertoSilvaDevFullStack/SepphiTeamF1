package TemporadaF1;

import Models.*;
import Data.DatabaseManager;

import java.util.ArrayList;
import java.util.Scanner;

public class SimulacaoCorrida {

    private ArrayList<Team> teams;
    private ArrayList<Race> races;
    private Scanner scanner;
    private DatabaseManager dbManager;

    public SimulacaoCorrida() {
        this(null);
    }

    public SimulacaoCorrida(DatabaseManager dbManager) {

        this.teams = new ArrayList<>();
        this.races = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dbManager = dbManager;

        // Se tiver acesso ao banco, carregar dados de lá
        if (dbManager != null && dbManager.testConnection()) {
            carregarDadosDosBanco();
        } else {
            // Fallback: carregar dados hardcoded (apenas para testes offline)
            inicializarEquipes();
        }
    }

    private void carregarDadosDosBanco() {
        System.out.println("[*] Carregando dados do banco de dados...\n");

        // Carregar todas as equipes do banco
        var teamsFromDb = dbManager.getTeamDAO().getAllTeams();
        teams = new ArrayList<>(teamsFromDb);

        if (teams.isEmpty()) {
            System.out.println("⚠️  Nenhuma equipe encontrada no banco!");
            System.out.println("    Carregando dados padrão...\n");
            inicializarEquipes();
            return;
        }

        // Carregar pilotos, carros e engenheiros para cada equipe
        for (Team team : teams) {
            // Carregar pilotos
            var drivers = dbManager.getDriverDAO().getDriversByTeam(team.getId());
            ArrayList<Driver> mainDrivers = new ArrayList<>();
            for (Driver driver : drivers) {
                team.addMember(driver);
                if (!driver.isReserve()) {
                    mainDrivers.add(driver);
                }
            }

            // Carregar carros e associar pilotos
            var cars = dbManager.getCarDAO().getCarsByTeam(team.getId());
            int driverIndex = 0;
            for (Car car : cars) {
                team.addCar(car);
                // Associar pilotos principais aos carros (1 piloto por carro)
                if (driverIndex < mainDrivers.size()) {
                    car.setAccountableDriver(mainDrivers.get(driverIndex));
                    driverIndex++;
                }
            }

            // Carregar engenheiros
            var engineers = dbManager.getEngineerDAO().getEngineersByTeam(team.getId());
            for (Engineer engineer : engineers) {
                team.addMember(engineer);
            }
        }

        System.out.println("[+] " + teams.size() + " equipes carregadas do banco!");

        // Carregar todas as corridas do banco
        var racesFromDb = dbManager.getRaceDAO().getRacesByYear(2025);
        for (Race race : racesFromDb) {
            // Adicionar todos os carros na corrida
            for (Team team : teams) {
                for (Car car : team.cars) {
                    race.addCar(car);
                }
            }

            races.add(race);
        }

        System.out.println("[+] " + races.size() + " corridas carregadas do banco!\n");
    }

    private void criarCalendarioCompleto() {
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

        for (String[] corrida : calendario) {
            Race race = new Race(corrida[0], corrida[1], corrida[2]);
            // Adicionar todos os carros na corrida
            for (Team team : teams) {
                for (Car car : team.cars) {
                    race.addCar(car);
                }
            }
            races.add(race);
        }

        System.out.println("[+] " + races.size() + " corridas do calendario criadas!");
    }

    private void inicializarEquipes() {
        // Red Bull Racing
        Team redBull = criarEquipe("Red Bull Racing", "Áustria", "Christian Horner", 51, 8000000);
        adicionarPilotoECarro(redBull, "Max Verstappen", 27, 50000000, 1, "RB21", 1050, 0.92);
        adicionarPilotoECarro(redBull, "Yuki Tsunoda", 25, 5000000, 22, "RB21", 1050, 0.91);
        adicionarPilotoReserva(redBull, "Ayumu Iwasa", 23, 500000, 40);
        adicionarEngenheiros(redBull, "Red Bull");
        teams.add(redBull);

        // Ferrari
        Team ferrari = criarEquipe("Ferrari", "Itália", "Frédéric Vasseur", 56, 7500000);
        adicionarPilotoECarro(ferrari, "Charles Leclerc", 27, 32000000, 16, "SF-25", 1040, 0.91);
        adicionarPilotoECarro(ferrari, "Lewis Hamilton", 40, 45000000, 44, "SF-25", 1040, 0.90);
        adicionarPilotoReserva(ferrari, "Oliver Bearman", 20, 800000, 50);
        adicionarEngenheiros(ferrari, "Ferrari");
        teams.add(ferrari);

        // Mercedes
        Team mercedes = criarEquipe("Mercedes-AMG", "Alemanha", "Toto Wolff", 52, 8500000);
        adicionarPilotoECarro(mercedes, "George Russell", 27, 20000000, 63, "W16", 1035, 0.89);
        adicionarPilotoECarro(mercedes, "Andrea Kimi Antonelli", 19, 2000000, 12, "W16", 1035, 0.88);
        adicionarPilotoReserva(mercedes, "Frederik Vesti", 22, 600000, 48);
        adicionarEngenheiros(mercedes, "Mercedes");
        teams.add(mercedes);

        // McLaren
        Team mclaren = criarEquipe("McLaren", "Reino Unido", "Andrea Stella", 53, 6000000);
        adicionarPilotoECarro(mclaren, "Lando Norris", 25, 25000000, 4, "MCL39", 1045, 0.90);
        adicionarPilotoECarro(mclaren, "Oscar Piastri", 24, 8000000, 81, "MCL39", 1045, 0.89);
        adicionarPilotoReserva(mclaren, "Ryo Hirakawa", 30, 500000, 45);
        adicionarEngenheiros(mclaren, "McLaren");
        teams.add(mclaren);

        // Aston Martin
        Team astonMartin = criarEquipe("Aston Martin", "Reino Unido", "Mike Krack", 52, 5000000);
        adicionarPilotoECarro(astonMartin, "Fernando Alonso", 43, 25000000, 14, "AMR25", 1020, 0.87);
        adicionarPilotoECarro(astonMartin, "Lance Stroll", 26, 10000000, 18, "AMR25", 1020, 0.86);
        adicionarPilotoReserva(astonMartin, "Felipe Drugovich", 24, 700000, 43);
        adicionarEngenheiros(astonMartin, "Aston Martin");
        teams.add(astonMartin);

        // Alpine
        Team alpine = criarEquipe("Alpine", "França", "Oliver Oakes", 37, 4500000);
        adicionarPilotoECarro(alpine, "Pierre Gasly", 29, 8000000, 10, "A525", 1015, 0.85);
        adicionarPilotoECarro(alpine, "Franco Colapinto", 22, 2000000, 45, "A525", 1015, 0.85);
        adicionarPilotoReserva(alpine, "Paul Aron", 21, 500000, 46);
        adicionarEngenheiros(alpine, "Alpine");
        teams.add(alpine);

        // Williams
        Team williams = criarEquipe("Williams", "Reino Unido", "James Vowles", 46, 4000000);
        adicionarPilotoECarro(williams, "Alexander Albon", 29, 5000000, 23, "FW47", 1010, 0.84);
        adicionarPilotoECarro(williams, "Carlos Sainz", 31, 10000000, 55, "FW47", 1010, 0.84);
        adicionarPilotoReserva(williams, "Luke Browning", 20, 400000, 49);
        adicionarEngenheiros(williams, "Williams");
        teams.add(williams);

        // RB (AlphaTauri)
        Team rb = criarEquipe("RB F1 Team", "Itália", "Laurent Mekies", 46, 3500000);
        adicionarPilotoECarro(rb, "Liam Lawson", 23, 3000000, 30, "VCARB 02", 1012, 0.84);
        adicionarPilotoECarro(rb, "Isack Hadjar", 20, 1000000, 6, "VCARB 02", 1012, 0.83);
        adicionarPilotoReserva(rb, "Ayumu Iwasa", 23, 500000, 40);
        adicionarEngenheiros(rb, "RB");
        teams.add(rb);

        // Haas
        Team haas = criarEquipe("Haas F1 Team", "Estados Unidos", "Ayao Komatsu", 52, 3000000);
        adicionarPilotoECarro(haas, "Esteban Ocon", 29, 5000000, 31, "VF-25", 1005, 0.83);
        adicionarPilotoECarro(haas, "Oliver Bearman", 20, 1500000, 87, "VF-25", 1005, 0.83);
        adicionarPilotoReserva(haas, "Robert Shwartzman", 25, 600000, 39);
        adicionarEngenheiros(haas, "Haas");
        teams.add(haas);

        // Kick Sauber (Audi)
        Team sauber = criarEquipe("Kick Sauber", "Suíça", "Mattia Binotto", 55, 5000000);
        adicionarPilotoECarro(sauber, "Nico Hulkenberg", 38, 4000000, 27, "C45", 1008, 0.83);
        adicionarPilotoECarro(sauber, "Gabriel Bortoleto", 20, 1000000, 5, "C45", 1008, 0.82);
        adicionarPilotoReserva(sauber, "Theo Pourchaire", 22, 700000, 47);
        adicionarEngenheiros(sauber, "Sauber");
        teams.add(sauber);

        // Andretti Cadillac
        Team andretti = criarEquipe("Andretti Cadillac", "Estados Unidos", "Michael Andretti", 62, 6000000);
        adicionarPilotoECarro(andretti, "Valtteri Bottas", 36, 5000000, 77, "AC-01", 1025, 0.86);
        adicionarPilotoECarro(andretti, "Sergio Pérez", 35, 8000000, 11, "AC-01", 1025, 0.85);
        adicionarPilotoReserva(andretti, "Colton Herta", 25, 2000000, 26);
        adicionarEngenheiros(andretti, "Andretti");
        teams.add(andretti);

        // Sepphi Team Race
        Team sepphi = criarEquipe("Sepphi Team Race", "Brasil", "Roberto Silva", 45, 5000000);
        adicionarPilotoECarro(sepphi, "Cloud Strife", 28, 15000000, 7, "STR-01", 1030, 0.88);
        adicionarPilotoECarro(sepphi, "Tifa Lockhart", 26, 12000000, 8, "STR-01", 1030, 0.87);
        adicionarPilotoReserva(sepphi, "Sephiroth", 30, 5000000, 9);
        adicionarEngenheiros(sepphi, "Sepphi");
        teams.add(sepphi);

        System.out.println("[+] " + teams.size() + " equipes inicializadas com sucesso!");
        System.out.println("[+] Pilotos reservas cadastrados para todas as equipes!");
    }

    private Team criarEquipe(String nome, String pais, String nomeBoss, int idadeBoss, double salarioBoss) {
        Team team = new Team(nome, pais);
        TeamBoss boss = new TeamBoss(nomeBoss, idadeBoss, salarioBoss);
        team.addMember(boss);
        return team;
    }

    private void adicionarPilotoECarro(Team team, String nomePiloto, int idade, double salario,
                                       int numero, String modeloCarro, int potencia, double coefAero) {
        Driver driver = new Driver(nomePiloto, idade, salario, numero, 0, 0);
        Car car = new Car(modeloCarro, potencia, coefAero);
        car.setAccountableDriver(driver);
        team.addMember(driver);
        team.addCar(car);
    }

    private void adicionarPilotoReserva(Team team, String nomePiloto, int idade, double salario, int numero) {
        Driver reserva = new Driver(nomePiloto, idade, salario, numero, 0, 0);
        team.addMember(reserva);
    }

    private void adicionarEngenheiros(Team team, String nomeEquipe) {
        String[] especialidades = {"Aerodinâmica", "Motor", "Suspensão", "Eletrônica", "Estratégia"};

        for (int i = 0; i < 5; i++) {
            int experiencia = 5 + (int)(Math.random() * 16); // 5 a 20 anos
            Engineer eng = new Engineer(
                "Engenheiro " + especialidades[i] + " " + nomeEquipe,
                30 + (int)(Math.random() * 25), // 30-55 anos
                80000 + (Math.random() * 120000), // 80k-200k
                especialidades[i],
                experiencia
            );
            team.addMember(eng);
        }
    }

    public void melhorarCarros() {
        System.out.println("\n=== MELHORANDO CARROS COM ENGENHEIROS ===");
        for (Team team : teams) {
            for (Car car : team.cars) {
                double performanceInicial = car.getPerformance();

                // Cada engenheiro melhora o carro
                for (TeamMember member : team.members) {
                    if (member instanceof Engineer) {
                        ((Engineer) member).improveCar(car);
                    }
                }

                System.out.printf("%s - Performance: %.2f -> %.2f\n",
                    car.getModel(), performanceInicial, car.getPerformance());
            }
        }
    }

    public void criarCorrida(String nomeCorrida, String pais) {
        Race race = new Race(nomeCorrida, pais);

        // Adicionar todos os carros na corrida
        for (Team team : teams) {
            for (Car car : team.cars) {
                race.addCar(car);
            }
        }

        races.add(race);
        System.out.println("[+] Corrida criada: " + nomeCorrida + " (" + pais + ")");
    }

    public void simularCorrida(int indiceCorrida) {
        if (indiceCorrida < 0 || indiceCorrida >= races.size()) {
            System.out.println("[ERRO] Corrida não encontrada!");
            return;
        }

        Race race = races.get(indiceCorrida);
        System.out.println("\n" + "=".repeat(50));
        System.out.println("[!] >>> INICIANDO CORRIDA <<<");
        System.out.println("=".repeat(50));

        // Chance de substituição antes da corrida
        simularSubstituicaoAleatoria();

        race.startRace();
        race.showRaceInfo();
        race.showResults();
        race.updatePointsOnSeason();

        System.out.println("\n[OK] Corrida finalizada!");
    }

    public void mostrarClassificacaoEquipes() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("*** CLASSIFICACAO DE EQUIPES ***");
        System.out.println("=".repeat(50));

        teams.sort((a, b) -> Integer.compare(b.calculatePointsOnSeason(), a.calculatePointsOnSeason()));

        int posicao = 1;
        for (Team team : teams) {
            System.out.printf("%d. %s - %d pontos\n",
                posicao++, team.getName(), team.calculatePointsOnSeason());
        }
    }

    public void mostrarClassificacaoPilotos() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("*** CLASSIFICACAO DE PILOTOS ***");
        System.out.println("=".repeat(50));

        ArrayList<Driver> drivers = new ArrayList<>();
        for (Team team : teams) {
            for (TeamMember member : team.members) {
                if (member instanceof Driver) {
                    drivers.add((Driver) member);
                }
            }
        }

        drivers.sort((a, b) -> Integer.compare(b.getPointsOfSeason(), a.getPointsOfSeason()));

        int posicao = 1;
        for (Driver driver : drivers) {
            System.out.printf("%d. #%d %s - %d pontos\n",
                posicao++, driver.carNumber, driver.getName(), driver.getPointsOfSeason());
        }
    }

    public void menuPrincipal() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("[F1] >>> SIMULACAO TEMPORADA F1 2025 <<<");
            System.out.println("=".repeat(50));
            System.out.println("[*] 1 - Melhorar carros (engenheiros trabalhando)");
            System.out.println("[+] 2 - Criar nova corrida");
            System.out.println("[!] 3 - Simular corrida");
            System.out.println("[T] 4 - Ver classificacao de equipes");
            System.out.println("[P] 5 - Ver classificacao de pilotos");
            System.out.println("[=] 6 - Ver detalhes de uma equipe");
            System.out.println("[C] 7 - Simular temporada completa (24 corridas)");
            System.out.println("[S] 8 - Substituir piloto manualmente");
            System.out.println("[X] 9 - Limpar lista de corridas");
            System.out.println("[0] 0 - Sair");
            System.out.print("\n>>> Escolha uma opcao: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    melhorarCarros();
                    break;
                case "2":
                    System.out.print("[*] Nome da corrida: ");
                    String nome = scanner.nextLine().trim();
                    System.out.print("[*] País: ");
                    String pais = scanner.nextLine().trim();
                    criarCorrida(nome, pais);
                    break;
                case "3":
                    if (races.isEmpty()) {
                        System.out.println("[!] Nenhuma corrida criada ainda!");
                    } else {
                        System.out.println("\n[!] Corridas disponíveis:");
                        for (int i = 0; i < races.size(); i++) {
                            System.out.println("   " + i + " - " + races.get(i).getCircuitNameWithFlag());
                        }
                        System.out.print("[?] Escolha o número da corrida: ");
                        try {
                            int idx = Integer.parseInt(scanner.nextLine().trim());
                            if (idx < 0 || idx >= races.size()) {
                                System.out.println("❌ Corrida invalida! Escolha um numero entre 0 e " + (races.size() - 1));
                            } else {
                                simularCorrida(idx);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Erro: Digite apenas numeros!");
                        } catch (Exception e) {
                            System.out.println("[ERRO] Erro inesperado ao simular corrida: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    break;
                case "4":
                    mostrarClassificacaoEquipes();
                    break;
                case "5":
                    mostrarClassificacaoPilotos();
                    break;
                case "6":
                    System.out.println("\n[T] Equipes disponíveis:");
                    for (int i = 0; i < teams.size(); i++) {
                        System.out.println("   " + i + " - " + teams.get(i).getName());
                    }
                    System.out.print("[?] Escolha o número da equipe: ");
                    try {
                        int idx = Integer.parseInt(scanner.nextLine().trim());
                        if (idx < 0 || idx >= teams.size()) {
                            System.out.println("[ERRO] Equipe invalida! Escolha um numero entre 0 e " + (teams.size() - 1));
                        } else {
                            teams.get(idx).showTeam();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("[ERRO] Erro: Digite apenas numeros!");
                    } catch (Exception e) {
                        System.out.println("[ERRO] Erro inesperado: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "7":
                    simularTemporadaCompleta();
                    break;
                case "8":
                    menuSubstituirPiloto();
                    break;
                case "9":
                    races.clear();
                    criarCalendarioCompleto();
                    System.out.println("[OK] Lista de corridas limpa e recriada com sucesso!");
                    break;
                case "0":
                    rodando = false;
                    System.out.println("\n[*] Encerrando simulacao. Ate logo!");
                    break;
                default:
                    System.out.println("[ERRO] Opcao invalida!");
            }
        }
    }

    private void menuSubstituirPiloto() {
        System.out.println("\n=== SUBSTITUIR PILOTO ===");
        System.out.println("\nEquipes disponiveis:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println(i + " - " + teams.get(i).getName());
        }
        System.out.print("Escolha o numero da equipe: ");

        try {
            int teamIdx = Integer.parseInt(scanner.nextLine().trim());
            if (teamIdx < 0 || teamIdx >= teams.size()) {
                System.out.println("X Equipe invalida! Escolha um numero entre 0 e " + (teams.size() - 1));
                return;
            }

            Team team = teams.get(teamIdx);
            System.out.println("\nPilotos titulares de " + team.getName() + ":");
            for (int i = 0; i < team.cars.size(); i++) {
                Driver d = team.cars.get(i).getAccountableDriver();
                System.out.println(i + " - #" + d.carNumber + " " + d.getName());
            }

            System.out.print("Escolha o piloto a ser substituido: ");
            int pilotoIdx = Integer.parseInt(scanner.nextLine().trim());

            if (pilotoIdx < 0 || pilotoIdx >= team.cars.size()) {
                System.out.println("X Piloto invalido! Escolha um numero entre 0 e " + (team.cars.size() - 1));
                return;
            }

            System.out.print("Motivo da substituicao: ");
            String motivo = scanner.nextLine().trim();

            substituirPiloto(team, pilotoIdx, motivo);

        } catch (NumberFormatException e) {
            System.out.println("X Erro: Digite apenas numeros!");
        } catch (Exception e) {
            System.out.println("X Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void simularTemporadaCompleta() {
        System.out.println("\n=== SIMULAR CORRIDAS EM SEQUENCIA ===");
        System.out.print("Quantas corridas deseja simular (1-24)? ");

        int quantidadeCorridas;
        try {
            quantidadeCorridas = Integer.parseInt(scanner.nextLine().trim());

            if (quantidadeCorridas < 1 || quantidadeCorridas > 24) {
                System.out.println("X Numero invalido! Escolha entre 1 e 24 corridas.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("X Erro: Digite apenas numeros!");
            return;
        }

        System.out.println("\n>>> SIMULANDO " + quantidadeCorridas + " CORRIDA(S) <<<");
        System.out.println("Deseja melhorar os carros antes de iniciar? (S/N): ");
        String melhorar = scanner.nextLine().trim().toUpperCase();

        if (melhorar.equals("S")) {
            melhorarCarros();
        }

        for (int i = 0; i < quantidadeCorridas && i < races.size(); i++) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println(">>> CORRIDA " + (i + 1) + " DE " + quantidadeCorridas + " <<<");
            System.out.println("-".repeat(50));

            simularCorrida(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Erro na pausa entre corridas: " + e.getMessage());
            }
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("*** " + quantidadeCorridas + " CORRIDA(S) FINALIZADA(S)! ***");
        System.out.println("=".repeat(50));
        mostrarClassificacaoEquipes();
        mostrarClassificacaoPilotos();
    }

    public void substituirPiloto(Team team, int indicePilotoTitular, String motivo) {
        if (team.cars.size() <= indicePilotoTitular) {
            System.out.println("❌ Piloto titular nao encontrado!");
            return;
        }

        Car car = team.cars.get(indicePilotoTitular);
        Driver pilotoTitular = car.getAccountableDriver();

        // Buscar piloto reserva
        Driver pilotoReserva = null;
        for (TeamMember member : team.members) {
            if (member instanceof Driver) {
                Driver d = (Driver) member;
                // Se não está em nenhum carro, é reserva
                boolean emCorrida = false;
                for (Car c : team.cars) {
                    if (c.getAccountableDriver() == d) {
                        emCorrida = true;
                        break;
                    }
                }
                if (!emCorrida) {
                    pilotoReserva = d;
                    break;
                }
            }
        }

        if (pilotoReserva == null) {
            System.out.println("❌ Nenhum piloto reserva disponivel!");
            return;
        }

        // ALERTA DE SUBSTITUIÇÃO COM EMOJIS
        System.out.println("\n" + "⚠".repeat(25));
        System.out.println("🚨 >>> ALERTA: SUBSTITUICAO DE PILOTO! <<< 🚨");
        System.out.println("⚠".repeat(25));
        System.out.println("🏎️  Equipe: " + team.getName());
        System.out.println("📋 Motivo: " + motivo);
        System.out.println("❌ Piloto saindo: #" + pilotoTitular.carNumber + " " + pilotoTitular.getName());
        System.out.println("✅ Piloto entrando: #" + pilotoReserva.carNumber + " " + pilotoReserva.getName());
        System.out.println("⚠".repeat(25) + "\n");

        car.setAccountableDriver(pilotoReserva);
    }

    public void simularSubstituicaoAleatoria() {
        // 10% de chance de haver uma substituição antes da corrida
        if (Math.random() < 0.10) {
            int teamIndex = (int) (Math.random() * teams.size());
            Team team = teams.get(teamIndex);

            if (team.cars.size() > 0) {
                int pilotoIndex = (int) (Math.random() * team.cars.size());
                String[] motivos = {
                    "Lesão", "Doença", "Compromisso pessoal",
                    "Problemas contratuais", "Teste de desempenho"
                };
                String motivo = motivos[(int) (Math.random() * motivos.length)];

                substituirPiloto(team, pilotoIndex, motivo);
            }
        }
    }
}
