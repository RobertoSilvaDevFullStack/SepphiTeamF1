package Models;

import java.util.ArrayList;
import java.util.Random;

public class Race {

    private int id;
    private String circuitName;
    private String country;
    private String flag;
    private String emoji;
    private int roundNumber;
    private ArrayList<Car> cars;
    public int[] classification;
    private Random random;

    public Race(String circuitName, String country) {
        this.id = -1;
        this.roundNumber = -1;
        this.circuitName = circuitName;
        this.country = country;
        this.flag = "";
        this.emoji = "";
        this.cars = new ArrayList<>();
        this.random = new Random();
    }

    public Race(String circuitName, String country, String flag) {
        this.id = -1;
        this.roundNumber = -1;
        this.circuitName = circuitName;
        this.country = country;
        this.flag = flag;
        this.emoji = flag;
        this.cars = new ArrayList<>();
        this.random = new Random();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getName() {
        return circuitName;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public String getCountry() {
        return country;
    }

    public String getFlag() {
        return flag;
    }

    public String getCircuitNameWithFlag() {
        return flag.isEmpty() ? circuitName : flag + " " + circuitName;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void startRace() {
        if (cars == null || cars.isEmpty()) return;

        if (classification == null || classification.length != cars.size()) {
            classification = new int[cars.size()];
        }

        // Adiciona variação aleatória no coeficiente aerodinâmico antes da corrida
        // Simula ajustes de setup, condições climáticas, etc.
        for (Car car : cars) {
            double coeficienteBase = car.getAerodynamicCoefficient();
            // Aumentado de ±5% para ±15% para maior imprevisibilidade
            double variacao = 0.85 + (random.nextDouble() * 0.30); // 0.85 a 1.15
            car.setAerodynamicCoefficient(coeficienteBase * variacao);

            // Cada piloto pratica e recebe novo handicap
            car.getAccountableDriver().Practice();
        }

        // Ordena cars por desempenho (maior desempenho primeiro)
        cars.sort((a, b) -> {
            double efficiencyA = a.calculateEfficiency(a.getAccountableDriver().getHandicap());
            double efficiencyB = b.calculateEfficiency(b.getAccountableDriver().getHandicap());
            return Double.compare(efficiencyB, efficiencyA);
        });

        // Preenche a classificação com o número dos pilotos
        for (int i = 0; i < cars.size(); i++) {
            classification[i] = cars.get(i).getAccountableDriver().carNumber;
        }
    }

    public void showRaceInfo() {
        System.out.println("\n>>> Corrida: " + circuitName + " (" + country + ")");
        System.out.println(">>> Total de carros: " + cars.size());
    }

    public void showResults() {
        System.out.println("\n>>> RESULTADOS DA CORRIDA <<<");
        System.out.println("=".repeat(50));

        for (int i = 0; i < classification.length; i++) {
            Car car = cars.get(i);
            Driver driver = car.getAccountableDriver();
            double efficiency = car.calculateEfficiency(driver.getHandicap());

            System.out.printf("%d - #%d %s | Handicap: %d | Eficiencia: %.2f\n",
                    (i + 1),
                    driver.carNumber,
                    driver.getName(),
                    driver.getHandicap(),
                    efficiency
            );
        }
        System.out.println("=".repeat(50));
    }

    public void updatePointsOnSeason() {
        for (int i = 0; i < cars.size(); i++) {
            Driver driver = cars.get(i).getAccountableDriver();
            driver.saveResult(i + 1); // Posição começa em 1
        }
    }

}
