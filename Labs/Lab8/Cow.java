public class Cow {
   private String cowName; // Name of the cow.
   private String cowImage; // Image of the cow.

   // Cow constructor.  Sets the cow's name to parameter name.
   public Cow(String name) {
      cowName = name;
   }

   // Method to get/return the name of the cow object.
   public String getName() {
      return cowName;
   }

   // Method to get/return the image of the cow object.
   public String getImage() {
      return cowImage;
   }

   // Method to set the image of the cow object to parameter _image.
   public void setImage(String _image) {
      cowImage = _image;
   }  
}
