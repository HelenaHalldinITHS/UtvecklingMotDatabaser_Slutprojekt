package se.iths.helena;

import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntegerInput(){
        int choice;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please try again: ");
            }
        } while (true);
        return choice;
    }

    public static int getIntegerInput(int from, int to){
        int choice;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < from || choice > to)
                    throw new IllegalArgumentException();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please try again: ");
            }
        } while (true);
        return choice;
    }
}
