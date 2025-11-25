package actions;

import character.Characters;
import enemies.Enemy;

public interface Attackable {
    public void attack(Characters attacker, Enemy target);
}
