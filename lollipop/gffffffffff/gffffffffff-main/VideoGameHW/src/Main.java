import character.Characters;
import character.Mage;
import character.Warrior;
import enemies.EMage;
import enemies.EWarrior;
import enemies.Enemy;
import inventory.Inventory;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;
import static java.lang.System.in;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        Scanner sc = new Scanner(in);

        System.out.println("Welcome to character creation! ");
        System.out.print("What type of character would you like to create? (Mage or Warrior) ");
        String hero = "";

        boolean correctInput = false;
        while (!correctInput) {
            String nameInput = sc.nextLine();

            switch (nameInput.toLowerCase()) {
                case "mage":
                    hero = nameInput;
                    correctInput = true;
                    break;
                case "warrior":
                    hero = nameInput;
                    correctInput = true;
                    break;
                default:
                    System.out.print("Invalid input, please try again. The options are Mage or Warrior: ");
                    break;
            }
        }

        System.out.print("What would you like your character's name to be? ");
        String name = sc.nextLine();

        boolean running = true;

        Characters character = null;

        switch (hero.toLowerCase()) {
            case "mage":
                character = new Mage(name);
                break;
            case "warrior":
                character = new Warrior(name);
                break;

        }

        err.println();
        int typeOfEnemy = rand.nextInt(10) + 1;
        System.out.println();
        System.out.println("Welcome to the game, " + character.getName() + "! ");
        System.err.println("If you wish to stop playing or you want to give up, you need to enter a negative number at any choice!!!");
        System.out.println("Let`s get this game started! \n");

        for (int i = 0; i < 50; i++) {
            out.print("-");

        }
        out.println();



        Enemy enemy = null;
        int encounterCount = 0;

        while (running) {
            encounterCount++;
            typeOfEnemy = rand.nextInt(10) + 1;

            int enemyLevel = character.getLevel();
            if (encounterCount < 5) {

                if (rand.nextInt(3) == 0 && enemyLevel > 1) {
                    enemyLevel--;
                }
            } else if (encounterCount < 15 && rand.nextInt(5) == 0 && enemyLevel > 1 ) {
                enemyLevel++;
            } else if (encounterCount > 15){
                enemyLevel++;
            }

            switch (typeOfEnemy % 2) {
                case 0:
                    enemy = new EMage("Enemy Mage", enemyLevel);
                    break;
                case 1:
                    enemy = new EWarrior("Enemy Warrior", enemyLevel);
            }
            System.out.println("\n--- Encounter #" + encounterCount + " ---");
            System.out.println("You have encountered a " + enemy.getName() + "!");

            while (enemy.getHealth() > 0) {


                for (int i = 0; i < 50; i++) {
                    out.print("-");

                }
                out.println();
                out.println();
                enemy.printEnemyStatus();
                out.println();

                System.out.println("What action would you like to perform? \n \n " +
                        "1. Attack, 2. Use Special Ability, 3. View Stats, 4. Open backpack, 5. SneakPast \n");
                Random lootChance = new Random();
                switch (sc.nextInt()) {
                    case 1:
                        character.attack(character, enemy);
                        if (enemy.getHealth() <= 0) {
                            int xpGained = 50 + (enemy.getLevel() * 25);
                            character.setXp(character.getXp() + xpGained);
                            System.out.println("Victory! Gained " + xpGained + " XP!");
                            if (lootChance.nextInt(100) % 5 == 0 ) {
                                out.println("You found a loot!");
                                character.foundLoot(sc);
                            }
                            if (character.getXp() >= character.getXpToNextLevel()) {
                                character.levelUp();
                            }
                        }
                        break;
                    case 2:
                        character.useSpecialAbility(character, enemy);
                        if (enemy.getHealth() <= 0) {
                            int xpGained = 50 + (enemy.getLevel() * 25);
                            character.setXp(character.getXp() + xpGained);
                            System.out.println("Victory! Gained " + xpGained + " XP!");
                            if (lootChance.nextInt(100) % 5 == 0 ) {
                                out.println("You found a loot!");
                                character.foundLoot(sc);
                            }
                            if (character.getXp() >= character.getXpToNextLevel()) {
                                character.levelUp();
                            }
                        }
                        break;

                    case 3:
                        System.out.println(character);
                        continue;

                    case 4:
                        character.printInventory();
                        out.println("Which item would you like to use?(0 to exit)");
                        int itemIndex = sc.nextInt();
                        if (itemIndex == 0) {
                            continue;
                        } else {
                            Inventory.useItem(itemIndex - 1, character, sc);
                            continue;

                        }


                    case 5:
                        System.out.println("Escaped successfully!");
                        enemy.setHealth(0);
                        break;
                }


                if (enemy.getHealth() > 0) {
                    enemy.enemyAttack(enemy, character);
                }

                if (character.getHealth() <= 0) {
                    out.println("You lost! You reached level " + character.getLevel() + "!");
                    out.println("Do you want to play again? (1 for yes, 0 for no)");
                    int choice = sc.nextInt();
                    if (choice == 0) {
                        running = false;
                    } else {

                        character.reset();
                        enemy.setHealth(0);
                    }
                }

            }



        }




        sc.close();
    }
}