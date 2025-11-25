package inventory;

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
        for (Item item : items) {
            System.out.println(item.getName() + ": " + item.getValue());
        }
    }

    public static void addItem(String name, int value) {
        items.add(new Item(name, value));
    }
    public void removeItem(int index) {
        items.remove(index);
    }

}
