import java.util.Scanner; // Import the Scanner class

public class Blackjack {

   //METHOD DEFINITIONS

   // Method for displaying the menu options 1-4
   public static void displayMenu() {
      System.out.println("1. Get another card");
      System.out.println("2. Hold hand");
      System.out.println("3. Print statistics");
      System.out.println("4. Exit\n");
      System.out.print("Choose an option: ");
   }

   // Method to accept player input for menu choice after displaying menu
   // Method will return the integer called choice
   public static int menuChoice(Scanner scnr) {
      int choice; // Variable to store user-provided menu option (from the list of four options)
      displayMenu();// Display the menu
      choice = scnr.nextInt();// Player inputs their choice

      // Choice must be an integer between 1 and 4, inclusive
      // Keep requiring a menu choice until the requirement is satisfied
      // If integer is less than 1 or greater than 4, requirement is not satisfied
      while (choice < 1 || choice > 4) {
         System.out.println("\nInvalid input!");
         System.out.println("Please enter an integer value between 1 and 4.\n");
         displayMenu();
         choice = scnr.nextInt();// Enter an integer until requirement is satisfied
      }
      System.out.print("\n");
      return choice;
   }

   // Method to display games statistics: Number of player wins, number of dealer wins,
   // number of ties, total games played, and percent of games won by the player
   public static void statistics(int playerWins, int dealerWins, int ties) {
      int gamesPlayed = playerWins + dealerWins + ties;
      System.out.println("Number of Player wins: " + playerWins);
      System.out.println("Number of Dealer wins: " + dealerWins);
      System.out.println("Number of tie games: " + ties);
      System.out.println("Total # of games played is: " + gamesPlayed);

      // If no games played, percentage of player wins will be 0% (avoiding dividing by 0)
      if (gamesPlayed == 0) {
         System.out.println("Percentage of Player wins: 0.0%\n");
      }

      else {
         System.out.print("Percentage of Player wins: ");
         // Display percentage with one digit after the decimal point
         System.out.printf("%.1f", 100.0 * playerWins / gamesPlayed);
         System.out.println("%\n");
      }
   }

   // Method that draws a random number from 1-13, representing player's card, returns card
   public static int playerDrawCard(P1Random rng) {
      int card; // This is the integer value of the card, ranging from 1-10 inclusive

      // Draw a random number between 1 and 13, inclusive, and store in variable num
      int num = rng.nextInt(13) + 1;

      switch (num) { // There are 13 possibilities for num, 1-13
         case 1: // 1 corresponds to an Ace, having a card value of 1
            card = num;
            System.out.println("Your card is a ACE!");
            break;

         case 11: // 11 corresponds to a Jack, having a card value of 10
            card = 10;
            System.out.println("Your card is a JACK!");
            break;

         case 12: // 12 corresponds to a Queen, having a card value of 10
            card = 10;
            System.out.println("Your card is a QUEEN!");
            break;

         case 13: // 13 corresponds to a King, having a card value of 10
            card = 10;
            System.out.println("Your card is a KING!");
            break;

         default: // possibilities 2-10, corresponding to card values of 2-10, respectively
            card = num;
            System.out.println("Your card is a " + card + "!");
            break;
      }

      return card;
   }

   // Method that draws a random number, representing dealer's hand, returns hand
   public static int dealerDrawCard(P1Random rng) {
      // Draw a random number between 16 and 26, inclusive.  This is the dealer's hand.
      int hand = rng.nextInt(11) + 16;
      System.out.println("Dealer's hand: " + hand);
      return hand;
   }

