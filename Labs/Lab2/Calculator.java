import java.util.Scanner; // Import the Scanner class

public class Calculator {
    public static void main(String [] args) {
        double operand1, operand2; // Variables to store two user-provided numbers
        int menuOption; // Variable to store user-provided menu option (from the list of four operations)
        Scanner scanner = new Scanner(System.in); // Create a new Scanner object to scan for user input

        System.out.print("Enter first operand: ");
        operand1 = scanner.nextDouble(); // User provides their first number

        System.out.print("Enter second operand: ");
        operand2 = scanner.nextDouble(); // User provides their second number

        // Below are the four menu options displayed to the user
        // User must choose which option they want by entering an integer from 1 - 4
        // Otherwise, program will terminate
        System.out.println("\nCalculator Menu");
        System.out.println("---------------");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division\n");
        System.out.print("Which operation do you want to perform? ");

        menuOption = scanner.nextInt(); // User provides their menu option

        switch (menuOption) { // Cases 1 - 4 correspond to the operations in the menu

            case 1: // User selected option 1, which is addition and displays the result
                System.out.print("\nThe result of the operation is " + (operand1 + operand2));
                System.out.println(". Goodbye!");
                break;

            case 2: // User selected option 2, which is subtraction and displays the result
                System.out.print("\nThe result of the operation is " + (operand1 - operand2));
                System.out.println(". Goodbye!");
                break;

            case 3: // User selected option 3, which is multiplication and displays the result
                System.out.print("\nThe result of the operation is " + (operand1 * operand2));
                System.out.println(". Goodbye!");
                break;

            case 4: // User selected option 4, which is division and displays the result
                System.out.print("\nThe result of the operation is " + (operand1 / operand2));
                System.out.println(". Goodbye!");
                break;

            default: // User didn't input an integer from 1 - 4, thus terminating the program
                System.out.println("\nError: Invalid selection! Terminating program.");
        }
    }
}
