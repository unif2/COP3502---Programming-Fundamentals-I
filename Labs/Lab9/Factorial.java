public class Factorial {

    /*
    The factorial function is not defined for non-positive integers.  If a non-positive integer is passed into any of
    these methods, an IllegalArgumentException will be thrown.
    */

    // A purely recursive method that calculates the factorial of n.
    public static long pureRecursive(int n) {

        // Throw a new IllegalArgumentException if a non-positive integer is passed into the method.
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Base case. 1! = 1.
        if (n == 1) {
            return 1;
        }

        // Recursive call for n > 1.
        return n * pureRecursive(n - 1);
    }

    // Kickoff method for tail recursion.
    public static long tailRecursive(int n) {

        // Throw a new IllegalArgumentException if a non-positive integer is passed into the method.
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Call the tail recursive method (defined below) with accumulator variable (acc) set to 1.
        return tail(1, n);
    }

    /*
    Tail recursive method called by the tailRecursive method.  The accumulator variable (acc) accumulates the value
    of the factorial.
    */
    private static long tail(long acc, int n) {

        // Throw a new IllegalArgumentException if a non-positive integer is passed into the method.
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        // Base case. 1! = 1.
        if (n == 1) {
            return acc;
        }

        // Recursive call for n > 1, while passing in new value for the accumulator variable.
        else {
            return tail(acc * n, n - 1);
        }
    }

    // Method that uses a for loop to calculate the factorial of a non-positive integer n.  No recursive method calls.
    public static long iterative(int n) {

        // Throw a new IllegalArgumentException if a non-positive integer is passed into the method.
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        long factorial = 1; // Variable storing result of factorial calculation to be returned.

        // Iterate through integers 1 to n and multiply them.  Their product is stored in the variable factorial.
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }
}
