import java.util.Scanner; // Import Scanner class

public class RleProgram {

    // METHOD DEFINITIONS

    /*
    Translates data (RLE or raw) to a hexadecimal string (without delimiters).
    This method can also aid debugging.
    */
    public static String toHexString(byte[] data) {

        String hexString = "";

        for (byte element: data) {

            /*
            If data element is greater than 9, convert to a, b, c, d, e, or f by finding the difference
            between data element and 10, add difference to character 'a', cast as char, then add to the string
            Else, add the data element as is to the string
            */
            hexString = element > 9 ? hexString + (char)('a' + element - 10) : hexString + element;
        }

        return hexString;
    }

    /*
    Returns number of runs of data in an image data set.
    Double this result for length of encoded (RLE) byte array.
    */
    public static int countRuns(byte[] flatData) {

        // Variable to store number of runs
        int numRuns = 0;

        // Variable to count the number of times the same element value appears in successive indices
        int numSamePixels = 0;

        for (int i = 0; i < flatData.length; i++) {

            /*
            First element (i = 0) will always be the start of a run no matter what the next element's value is.
            When the max run length is reached, a new run has finished, so we set numSamePixels to 0 below,
            and even when i is not 0, we still execute this if statement and increase numRuns by 1 and
            numSamePixels becomes 1.  Then we skip the rest of the for loop.
            */
            if (i == 0 || numSamePixels == 0) {
                numRuns++;
                numSamePixels++;
                continue;
            }

            // If the current element is the same as the previous element, increase numSamePixels by 1
            if (flatData[i] == flatData[i - 1]) {
                numSamePixels++;
            }

            /*
            If the current element is different from previous element, a new run has started.
            Increase numRuns by 1 and set numSamePixels to 1 because the current element represents
            the start of a new run.
            */
            if (flatData[i] != flatData[i - 1]) {
                numRuns++;
                numSamePixels = 1;
            }

            // Max run length is 15. If numSamePixels is 15, set numSamePixels to 0.
            if (numSamePixels == 15) {
                numSamePixels = 0;
            }
        }

        return numRuns;
    }

    /*
    Returns encoding (in RLE) of the raw data passed in.
    This method is used to generate RLE representation of the data.
    */
    public static byte[] encodeRle(byte[] flatData) {

        /*
        For each run, the RLE data is an array of the number of pixels in each run and the associated
        color integer value for each run.  Thus, the length of the resulting array should be twice the
        count of runs.
        */
        byte[] rlEncoded = new byte[2 * countRuns(flatData)];
        int flatIndex = 0; // Variable to store current index of flatData

        //For loop to assign values to rlEncoded
        for (int encodedIndex = 0; encodedIndex < rlEncoded.length; encodedIndex++) {

            /*
            Variable to store the count of pixels in each run.
            Initialized to 1 because the beginning of every run has 1 pixel to start it.
            */
            byte pixelCount = 1;

            /*
             Loop through elements of flatData.  If there exists an element after the current one AND
             the current element is the same as the element after it, increase pixelCount by 1, and
             move onto the next element of flatData.  Continue doing this until either you've reached
             the end of the array or until the current element is not the same as the one after it.
             If you've reached the maximum run length (15), break.
            */
            while ((flatIndex + 1 != flatData.length) && (flatData[flatIndex + 1] == flatData[flatIndex])) {
                pixelCount++;
                flatIndex++;

                // If max run length reached, break out of loop
                if (pixelCount == 15) {
                    break;
                }
            }

            // Assign pixelCount of this run as element of rlEncoded
            rlEncoded[encodedIndex] = pixelCount;

            // Increment to the next index of rlEncoded
            encodedIndex++;

            // Assign the pixel value in this run as next element of rlEncoded
            rlEncoded[encodedIndex] = flatData[flatIndex];

            // Move onto the next run and the next element of flatData
            flatIndex++;
        }

        return rlEncoded;
    }

    /*
    Returns size of decompressed RLE data.
    This method is used to generate flat data from RLE encoding.
    */
    public static int getDecodedLength(byte[] rleData) {

        // Variable to store size of decompressed data array
        int decodedLength = 0;

        /*
         Starting with index 0, loop through every other index of the encoded data array (by incrementing
         the index by 2) since it is at those indices that have the pixel counts of each run of the
         decompressed data array.  The sum of those gives the size of the decompressed data array.
        */
        for (int i = 0; i < rleData.length; i +=2) {
            decodedLength += rleData[i];
        }

        return decodedLength;
    }

