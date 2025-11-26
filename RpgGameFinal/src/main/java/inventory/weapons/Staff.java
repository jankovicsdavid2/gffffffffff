package inventory.weapons;

public class Staff extends Weapon{

    public Staff(String name) {
        super(name);
        setItemEffectValue(5);
        setEffect(" mana");
    }
}
