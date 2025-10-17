package Models;

public class Engineer extends TeamMember{

    public String speciality;
    public int yearsOfExperience;

    public Engineer(String name, int age, double wage, String speciality, int yearsOfExperience) {
        super(name, age, wage);
        this.speciality = speciality;
        this.yearsOfExperience = yearsOfExperience;
    }


    public Car improveCar(Car  car) {
        car.setPerformance(car.getPerformance() + (yearsOfExperience * 0.5));
        return car;
    }

    @Override
    public void exibirInfo() {
        System.out.println("--------------------------");
        super.exibirInfo();
        System.out.println("Especialidade: " + speciality);
        System.out.println("Anos de Experiência: " + yearsOfExperience);
        System.out.println("--------------------------");
    }
}
