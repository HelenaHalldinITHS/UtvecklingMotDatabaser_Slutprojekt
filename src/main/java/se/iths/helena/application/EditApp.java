package se.iths.helena.application;

import se.iths.helena.dao.CourseDao;
import se.iths.helena.dao.EducationDao;
import se.iths.helena.dao.StudentDao;
import se.iths.helena.dao.TeacherDao;
import se.iths.helena.entities.Course;
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
            case 3 -> updateEducation();
        }
        System.out.println();
    }

    private void updateEducation() {
        System.out.println("What is the id of the education you want to edit? ");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        Education education = educationDao.getById(id);
        System.out.println("What is the new name of the of the education?");
        System.out.println();
        String name = InputHandler.getStringInput();
        education.setName(name);
        educationDao.update(education);
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
                1. Add education
                2. Delete education
                3. Edit education
                0. Exit
                """);
    }

    // --- Course ---
    private void editCourses() {
        printEditCourseMenu();
        int choice = InputHandler.getIntegerInput(0,5);
        runCourseChoice(choice);
    }

    private void runCourseChoice(int choice) {
        switch (choice){
            case 1 -> addCourse();
            case 2 -> deleteCourse();
            case 3 -> updateCourse();
        }
        System.out.println();
    }

    private void updateCourse() {
        System.out.println("What is the id of the course you want to edit? ");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        Course course = courseDao.getById(id);

        System.out.println("Enter new name: ");
        System.out.println();
        String name = InputHandler.getStringInput();
        course.setName(name);

        System.out.println("Enter new amount of points: ");
        System.out.println();
        int points = InputHandler.getIntegerInput();
        course.setPoints(points);

        courseDao.update(course);
    }

    private void deleteCourse() {
        System.out.println("What is the id of the course you want to delete? ");
        System.out.println();
        int id = InputHandler.getIntegerInput();
        courseDao.delete(courseDao.getById(id));
    }

    private void addCourse() {
        System.out.println("What is the name of the new course? ");
        System.out.println();
        String name = InputHandler.getStringInput();
        System.out.println("How many points is the new course? ");
        System.out.println();
        int points = InputHandler.getIntegerInput();
        courseDao.add(new Course(name,points));
    }

    private void printEditCourseMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Add course
                2. Delete course
                3. Edit course
                4. Add course to education
                5. Remove course from education
                0. Exit
                """);
    }

    // --- Student ---
    private void editStudents() {
    }

    // --- Teacher ---
    private void editTeachers() {
    }

}