    /*
    Returns the decoded data set from RLE encoded data.
    This method decompresses the RLE data for use. This method is the inverse of encodeRle.
    */
    public static byte[] decodeRle(byte[] rleData) {

        // Variable to store the decoded data array
        byte[] decoded = new byte[getDecodedLength(rleData)];

        // Variable to keep track of decoded array index as we assign values to decoded array
        int decodedIndex = 0;

        /*
        Loop through each pair of elements of the encoded data, starting at index 0.
        The start of each pair is at index encodedIndex + 2, so we increment the index by 2.
        The first element of the pair is the run length of the decoded data for the current run.
        The second element of the pair is the pixel value of each element of the current run.
        */
        for (int encodedIndex = 0; encodedIndex < rleData.length; encodedIndex += 2) {

            // Run length of current run
            int runLength = rleData[encodedIndex];

            // Pixel value of each element of the current run
            byte pixelVal = rleData[encodedIndex + 1];

            /*
            For loop to assign values to the decoded data array.  Traverse the loop runLength number of times,
            each time assigning the same value, pixelVal, until the end of the run has been reached.
            */
            for (int i = 0; i < runLength; i++) {

                // Assign pixelVal at array index decodedIndex
                decoded[decodedIndex] = pixelVal;

                // Increment decoded array index by 1 for the next value assignment in the decoded array
                decodedIndex++;
            }
        }

        return decoded;
    }

    /*
    Translates a string in hexadecimal format into byte data (can be raw or RLE).
    This method is the inverse of toHexString.
    */
    public static byte[] stringToData(String dataString) {

        // Variable to store data array
        byte[] data = new byte[dataString.length()];

        // Loop through the entire data string
        for (int i = 0; i < dataString.length(); i++) {

            // Variable to store value of current character in the string
            char currentChar = dataString.charAt(i);

            /*
            If the current character is a digit, convert it to a String,
            then convert the String to a byte, and assign it to the current
            element of the data array
            */
            if (Character.isDigit(currentChar)) {
                data[i] = Byte.valueOf(String.valueOf(currentChar));
            }

            /*
            If the current letter is not a digit, then it must be a letter from a - f, representing
            the decimal numbers 10 - 15.  Convert to lower case and get the difference between the
            letter and 'a' and cast as a byte.  Add this difference to 10 and assign the result to
            the current element of the data array.
            */
            else {
                byte difference = (byte)(Character.toLowerCase(currentChar) - 'a');
                data[i] = (byte)(difference + (byte)(10));
            }
        }

        return data;
    }

    /*
    Translates RLE data into a human-readable representation. The output contains delimiters ':'
    to separate the data for different runs.
    */
    public static String toRleString(byte[] rleData) {

        String rleStr = ""; // Variable to store string to return

        /*
        Loop through each pair of elements of the encoded data, starting at index 0.
        The start of each pair is at index i + 2, so we increment i by 2.
        The first element of the pair is the run length of the decoded data for the current run.
        The second element of the pair is the pixel value of each element of the current run.
        */
        for (int i = 0; i < rleData.length; i += 2) {

            // Convert first element of the pair to a String and add it to rleStr.
            rleStr += String.valueOf(rleData[i]);

            /*
            If pixel value is greater than 9, convert to a, b, c, d, e, or f by finding the difference
            between the value and 10, add difference to character 'a', cast as char, then add to rleStr.
            Else, add the value as is to rleStr.
            */
            rleStr = rleData[i + 1] > 9 ? rleStr + (char)('a' + rleData[i + 1] - 10) : rleStr + rleData[i + 1];

            // Add the : delimiter, but not after the final pair has been added to rleStr.
            if (i != rleData.length - 2) {
                rleStr += ":";
            }
        }

        return rleStr;
    }

