package re.forestier.edu.rpg.CharacterTypes;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {

    @Test
    void getProgression_containsLevels1To5() {
        Goblin g = new Goblin();
        CharacterProgression p = g.getProgression();

        assertNotNull(p);
        for (int i = 1; i <= 5; i++) {
            assertTrue(p.containsKey(i), "Missing level " + i);
            assertNotNull(p.get(i), "Null AbilitySet at level " + i);
        }
    }

    @Test
    void getProgression_level1_statsAreCorrect() {
        AbilitySet s = new Goblin().getProgression().get(1);

        assertEquals(2, s.get(Stats.INT));
        assertEquals(2, s.get(Stats.ATK));
        assertEquals(1, s.get(Stats.ALC));
    }

    @Test
    void getProgression_level2_usesATK_andHasExpectedStats() {
        AbilitySet s = new Goblin().getProgression().get(2);

        assertEquals(3, s.get(Stats.ATK));
        assertEquals(4, s.get(Stats.ALC));
    }

    @Test
    void soigner_addsOneHp() {
        Player p = new Player("test", "avatar", "GOBLIN", 0, new ArrayList<>());
        p.currenthealthpoints = 10;

        int before = p.currenthealthpoints;

        new Goblin().soigner(p);

        assertEquals(before + 1, p.currenthealthpoints);
    }
}
