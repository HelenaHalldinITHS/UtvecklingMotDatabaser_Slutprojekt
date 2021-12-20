package se.iths.helena.application;

import se.iths.helena.dao.*;
import se.iths.helena.entities.*;
import se.iths.helena.impl.*;

import java.util.Optional;


public class AppEditData {
    EducationDao educationDao;
    CourseDao courseDao;
    StudentDao studentDao;
    TeacherDao teacherDao;
    TeacherCourseRelationDao teacherCourseRelationDao;

    public AppEditData() {
        educationDao = new EducationDaoImpl();
        courseDao = new CourseDaoImpl();
        studentDao = new StudentDaoImpl();
        teacherDao = new TeacherDaoImpl();
        teacherCourseRelationDao = new TeacherCourseRelationDaoImpl();
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
        int choice = InputHandler.getIntegerInput(0, 3);
        runEducationChoice(choice);
    }

    private void runEducationChoice(int choice) {
        switch (choice) {
            case 1 -> addEducation();
            case 2 -> deleteEducation();
            case 3 -> updateEducation();
        }
        System.out.println();
    }

    private void updateEducation() {
        System.out.println("What is the id of the education you want to edit? \n");
        int id = InputHandler.getIntegerInput();
        Optional<Education> optionalEducation = educationDao.getById(id);

        if (optionalEducation.isEmpty()){
            System.out.println("There is no education with id: " + id + "\n");
            return;
        }

        Education education = optionalEducation.get();
        System.out.println("What is the new name of the of the education? \n");
        String name = InputHandler.getStringInput();
        education.setName(name);
        educationDao.update(education);
    }

    private void deleteEducation() {
        System.out.println("What is the id of the education you want to delete? \n");
        int id = InputHandler.getIntegerInput();
        Optional<Education> optionalEducation = educationDao.getById(id);
        optionalEducation.ifPresent(education -> educationDao.delete(education));
    }

    private void addEducation() {
        System.out.println("What is the name of the new education? \n");
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
        int choice = InputHandler.getIntegerInput(0, 5);
        runCourseChoice(choice);
    }

    private void runCourseChoice(int choice) {
        switch (choice) {
            case 1 -> addCourse();
            case 2 -> deleteCourse();
            case 3 -> updateCourse();
            case 4 -> addCourseToEducation();
            case 5 -> deleteCourseFromEducation();
        }
        System.out.println();
    }

    private void deleteCourseFromEducation() {
        System.out.println("What is the id of the course you want to remove from its education? \n");
        int courseId = InputHandler.getIntegerInput();
        Optional<Course> course = courseDao.getById(courseId);
        course.ifPresent(course1 -> courseDao.removeCourseFromEducation(course1));
    }

    private void addCourseToEducation() {
        System.out.println("What is the id of the education you want to add a course to? \n");
        int educationId = InputHandler.getIntegerInput();
        Optional<Education> optionalEducation = educationDao.getById(educationId);

        System.out.println("What is the id of the course you want to add? \n");
        int courseId = InputHandler.getIntegerInput();
        Optional<Course> optionalCourse = courseDao.getById(courseId);

        if (optionalEducation.isPresent() && optionalCourse.isPresent())
            courseDao.addCourseToEducation(optionalCourse.get(), optionalEducation.get());
    }

    private void updateCourse() {
        System.out.println("What is the id of the course you want to edit? \n");
        int id = InputHandler.getIntegerInput();
        Optional<Course> optionalCourse = courseDao.getById(id);

        if (optionalCourse.isEmpty()){
            System.out.println("There is no course with id " + id);
            return;
        }

        Course course = optionalCourse.get();

        System.out.println("Enter new name: \n");
        String name = InputHandler.getStringInput();
        course.setName(name);

        System.out.println("Enter new amount of points: \n");
        int points = InputHandler.getIntegerInput();
        course.setPoints(points);

        courseDao.update(course);
    }

    private void deleteCourse() {
        System.out.println("What is the id of the course you want to delete? \n");
        int id = InputHandler.getIntegerInput();
        Optional<Course> optionalCourse = courseDao.getById(id);
        optionalCourse.ifPresent(course -> courseDao.delete(course));
    }

    private void addCourse() {
        System.out.println("What is the name of the new course? \n");
        String name = InputHandler.getStringInput();
        System.out.println("How many points is the new course? \n");
        int points = InputHandler.getIntegerInput();
        courseDao.add(new Course(name, points));
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
        printEditStudentMenu();
        int choice = InputHandler.getIntegerInput(0, 5);
        runStudentChoice(choice);
    }

    private void runStudentChoice(int choice) {
        switch (choice) {
            case 1 -> addStudent();
            case 2 -> deleteStudent();
            case 3 -> updateStudent();
            case 4 -> addStudentToEducation();
            case 5 -> deleteStudentFromEducation();
        }
        System.out.println();
    }

