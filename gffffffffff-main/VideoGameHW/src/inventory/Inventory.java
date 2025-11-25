package inventory;

import inventory.items.*;
import inventory.weapons.Bow;
import inventory.weapons.Staff;
import inventory.weapons.Sword;
import inventory.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }


    public Inventory(List<Item> items) {
        this.items = items != null ? items : new ArrayList<>();

    }
    public static void listItems() {
        System.out.println("Inventory:");
        printInventory();


    }

    public static void printInventory() {
        int counter = 1;
        for (Item item : items) {
            System.out.println(counter + ".: " + item.getName() + ": " + item.getValue());
            counter++;
        }
    }

    public static void addItem(Sword sword) {
    }

    public void useItem(int index) {
        removeItem(index);
    }



    public static void addItem(String name) {
        switch (name) {
            case "Sword":
                items.add(new Sword(name));
                break;
            case "Wand":
                items.add(new Staff(name));
                break;
            case "Bow":
                items.add(new Bow(name));
                break;
            case "Health potion":
                items.add(new Healthpotion(name));
                break;
            case "Mana potion":
                items.add(new ManaPotion(name));
                break;
            case "Strength potion":
                items.add(new StrengthPotion(name));
                break;
            case "Stamina potion":
                items.add(new StaminaPotion(name));
                break;
            case "Mp potion":
                items.add(new MpPotion(name));
                break;

        }

    }
    public void removeItem(int index) {
        items.remove(index);
    }

}
