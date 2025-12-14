package re.forestier.edu.rpg.Progression;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.Character.Stats;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AbilityManagerTest {

    @Test
    void constructor_throwsBecauseUtilityClass() {
        assertThrows(IllegalStateException.class, AbilityManager::new);
    }

    @Test
    void updatePlayerAbilities_updatesAbilitiesForLevel3_forAdventurer() {
        Player player = new Player("John", "Avatar", "ADVENTURER", 0, new ArrayList<>());

        // level 3 in your Adventurer progression: ATK=5, ALC=1
        AbilityManager.updatePlayerAbilities(player, 3);

        assertEquals(5, player.abilities.get(Stats.ATK));
        assertEquals(1, player.abilities.get(Stats.ALC));
    }

    @Test
    void updatePlayerAbilities_overridesExistingValues() {
        Player player = new Player("John", "Avatar", "ADVENTURER", 0, new ArrayList<>());

        player.abilities.put(Stats.ATK, 999);

        AbilityManager.updatePlayerAbilities(player, 1);

        // level 1 Adventurer: ATK=3
        assertEquals(3, player.abilities.get(Stats.ATK));
    }
}
