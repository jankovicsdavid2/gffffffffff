package inventory.items;

import inventory.Item;

public class CritChancePotion extends Item {
    public CritChancePotion(String name) {
        super(name);
        setItemEffectValue(30);
        setEffect( "critChance");
        setPotion(true);


    }
}
