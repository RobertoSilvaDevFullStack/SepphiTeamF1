package Models;

import java.util.ArrayList;

public class Team {

    private int id;
    private String name;
    private String citizenship;
    public ArrayList<Car> cars;
    public ArrayList<TeamMember> members;
    private TeamBoss boss;

    public Team(String name, String citizenship) {
        this.id = -1; // -1 indica que não foi salvo no banco ainda
        this.name = name;
        this.citizenship = citizenship;
        this.boss = boss;
        this.cars = new ArrayList<Car>();
        this.members = new ArrayList<TeamMember>();
        this.members.add(boss);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void addMember(TeamMember member) {
        this.members.add(member);
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void showTeam(){
        System.out.println("--------------------------");
        System.out.println("Nome do Time: " + this.name);
        System.out.println("Nacionalidade: " + this.citizenship);
        System.out.println("--------------------------");

        for(int i = 0; i < this.cars.size(); i++) {
            System.out.println("Carro " + (i + 1) + ":");
            this.cars.get(i).showInfo();
        }

        for (int i = 0; i < this.members.size(); i++) {
//            System.out.println("Piloto " + (i + 1) + ":");
            this.members.get(i).showInfo();
        }
    }

    public int calculatePointsOnSeason() {
        int totalPoints = 0;

        for(int i = 0; i < members.size(); i++) {
            if(members.get(i) instanceof Driver) {
                totalPoints += ((Driver) members.get(i)).getPointsOfSeason();
            }
        }
        return totalPoints;
    }
}
