package pl.javastart.task;

public class UniversityApp {

    College college = new College();

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */
    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (!college.isLecturerExist(id)) {
            college.getLecturers().add(new Lecturer(firstName, lastName, degree, id));
        } else {
            System.out.println("Prowadzący z id [" + id + "] już istnieje");
        }
    }

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */
    public void createGroup(String code, String name, int lecturerId) {
        if (!college.isGroupExist(code) && college.isLecturerExist(lecturerId)) {
            college.getGroups().add(new Group(code, name, lecturerId));
        } else if (college.isGroupExist(code)) {
            System.out.println("Grupa [" + code + "] już istnieje");
        } else if (!college.isLecturerExist(lecturerId)) {
            System.out.println("Prowadzący z id [" + lecturerId + "] nie istnieje");
        }
    }


    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */
    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        if (college.isGroupExist(groupCode) && !college.isStudentExist(index)) {
            college.findGroupByCode(groupCode).getStudents().add(new Student(firstName, lastName, index));
            college.getStudents().add(new Student(firstName, lastName, index));
        } else if (college.isStudentExist(index)) {
            college.findGroupByCode(groupCode).getStudents().add(new Student(firstName, lastName, index));
        } else {
            System.out.println("Grupa [" + groupCode + "] nie istnieje");
        }
    }


    /**
     * Wyświetla informacje o grupie w zadanym formacie.
     * Oczekiwany format:
     * Kod: [kod_grupy]
     * Nazwa: [nazwa przedmiotu]
     * Prowadzący: [stopień naukowy] [imię] [nazwisko]
     * Uczestnicy:
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
     *
     * @param groupCode - kod grupy, dla której wyświetlić informacje
     */
    public void printGroupInfo(String groupCode) {
        if (college.isGroupExist(groupCode)) {
            Group group = college.findGroupByCode(groupCode);
            Lecturer lecturer = college.findLecturerById(group.getLecturerID());
            System.out.println("Kod: [" + group.getCode() + ']');
            System.out.println("Prowadzący:  [" + lecturer.getDegree()
                    + "] [" + lecturer.getFirstName() + "] [" + lecturer.getLastName() + "]");
            System.out.println("Uczestnicy:");
            printStudentByGroup(groupCode);
        } else {
            System.out.println("Grupa [" + groupCode + "] nie znaleziona");
        }
    }

    /**
     * Dodaje ocenę końcową dla wskazanego studenta i grupy.
     * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
     * W przypadku, gdy grupa o wskazanym kodzie nie istnieje, wyświetlany jest komunikat postaci:
     * "Grupa pp-2022 nie istnieje"
     * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
     * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
     * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
     * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
     *
     * @param studentIndex - numer indeksu studenta
     * @param groupCode    - kod grupy
     * @param grade        - ocena
     */
    public void addGrade(int studentIndex, String groupCode, double grade) {
        if (college.isStudentExistInGroup(studentIndex, groupCode)
                && !college.doesStudentHaveFinalGrade(studentIndex, groupCode)) {
            college.findStudentByIndex(studentIndex).getFinalGrades().add(new FinalGrade(grade, groupCode));
        } else if (!college.isStudentExistInGroup(studentIndex, groupCode)) {
            System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany"
                    + "do grupy " + groupCode);
        } else if (!college.isGroupExist(groupCode)) {
            System.out.println("Grupa " + groupCode + " nie istenieje");
        } else if (college.doesStudentHaveFinalGrade(studentIndex, groupCode)) {
            System.out.println("Student o indeksie " + studentIndex
                    + " ma juz wystawiona ocene dla grupy " + groupCode);
        }
    }

    /**
     * Wyświetla wszystkie oceny studenta.
     * Przykładowy wydruk:
     * Podstawy programowania: 5.0
     * Programowanie obiektowe: 5.5
     *
     * @param index - numer indesku studenta dla którego wyświetlić oceny
     */
    public void printGradesForStudent(int index) {
        for (FinalGrade finalGrade : college.findStudentByIndex(index).getFinalGrades()) {
            System.out.println(college.findGroupByCode(finalGrade.getGroupCode()).getName()
                    + ": " + finalGrade.getGrade());
        }
    }

    /**
     * Wyświetla oceny studentów dla wskazanej grupy.
     * Przykładowy wydruk:
     * 179128 Marcin Abacki: 5.0
     * 179234 Dawid Donald: 4.5
     * 189521 Anna Kowalska: 5.5
     *
     * @param groupCode - kod grupy, dla której wyświetlić oceny
     */
    public void printGradesForGroup(String groupCode) {
        for (Student student : college.findGroupByCode(groupCode).getStudents()) {
            System.out.println(student.getIndex() + " " + student.getFirstName() + " "
                    + student.getLastName() + " " +
                    college.findGradeByStudentIndexAndGroupCode(student.getIndex(),
                            groupCode));
        }
    }

    /**
     * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
     * Każdy student drukowany jest w nowej linii w formacie [nr_indesku] [imie] [nazwisko]
     * Przykładowy wydruk:
     * 179128 Marcin Abacki
     * 179234 Dawid Donald
     * 189521 Anna Kowalska
     */
    public void printAllStudents() {
        for (Student student : college.getStudents()) {
            System.out.println(student.getIndex() + student.getFirstName()
                    + student.getLastName());
        }

    }

    public void printStudentByGroup(String groupCode) {
        for (Student student : college.findGroupByCode(groupCode).getStudents()) {
            System.out.println("[" + student.getIndex() + "] ["
                    + "[" + student.getFirstName() + "] ["
                    + student.getLastName() + "]");
        }
    }
}
