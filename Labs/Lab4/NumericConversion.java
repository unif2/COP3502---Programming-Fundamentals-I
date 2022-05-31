import java.util.Scanner; // Import Scanner class

public class NumericConversion {

    // METHOD DEFINITIONS

    /*
    Method that decodes a single hexadecimal digit and returns its value.
    Note: I am basing the code for this method on my own code for the method stringToData
    from Project 2b.
    */
    public static short hexCharDecode(char digit) {

        /*
            If digit is a number, convert it to a String, then convert the String to a short,
            each time using the valueOf method.  Return the resulting value.
        */
        if (Character.isDigit(digit)) {
            return Short.valueOf(String.valueOf(digit));
        }

            /*
            If digit is not a number, then it must be a letter from a - f, representing
            the decimal numbers 10 - 15.  Convert to lower case and get the difference between the
            letter and 'a' and cast as a short.  Add this difference to 10 and return the resulting value.
            */
        else {
            short difference = (short)(Character.toLowerCase(digit) - 'a');
            return (short)(difference + (short)(10));
        }
    }

    /*
    Decodes an entire hexadecimal string and returns its decimal value.
    */
    public static Long hexStringDecode(String hex) {

        // If there is a "0x" prefix, remove it from hex.
        hex = hex.replace("0x", "");

        int exponent16 = 0; // Variable to store the exponent of 16 needed for the conversion to decimal
        long decodedHex = 0; // Variable to store the resulting decimal value of hex

        /*
        Use a for loop to traverse hex, starting from the right-most point of hex. For each iteration,
        we get the character at that location, decode its decimal value, then multiply by the appropriate
        power of 16 (right-most point of hex corresponds to initial value of exponent16), and add to decodedHex.
        After the loop ends, the resulting sum stored in decodedHex is hex's decimal value.
        */
        for (int i = hex.length() - 1; i >= 0; i--) {

            // Variable to store decimal value of character at current index of hex
            short digitValue = hexCharDecode(hex.charAt(i));

            // Raise 16 to the power of the current exponent, multiply by digitValue, add to decodedHex
            decodedHex += Math.pow(16, exponent16) * digitValue;

            // Increment exponent16 to get the exponent associated with the next position of hex
            exponent16++;
        }
        return decodedHex;
    }

    /*
    Decodes a binary string and returns its value.
    */
    public static short binaryStringDecode(String binary) {

        // If there is a "0b" prefix, remove it from binary.
        binary = binary.replace("0b", "");

        short exponent2 = 0; // Variable to store the exponent of 2 needed for the conversion to decimal
        short decodedBinary = 0; // Variable to store the resulting decimal value of binary

        /*
        Use a for loop to traverse binary, starting from the right-most point of binary. For each iteration,
        we get the character at that location, decode its decimal value, then multiply by the appropriate
        power of 2 (right-most point of binary corresponds to initial value of exponent2), and add to decodedBinary.
        After the loop ends, the resulting sum stored in decodedBinary is binary's decimal value.
        */
        for (int i = binary.length() - 1; i >= 0; i--) {

            // If there is a '1' at current index of binary, add the appropriate decimal value to decodedBinary.
            if (binary.charAt(i) == '1') {

                // Raise 2 to the power of the current exponent, and add to decodedBinary
                decodedBinary += Math.pow(2, exponent2);
            }

            // Increment exponent2 to get the exponent associated with the next position of binary
            exponent2++;
        }
        return decodedBinary;
    }

