package inventory;

import java.util.Random;

public abstract class Item {
    private String name;
    private int value;
    private int itemEffectValue;
    private String effect;
    private boolean isPotion;
    private int cooldown;
    private int duration;


    public Item(String name) {
        Random rnd = new Random();
        this.name = name;
        value = rnd.nextInt(100) + 1;
        isPotion = false;
    }
    public void printeffect() {
        if (itemEffectValue == 50) {
            System.out.print("recovers " + itemEffectValue + effect);
        } else if (itemEffectValue == 5 && !isPotion) {
            System.out.print("has an attack power of: " + itemEffectValue + effect);
        } else if (itemEffectValue == 5) {
            System.out.print("grants: " + itemEffectValue + effect);
        } else {
            System.out.print("increases: " + effect + " by: " + itemEffectValue + " for " + duration + " turns");
        }

        System.out.println();
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

    public int getItemEffectValue() {
        return itemEffectValue;
    }

    public void setItemEffectValue(int itemEffectValue) {
        this.itemEffectValue = itemEffectValue;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public boolean isPotion() {
        return isPotion;
    }

    public void setPotion(boolean potion) {
        isPotion = potion;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
