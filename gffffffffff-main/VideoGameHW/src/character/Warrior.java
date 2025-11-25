package character;

import enemies.Enemy;
import inventory.Inventory;

import java.lang.annotation.Target;
import java.util.Random;
import java.util.Scanner;

public class Warrior extends Characters{
    private double strength;
    private double stamina;
    private int cooldown = 0;

    public Warrior(String name) {
        super(name);
        strength = 10;
        stamina = strength * 10;
    }

    @Override
    public void attack(Characters attacker, Enemy target) {
        System.out.println(getName() + " attacks with their sword!");
        stamina = stamina - 10;
        target.takeDamage(damage());
        cooldown--;
    }
    @Override
    public void useSpecialAbility(Characters attacker, Enemy target) {
        System.out.println(getName() + " casts Power Strike!!!");
        stamina = stamina - 15;
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
        strength =  strength * chance;
        strength = (double) Math.round(strength * 100) / 100;
        stamina = strength * 10;
        stamina = (double) Math.round(stamina * 100) / 100;

        System.out.println(getName() + " has leveled up!");
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
        return Math.round(strength * variance * 100) / 100.0;
    }

    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
}
