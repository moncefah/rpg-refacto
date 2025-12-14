package re.forestier.edu.rpg.CharacterTypes;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DwarfTest {

    @Test
    void getProgression_containsLevels1To5() {
        Dwarf d = new Dwarf();
        CharacterProgression p = d.getProgression();

        assertNotNull(p);
        for (int i = 1; i <= 5; i++) {
            assertTrue(p.containsKey(i), "Missing level " + i);
            assertNotNull(p.get(i), "Null AbilitySet at level " + i);
        }
    }

    @Test
    void getProgression_level1_statsAreCorrect() {
        AbilitySet s = new Dwarf().getProgression().get(1);

        assertEquals(4, s.get(Stats.ALC));
        assertEquals(1, s.get(Stats.INT));
        assertEquals(3, s.get(Stats.ATK));
    }

    @Test
    void soigner_withoutHolyElixir_addsOneHp() {
        Player p = new Player("test", "avatar", "DWARF", 0, new ArrayList<>());
        p.currenthealthpoints = 10;

        int before = p.currenthealthpoints;

        new Dwarf().soigner(p);

        // always +1 (the last line)
        assertEquals(before + 1, p.currenthealthpoints);
    }

    @Test
    void soigner_withHolyElixir_addsTwoHp() {
        Player p = new Player("test", "avatar", "DWARF", 0, new ArrayList<>());
        p.currenthealthpoints = 10;

        p.inventory.add(new Item("Holy Elixir", "this a Holy Elixir", 1, 1));

        int before = p.currenthealthpoints;

        new Dwarf().soigner(p);

        // +1 from if +1 always
        assertEquals(before + 2, p.currenthealthpoints);
    }
}
