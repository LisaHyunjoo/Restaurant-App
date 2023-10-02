package screen;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput() {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();  // Clear the invalid input
            }
        }

        return input;
    }

    public static String getStringInput() {
        return scanner.nextLine().trim();  //we use trim to exclude the space from the user input
    }
}
