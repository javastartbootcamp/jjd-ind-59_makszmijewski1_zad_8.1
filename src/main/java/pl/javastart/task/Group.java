package pl.javastart.task;

import java.util.ArrayList;

public class Group {
    private String code;
    private String name;
    private int lecturerID;
    private ArrayList<Student> students = new ArrayList<>();

    public Group(String code, String name, int lecturerID) {
        this.code = code;
        this.name = name;
        this.lecturerID = lecturerID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
