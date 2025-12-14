package re.forestier.edu.rpg.CharacterTypes;

import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Character.CharacterClass;
import re.forestier.edu.rpg.Player;

public class Goblin extends CharacterClass {

    public Goblin() {
    }

    @Override
    public CharacterProgression getProgression() {

        CharacterProgression goblinMap = new CharacterProgression();

        goblinMap.put(1, AbilitySet.of(
                Stats.INT, 2,
                Stats.ATK, 2,
                Stats.ALC, 1
        ));

        goblinMap.put(2, AbilitySet.of(
                Stats.ATK, 3,   // ATF → ATK (corrected)
                Stats.ALC, 4
        ));

        goblinMap.put(3, AbilitySet.of(
                Stats.VIS, 1
        ));

        goblinMap.put(4, AbilitySet.of(
                Stats.DEF, 1
        ));

        goblinMap.put(5, AbilitySet.of(
                Stats.DEF, 2,
                Stats.ATK, 3
        ));

        return goblinMap;
    }

    @Override
    public void soigner(Player player) {
        // Le sujet ne précise pas de soin spécial pour le Gobelin.
        // On lui donne un soin standard (+1 PV).
        player.currenthealthpoints += 1;
    }
}