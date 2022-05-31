import java.util.Scanner; // Import Scanner class for user input.

public class PakuriProgram {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); // Create Scanner object to capture user input.
        int menuOption = 1; // Declare and initialize Pakudex menu option selected by user to 1.
        int capacity = 1; // Declare and initialize Pakudex capacity to 1.
        boolean validCapacity = false; // Stores whether capacity from user is valid.  Initialize to false.

        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        // Keep prompting user for a capacity until they enter a valid capacity value.
        while(!validCapacity) {
            System.out.print("Enter max capacity of the Pakudex: ");

            // Parse input for Integer.  If successful, assign it to capacity.
            try {
                capacity = Integer.parseInt(in.next());
                // Value of capacity is valid if it's at least 1.
                if (capacity < 1) {
                    System.out.println("Please enter a valid size.");
                    validCapacity = false;
                }
                else {
                    validCapacity = true;
                }
            }
            // If NumberFormatException occurs, display error message and set validCapacity to false.
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid size.");
                validCapacity = false;
            }
        }

        // Once a valid capacity is entered, create a Pakudex object with that capacity.
        Pakudex pDex = new Pakudex(capacity);

        // Run below code as long as user does not choose to Exit program (menu option 6).
        while(menuOption != 6) {

            // Display menu to user and prompt user for a menu option.
            menuOption = pDex.promptMenuOption(in);

            switch (menuOption) {

                // User wants to list the Pakuri species currently in the Pakudex.
                case 1:
                    pDex.listPakuri();
                    break;

                // User wants to show the statistics of the Pakuri species of their choice.
                case 2:
                    pDex.showPakuri(in);
                    break;

                // User wants to add a new Pakuri species to the Pakudex.
                case 3:
                    pDex.addPakuriStatus(in);
                    break;

                // User wants to evolve the Pakuri species of their choice.
                case 4:
                    pDex.evolveSpeciesStatus(in);
                    break;

                // User wants to sort the Pakuri in the Pakudex (by species).
                case 5:
                    pDex.sortPakuriStatus();
                    break;

                // User wants to exit the program.
                case 6:
                    System.out.println("Thanks for using Pakudex! Bye!");
                    break;

                // User's menu selection was invalid (either not one of the menu choices, or not an integer).
                default:
                    System.out.println("Unrecognized menu selection!");
            }
        }
    }
}
