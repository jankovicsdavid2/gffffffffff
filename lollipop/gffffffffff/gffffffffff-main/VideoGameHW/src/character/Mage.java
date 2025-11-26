package character;

import enemies.Enemy;
import inventory.Inventory;
import inventory.weapons.Sword;

import java.util.Random;
import java.util.Scanner;

public class Mage extends Characters{
    private double mana;
    private double manaPoints;
    private int cooldown = 0;

    public Mage(String name) {
        super(name);
        mana = 15;
        manaPoints = mana * 10;
    }

    @Override
    public void reset() {
        super.reset();
        mana = 15;
        manaPoints = mana * 10;
    }

    @Override
    public void attack(Characters attacker, Enemy target) {
        if (manaPoints < 10) {
            System.out.println(getName() + " is out of mana! Skipping turn...");
            manaPoints += 20; // Recover some mana
            return;
        }

        System.out.println(getName() + " fires a bullet made of mana");
        manaPoints = manaPoints - 10;
       target.takeDamage(damage() + 1.1);
       cooldown--;
    }

    @Override
    public void useSpecialAbility(Characters attacker, Enemy target) {
        if (manaPoints < 25) {
            System.out.println("Not enough mana!");
            return;
        }
        System.out.println(getName() + " casts Fireball!!!");
        manaPoints = manaPoints - 25;
        target.takeDamage(damage() * 1.1 * 2.5);
        cooldown = 3;
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() + 1);

        setMaxHealth(getMaxHealth() * 1.2);
        setMaxHealth(Math.round(getMaxHealth() * 100.0) / 100.0);

        setHealth(Math.min(getMaxHealth(), getHealth() + (getMaxHealth() * 0.5)));
        setHealth(Math.round(getHealth() * 100.0) / 100.0);

        mana = mana * 1.15 + 1;
        mana = Math.round(mana * 100.0) / 100.0;

        manaPoints = mana * 10;

        System.out.println("=== LEVEL UP! ===");
        System.out.println(getName() + " reached level " + getLevel() + "!");
        System.out.println("Max Health: " + getMaxHealth());
        System.out.println("Mana Power: " + mana);
        System.out.println("================");
        setXp(0);


    }

    @Override
    public String toString() {
        return "Mage Lv:" + getLevel() + "{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", mana=" + mana +
                ", manaPoints=" + manaPoints +
                " Exp: " + getXp() + '/' + getXpToNextLevel() + '}';

    }

    @Override
    public double damage() {
        Random random = new Random();
        double variance = 1 + (random.nextDouble() * 0.5);
        return Math.round((mana + getWeapon()) * variance * 100) / 100.0;
    }

    public double getMana() {
        return mana;
    }
    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(double manaPoints) {
        this.manaPoints = manaPoints;
    }
}
