package pl.javastart.task;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private int index;
    private List<FinalGrade> finalGrades = new ArrayList<>();

    public Student(String firstName, String lastName, int index) {
        super(firstName, lastName);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<FinalGrade> getFinalGrades() {
        return finalGrades;
    }

    public void setFinalGrades(ArrayList<FinalGrade> finalGrades) {
        this.finalGrades = finalGrades;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + " " + index;
    }
}
