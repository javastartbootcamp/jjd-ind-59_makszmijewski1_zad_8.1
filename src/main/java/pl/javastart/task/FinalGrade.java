package pl.javastart.task;

public class FinalGrade {
    private double grade;
    private String groupCode;

    public FinalGrade(double grade, String groupCode) {
        this.grade = grade;
        this.groupCode = groupCode;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
