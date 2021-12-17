package se.iths.helena.application;

import se.iths.helena.dao.CourseDao;
import se.iths.helena.dao.EducationDao;
import se.iths.helena.dao.StudentDao;
import se.iths.helena.dao.TeacherDao;
import se.iths.helena.entities.Education;
import se.iths.helena.impl.CourseDaoImpl;
import se.iths.helena.impl.EducationDaoImpl;
import se.iths.helena.impl.StudentDaoImpl;
import se.iths.helena.impl.TeacherDaoImpl;

import java.util.Scanner;

public class EditApp {
    EducationDao educationDao;
    CourseDao courseDao;
    StudentDao studentDao;
    TeacherDao teacherDao;

    public EditApp() {
        educationDao = new EducationDaoImpl();
        courseDao = new CourseDaoImpl();
        studentDao = new StudentDaoImpl();
        teacherDao = new TeacherDaoImpl();
    }

    public void run(){
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
                What would you like to edit?
                Choose one of the following by writing its corresponding number:
                1. Educations
                2. Courses
                3. Students
                4. Teachers
                0. Exit
                """);
    }

    private int getChoice() {
        return InputHandler.getIntegerInput(0, 4);
    }

    private void runChoice(int choice) {
        switch (choice) {
            case 1 -> editEducations();
            case 2 -> editCourses();
            case 3 -> editStudents();
            case 4 -> editTeachers();
        }
    }

    // --- Education ---
    private void editEducations() {
        printEditEducationMenu();
        int choice = InputHandler.getIntegerInput(0,3);
        runEducationChoice(choice);
    }

    private void runEducationChoice(int choice) {
        switch (choice){
            case 1 -> addEducation();
            case 2 -> deleteEducation();
        }
        System.out.println();
    }

    private void deleteEducation() {
        System.out.println("What is the id of the education you want to delete? ");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        educationDao.delete(educationDao.getById(id));
    }

    private void addEducation() {
        System.out.println("What is the name of the new education? ");
        System.out.println();
        String name = InputHandler.getStringInput();
        educationDao.add(new Education(name));
    }

    private void printEditEducationMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Add Education
                2. Delete Education
                3. Edit Education
                0. Exit
                """);
    }

    // --- Course ---
    private void editCourses() {
    }

    // --- Student ---
    private void editStudents() {
    }

    // --- Teacher ---
    private void editTeachers() {
    }

}
