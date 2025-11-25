package inventory;

import java.util.Random;

public abstract class Item {
    private String name;
    private int value;
    private int itemEffect;

    public Item(String name) {
        Random rnd = new Random();
        this.name = name;
        value = rnd.nextInt(100) + 1;

    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getItemEffect() {
        return itemEffect;
    }

    public void setItemEffect(int itemEffect) {
        this.itemEffect = itemEffect;
    }
}
