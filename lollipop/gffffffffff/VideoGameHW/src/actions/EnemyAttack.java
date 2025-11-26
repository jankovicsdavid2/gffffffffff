package actions;

import character.Characters;
import enemies.Enemy;

public interface EnemyAttack {
    public void enemyAttack(Enemy attacker, Characters target);
}
