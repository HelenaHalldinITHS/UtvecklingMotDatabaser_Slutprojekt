package se.iths.helena;

import se.iths.helena.dao.*;
import se.iths.helena.entities.*;
import se.iths.helena.impl.*;

import java.util.Scanner;

public class MainApp {
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainApp application = new MainApp();
        application.start();
    }

    private void start() {
        // loadInitialData(); //If it's the first time you run the program. Uncomment this line
        do {
            printMainMenu();
            int choice = getChoice();
            if (choice == 0)
                break;
            else
                runChoice(choice);
        } while (true);
    }

    private void runChoice(int choice) {
        switch (choice) {
            case 1 -> RetrieveInfoApp.run();
            case 2 -> EditApp.run();
        }
    }

    private int getChoice() {
        return InputHandler.getIntegerInput(0, 2);
    }

    private void printMainMenu() {
        System.out.println("""
                Welcome! Choose one of the following by writing its corresponding number:
                1. Get data
                2. Edit data
                0. End application
                """);
    }

    private void loadInitialData() {
        EducationDao educationDao = new EducationDaoImpl();
        StudentDao studentDao = new StudentDaoImpl();
        CourseDao courseDao = new CourseDaoImpl();
        TeacherDao teacherDao = new TeacherDaoImpl();

        Education education1 = new Education("Javautvecklare");
        Education education2 = new Education("UX-designer");
        educationDao.add(education1);
        educationDao.add(education2);

        Student student1 = new Student("Helena", "Halldin");
        Student student2 = new Student("Sara", "Olsson", education1);
        studentDao.add(student1);
        studentDao.add(student2);

        Course course1 = new Course("Java", 60);
        Course course2 = new Course("Sql", 35, education1);
        courseDao.add(course1);
        courseDao.add(course2);

        Teacher teacher1 = new Teacher("Eddie", "Neumann");
        Teacher teacher2 = new Teacher("Martin", "Test");
        teacherDao.add(teacher1);
        teacherDao.add(teacher2);

        TeacherCourseRelationDao teacherCourseRelationDao = new TeacherCourseRelationDaoImpl();
        TeacherCourseRelation relation = new TeacherCourseRelation(teacher2, course1);
        teacherCourseRelationDao.add(relation);
    }
}
