package actions;

import character.Characters;
import enemies.Enemy;

public interface SpecialAbility {
    public void useSpecialAbility(Characters attacker, Enemy target);
}