    /*
    Translates a string in human-readable RLE format, with delimiter ':', into RLE byte data.
    This method is the inverse of toRleString.
    */
    public static byte[] stringToRle(String rleString) {

        int numDelimiters = 0; // Variable to store the number of delimiters in rleString

        int index = rleString.indexOf(':', 0); // Index of first delimiter in rleString

        /*
        If index is not -1, then a delimiter is present.  Increment numDelimiters by 1.
        Find the index of the next delimiter starting from the previous index + 1.
        Loop exits when no more delimiters are present.
         */
        while (index != -1) {
            numDelimiters++;
            index = rleString.indexOf(':', index + 1);
        }

        /*
        There are (numDelimiters + 1) pairs of RLE data in rleString.  Create a new byte array
        to store these data pairs.  The length of the array should be 2 * (numDelimiters + 1).
        This is the byte array that the method with return.
         */
        byte[] rleData = new byte[2 * (numDelimiters + 1)];

        int prevIndex = 0; // Variable to keep track of index of previous delimiter.

        /*
        For loop to assign values to rleData. Element at position i is the run length.
        Element at position i + 1 is the pixel value.  Each loop iteration fills in these two
        values, so the loop increments i by 2, starting at 0.
        Loop through each pair of elements of the encoded data, starting at index 0.
        */
        for (int i = 0; i < rleData.length; i += 2) {
            index = rleString.indexOf(':', prevIndex); // Index of first delimiter in rleString
            String rleSub; // Variable to store the substring containing each run length and pixel value

            /* If no delimiter is present, then either:
            1. rleString contains only one pair of data, thus prevIndex is still 0, so that
            rleSub defined below and rleString point to the same string, or:
            2. The last data pair has been reached in a rleString with multiple delimiters, so that
            prevIndex (after being incremented below) as an argument to substring will give the substring
            of the last remaining data pair.
            */
            if (index == -1) {
                rleSub = rleString.substring(prevIndex);
            }

            /*
            Else, there is a delimiter present, so form the substring of rleString from prevIndex
            to the index of the delimiter that was found.
             */
            else {
                rleSub = rleString.substring(prevIndex, index);
            }

            /*
             Get the Byte value of the part of rleSub that has the run length. The last character of
             rleSub is the pixel value.  So the substring in this line must be the run length.
             Assign this Byte value to the i-th position of rleData.
             */
            rleData[i] = Byte.valueOf(rleSub.substring(0, rleSub.length() - 1));

            // Variable to store the hexadecimal digit character at the end of rleSub
            char hexDigit = rleSub.charAt(rleSub.length() - 1);

            /*
            If hexDigit is a digit, convert it to a String, then convert the String
            to a byte, and assign it to the second element of the data pair, which is at
            the (i + 1)-th position of rleData.
            */
            if (Character.isDigit(hexDigit)) {
                rleData[i + 1] = Byte.valueOf(String.valueOf(hexDigit));
            }

            /*
            If hexDigit is not a digit, then it must be a letter from a - f, representing
            the decimal numbers 10 - 15.  Convert to lower case and get the difference between the
            letter and 'a' and cast as a byte.  Add this difference to 10 and assign the result to
            the second element of the data pair, which is at the (i + 1)-th position of rleData.
            */
            else {
                byte difference = (byte)(Character.toLowerCase(hexDigit) - 'a');
                rleData[i + 1] = (byte)(difference + (byte)(10));
            }

            // Increment prevIndex to get the position of the start of next substring having the next data pair.
            prevIndex = index + 1;
        }

        return rleData;
    }

