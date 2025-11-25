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
        mana = 10;
        manaPoints = mana * 10;
    }

    @Override
    public void attack(Characters attacker, Enemy target) {
        System.out.println(getName() + " fires a bullet made of mana");
        manaPoints = manaPoints - 10;
       target.takeDamage(damage());
       cooldown--;
    }

    @Override
    public void useSpecialAbility(Characters attacker, Enemy target) {
        System.out.println(getName() + " casts Fireball!!!");
        manaPoints = manaPoints - 25;
        target.takeDamage(damage() * 2);
        cooldown = 3;
    }

    @Override
    public void levelUp() {
        Random rnd = new Random();
        double chance = 1.01 + (rnd.nextDouble() * (1.1 - 1.01));
        setLevel( getLevel() + 1);
        setMaxHealth( getMaxHealth() * (chance * 2 - 1));
        setMaxHealth( (double) Math.round(getHealth() * 100) / 100);
        if ((getHealth() + (getMaxHealth() / 5)) < getMaxHealth()) {
            setHealth( getHealth() + (getMaxHealth() / 5));
        } else {
            setHealth(getMaxHealth());
        }
        mana = mana * chance;
        mana = (double) Math.round(mana * 100) / 100;
        manaPoints = mana * 10;
        manaPoints = (double) Math.round(manaPoints * 100) / 100;
        System.out.println(getName() + " has leveled up!");
        setXp(0);

    }




    @Override
    public String toString() {
        return "Mage Lv:" + getLevel() + "{" +
                "name='" + getName() + '\'' +
                ", health=" + getHealth() +
                ", mana=" + mana +
                ", manaPoints=" + manaPoints +
                '}';

    }

    @Override
    public double damage() {
        Random random = new Random();
        double variance = 1 + (random.nextDouble() * 0.5);
        return Math.round(mana * variance * 100) / 100.0;

    }

    public double getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }


}
