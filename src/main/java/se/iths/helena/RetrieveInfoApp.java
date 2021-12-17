package se.iths.helena;

public class RetrieveInfoApp {

    public static void run() {
        RetrieveInfoApp retrieveInfoApp = new RetrieveInfoApp();
        retrieveInfoApp.start();
    }

    private void start() {
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
                0. Exit
                """);
    }

    private int getChoice() {
        return InputHandler.getIntegerInput(0, 4);
    }

    private void runChoice(int choice) {
        switch (choice) {
            case 1 -> getEducationInfo();
            case 2 -> getCourseInfo();
            case 3 -> getStudentInfo();
            case 4 -> getTeacherInfo();
        }
    }


    private void getTeacherInfo() {
    }

    private void getStudentInfo() {
    }

    private void getCourseInfo() {
    }

    private void getEducationInfo() {
    }
}
