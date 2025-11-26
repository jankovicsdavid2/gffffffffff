package character;

import enemies.Enemy;
import inventory.Inventory;

import java.lang.annotation.Target;
import java.util.Random;
import java.util.Scanner;

public class Warrior extends Characters{
    private double strength;
    private double stamina;

    public Warrior(String name) {
        super(name);
        strength = 15;
        stamina = strength * 10;
    }

    @Override
    public void reset() {
        super.reset();
        strength = 15;
        stamina = strength * 10;
    }

    @Override
    public void attack(Characters attacker, Enemy target) {
        if (stamina < 10) {
            System.out.println(getName() + " is too tired to attack! Skipping turn...");
            stamina += 15;
            return;
        }

        System.out.println(getName() + " attacks with their sword!");
        stamina = stamina - 10;
        target.takeDamage(damage());
    }
    @Override
    public void useSpecialAbility(Characters attacker, Enemy target) {
        if (stamina < 20) {
            System.out.println(getName() + " doesn't have enough stamina for Power Strike!");
            System.out.println(getName() + " is too tired to attack! Skipping turn...");
            stamina += 15;
            return;
        }
        System.out.println(getName() + " casts Power Strike!!!");
        stamina = stamina - 15;
        target.takeDamage(damage() * 2);
    }

    @Override
    public void levelUp() {
        setLevel( getLevel() + 1);

        setMaxHealth(getMaxHealth() * 1.35);
        setMaxHealth(Math.round(getMaxHealth() * 100.0) / 100.0);

        setHealth(Math.min(getMaxHealth(), getHealth() + (getMaxHealth() * 0.5)));
        setHealth(Math.round(getHealth() * 100.0) / 100.0);

        strength = strength * 1.15;
        strength = Math.round(strength * 100.0) / 100.0;

        stamina = strength * 10;

        System.out.println("=== LEVEL UP! ===");
        System.out.println(getName() + " reached level " + getLevel() + "!");
        System.out.println("Max Health: " + getMaxHealth());
        System.out.println("Strength: " + strength);
        System.out.println("================");
        setXp(0);

    }


    @Override
    public String toString() {
        return "Warrior Lv:" + getLevel() + "{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", strength=" + strength +
                ", stamina=" + stamina +
                '}';

    }

    @Override
    public double damage() {
        Random random = new Random();
        double variance = 1 + (random.nextDouble() * 0.5);
        return Math.round((strength + getWeapon()) * variance * 100) / 100.0;
    }

    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
}
