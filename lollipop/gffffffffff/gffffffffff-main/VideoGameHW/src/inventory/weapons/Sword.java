package inventory.weapons;

public class Sword extends Weapon{

    public Sword(String name) {
        super(name);
        setItemEffectValue(5);
        setEffect(" strength");
    }


}
