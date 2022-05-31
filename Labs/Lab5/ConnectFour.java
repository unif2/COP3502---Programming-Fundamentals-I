import java.util.Scanner; // Import Scanner class to capture player input.

public class ConnectFour {

    // This method prints the board.
    public static void printBoard(char[][] array) {

        /*
        Iterate through the rows and columns of the board and print the current value
        of each board element ('x', 'o', or '-').  Insert a space between elements unless the
        last element of each row is printed, in which case a new line should be printed before
        moving on to the next row of the board.  We start from the last row index of the array
        because we are inserting tokens starting from row 0 and this for loop will end up printing
        row 0 last, which is how the board should look to the player (token placed in the next available
        row starting from the bottom).
        */
        for (int row = array.length - 1; row >= 0; row--) {
            for (int col = 0; col < array[0].length; col++) {
                System.out.print(array[row][col]);

                // If the last element of each board row hasn't been printed, print a space.
                if (col != array[0].length - 1) {
                    System.out.print(" ");
                }

                // Else, print a new line before moving on to the next row of the board.
                else {
                    System.out.println();
                }
            }
        }

        // Print a new line to separate the board from the rest of the program's output.
        System.out.println();
    }

    // This method initializes the board's elements to '-', meaning no token has been placed.
    public static void initializeBoard(char[][] array) {

        // Iterate through the rows and columns of the board and assign '-' to each board's element.
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                array[row][col] = '-';
            }
        }
    }

    /*
    This method places the token in the column of the board that the player has chosen, placing it in the
    first available row.  The row that the token is placed in is returned.
    */
    public static int insertChip(char[][] array, int col, char chipType) {

        int row; // Row to be returned.

        /*
        Starting from the bottom of the column the player has chosen (according to the way the board is printed),
        check each row to see if the spot is empty (i.e. the board element there is '-').  If it is, set the
        board element at that location to the player's token ('x' or 'o') and break out of the loop.  The for loop
        starts at row = 0 because row 0 is printed last when the board is displayed to the player.
        */
        for (row = 0; row < array.length; row++) {
            if (array[row][col] == '-') {
                array[row][col] = chipType;
                break;
            }
        }

        // Return the row number the player's token is placed in.
        return row;
    }

    /*
    This method checks to see if a player has won the game after placing their token at board position row, col.
    A player wins the game by having 4 of their tokens in a row either vertically or horizontally.
    The method returns true if a player has won the game, otherwise false.
    */
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {

        int countVertical = 0; // Counts the number of times the player's token appears in a row along the column.
        int countHorizontal = 0; // Counts the number of times the player's token appears in a row along the row.

        /*
        For loop checking if the player has won with 4 in a row along the column the token was placed in.
        Starting from the bottom of the column (i = 0), if the space is occupied by the player's token, increase
        countVertical by 1.  Then check if there are 4 in a row of that token.  If so, return true.  If not,
        move to the next row up.  Loop stops at the row where the token was placed.
        */
        for (int i = 0; i <= row; i++) {
            if (array[i][col] == chipType) {
                countVertical++;
                if (countVertical == 4) {
                    return true;
                }
            }

            // If the space isn't occupied by the player's token, reset countVertical to 0 and move to the next row up.
            else {
                countVertical = 0;
            }
        }

        /*
        For loop checking if the player has won with 4 in a row along the row the token was placed in, starting from
        the left end of the row.  As we loop, we check to see if the space is occupied by the player's token.  If it is,
        increase countHorizontal by 1, then check if there are 4 in a row of that token.  If so, return true.  If not,
        move to the next column.
        */
        for (int j = 0; j < array[row].length; j++) {
            if (array[row][j] == chipType) {
                countHorizontal++;
                if (countHorizontal == 4) {
                    return true;
                }
            }

            // If the space isn't occupied by player's token, reset countHorizontal to 0 and move to the next column.
            else {
                countHorizontal = 0;
            }
        }

        // If the method reaches this point, the player hasn't won with this move, so return false.
        return false;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); // Create new Scanner object to capture player input.
        int player = 1; // Variable to store which player is placing the token. Program starts with player 1.
        char chipType = 'x'; // Variable to store the token being placed on the board. Player 1's token is 'x'.
        int row; // Variable to store which row the player places their token in.
        int col; // Variable to store which column the player places their token in.
        boolean gameOver = false; // Variable to store whether the game is over (true) or not (false).
        boolean existsWinner; // Variable to store if there is a winner (true) or not (false).
        int moveCount = 0; // Variable to store the total number of moves that have taken place in the game.

        // Ask the player to input the height of the board (number of rows).
        System.out.print("What would you like the height of the board to be? ");
        int boardHeight = in.nextInt();

        // Ask the player to input the length of the board (number of columns).
        System.out.print("What would you like the length of the board to be? ");
        int boardLength = in.nextInt();

        // Create the Character array representing the board with the number of rows and columns provided by the player.
        char[][] board = new char[boardHeight][boardLength];

        // Initialize the board's spaces with '-', representing an empty space.
        initializeBoard(board);

        // Print the board before player 1 makes their first move.
        printBoard(board);

        // Player 1's token is 'x', player 2's token is 'o'.
        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println();

        // While the game is not over, execute the following block of code.
        while (!gameOver) {
            // Ask the current player to input the column they'd like to place their token in.
            System.out.print("Player " + player + ": Which column would you like to choose? ");
            col = in.nextInt();

            // Insert the token in this column at the next available row.
            row = insertChip(board, col, chipType);

            // A move has just taken place, so increment moveCount by 1 and print the board.
            moveCount++;
            printBoard(board);

            // We should only check for a winner if at least 7 moves have taken place.
            if (moveCount >= 7) {
                // Check to see if the current player has won with this move.
                existsWinner = checkIfWinner(board, col, row, chipType);

                // If the current player has won, print that they've won and set gameOver to true.
                if (existsWinner) {
                    System.out.println("Player " + player + " won the game!");
                    gameOver = true;
                }

                // If the current player hasn't won, and all spaces on the board have been filled, the game is a draw.
                else if (!existsWinner && moveCount == boardHeight * boardLength) {
                    System.out.println("Draw. Nobody wins.");
                    gameOver = true;
                }
            }

            /*
            If the program reaches this point, then the game is not over yet.  If player 1 just made a move, then
            it is player 2's turn.  Set player to 2 and chipType to 'o'.
            */
            if (player == 1) {
                player = 2;
                chipType = 'o';
            }

            // Otherwise, it is player 1's turn.  Set player to 1 and chipType to 'x'.
            else {
                player = 1;
                chipType = 'x';
            }
        }
    }
}
