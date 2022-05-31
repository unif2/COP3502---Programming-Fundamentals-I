// Pakuri class definition.  Class will implement the Comparable interface.
public class Pakuri implements Comparable<Pakuri> {

    private String species; // Species name of Pakuri object.
    private int attack, defense, speed; // properties of Pakuri object.

    // Constructor which also sets initial values of the name and properties.
    public Pakuri(String species) {

        attack = (species.length() * 7) + 9;
        defense = (species.length() * 5) + 17;
        speed = (species.length() * 6) + 13;
        this.species = species;
    }

    // Method to return species name of Pakuri object.
    public String getSpecies() { return species; }

    // Method to return attack value of Pakuri object.
    public int getAttack() { return attack; }

    // Method to return defense value of Pakuri object.
    public int getDefense() { return defense; }

    // Method to return speed value of Pakuri object.
    public int getSpeed() { return speed; }

    // Method to change the attack value of Pakuri object to newAttack.
    public void setAttack(int newAttack) { attack = newAttack; }

    // Method to update properties of Pakuri object.
    public void evolve() {

        attack *= 2;
        defense *= 4;
        speed *= 3;
    }

    // Returns integer determining order of this object and target object, using compareTo() method.
    @Override
    public int compareTo(Pakuri target) { return (this.species).compareTo(target.species); }
}
