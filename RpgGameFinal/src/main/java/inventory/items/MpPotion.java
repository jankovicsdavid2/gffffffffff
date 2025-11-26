package inventory.items;

import inventory.Item;

public class MpPotion extends Item {
    public MpPotion(String name) {
        super(name);
        setItemEffectValue(50);
        setEffect( "Mp");
        setPotion(true);
    }
}
