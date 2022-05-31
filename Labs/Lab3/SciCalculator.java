import java.util.Scanner; // Import Scanner class

public class SciCalculator {

    // METHOD DEFINITIONS

    // Method to display calculator menu
    // Requires parameter currentResult (result of the previous calculation) which is printed above the menu
    public static void displayMenu(double currentResult) {

        System.out.println();
        System.out.println("Current Result: " + currentResult);
        System.out.println();
        System.out.println("Calculator Menu");
        System.out.println("---------------");
        System.out.println("0. Exit Program");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Exponentiation");
        System.out.println("6. Logarithm");
        System.out.println("7. Display Average");
        System.out.println();
    }

    // Method to prompt user to make a menu selection and accepts user input for the selection
    // Method returns the integer selection
    public static int menuSelection(Scanner scanner) {

        System.out.print("Enter Menu Selection: ");
        int selection = scanner.nextInt();

        // User must select an integer in the range [0, 7]
        // Keep prompting user for a menu selection until they input an integer in this range
        while (selection < 0 || selection > 7) {
            System.out.println();
            System.out.println("Error: Invalid selection!");
            System.out.println();
            System.out.print("Enter Menu Selection: ");
            selection = scanner.nextInt();
        }

        return selection;
    }

    // Method to prompt user for their first/second operand.  Method returns the (double) operand
    // Input parameter order is either "first" or "second"
    public static double getOperand(String order, Scanner scanner, double currentResult) {

        System.out.print("Enter " + order + " operand: ");
        String operand = scanner.next();

        // If user enters "RESULT", the operand will be the result of the most recent calculation, currentResult
        // Otherwise, string input is converted to a double (using Double.valueOf()) and returned
        if (operand.equals("RESULT")) {
            return currentResult;
        }

        return Double.valueOf(operand);
    }

    // Method to add two operands and returns the result
    // Method first prompts user for first and second operands, using method getOperand()
    public static double addition(double currentResult, Scanner scanner) {

        double operand1 = getOperand("first", scanner, currentResult);
        double operand2 = getOperand("second", scanner, currentResult);
        currentResult = operand1 + operand2;

        return currentResult;
    }

    // Method to subtract two operands (first minus second) and returns the result
    // Method first prompts user for first and second operands, using method getOperand()
    public static double subtraction(double currentResult, Scanner scanner) {

        double operand1 = getOperand("first", scanner, currentResult);
        double operand2 = getOperand("second", scanner, currentResult);
        currentResult = operand1 - operand2;

        return currentResult;
    }

    // Method to multiply two operands and returns the result
    // Method first prompts user for first and second operands, using method getOperand()
    public static double multiplication(double currentResult, Scanner scanner) {

        double operand1 = getOperand("first", scanner, currentResult);
        double operand2 = getOperand("second", scanner, currentResult);
        currentResult = operand1 * operand2;

        return currentResult;
    }

    // Method to divide two operands (first divided by second) and returns the result
    // Method first prompts user for first and second operands, using method getOperand()
    public static double division(double currentResult, Scanner scanner) {

        double operand1 = getOperand("first", scanner, currentResult);
        double operand2 = getOperand("second", scanner, currentResult);
        currentResult = operand1 / operand2;

        return currentResult;
    }

    // Method to raise first operand to the power of second operand and returns the result
    // Method first prompts user for first and second operands, using method getOperand()
    public static double exponentiation(double currentResult, Scanner scanner) {

        double operand1 = getOperand("first", scanner, currentResult);
        double operand2 = getOperand("second", scanner, currentResult);

        // Formula for the desired result, using the Math.pow() method
        currentResult = Math.pow(operand1, operand2);

        return currentResult;
    }

    // Method to take the logarithm of second operand with respect to base first operand and returns the result
    // Method first prompts user for first and second operands, using method getOperand()
    public static double logarithm(double currentResult, Scanner scanner) {

        double operand1 = getOperand("first", scanner, currentResult);
        double operand2 = getOperand("second", scanner, currentResult);

        // Formula for the desired result (derived by myself), in terms of the log-base-e method, Math.log()
        currentResult = Math.log(operand2) / Math.log(operand1);

        return currentResult;
    }

    // Method to display sum of all calculation results, number of calculations, and average of all calculations
    public static void average(double sumCalculations, int numCalculations, Scanner scanner) {

        // These statistics are displayed if at least one calculation has been performed
        if (numCalculations > 0) {
            double average = sumCalculations / numCalculations;
            System.out.println();
            System.out.println("Sum of calculations: " + sumCalculations);
            System.out.println("Number of calculations: " + numCalculations);
            System.out.print("Average of calculations: ");
            System.out.printf("%.2f", average); // Display average to two decimal places
            System.out.println();
        }

        // Otherwise, if no calculations have been performed yet, display this Error message
        else {
            System.out.println();
            System.out.println("Error: No calculations yet to average!");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create an instance of Scanner class to accept user input
        int numCalculations = 0; // Total number of calculations performed
        double sumCalculations = 0; // Sum of the results of all calculations performed
        double currentResult = 0; // Result of most recent calculation performed
        int selection = 1; // User's menu selection

        // Keep performing these steps as long as user doesn't select option 0 (Exit Program)
        do {

            // Menu is not to be displayed after user requests calculation statistics (option 7)
            // Otherwise, display menu options and currentResult (0.0 if no calculations yet done)
            if (selection != 7) {
                displayMenu(currentResult);
            }

            // Prompt user to enter a menu selection
            selection = menuSelection(scanner);

            switch (selection) {

                // Perform addition, add result to sumCalculations, increment numCalculations
                case 1:
                    currentResult = addition(currentResult, scanner);
                    sumCalculations += currentResult;
                    numCalculations++;
                    break;

                // Perform subtraction, add result to sumCalculations, increment numCalculations
                case 2:
                    currentResult = subtraction(currentResult, scanner);
                    sumCalculations += currentResult;
                    numCalculations++;
                    break;

                // Perform multiplication, add result to sumCalculations, increment numCalculations
                case 3:
                    currentResult = multiplication(currentResult, scanner);
                    sumCalculations += currentResult;
                    numCalculations++;
                    break;

                // Perform division, add result to sumCalculations, increment numCalculations
                case 4:
                    currentResult = division(currentResult, scanner);
                    sumCalculations += currentResult;
                    numCalculations++;
                    break;

                // Perform exponentiation, add result to sumCalculations, increment numCalculations
                case 5:
                    currentResult = exponentiation(currentResult, scanner);
                    sumCalculations += currentResult;
                    numCalculations++;
                    break;

                // Perform logarithm, add result to sumCalculations, increment numCalculations
                case 6:
                    currentResult = logarithm(currentResult, scanner);
                    sumCalculations += currentResult;
                    numCalculations++;
                    break;

                // Display calculation statistics
                case 7:
                    average(sumCalculations, numCalculations, scanner);
                    break;

                // Default option is option 0, which is to exit from the program
                default:
                    break;
            }

        } while (selection != 0); // Exit loop if user selection menu option 0

        System.out.println();
        // Display this goodbye message when user chooses to exit from the program
        System.out.println("Thanks for using this calculator. Goodbye!");
    }
}
