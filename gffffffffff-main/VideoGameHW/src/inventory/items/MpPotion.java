package inventory.items;

import inventory.Item;

public class MpPotion extends Item {
    public MpPotion(String name) {
        super(name);
        setItemEffect(50);
    }
}
