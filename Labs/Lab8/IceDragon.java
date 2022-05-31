// Class IceDragon is derived from Dragon
public class IceDragon extends Dragon {
   
   // IceDragon constructor only calls Dragon constructor, setting the name and image.
   public IceDragon(String name, String image) {
      super(name, image);
   }

   // IceDragon objects cannot breathe fire, so this will always return false.
   public boolean canBreatheFire() {
      return false;
   }
}
