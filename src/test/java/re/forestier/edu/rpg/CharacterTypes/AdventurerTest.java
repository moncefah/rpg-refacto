package re.forestier.edu.rpg.CharacterTypes;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    // ----------- getProgression tests -----------

    @Test
    void getProgression_containsLevels1To5() {
        Adventurer a = new Adventurer();
        CharacterProgression p = a.getProgression();

        assertNotNull(p);
        for (int i = 1; i <= 5; i++) {
            assertTrue(p.containsKey(i), "Missing level " + i);
            assertNotNull(p.get(i), "Null AbilitySet at level " + i);
        }
    }

    @Test
    void getProgression_level1_statsAreCorrect() {
        AbilitySet s = new Adventurer().getProgression().get(1);

        assertEquals(1, s.get(Stats.INT));
        assertEquals(1, s.get(Stats.DEF));
        assertEquals(3, s.get(Stats.ATK));
        assertEquals(2, s.get(Stats.CHA));
    }

    @Test
    void getProgression_level3_statsAreCorrect() {
        AbilitySet s = new Adventurer().getProgression().get(3);

        assertEquals(5, s.get(Stats.ATK));
        assertEquals(1, s.get(Stats.ALC));
    }

    // ----------- soigner tests -----------

    @Test
    void soigner_levelBelow3_netAddsOneHp() {
        Player p = new Player("test", "avatar", "ADVENTURER", 0, new ArrayList<>());
        p.currenthealthpoints = 10;
        p.setXp(0); // level 1

        int before = p.currenthealthpoints;

        new Adventurer().soigner(p);

        // +2 then -1 because level < 3
        assertEquals(before + 1, p.currenthealthpoints);
    }

    @Test
    void soigner_levelAtLeast3_addsTwoHp() {
        Player p = new Player("test", "avatar", "ADVENTURER", 0, new ArrayList<>());
        p.currenthealthpoints = 10;
        p.setXp(300); // level >= 3 (100 XP per level)

        int before = p.currenthealthpoints;

        new Adventurer().soigner(p);

        assertEquals(before + 2, p.currenthealthpoints);
    }
}
