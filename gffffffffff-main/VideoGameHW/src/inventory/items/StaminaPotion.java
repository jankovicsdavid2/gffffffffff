package inventory.items;

import inventory.Item;

public class StaminaPotion extends Item {
    public StaminaPotion(String name) {
        super(name);
        setItemEffect(50);
    }
}
