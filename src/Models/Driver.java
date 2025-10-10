package Models;

import java.util.Random;

public class Driver extends TeamMember{

    public int carNumber;
    private int handicap;
    private int pointsOfSeason;

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        if(handicap < 100) {
            System.out.println("O handicap não pode ser menor que 0");
            return;
        }
        this.handicap += handicap;
    }

    public int getPointsOfSeason() {
        return pointsOfSeason;
    }

    public void setPointsOfSeason(int points) {
        this.pointsOfSeason += points;
    }

    public Driver(String name, int age, double wage, int carNumber, int handicap, int pointsOfSeason) {
        super(name, age, wage);
        this.carNumber = carNumber;
        this.handicap = 0;
        this.pointsOfSeason = 0;
    }

    public void saveResult(int finishPosition){
        switch (finishPosition){
            case 1:
                setPointsOfSeason(25);
                break;
            case 2:
                setPointsOfSeason(18);
                break;
            case 3:
                setPointsOfSeason(15);
                break;
            case 4:
                setPointsOfSeason(12);
                break;
            case 5:
                setPointsOfSeason(10);
                break;
            case 6:
                setPointsOfSeason(8);
                break;
            case 7:
                setPointsOfSeason(6);
                break;
            case 8:
                setPointsOfSeason(4);
                break;
            case 9:
                setPointsOfSeason(2);
                break;
            case 10:
                setPointsOfSeason(1);
                break;
            default:
                setPointsOfSeason(0);
                setHandicap(1);
                break;
        }
    }

    public void Practice(){
        Random rand = new Random();
        setHandicap(rand.nextInt(6));
    }



    @Override
    public void showInfo() {
        System.out.println("--------------------------");
        super.showInfo();
        System.out.println("Carro: " + this.carNumber);
        System.out.println("Nível de Habilidade: " + this.handicap);
        System.out.println("Pontos da Temporada: " + this.pointsOfSeason);
        System.out.println("--------------------------");
    }
}
