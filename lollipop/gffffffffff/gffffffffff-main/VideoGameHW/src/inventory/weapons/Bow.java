package inventory.weapons;

public class Bow extends Weapon{

    public Bow(String name) {
        super(name);
        setItemEffectValue(5);
        setEffect(" accuracy");
    }
}
