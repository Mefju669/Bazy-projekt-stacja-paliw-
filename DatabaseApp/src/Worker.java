public class Worker {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String position;
    private double salary;


    public Worker(int id, String name, String surname, String email, String position, double salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }
}