    public static void main(String[] args) {

        // 1. Display welcome message
        System.out.println("Welcome to the RLE image encoder!");
        System.out.println();

        // 2. Display color test with the message
        System.out.println("Displaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);
        System.out.println();
        System.out.println();

        Scanner scanner = new Scanner(System.in); // New Scanner object to accept user input
        byte[] imageData = null; // Byte array variable to reference flat image data after loading image
        int menuOption; // Variable to store the menu option (0-9)
        String filename; // Variable referring to filename of image file

        do { // Keep running this block while user does not select option 0 (Exit)

            // 3. Display the menu (0-9) and prompt user for their menu selection
            System.out.println();
            System.out.println("RLE Menu");
            System.out.println("--------");
            System.out.println("0. Exit");
            System.out.println("1. Load File");
            System.out.println("2. Load Test Image");
            System.out.println("3. Read RLE String");
            System.out.println("4. Read RLE Hex String");
            System.out.println("5. Read Data Hex String");
            System.out.println("6. Display Image");
            System.out.println("7. Display RLE String");
            System.out.println("8. Display Hex RLE Data");
            System.out.println("9. Display Hex Flat Data");
            System.out.println();
            System.out.print("Select a Menu Option: ");

            // Prompt user for their menu selection
            menuOption = scanner.nextInt();

            // User selects option to load file and is prompted to enter the image filename
            if (menuOption == 1) {
                System.out.print("Enter name of file to load: ");
                filename = scanner.next();

                // Load image file, store into (flat) imageData byte array
                imageData = ConsoleGfx.loadFile(filename);
            }

            // User selects option to load the test image
            else if (menuOption == 2) {
                // Load the test image, store into (flat) imageData byte array
                imageData = ConsoleGfx.testImage;
                System.out.println("Test image data loaded.");
            }

            // User selects option to read RLE string in decimal notation with delimiters
            else if (menuOption == 3) {
                System.out.print("Enter an RLE string to be decoded: ");
                String rleDecString = scanner.next();

                // Convert the user-entered string to an RLE data byte array
                byte[] rleDecimalData = stringToRle(rleDecString);

                // Convert the RLE data byte array to a flat data byte array
                byte[] flatDecimalData = decodeRle(rleDecimalData);

                // Convert the flat data byte array to a hexadecimal string, converting decimals to hexadecimals
                String hexString = toHexString(flatDecimalData);

                // Convert the hexadecimal string to a flat data byte array, imageData
                imageData = stringToData(hexString);
            }

            // User selects option to read RLE string in hexadecimal notation without delimiters
            else if (menuOption == 4) {
                System.out.print("Enter the hex string holding RLE data: ");
                String rleHexString = scanner.next();

                // Convert the user-entered string to an RLE data byte array
                byte[] rleData = stringToData(rleHexString);

                // Convert the RLE data byte array to a flat data byte array, imageData
                imageData = decodeRle(rleData);

            }

            // User selects option to read flat data string in hexadecimal notation
            else if (menuOption == 5) {
                System.out.print("Enter the hex string holding flat data: ");
                String flatHexData = scanner.next();

                // Convert the user-entered string to a flat data byte array, imageData
                imageData = stringToData(flatHexData);
            }

            // User selects option to display the image that has been previously loaded
            else if (menuOption == 6) {
                System.out.println("Displaying image...");

                // An image was previously loaded, stored in imageData
                if (imageData != null) {
                    // Displays the image, stored in imageData
                    ConsoleGfx.displayImage(imageData);
                }

                // No image was previously loaded - display error message and go back to the top
                else {
                    System.out.println("(no data)");
                }
            }

            // User selects option to display data as human-readable RLE representation with delimiters
            else if (menuOption == 7) {
                System.out.print("RLE representation: ");

                // An image was previously loaded, stored in imageData
                if (imageData != null) {
                    // Convert previously-loaded imageData to RLE data byte array
                    byte[] rleData = encodeRle(imageData);

                    // Convert the RLE data byte array to a human-readable RLE representation string with delimiters
                    String rleString = toRleString(rleData);
                    System.out.println(rleString);
                }

                // No image was previously loaded - display error message and go back to the top
                else {
                    System.out.println("(no data)");
                }
            }

            // User selects option to display data as RLE hexadecimal representation without delimiters
            else if (menuOption == 8) {
                System.out.print("RLE hex values: ");

                // An image was previously loaded, stored in imageData
                if (imageData != null) {
                    // Convert previously-loaded imageData to RLE data byte array
                    byte[] rleData = encodeRle(imageData);

                    // Convert the RLE data byte array to RLE hexadecimal string representation without delimiters
                    String hexString = toHexString(rleData);
                    System.out.println(hexString);
                }

                // No image was previously loaded - display error message and go back to the top
                else {
                    System.out.println("(no data)");
                }
            }

            // User selects option to display data as flat hexadecimal representation without delimiters
            else if (menuOption == 9) {
                System.out.print("Flat hex values: ");

                // An image was previously loaded, stored in imageData
                if (imageData != null) {
                    // Convert previously-loaded imageData to flat hexadecimal string representation without delimiters
                    String hexString = toHexString(imageData);
                    System.out.println(hexString);
                }

                // No image was previously loaded - display error message and go back to the top
                else {
                    System.out.println("(no data)");
                }
            }

            // User hasn't selected any of the above options nor selects 0
            else if (menuOption != 0) {
                System.out.println("Error! Invalid input.");
            }

        } while (menuOption != 0);
    }
}
