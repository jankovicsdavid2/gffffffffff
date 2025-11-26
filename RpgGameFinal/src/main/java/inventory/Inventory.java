package inventory;

import character.Characters;
import character.Mage;
import character.Warrior;
import inventory.items.*;
import inventory.weapons.Bow;
import inventory.weapons.Staff;
import inventory.weapons.Sword;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            System.out.print(counter + ".: " + item.getName() + ": ");
            item.printeffect();

            counter++;
        }
    }

    public static void useItem(int index, Characters character, Scanner sc) {
        String name = items.get(index).getName();
        int effect = items.get(index).getItemEffectValue();

        switch (name) {
            case "Sword":
                if (character instanceof Warrior) {
                    character.setWeapon(effect);
                    System.out.println("You have equipped a " + name + "!");
                } else {
                    System.out.println("You can't equip a sword on a non Warrior!" +
                            "\n Would you like to delete it from your inventory? (1 for yes, 0 for no)");
                    if (sc.nextInt() == 1) break;
                    else return;
                }
                break;
            case "Wand":
                if (character instanceof Mage) {
                    character.setWeapon(effect);
                    System.out.println("You have equipped a " + name + "!");
                } else {
                    System.out.println("You can't equip a wand on a non mage!" +
                            "\n Would you like to delete it from your inventory? (1 for yes, 0 for no)");
                    if (sc.nextInt() == 1) break;
                    else return;
                }
                break;
            /*case "Bow":
                character.setWeapon(effect);
                System.out.println("You have equipped a " + name + "!");
                break;*/
            case "Health potion":
                character.setHealth(character.getHealth() + effect);
                System.out.println("You have been healed for " + effect + " health points!");
                break;
            case "Mana potion":
                if (character instanceof Mage) {
                    ((Mage) character).setMana(((Mage) character).getMana() + effect);
                    System.out.println("You have gained " + effect + " mana!");
                } else {
                    System.out.println("You can't use mana potion on a non mage!" +
                            "\n Would you like to delete it from your inventory? (1 for yes, 0 for no)");
                    if (sc.nextInt() == 1) break;
                    else return;
                }
                break;
            case "Strength potion":
                if (character instanceof Warrior) {
                    ((Warrior) character).setStrength(((Warrior) character).getStrength() + effect);
                    System.out.println("You have gained " + effect + " strength!");
                } else {
                    System.out.println("You can't use strength potion on a non warrior!" +
                            "\n Would you like to delete it from your inventory? (1 for yes, 0 for no)");
                    if (sc.nextInt() == 1) break;
                    else return;
                }
                break;
            case "Stamina potion":
                if (character instanceof Warrior) {
                    ((Warrior) character).setStrength(((Warrior) character).getStrength() + effect);
                    System.out.println("You have recovered " + effect + " stamina!");
                } else {
                    System.out.println("You can't use strength potion on a non warrior!" +
                            "\n Would you like to delete it from your inventory? (1 for yes, 0 for no)");
                    if (sc.nextInt() == 1) break;
                    else return;
                }
                break;
            case "Mp potion":
                if (character instanceof Mage) {
                    ((Mage) character).setManaPoints(((Mage) character).getManaPoints() + effect);
                    System.out.println("You have recovered " + effect + " mana points!");
                } else {
                    System.out.println("You can't use mana potion on a non mage!" +
                            "\n Would you like to delete it from your inventory? (1 for yes, 0 for no)");
                    if (sc.nextInt() == 1) break;
                    else return;
                }
                break;
        }

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
    public static void removeItem(int index) {
        items.remove(index);
    }

}
