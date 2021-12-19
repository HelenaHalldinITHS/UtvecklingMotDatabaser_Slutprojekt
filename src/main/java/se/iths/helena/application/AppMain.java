package se.iths.helena.application;

import se.iths.helena.dao.*;
import se.iths.helena.entities.*;
import se.iths.helena.impl.*;

public class AppMain {
    private final AppRetrieveInfo retrieveInfo;
    private final AppEditData edit;

    private AppMain(){
        retrieveInfo = new AppRetrieveInfo();
        edit = new AppEditData();
    }

    public static void main(String[] args) {
        AppMain application = new AppMain();
        application.start();
    }

    private void start() {
        loadInitialData(); //If it's the first time you run the program. Uncomment this line
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
            case 1 -> retrieveInfo.run();
            case 2 -> edit.run();
        }
    }

    private int getChoice() {
        return InputHandler.getIntegerInput(0, 2);
    }

    private void printMainMenu() {
        System.out.println("""
                Choose one of the following by writing its corresponding number:
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
        TeacherCourseRelationDao teacherCourseRelationDao = new TeacherCourseRelationDaoImpl();

        Education education1 = new Education("Javautvecklare");
        Education education2 = new Education("UX-designer");
        educationDao.add(education1);
        educationDao.add(education2);

        studentDao.add(new Student("Helena", "Halldin"));
        studentDao.add(new Student("Sara", "Olsson", education1));
        studentDao.add(new Student("Tobias", "Eklund", education2));

        Course course1 = new Course("Java", 60, education1);
        Course course2 = new Course("Databaser", 35);
        courseDao.add(course1);
        courseDao.add(course2);

        Teacher teacher1 = new Teacher("Eddie", "Neumann");
        Teacher teacher2 = new Teacher("Martin", "Blomberg");
        teacherDao.add(teacher1);
        teacherDao.add(teacher2);

        teacherCourseRelationDao.add(new TeacherCourseRelation(teacher2, course1));
        teacherCourseRelationDao.add(new TeacherCourseRelation(teacher1, course2));
    }
}
