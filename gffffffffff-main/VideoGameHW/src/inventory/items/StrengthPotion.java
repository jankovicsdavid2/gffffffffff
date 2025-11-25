package inventory.items;

import inventory.Item;

public class StrengthPotion extends Item {
    public StrengthPotion(String name) {
        super(name);
        setItemEffect(5);
    }
}
