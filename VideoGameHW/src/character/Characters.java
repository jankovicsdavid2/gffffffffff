package character;

import actions.Attackable;
import actions.SpecialAbility;
import inventory.Inventory;

import java.util.Random;
import java.util.Scanner;

public abstract class Characters implements Attackable, SpecialAbility {
    private double maxHealth;
    private String name;
    private double health;
    private int level;
    private int xp = 0;
    private Inventory inventory;


    public Characters(String name) {
        inventory = new Inventory();
        this.name = name;
        health = 100;
        maxHealth = 100;
        level = 1;
    }


    public abstract double damage();

    public void takeDamage(double damage) {
        this.health -= damage;
    }
    public abstract void levelUp();

    public void printInventory() {
        if (inventory != null) {
            Inventory.listItems();
        } else {
            System.out.println("You have no items in your inventory");
        }

    }

    public void reset() {
        health = 100;
        maxHealth = 100;
        level = 1;
    }

    public abstract void foundLoot(Scanner sc);



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
