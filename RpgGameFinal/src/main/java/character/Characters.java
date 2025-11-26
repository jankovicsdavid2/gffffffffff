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
    private int weapon;


    public Characters(String name) {
        inventory = new Inventory();
        this.name = name;
        health = 150;
        maxHealth = 150;
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
        health = 150;
        maxHealth = 150;
        level = 1;
        xp = 0;

    }

    public int getXpToNextLevel() {
        return level * 100;
    }


    public void foundLoot(Scanner sc) {
        Random chance = new Random();
        final int CURRENTCHANCE = chance.nextInt(81);
        int lootType;
        System.out.println("Congratulations! You found some loot!");
        if (CURRENTCHANCE % 8 == 0) {
            System.out.println("It`s a Sword! It ill grant extra strength to your attacks.");
            lootType = 1;
        } else if (CURRENTCHANCE % 7 == 0){
            System.out.println("It's a Wand! It will grant extra mana to your attacks");
            lootType = 2;
        } else if (CURRENTCHANCE % 6 == 0){
            System.out.println("It's a Bow! It will grant extra accuracy to your attacks");
            lootType = 3;
        } else if (CURRENTCHANCE % 5 == 0){
            System.out.println("It's a Health potion! You can use it to recover 50HP");
            lootType = 4;
        } else if (CURRENTCHANCE % 4 == 0){
            System.out.println("It's a Mana potion! You can gain 5 mana by consuming it.");
            lootType = 5;
        } else if (CURRENTCHANCE % 3 == 0){
            System.out.println("It's a Strength potion! You can gain 5 strength by consuming it.");
            lootType = 6;
        } else if (CURRENTCHANCE % 2 == 0){
            System.out.println("It's a Stamina potion! You can use it to recover 50 stamina");
            lootType = 7;
        } else {
            System.out.println("It's a Mp potion! You can use it to recover 50 mana points");
            lootType = 8;
        }


        System.out.println("Would you like to pick it up? (1 for yes, 0 for no)");
        if (sc.nextInt() == 1) {
            if (lootType == 1) {
                Inventory.addItem("Sword");
            } else if (lootType == 2) {
                Inventory.addItem("Wand");
            } else if (lootType == 3) {
                Inventory.addItem("Bow");
            } else if (lootType == 4) {
                Inventory.addItem("Health potion");
            } else if (lootType == 5) {
                Inventory.addItem("Mana potion");
            } else if (lootType == 6) {
                Inventory.addItem("Strength potion");
            } else if (lootType == 7) {
                Inventory.addItem("Stamina potion");
            } else if (lootType == 8) {
                Inventory.addItem("Mp potion");
            }
        }
    }



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

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }
}
