package inventory.items;

import inventory.Item;

public class Healthpotion extends Item {
    public Healthpotion(String name) {
        super(name);
        setItemEffectValue(50);
        setEffect( "health");
        setPotion(true);
    }
}