    private void deleteStudentFromEducation() {
        System.out.println("What is the id of the student you want to unregister from its education? \n");
        int studentId = InputHandler.getIntegerInput();
        studentDao.unregisterToEducation(studentDao.getById(studentId));
    }

    private void addStudentToEducation() {
        System.out.println("What is the id of the education you want to add a student to? \n");
        int educationId = InputHandler.getIntegerInput();
        Optional<Education> optionalEducation = educationDao.getById(educationId);

        System.out.println("What is the id of the student you want to add? \n");
        int studentId = InputHandler.getIntegerInput();
        Student student = studentDao.getById(studentId);

        optionalEducation.ifPresent(education -> studentDao.registerToEducation(student, education));
    }

    private void updateStudent() {
        System.out.println("What is the id of the student you want to edit? \n");
        int id = InputHandler.getIntegerInput();
        Student student = studentDao.getById(id);

        System.out.println("Enter new first name: \n");
        String firstName = InputHandler.getStringInput();
        student.setFirstName(firstName);

        System.out.println("Enter new last name: \n");
        String lastName = InputHandler.getStringInput();
        student.setLastName(lastName);

        studentDao.update(student);
    }

    private void deleteStudent() {
        System.out.println("What is the id of the student you want to delete? \n");
        int id = InputHandler.getIntegerInput();
        studentDao.delete(studentDao.getById(id));
    }

    private void addStudent() {
        System.out.println("What is the first name of the new student? \n");
        String firstName = InputHandler.getStringInput();
        System.out.println("What is the last name of the new student? \n");
        String lastName = InputHandler.getStringInput();
        studentDao.add(new Student(firstName, lastName));
    }

    private void printEditStudentMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Add student
                2. Delete student
                3. Edit student
                4. Add student to education
                5. Remove student from education
                0. Exit
                """);
    }

    // --- Teacher ---
    private void editTeachers() {
        printEditTeacherMenu();
        int choice = InputHandler.getIntegerInput(0, 5);
        runTeacherChoice(choice);
    }

    private void runTeacherChoice(int choice) {
        switch (choice) {
            case 1 -> addTeacher();
            case 2 -> deleteTeacher();
            case 3 -> updateTeacher();
            case 4 -> addTeacherToEducation();
            case 5 -> removeTeacherFromEducation();
        }
        System.out.println();
    }

    private void removeTeacherFromEducation() {
        System.out.println("What is the id of the course you want to remove a teacher from? \n");
        int courseId = InputHandler.getIntegerInput();
        Optional<Course> optionalCourse = courseDao.getById(courseId);
       // Course course = courseDao.getById(courseId);

        System.out.println("What is the id of the teacher you want to remove? \n");
        int teacherId = InputHandler.getIntegerInput();
        Teacher teacher = teacherDao.getById(teacherId);

        optionalCourse.ifPresent(course -> teacherCourseRelationDao.remove(teacherCourseRelationDao.get(teacher, course)));
    }

    private void addTeacherToEducation() {
        System.out.println("What is the id of the course you want to add a teacher to? \n");
        int courseId = InputHandler.getIntegerInput();

        Optional<Course> optionalCourse = courseDao.getById(courseId);

        System.out.println("What is the id of the teacher you want to add? \n");
        int teacherId = InputHandler.getIntegerInput();
        Teacher teacher = teacherDao.getById(teacherId);

        optionalCourse.ifPresent(course -> teacherCourseRelationDao.add(new TeacherCourseRelation(teacher, course)));
    }

    private void updateTeacher() {
        System.out.println("What is the id of the teacher you want to edit? \n");
        int id = InputHandler.getIntegerInput();
        Teacher teacher = teacherDao.getById(id);

        System.out.println("Enter new first name: \n");
        String firstName = InputHandler.getStringInput();
        teacher.setFirstName(firstName);

        System.out.println("Enter new last name: \n");
        String lastName = InputHandler.getStringInput();
        teacher.setLastName(lastName);

        teacherDao.update(teacher);
    }

    private void deleteTeacher() {
        System.out.println("What is the id of the teacher you want to delete? \n");
        int id = InputHandler.getIntegerInput();
        teacherDao.delete(teacherDao.getById(id));
    }

    private void addTeacher() {
        System.out.println("What is the first name of the new teacher? \n");
        String firstName = InputHandler.getStringInput();
        System.out.println("What is the last name of the new teacher? \n");
        String lastName = InputHandler.getStringInput();
        teacherDao.add(new Teacher(firstName, lastName));
    }

    private void printEditTeacherMenu() {
        System.out.println("""
                What would you like to do?
                Choose one of the following by writing its corresponding number:
                1. Add teacher
                2. Delete teacher
                3. Edit teacher
                4. Add teacher to course
                5. Remove teacher from course
                0. Exit
                """);
    }


}
