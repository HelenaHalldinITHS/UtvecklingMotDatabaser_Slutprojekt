package se.iths.helena.application;

import se.iths.helena.dao.*;

import se.iths.helena.entities.Course;
import se.iths.helena.entities.Education;
import se.iths.helena.entities.Student;
import se.iths.helena.impl.*;

import java.util.List;
import java.util.Optional;

public class AppRetrieveInfo {
    EducationDao educationDao;
    CourseDao courseDao;
    StudentDao studentDao;
    TeacherDao teacherDao;

    public AppRetrieveInfo() {
        educationDao = new EducationDaoImpl();
        courseDao = new CourseDaoImpl();
        studentDao = new StudentDaoImpl();
        teacherDao = new TeacherDaoImpl();
    }

    public void run() {
        do {
            printMenu();
            int choice = getChoice();
            if (choice == 0)
                break;
            else
                runChoice(choice);
        } while (true);
    }

    private void printMenu() {
        System.out.println("""
                What would you like to retrieve data about?
                Choose one of the following by writing its corresponding number:
                1. Educations
                2. Courses
                3. Students
                4. Teachers
                5. Statistics
                0. Exit
                """);
    }

    private int getChoice() {
        return InputHandler.getIntegerInput(0, 5);
    }

    private void runChoice(int choice) {
        switch (choice) {
            case 1 -> getEducationInfo();
            case 2 -> getCourseInfo();
            case 3 -> getStudentInfo();
            case 4 -> getTeacherInfo();
            case 5 -> getStatistics();
        }
    }

    // --- Education ---
    private void getEducationInfo() {
        printEducationInfoMenu();
        int choice = InputHandler.getIntegerInput(0, 2);
        runEducationInfoChoice(choice);
    }

    private void runEducationInfoChoice(int choice) {
        switch (choice) {
            case 1 -> educationDao.showAll();
            case 2 -> showEducationById();
        }
        System.out.println();
    }

    private void showEducationById() {
        System.out.println("What is the id of the education?");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        educationDao.showInfo(educationDao.getById(id));
    }

    private void printEducationInfoMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Show all educations
                2. Show info about a specific education
                0. Exit
                """);
    }

    // --- Course ---

    private void getCourseInfo() {
        printCourseInfoMenu();
        int choice = InputHandler.getIntegerInput(0, 3);
        runCourseInfoChoice(choice);
    }

    private void runCourseInfoChoice(int choice) {
        switch (choice) {
            case 1 -> courseDao.showAll();
            case 2 -> showCourseById();
            case 3 -> showCourseByEducation();
        }
        System.out.println();
    }

    private void showCourseByEducation() {
        System.out.println("What is the id of the education?");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        courseDao.showByEducation(educationDao.getById(id));
    }

    private void showCourseById() {
        System.out.println("What is the id of the course?");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        courseDao.showInfo(courseDao.getById(id));
    }

    private void printCourseInfoMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Show all courses
                2. Show info about a specific course
                3. Show courses in a specific education
                0. Exit
                """);
    }

    // --- Student ---
    private void getStudentInfo() {
        printStudentInfoMenu();
        int choice = InputHandler.getIntegerInput(0, 3);
        runStudentInfoChoice(choice);
    }

    private void runStudentInfoChoice(int choice) {
        switch (choice) {
            case 1 -> studentDao.showAll();
            case 2 -> showStudentById();
            case 3 -> showStudentByEducation();
        }
        System.out.println();
    }

    private void showStudentByEducation() {
        System.out.println("What is the id of the education?");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        studentDao.showByEducation(educationDao.getById(id));
    }

    private void showStudentById() {
        System.out.println("What is the id of the student?");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        studentDao.showInfo(studentDao.getById(id));
    }

    private void printStudentInfoMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Show all student
                2. Show info about a specific student
                3. Show students in a specific education
                0. Exit
                """);
    }

    // --- Teacher ---
    private void getTeacherInfo() {
        printTeacherInfoMenu();
        int choice = InputHandler.getIntegerInput(0, 2);
        runTeacherInfoChoice(choice);
    }

    private void runTeacherInfoChoice(int choice) {
        switch (choice) {
            case 1 -> teacherDao.showAll();
            case 2 -> showTeacherById();
        }
        System.out.println();
    }

    private void showTeacherById() {
        System.out.println("What is the id of the teacher?");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        teacherDao.showInfo(teacherDao.getById(id));
    }

    private void printTeacherInfoMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Show all teachers
                2. Show info about a specific teacher
                0. Exit
                """);
    }


    // --- Statistics ---

    private void getStatistics() {
        printStatisticsMenu();
        int choice = InputHandler.getIntegerInput(0, 3);
        runStatisticsChoice(choice);
    }

    private void runStatisticsChoice(int choice) {
        switch (choice) {
            case 1 -> showAverageStudentPerEducation();
            case 2 -> showCourseWithMostPoints();
            case 3 -> showAmountOfStudentsNotRegisteredToEducation();
        }
        System.out.println();
    }

    private void showAmountOfStudentsNotRegisteredToEducation() {
        List<Student> students = studentDao.getAll();
        int notRegistered = 0;

        for (Student student : students) {
            if (student.getEducation() == null)
                notRegistered++;
        }

        System.out.println("The current amount of students not currently registered to any education is: " + notRegistered);
    }

    private void showCourseWithMostPoints() {
        List<Course> courses = courseDao.getAll();
        int max = 0;
        Optional<Course> mostPointsCourse = Optional.empty();

        for (Course course : courses) {
            int points = course.getPoints();
            if (points > max) {
                mostPointsCourse = Optional.of(course);
                max = points;
            }
        }
        mostPointsCourse.ifPresentOrElse(course -> System.out.println("The course with most points is: " + course.getId() + "(" + course.getName() + ")"),
                () -> System.out.println("There are no courses"));
    }

    private void showAverageStudentPerEducation() {
        List<Education> educations = educationDao.getAll();
        double students = 0;
        for (Education education : educations)
            students += studentDao.getByEducation(education).size();
        double average = students / educations.size();
        System.out.println("The average number of students per education is: " + average);
    }

    private void printStatisticsMenu() {
        System.out.println("""
                What would you like to see?
                Choose one of the following by writing its corresponding number:
                1. Average number of students per education
                2. Course with most points
                3. Amount of students not currently registered to any education
                0. Exit
                """);
    }
}
