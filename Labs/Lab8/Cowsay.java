// Written entirely using vim on a Mac.

public class Cowsay {
   /*
   Method to display the list of available cows.
   Input is an array of Cow objects.
   Output is the list of available cows by name.
   */
   private static void listCows(Cow[] cows) {
      System.out.print("Cows available: ");
      
      // Iterate through each cow object in the cows array.
      for (int i = 0; i < cows.length; i++) {
         // Use getName() to get the name of each cow.
         System.out.print(cows[i].getName());
         // Separate each name with a space.
         if (i != cows.length - 1) {
            System.out.print(" ");
         }
      }
      System.out.println();
   }

   /*
   Method that searches through the list of available cows to
   see if the user-provided cow name exists in the list.
   If it does, it returns the matching cow object, otherwise
   returns null.  The list of available cows is provided by the
   cows array.
   */
   private static Cow findCow(String name, Cow[] cows) {
      // Iterate through each cow object in the cows array.
      for (int i = 0; i < cows.length; i++) {
         // If String name matches name of an available cow, return the cow object.
         if (name.equals(cows[i].getName())) {
            return cows[i];
         }
      }
      
      // There is no cow in the list by that name.  Print message and return null.
      System.out.println("Could not find " + name + " cow!");
      return null;
   }

   public static void main(String[] args) {
      // User getCows() method in HeiferGenerator class to load list of available cows.
      Cow[] cows = HeiferGenerator.getCows();
   
      // Only run the below code if user runs the program with arguments.
      if (args.length > 0) {
         // If the only argument is '-l', output a list of available cows.
         if (args.length == 1 && args[0].equals("-l")) {
            listCows(cows);
         }
         
         // If user provides more than 2 arguments and the first is '-n'
         else if (args.length > 2 && args[0].equals("-n")) {
            // Second argument will be the name of the cow user wants. Get it using findCow().
            String name = args[1];
            Cow cow = findCow(name, cows);
            // If the cow exists in the list, the message to display consists of the rest of the arguments.
            if (cow != null) {
               // Message to display starts with 3rd argument, all separated by a space.
               for (int i = 2; i < args.length; i++) {
                  System.out.print(args[i]);
                  // Only insert a space between message arguments, not after last argument.
                  if (i != args.length - 1) {
                     System.out.print(" ");
                  }
               }
               System.out.println();
               // After message is displayed, display the cow's image.
               System.out.println(cow.getImage());
               
               /*
               If cow is an instance of Dragon, canBreatheFire can be called. However, since
               the Cow class doesn't have this method, we must cast the cow object as Dragon.
               If the cow object points to an instance of the IceDragon class, we can still
               cast cow as Dragon since all IceDragon objects are also instances of Dragon.
               Polymorphism enables the correct canBreatheFire method to be called.
               */
               if (cow instanceof Dragon) {
                  boolean breatheFire = ((Dragon) cow).canBreatheFire();
                  // For Dragon objects, breatheFire is true. For IceDragon objects, it is false.
                  if (breatheFire) {
                     System.out.println("This dragon can breathe fire.");
                  }
                  else {
                     System.out.println("This dragon cannot breathe fire.");
                  }
               }
            }
         }
         
         // If the above 2 conditions are false, the entire arguments list is the message
         else {
            for (int i = 0; i < args.length; i++) {
               System.out.print(args[i]);
               // Only insert a space between message arguments, not after last argument.
               if (i != args.length - 1) {
                  System.out.print(" ");
               }
            }
            System.out.println();
            // No command to find a specific cow was properly used, so load image of default cow.
            System.out.println(cows[0].getImage());
         }
      }
   }
}
