package re.forestier.edu.rpg;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Items.Item;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

     @Test
    void constructor_throwsBecauseUtilityClass() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, Utilities::new);
        assertEquals("Utility class", ex.getMessage());
    }

    // -------------------------------
    // Tests for restorerViePlayer
    // -------------------------------

    @Test
    void restorerViePlayer_capsHealthAtMax() {
        Player p = new Player("John", "Avatar", "ARCHER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 150;

        Utilities.restorerViePlayer(p);

        assertEquals(100, p.currenthealthpoints);
    }

    @Test
    void restorerViePlayer_doesNothingWhenHealthBelowMax() {
        Player p = new Player("John", "Avatar", "ARCHER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 60;

        Utilities.restorerViePlayer(p);

        assertEquals(60, p.currenthealthpoints);
    }

    // -------------------------------
    // Tests for pickRandomObject
    // -------------------------------

    @Test
    void pickRandomObject_returnsElementFromArray() {
        Item[] items = {
                new Item("Sword", 5, 50),
                new Item("Shield", 7, 70),
                new Item("Potion", 1, 10)
        };

        Item result = Utilities.pickRandomObject(items);

        assertNotNull(result);
        assertTrue(
                result == items[0] || result == items[1] || result == items[2],
                "Returned item must come from the provided array"
        );
    }

    @Test
    void pickRandomObject_singleElementArray_returnsThatElement() {
        Item only = new Item("Unique", 1, 1);
        Item[] items = { only };

        Item result = Utilities.pickRandomObject(items);

        assertSame(only, result);
    }
}
