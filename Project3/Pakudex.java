import java.util.Arrays; // Import Arrays class to use its sort() method.
import java.util.Scanner; // Import Scanner class to pass Scanner objects in some methods below.

public class Pakudex {

    private int capacity; // Maximum number of Pakuri species the Pakudex can contain.
    private int size; // Number of Pakuri species currently in the Pakudex.
    private Pakuri[] arrayPakuri; // Refers to the array of Pakuri objects Pakudex uses to store the Pakuri.

    // Default constructor.  Default capacity is 20.
    public Pakudex() {

        capacity = 20;
        // Initialize arrayPakuri to hold a maximum number of capacity Pakuri.
        arrayPakuri = new Pakuri[capacity];
        // When Pakudex object is created, no Pakuri are stored, so size is initialized to 0.
        size = 0;
        System.out.println("The Pakudex can hold " + capacity + " species of Pakuri.");
    }

    // Constructor for Pakudex, allowing for any capacity of the Pakudex object.
    public Pakudex(int capacity) {

        // Set capacity for this Pakudex to be parameter capacity.
        this.capacity = capacity;
        // Initialize arrayPakuri to hold a maximum number of capacity Pakuri.
        arrayPakuri = new Pakuri[capacity];
        // When Pakudex object is created, no Pakuri are stored, so size is initialized to 0.
        size = 0;
        System.out.println("The Pakudex can hold " + this.capacity + " species of Pakuri.");
    }

    // Returns number of Pakuri currently being stored in the Pakudex.
    public int getSize() { return size; }

    // Returns capacity of the Pakudex.
    public int getCapacity() { return capacity; }

    // Returns String array containing the species as ordered in the Pakudex.
    public String[] getSpeciesArray() {

        // Return null if no Pakuri are in the Pakudex yet.
        if (size == 0) {
            return null;
        }

        // Create new array to store the species of all the Pakuri in the Pakudex.
        String[] speciesArray = new String[size];

        // Iterate through arrayPakuri until last one is reached (where index is size - 1).
        for (int i = 0; i < size; i++) {
            // Get species of the i-th Pakuri and store value in i-th element of speciesArray.
            speciesArray[i] = arrayPakuri[i].getSpecies();
        }

        return speciesArray;
    }

    // Returns int array containing attack, defense, and speed statistics of species at indices 0, 1, and 2.
    public int[] getStats(String species) {

        // Only attempt to get stats if the size is nonzero.
        if (size > 0) {
            // Iterate through arrayPakuri to search for Pakuri with name species, until last one is reached.
            for (int i = 0; i < size; i++) {
                // If the i-th Pakuri is a match, create new int array storing the values to be returned.
                if ((arrayPakuri[i].getSpecies()).equals(species)) {
                    int[] speciesStats = new int[]{arrayPakuri[i].getAttack(),
                            arrayPakuri[i].getDefense(), arrayPakuri[i].getSpeed()};
                    return speciesStats;
                }
            }
        }

        // If size is 0 or no Pakuri with name species exists, return null.
        return null;
    }

    /*
    Sorts Pakuri objects in the Pakudex according to Java standard lexicographical ordering of species name.
    Note: Pakudex is checked to see if size is 0 in the method sortPakuriStatus defined below.
    sortPakuri is only called if Pakudex is not empty.
    */
    public void sortPakuri() {

        // Create Pakuri array of length size to store the non-null Pakuri objects from arrayPakuri.
        Pakuri[] tempArray = new Pakuri[size];

        // Copy i-th Pakuri object in arrayPakuri to i-th element of tempArray until last one is reached.
        for (int i = 0; i < size; i++) {
            tempArray[i] = arrayPakuri[i];
        }

        // Sort tempArray by species name, using the Overriden compareTo() method in Pakuri class.
        Arrays.sort(tempArray);

        // Copy i-th Pakuri object in tempArray to i-th element of arrayPakuri.  arrayPakuri is now sorted.
        for (int i = 0; i < size; i++) {
            arrayPakuri[i] = tempArray[i];
        }
    }

