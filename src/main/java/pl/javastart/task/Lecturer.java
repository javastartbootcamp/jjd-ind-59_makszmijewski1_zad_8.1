package pl.javastart.task;

public class Lecturer extends Person {
    private String degree;
    private int id;

    public Lecturer(String firstName, String lastName, String degree, int id) {
        super(firstName, lastName);
        this.degree = degree;
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
