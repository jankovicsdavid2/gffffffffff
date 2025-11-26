package enemies;

import character.Characters;

import java.util.Random;

public class EWarrior extends Enemy {
    private double strength;
    private double stamina;


    public EWarrior(String name, int level) {
        super(name, level);
        setHealth(getHealth() * 1.1);
        strength = 10  + (level - 1) * 2;
        stamina = strength * 10;
    }

    @Override
    public void enemyAttack(Enemy attacker, Characters target) {
        target.takeDamage(damage());
        System.out.println(attacker.getName() + " hit you for " + damage() + " damage!");
    }

    @Override
    public double damage() {
        Random random = new Random();
        double variance = 0.7 + (random.nextDouble() * 0.4); // 70% - 110% damage variance
        return Math.round(strength * variance * 100.0) / 100.0;
    }

    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }


}
