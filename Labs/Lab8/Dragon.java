// Class Dragon is derived from Cow
public class Dragon extends Cow {
   
   // Dragon constructor calls Cow constructor and calls setImage to set the Dragon object's image.
   public Dragon(String name, String image) {
      super(name);
      super.setImage(image);
   }

   // All Dragon objects can breathe fire, so this will always return true.
   public boolean canBreatheFire() {
      return true;
   }
}