    /*
    Method attempts to add species to the Pakudex.  Returns true if successful, otherwise returns false.
    Note: Pakudex is checked to see if it's already full in the method addPakuriStatus defined below.
    addPakuri is only called if Pakudex is not already full.
    */
    public boolean addPakuri(String species) {

        // If size is nonzero, search through Pakudex to see if species is already there.
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                // If species is already there, do not add it to Pakudex, return false.
                if ((arrayPakuri[i].getSpecies()).equals(species)) {
                    return false;
                }
            }
        }

        // Pakudex doesn't already have the species; add it to the Pakudex (add it to arrayPakuri at index size).
        arrayPakuri[size] = new Pakuri(species);
        // Species is added, so size of Pakudex must go up by 1.
        size++;
        return true;
    }

    /*
    Method attempts to evolve species within the Pakudex.  Returns true if successful, otherwise returns false.
    Note: Pakudex is checked to see if size is 0 in the method evolveSpeciesStatus defined below.
    evolveSpecies is only called if Pakudex is not empty.
    */
    public boolean evolveSpecies (String species) {

        // Search through Pakudex to see if species is to evolve is there.
        for (int i = 0; i < size; i++) {
            // If species is there, operate on it with the evolve() method and return true.
            if (arrayPakuri[i].getSpecies().equals(species)) {
                arrayPakuri[i].evolve();
                return true;
            }
        }

        // Pakudex does not contain the species to evolve, so return false.
        return false;
    }

    // Displays menu of available actions when using the Pakudex.
    public void displayMenu() {

        System.out.println();
        System.out.println("Pakudex Main Menu");
        System.out.println("-----------------");
        System.out.println("1. List Pakuri");
        System.out.println("2. Show Pakuri");
        System.out.println("3. Add Pakuri");
        System.out.println("4. Evolve Pakuri");
        System.out.println("5. Sort Pakuri");
        System.out.println("6. Exit");
        System.out.println();
        System.out.print("What would you like to do? ");

    }

    // Prompts user for the menu option they wish to select and returns their selection as an int.
    public int promptMenuOption(Scanner scanner) {

        int menuOption; // Variable to store user's selected menu option.

        // Show the menu to the user.
        displayMenu();

            // Parse input for Integer.  If successful, assign it to menuOption.
            try {
                menuOption = Integer.parseInt(scanner.next());
            }
            // If NumberFormatException occurs, set menuOption to 0.
            catch (NumberFormatException e) {
                menuOption = 0;
            }

        return menuOption;
    }

    // Displays a list to the user of all species stored in the Pakudex.
    public void listPakuri() {

        // Get array of species currently in Pakudex and store in speciesArray.  Its length is equal to size.
        String[] speciesArray = getSpeciesArray();

        // If there are species in speciesArray, list them and number them from 1 to size and return.
        if (speciesArray != null) {
            System.out.println("Pakuri In Pakudex:");
            for (int i = 0; i < speciesArray.length; i++) {
                System.out.println((i + 1) + ". " + speciesArray[i]);
            }
            return;
        }

        // If there are no species in speciesArray, print this message.
        System.out.println("No Pakuri in Pakudex yet!");
    }

    // Prompts user for a species and displays the stats of the species if it exists in the Pakudex.
    public void showPakuri(Scanner scanner) {

        // Prompt user for species and attempt to get its stats.  Store stats in array speciesStats.
        System.out.print("Enter the name of the species to display: ");
        String species = scanner.next();
        int[] speciesStats = getStats(species);

        // If the stats were successfully obtained, print them to the user and return.
        if (speciesStats != null) {
            System.out.println();
            System.out.println("Species: " + species);
            System.out.println("Attack: " + speciesStats[0]);
            System.out.println("Defense: " + speciesStats[1]);
            System.out.println("Speed: " + speciesStats[2]);
            return;
        }

        // If the stats were not obtained (species not in the Pakudex), print this message.
        System.out.println("Error: No such Pakuri!");
    }

    // Prompts user for a species to add, attempts to add it, and displays status of the attempt.
    public void addPakuriStatus(Scanner scanner) {

        // If Pakudex already full, don't call addPakuri.  Instead, print this message and return.
        if (getSize() == capacity) {
            System.out.println("Error: Pakudex is full!");
            return;
        }

        // Otherwise, prompt user for a species to add and call addPakuri.
        System.out.print("Enter the name of the species to add: ");
        String species = scanner.next();
        // True if species added, false if not added.
        boolean pakuriAdded = addPakuri(species);

        // If species was added, print that the attempt was successful.
        if (pakuriAdded) {
            System.out.println("Pakuri species " + species + " successfully added!");
        }

        // If species not added, it was because species already exists in the Pakudex.  Print this message.
        else {
            System.out.println("Error: Pakudex already contains this species!");
        }
    }

    // Prompts user for a species to evolve, attempts to evolve it, and displays status of the attempt.
    public void evolveSpeciesStatus(Scanner scanner) {

        // If Pakudex is empty, don't call evolveSpecies.  Instead, print this message and return.
        if (getSize() == 0) {
            System.out.println("No Pakuri in Pakudex yet!");
            return;
        }

        System.out.print("Enter the name of the species to evolve: ");
        String species = scanner.next();
        boolean pakuriEvolved = evolveSpecies(species);

        if (pakuriEvolved) {
            System.out.println(species + " has evolved!");
        }

        else {
            System.out.println("Error: No such Pakuri!");
        }
    }

    // Attempts to sort Pakuri in the Pakudex, and displays status of the attempt.
    public void sortPakuriStatus() {

        // If Pakudex is not empty, sort the Pakuri as specified in sortPakuri(), and return.
        if (getSize() > 0) {
            sortPakuri();
            System.out.println("Pakuri have been sorted!");
            return;
        }

        // Otherwise, Pakudex is empty, and there is nothing to sort.
        System.out.println("No Pakuri in Pakudex yet!");
    }
}