    /*
    Decodes a binary string, re-encodes it as hexadecimal, and returns the hexadecimal string.
    */
    public static String binaryToHex(String binary) {

        // If there is a "0b" prefix, remove it from binary.
        binary = binary.replace("0b", "");

        String hexString = ""; // Variable to store hexadecimal string to be returned

        int groupsOf4 = binary.length() / 4; // Number of 4-bit strings in binary
        int lengthRemainingGroup = binary.length() % 4; // Number of remaining bits in binary

        if (lengthRemainingGroup > 0) {

            // If there are remaining bits, get the substring associated with those bits.
            String binaryRemaining = binary.substring(0, lengthRemainingGroup);

            // Get the decimal value of the substring associated with those remaining bits.
            short remainingValue = binaryStringDecode(binaryRemaining);

            /*
            This code snippet is based on my code for method toHexString in Project 2b:
            If data element is greater than 9, convert to A, B, C, D, E, or F by finding the difference
            between data element and 10, add difference to character 'A', cast as char, then add to the string.
            Else, add the data element as is to the string.
            */
            hexString = remainingValue > 9 ? hexString + (char)('A' + remainingValue - 10) : hexString + remainingValue;
        }

        /*
        For each 4-bit group, get the substring associated with the group and find its decimal value.
        Then convert the value to hexadecimal and add to hexString.
        */
        for (int i = 0; i < groupsOf4; i++) {

            /*
            First group starts at index lengthRemainingGroup and ends at index lengthRemainingGroup + 3.
            Next group starts at index lengthRemainingGroup + 4 and ends at index lengthRemainingGroup + 7.
            So the i-th group substring arguments are lengthRemainingGroup + 4i and lengthRemainingGroup + 4(i+1).
            */
            String group = binary.substring(lengthRemainingGroup + 4 * i, lengthRemainingGroup + 4 * (i + 1));

            // Get the decimal value of this 4-bit binary group
            short groupValue = binaryStringDecode(group);

            // Convert to decimal value to hexadecimal and add to hexString (using the same code as above).
            hexString = groupValue > 9 ? hexString + (char)('A' + groupValue - 10) : hexString + groupValue;
        }

        return hexString;
    }

    /*
     Method to display Decoding Menu
    */
    public static void displayMenu() {

        System.out.println("Decoding Menu");
        System.out.println("-------------");
        System.out.println("1. Decode hexadecimal");
        System.out.println("2. Decode binary");
        System.out.println("3. Convert binary to hexadecimal");
        System.out.println("4. Quit");
        System.out.println();
    }

    /*
    Method to prompt user to make a menu selection and accepts user input for the selection.
    Method returns the integer selection. Code adapted from similar code I wrote in Lab 2.
    */
    public static int menuSelection(Scanner scanner) {

        System.out.print("Please enter an option: ");
        int selection = scanner.nextInt();

        /*
        User must select an integer in the range [1, 4].
        Keep prompting user for a menu selection until they input an integer in this range.
        */
        while (selection < 1 || selection > 4) {
            System.out.println();
            System.out.println("Error: Invalid selection!");
            System.out.print("Please enter an option: ");
            selection = scanner.nextInt();
        }

        return selection;
    }

    /*
     Method to prompt user for, and return, a numeric string to convert.
    */
    public static String stringToConvert(Scanner scanner) {

        System.out.print("Please enter the numeric string to convert: ");
        return scanner.next();

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); // Create an instance of Scanner class to accept user input
        int selection = 1; // User's menu selection

        // Keep performing these steps as long as user doesn't select option 4 (to Quit).
        do {

            // Display menu of options to the user.
            displayMenu();

            // Prompt user to enter a menu selection
            selection = menuSelection(in);

            switch (selection) {

                // Decode hexadecimal.  Prompt user for string to convert, convert string, print the result.
                case 1:
                    String inputString1 = stringToConvert(in);
                    long result1 = hexStringDecode(inputString1);
                    System.out.println("Result: " + result1);
                    System.out.println();
                    break;

                // Decode binary.  Prompt user for string to convert, convert string, print the result.
                case 2:
                    String inputString2 = stringToConvert(in);
                    short result2 = binaryStringDecode(inputString2);
                    System.out.println("Result: " + result2);
                    System.out.println();
                    break;

                // Convert binary to hexadecimal.  Prompt user for string to convert, convert string, print the result.
                case 3:
                    String inputString3 = stringToConvert(in);
                    String result3 = binaryToHex(inputString3);
                    System.out.println("Result: " + result3);
                    System.out.println();
                    break;

                // Default option is option 4, which is to quit the program
                default:
                    break;
            }

        } while (selection != 4); // Exit loop if user selects menu option 4

        // Display this goodbye message when user chooses to quit the program
        System.out.println("Goodbye!");
    }
}
