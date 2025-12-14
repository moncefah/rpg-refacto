package re.forestier.edu.rpg.Character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbilityTest {

    @Test
    void constructor_setsStatAndValueCorrectly() {
        Ability ability = new Ability(Stats.ATK, 5);

        assertEquals(Stats.ATK, ability.getStat());
        assertEquals(5, ability.getValue());
    }
}
