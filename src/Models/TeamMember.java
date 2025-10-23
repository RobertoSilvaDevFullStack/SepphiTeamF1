package Models;

public class TeamMember {

    protected int id;
    protected int teamId;
    private String name;
    private int age;
    private double wage;

    public TeamMember(String name, int age, double wage) {
        this.id = -1; // -1 indica que não foi salvo no banco ainda
        this.teamId = -1;
        this.name = name;
        this.age = age;
        this.wage = wage;
    }

    public void showInfo() {
        System.out.println("Nome: " + getName());
        System.out.println("Idade: " + getAge());
        System.out.println("Salário: " + getWage());
    }

    public void exibirInfo() {
        System.out.println("Nome: " + getName());
        System.out.println("Idade: " + getAge());
        System.out.println("Salário: " + getWage());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() < 3) {
            System.out.println("O nome não pode ser menor que 3 caracteres");
            return;
        }
        else {
        this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 16) {
            System.out.println("O piloto deve ter mais de 16 anos para ser admitido");
            return;
        }
        this.age = age;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    // Getters e Setters para ID e TeamId
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
