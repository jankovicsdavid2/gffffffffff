package enemies;

import character.Characters;

import java.util.Random;

public class EMage extends Enemy {
    private double mana;
    private double manaPoints;

    public EMage(String name, int level) {
        super(name, level);
        mana = 10;
        manaPoints = mana * 10;
    }

    @Override
    public void enemyAttack(Enemy attacker, Characters target) {
        target.takeDamage(damage());
        System.out.println(attacker.getName() + " hit you for " + damage() + " damage!");
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