   // MAIN PROGRAM
   public static void main(String [] args) {
      int menuSelection = 1; // Initialize player's menu selection (an integer from 1 to 4) to 1
      int gameNumber; // Game number of current game
      int gamesPlayed = 0; // Total number of games played, initially 0
      int playerWins = 0; // Total number of player wins, initially 0
      int dealerWins = 0; // Total number of dealer wins, initially 0
      int ties = 0; // Total number of ties, initially 0
      int playerCard; // Player's card
      int dealerHand = 0; // Dealer's hand
      int playerTotalValue = 0; // Total value of player's hand
      boolean newGame = true; // newGame is set to true at the start of every new game
      P1Random rng = new P1Random(); // Random number generator instance
      Scanner scnr = new Scanner(System.in); // Create Scanner object to pass to menuChoice()

      // Execute the code in this do-while loop as long as player chooses not to exit (option 4)
      do {
         if (newGame) { // Start of new game, initialize the game statistics
            playerTotalValue = 0;
            dealerHand = 0;
            gameNumber = gamesPlayed + 1; // Current game number is one more than games played
            System.out.println("\nSTART GAME #" + gameNumber + "\n");
            playerCard = playerDrawCard(rng); // Draw player's first card
            playerTotalValue += playerCard; // Player's hand is updated
            System.out.println("Your hand is: " + playerTotalValue + "\n");
            menuSelection = menuChoice(scnr); // Ask player to make a choice in the menu
            newGame = false; // game has started, so this is not a new game
         }

         if (menuSelection == 1) {// Corresponds to player wishing to draw another card
            playerCard = playerDrawCard(rng);// Draw card
            playerTotalValue += playerCard; // Update hand value
            System.out.println("Your hand is: " + playerTotalValue + "\n");

            if (playerTotalValue > 21) {// Player loses if hand is over 21
               System.out.println("You exceeded 21! You lose.");
               dealerWins++; // Dealer wins, so increment dealerWins by 1
               gamesPlayed++; // Game is finished, so increment increment gamesPlayed by 1
               newGame = true; // Start a new game
            }

            else if (playerTotalValue == 21) {// Player automatically wins if hand is 21
               System.out.println("BLACKJACK! You win!");
               playerWins++; // Player wins, so increment playerWins by 1
               gamesPlayed++; // Game is finished, so increment gamesPlayed by 1
               newGame = true; // Start a new game
            }

            else { // Player does not automatically win, so give player a choice of what to do
               menuSelection = menuChoice(scnr);
               newGame = false; // Still in current game, not a new game
            }
         }

         else if (menuSelection == 3) {// Player wishes to display games statistics
            statistics(playerWins, dealerWins, ties);
            menuSelection = menuChoice(scnr); // Player must now make their next choice
            newGame = false; // Takes place during a game, so not a new game
         }

         else if (menuSelection == 2) {// Player wishes to hold, so dealer draws their hand
            dealerHand = dealerDrawCard(rng); // Draw hand
            System.out.println("Your hand is: " + playerTotalValue + "\n");

            // Dealer loses if their hand is over 21 or less than player's hand
            if (dealerHand > 21 || dealerHand < playerTotalValue) {
               System.out.println("You win!");
               playerWins++; // Player wins, so increment playerWins by 1
               gamesPlayed++; // Game is finished, so increment gamesPlayed by 1
               newGame = true; // Start a new game
            }

            // Dealer wins if their hand is greater than the player's hand
            // If program executes this code, it means dealer's hand is less than 21,
            // otherwise previous if statement would have executed
            else if (dealerHand > playerTotalValue) {
               System.out.println("Dealer wins!");
               dealerWins++; // Dealer wins, so increment dealerWins by 1
               gamesPlayed++; // Game is finished, so increment gamesPlayed by 1
               newGame = true; // Start a new game
            }

            else { // Dealer's hand and player's hand are the same, so game ends in a tie.
               // Note: It's impossible for dealer to tie with 21 because player automatically
               // wins with a hand of 21, from line 144.
               System.out.println("It's a tie! No one wins!");
               ties++; // Game ends in a tie, so increment ties by 1
               gamesPlayed++; // Game is finished, so increment gamesPlayed by 1
               newGame = true; // Start a new game
            }
         }
      } while (menuSelection != 4); // While player chooses not to exit (option 4).
   }
}
