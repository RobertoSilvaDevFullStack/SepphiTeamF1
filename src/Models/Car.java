package Models;

public class Car {

    private String model;
    private int horsePower;
    private double aerodynamicCoefficient;
    private Driver accountableDriver;
    private double performance;


    public Car(String model, int horsePower, double aerodynamicCoefficient) {
        this.model = model;
        this.horsePower = horsePower;
        this.aerodynamicCoefficient = aerodynamicCoefficient;
        this.accountableDriver = getAccountableDriver();
        this.performance = calculateInitialPerformance();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public double getAerodynamicCoefficient() {
        return aerodynamicCoefficient;
    }

    public void setAerodynamicCoefficient(double aerodynamicCoefficient) {
        this.aerodynamicCoefficient = aerodynamicCoefficient;
    }

    public Driver getAccountableDriver() {
        return accountableDriver;
    }

    public void setAccountableDriver(Driver accountableDriver) {
        this.accountableDriver = accountableDriver;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }


    public void showInfo() {
        System.out.println("--------------------------");
        System.out.println("Modelo: " + getModel());
        System.out.println("Potência: " + getHorsePower() + " HP");
        System.out.println("Coeficiente Aerodinâmico: " + getAerodynamicCoefficient());
        if(getAccountableDriver() != null) {
            System.out.println("Piloto Responsável: " + getAccountableDriver().getName());
        } else {
            System.out.println("Piloto Responsável: Nenhum");
        }
        System.out.println("--------------------------");
    }

    public double calculateInitialPerformance() {
        return this.horsePower * this.aerodynamicCoefficient;
    }

    public double calculateEfficiency(int driverHandicap) {
        return this.horsePower * this.aerodynamicCoefficient * ((double) driverHandicap / 100);
    }
}
