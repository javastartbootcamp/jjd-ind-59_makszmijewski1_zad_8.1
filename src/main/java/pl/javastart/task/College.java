package pl.javastart.task;

import java.util.ArrayList;
import java.util.List;

public class College {
    private List<Group> groups = new ArrayList<>();
    private List<Lecturer> lecturers = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    boolean doesLecturerExist(int id) {
        for (Lecturer lecturer : lecturers) {
            if (id == lecturer.getId()) {
                return true;
            }
        }
        return false;
    }

    boolean doesGroupExist(String code) {
        for (Group group : groups) {
            if (code.equals(group.getCode())) {
                return true;
            }
        }
        return false;
    }

    boolean doesStudentExist(int index) {
        for (Student student : students) {
            if (index == student.getIndex()) {
                return true;
            }
        }
        return false;
    }

    boolean doesStudentExistInGroup(int studentIndex, String groupCode) {
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
            if (group.getCode().equals(code)) {
                return group;
            }
        }
        return null;
    }

    Student findStudentByIndex(int index) {
        for (Student student : students) {
            if (student.getIndex() == index) {
                return student;
            }
        }
        return null;
    }

    double findGradeByStudentIndexAndGroupCode(int index, String code) {
        Student student = findStudentByIndex(index);
        for (FinalGrade finalGrade : student.getFinalGrades()) {
            if (code.equals(finalGrade.getGroupCode())) {
                return finalGrade.getGrade();
            }
        }
        return 0;
    }

    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public boolean isStudentInGroup(int studentIndex, String groupCode) {
        for (Student student : findGroupByCode(groupCode).getStudents()) {
            if (studentIndex == student.getIndex()) {
                return true;
            }
        }
        return false;
    }
}
