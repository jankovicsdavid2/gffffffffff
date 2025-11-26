package inventory.items;

import inventory.Item;

public class ManaPotion extends Item {

    public ManaPotion(String name) {
        super(name);
        setItemEffectValue(5);
        setEffect( "mana");
        setPotion(true);
    }
}
