package inventory.items;

import inventory.Item;

public class DamageBoostPotion extends Item {
    public DamageBoostPotion(String name) {
        super(name);
        setItemEffect(10);
    }
}
