package re.forestier.edu.rpg.CharacterTypes;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArcherTest {

    @Test
    void getProgression_containsLevels1To5() {
        Archer a = new Archer();
        CharacterProgression p = a.getProgression();

        assertNotNull(p);
        for (int i = 1; i <= 5; i++) {
            assertTrue(p.containsKey(i), "Missing level " + i);
            assertNotNull(p.get(i), "Null AbilitySet at level " + i);
        }
    }

    @Test
    void getProgression_level1_statsAreCorrect() {
        AbilitySet s = new Archer().getProgression().get(1);

        assertEquals(1, s.get(Stats.INT));
        assertEquals(3, s.get(Stats.ATK));
        assertEquals(1, s.get(Stats.CHA));
        assertEquals(3, s.get(Stats.VIS));
    }

    @Test
    void soigner_withoutMagicBow_addsOneHp() {
        Player p = new Player("test", "avatar", "ARCHER", 0, new ArrayList<>());
        p.currenthealthpoints = 16;

        int before = p.currenthealthpoints;

        new Archer().soigner(p);

        assertEquals(before + 1, p.currenthealthpoints);
    }

    @Test
    void soigner_withMagicBow_addsExtraBasedOnCurrentHp() {
        Player p = new Player("test", "avatar", "ARCHER", 0, new ArrayList<>());
        p.currenthealthpoints = 16;

        // Add the bow so inventory.hasItem(new Item(...)) returns true
        p.inventory.add(new Item("Magic Bow", "this is a Magic Bow", 1, 1));

        int before = p.currenthealthpoints;

        new Archer().soigner(p);

        // After +1: hp = before + 1
        int afterPlusOne = before + 1;

        // Extra: currenthealthpoints/8 - 1 (integer division)
        int extra = afterPlusOne / 8 - 1;

        assertEquals(afterPlusOne + extra, p.currenthealthpoints);
    }
}
