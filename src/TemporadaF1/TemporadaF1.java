// src/TemporadaF1/TemporadaF1.java
package TemporadaF1;

import Models.Car;
import Models.Driver;
import Models.Team;
import Models.TeamMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TemporadaF1 {

    private final Scanner scanner = new Scanner(System.in);

    public Driver CreateDriver() {
        System.out.println("Bem vindo ao Sepphi Team F1");
        System.out.println("---------------------");

        System.out.print("Informe o nome do piloto: ");
        String name = scanner.nextLine().trim();

        System.out.print("Informe a idade do piloto: ");
        int age = parseIntLine("idade");

        System.out.print("Informe o salário do piloto: ");
        double wage = parseDoubleLine("salário");

        System.out.print("Informe o número que o piloto tem na F1: ");
        int carNumber = parseIntLine("número do carro");

        // Os últimos dois parâmetros são inicializados como 0 (ex.: pontos, vitórias)
        return new Driver(name, age, wage, carNumber, 0, 0);
    }

    public Team CreateTeam() {
        System.out.print("Informe o nome do time: ");
        String name = scanner.nextLine().trim();

        System.out.print("Informe a nacionalidade do time: ");
        String citizenship = scanner.nextLine().trim();

        System.out.print("Informe o nome do boss do time: ");
        String bossName = scanner.nextLine().trim();

        // Solicita dados do boss (idade e salário) e cria um TeamMember
        System.out.print("Informe a idade do boss: ");
        int bossAge = parseIntLine("idade do boss");

        System.out.print("Informe o salário do boss: ");
        double bossWage = parseDoubleLine("salário do boss");

        Team team = new Team(name, citizenship);
        TeamMember bossMember = new TeamMember(bossName, bossAge, bossWage);

        // Se a classe Team tiver método para adicionar membro, pode ser chamado aqui.
        // Ex.: team.addMember(bossMember);
        System.out.println("Time criado com sucesso. Boss cadastrado como membro da equipe.");

        return team;
    }

    public Car CreateCar() {
        System.out.print("Informe o modelo do carro: ");
        String model = scanner.nextLine().trim();

        System.out.print("Informe a potencia do carro: ");
        int potencia = parseIntLine("potência");

        System.out.print("Informe o coeficiente aerodinamico do carro: ");
        double coeficiente = parseDoubleLine("coeficiente");

        System.out.print("Informe o numero do carro: ");
        int carNumber = parseIntLine("número do carro");

        // Se a classe Car tiver construtor que aceita número do carro, ajustar conforme necessário.
        Car car = new Car(model, potencia, coeficiente);
        System.out.println("Carro criado com sucesso.");

        return car;
    }

    private int parseIntLine(String fieldName) {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (Exception e) {
                System.out.print("Valor inválido para " + fieldName + ". Tente novamente: ");
            }
        }
    }

    private double parseDoubleLine(String fieldName) {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (Exception e) {
                System.out.print("Valor inválido para " + fieldName + ". Tente novamente: ");
            }
        }
    }

    public static void main(String[] args) {
        TemporadaF1 app = new TemporadaF1();
        List<Driver> drivers = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        Scanner menuScanner = app.scanner;
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu Temporada F1 ---");
            System.out.println("1 - Criar piloto");
            System.out.println("2 - Criar time");
            System.out.println("3 - Criar carro");
            System.out.println("4 - Listar contagens (pilotos, times, carros)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String option = menuScanner.nextLine().trim();
            switch (option) {
                case "1":
                    Driver d = app.CreateDriver();
                    drivers.add(d);
                    System.out.println("Piloto adicionado.");
                    break;
                case "2":
                    Team t = app.CreateTeam();
                    teams.add(t);
                    System.out.println("Time adicionado.");
                    break;
                case "3":
                    Car c = app.CreateCar();
                    cars.add(c);
                    System.out.println("Carro adicionado.");
                    break;
                case "4":
                    System.out.println("Pilotos cadastrados: " + drivers.size());
                    System.out.println("Times cadastrados: " + teams.size());
                    System.out.println("Carros cadastrados: " + cars.size());
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        System.out.println("Encerrando aplicação.");
    }

}
