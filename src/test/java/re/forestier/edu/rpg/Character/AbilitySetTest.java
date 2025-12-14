package re.forestier.edu.rpg.Character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbilitySetTest {

    @Test
    void of_withNoArguments_returnsEmptySet() {
        AbilitySet set = AbilitySet.of();
        assertNotNull(set);
        assertTrue(set.isEmpty());
    }

    @Test
    void of_withEvenArguments_buildsMapCorrectly() {
        AbilitySet set = AbilitySet.of(
                Stats.ATK, 10,
                Stats.INT, 5,
                Stats.DEF, 3
        );

        assertEquals(3, set.size());
        assertEquals(10, set.get(Stats.ATK));
        assertEquals(5, set.get(Stats.INT));
        assertEquals(3, set.get(Stats.DEF));
    }

    @Test
    void of_withOddArguments_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> AbilitySet.of(Stats.ATK)
        );
        assertEquals("Arguments must be in pairs", ex.getMessage());
    }

    @Test
    void of_withWrongKeyType_throwsClassCastException() {
        assertThrows(
                ClassCastException.class,
                () -> AbilitySet.of("ATK", 10)
        );
    }

    @Test
    void of_withWrongValueType_throwsClassCastException() {
        assertThrows(
                ClassCastException.class,
                () -> AbilitySet.of(Stats.ATK, "10")
        );
    }

    @Test
    void of_withDuplicateStat_keepsLastValue() {
        AbilitySet set = AbilitySet.of(
                Stats.ATK, 10,
                Stats.ATK, 99
        );

        assertEquals(1, set.size());
        assertEquals(99, set.get(Stats.ATK));
    }
}
