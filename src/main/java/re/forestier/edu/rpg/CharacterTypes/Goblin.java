package re.forestier.edu.rpg.CharacterTypes;

import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.CharacterClass;
import re.forestier.edu.rpg.Player;

public class Goblin extends CharacterClass {

    public Goblin() {
    }

    @Override
    public CharacterProgression getProgression() {
        CharacterProgression goblinMap = new CharacterProgression();

        // Niveau 1 : INT=2, ATK=2, ALC=1
        AbilitySet lvl1 = new AbilitySet();
        lvl1.put("INT", 2);
        lvl1.put("ATK", 2);
        lvl1.put("ALC", 1);
        goblinMap.put(1, lvl1);

        // Niveau 2 : ATF=3 (Typo du sujet, on assume ATK), ALC=4
        AbilitySet lvl2 = new AbilitySet();
        lvl2.put("ATK", 3);
        lvl2.put("ALC", 4);
        goblinMap.put(2, lvl2);

        // Niveau 3 : VIS=1
        AbilitySet lvl3 = new AbilitySet();
        lvl3.put("VIS", 1);
        goblinMap.put(3, lvl3);

        // Niveau 4 : DEF=1
        AbilitySet lvl4 = new AbilitySet();
        lvl4.put("DEF", 1);
        goblinMap.put(4, lvl4);

        // Niveau 5 : DEF=2, ATK=3
        AbilitySet lvl5 = new AbilitySet();
        lvl5.put("DEF", 2);
        lvl5.put("ATK", 3);
        goblinMap.put(5, lvl5);

        return goblinMap;
    }

    @Override
    public void soigner(Player player) {
        // Le sujet ne précise pas de soin spécial pour le Gobelin.
        // On lui donne un soin standard (+1 PV).
        player.currenthealthpoints += 1;
    }
}