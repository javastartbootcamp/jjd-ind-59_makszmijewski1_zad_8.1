package pl.javastart.task;

import java.util.ArrayList;

public class College {
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Lecturer> lecturers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    boolean isLecturerExist(int id) {
        for (Lecturer lecturer : lecturers) {
            if (id == lecturer.getId()) {
                return true;
            }
        }
        return false;
    }

    boolean isGroupExist(String code) {
        for (Group group : groups) {
            if (code.equals(group.getCode())) {
                return true;
            }
        }
        return false;
    }

    boolean isStudentExist(int index) {
        for (Student student : students) {
            if (index == student.getIndex()) {
                return true;
            }
        }
        return false;
    }

    boolean isStudentExistInGroup(int studentIndex, String groupCode) {
        for (Student student : findGroupByCode(groupCode).getStudents()) {
            if (studentIndex == student.getIndex()) {
                return true;
            }
        }
        return false;
    }

    boolean doesStudentHaveFinalGrade(int studentIndex, String groupCode) {
        for (FinalGrade finalGrade : findStudentByIndex(studentIndex).getFinalGrades()) {
            if (finalGrade.getGrade() != 0 && groupCode.equals(finalGrade.getGroupCode())) {
                return true;
            }
        }
        return false;
    }

    Lecturer findLecturerById(int id) {
        for (Lecturer lecturer : lecturers) {
            if (id == lecturer.getId()) {
                return lecturer;
            }
        }
        return null;
    }

    Group findGroupByCode(String code) {
        for (Group group : groups) {
            if (code.equals(group.getCode())) {
                return group;
            }
        }
        return null;
    }

    Student findStudentByIndex(int index) {
        for (Student student : students) {
            if (index == student.getIndex()) {
                return student;
            }
        }
        return null;
    }

    double findGradeByStudentIndexAndGroupCode(int index, String code) {
        for (FinalGrade finalGrade : findStudentByIndex(index).getFinalGrades()) {
            if (code.equals(finalGrade.getGroupCode())) {
                return finalGrade.getGrade();
            }
        }
        return 0;
    }
}
