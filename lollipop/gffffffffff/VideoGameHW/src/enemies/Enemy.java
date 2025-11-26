package enemies;

import actions.Attackable;
import actions.EnemyAttack;

import java.util.Random;

public abstract class Enemy implements EnemyAttack {
    private String name;
    private double health;
    private int level;
    private static final String STATUS_MESSAGE_FORMAT = "The %s is at level %d and has %.1f health left!";



    public Enemy(String name, int level) {
        this.name = name;
        this.level = level;
        health = 100;
        Random rnd = new Random();
        double chance = 1.01 + (rnd.nextDouble() * (1.1 - 1.01));
        for (int i = 0; i < level; i++) {
            health = health * chance;
        }


    }

    public void printEnemyStatus() {
        System.out.println(getStatusMessage());
    }

    public String getStatusMessage() {
        return String.format(STATUS_MESSAGE_FORMAT, getName(), getLevel(), getHealth());
    }


    public abstract double damage();

    public void takeDamage(double damage) {
        this.health -= damage;
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


}
