package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

import re.forestier.edu.rpg.Utilities;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    public static HashMap<String,CharacterProgression> abilitiesPerTypeAndLevel() {
        HashMap<String, CharacterProgression> abilitiesPerTypeAndLevel = new HashMap<>();

        CharacterProgression adventurerMap = new CharacterProgression();
        AbilitySet adventurerLevel1 = new AbilitySet();
        adventurerLevel1.put("INT", 1);
        adventurerLevel1.put("DEF", 1);
        adventurerLevel1.put("ATK", 3);
        adventurerLevel1.put("CHA", 2);
        adventurerMap.put(1, adventurerLevel1);

        AbilitySet adventurerLevel2 = new AbilitySet();
        adventurerLevel1.put("INT", 2);
        adventurerLevel1.put("CHA", 3);
        adventurerMap.put(2, adventurerLevel2);

        AbilitySet adventurerLevel3 = new AbilitySet();
        adventurerLevel3.put("ATK", 5);
        adventurerLevel3.put("ALC", 1);
        adventurerMap.put(3, adventurerLevel3);

        AbilitySet adventurerLevel4 = new AbilitySet();
        adventurerLevel4.put("DEF", 3);
        adventurerMap.put(4, adventurerLevel4);

        AbilitySet adventurerLevel5 = new AbilitySet();
        adventurerLevel5.put("VIS", 1);
        adventurerLevel5.put("DEF", 4);
        adventurerMap.put(5, adventurerLevel5);

        abilitiesPerTypeAndLevel.put("ADVENTURER", adventurerMap);


        CharacterProgression archerMap = new  CharacterProgression();
        AbilitySet archerLevel1 = new AbilitySet();
        archerLevel1.put("INT", 1);
        archerLevel1.put("ATK", 3);
        archerLevel1.put("CHA", 1);
        archerLevel1.put("VIS", 3);
        archerMap.put(1, archerLevel1);

        AbilitySet archerLevel2 = new AbilitySet();
        archerLevel2.put("DEF", 1);
        archerLevel2.put("CHA", 2);
        archerMap.put(2, archerLevel2);

        AbilitySet archerLevel3 = new AbilitySet();
        archerLevel3.put("ATK", 3);
        archerMap.put(3, archerLevel3);

        AbilitySet archerLevel4 = new AbilitySet();
        archerLevel4.put("DEF", 2);
        archerMap.put(4, archerLevel4);

        AbilitySet archerLevel5 = new AbilitySet();
        archerLevel5.put("ATK", 4);
        archerMap.put(5, archerLevel5);

        abilitiesPerTypeAndLevel.put("ARCHER", archerMap);


        CharacterProgression dwarf = new CharacterProgression();
        AbilitySet dwarfLevel1 = new AbilitySet();
        dwarfLevel1.put("ALC", 4);
        dwarfLevel1.put("INT", 1);
        dwarfLevel1.put("ATK", 3);
        dwarf.put(1, dwarfLevel1);

        AbilitySet dwarfLevel2 = new AbilitySet();
        dwarfLevel2.put("DEF", 1);
        dwarfLevel2.put("ALC", 5);
        dwarf.put(2, dwarfLevel2);

        AbilitySet dwarfLevel3 = new AbilitySet();
        dwarfLevel3.put("ATK", 4);
        dwarf.put(3, dwarfLevel3);

        AbilitySet dwarfLevel4 = new AbilitySet();
        dwarfLevel4.put("DEF", 2);
        dwarf.put(4, dwarfLevel4);

        AbilitySet dwarfLevel5 = new AbilitySet();
        dwarfLevel5.put("CHA", 1);
        dwarf.put(5, dwarfLevel5);

        abilitiesPerTypeAndLevel.put("DWARF", dwarf);

        return abilitiesPerTypeAndLevel;
    }

    public static boolean addXp(Player player, int xp) {

        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();

        if (newLevel != currentLevel) {
            // Player leveled-up!
            // Give a random object
            ;
            player.inventory.add(Utilities.pickRandomObject(objectList));
            // Add/upgrade abilities to player
            updatePlayerAbilities(player, newLevel);
            return true;
        }
        return false;
    }

    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(Player player) {
        if(player.currenthealthpoints == 0) {
            gererJouereKO(player);
            return;
        }
        if(player.currenthealthpoints < player.healthpoints/2) {
            appliquerSoinsSelonClasse(player);
        }
            Utilities.restorerViePlayer(player);

    }



    private static void appliquerSoinsSelonClasse(Player player) {
        switch (player.getAvatarClass()) {
            case "DWARF":
                Utilities.soignerDwarf(player);
                break;
            case "ARCHER":
                Utilities.soignerArcher(player);
                break;
            case "ADVENTURER":
                Utilities.soignerAdventurer(player);
                break;
            default:
                System.out.println("Classe inconnue !");
                break;
        }
    }

    private static void gererJouereKO(Player player) {
        System.out.println("Le joueur est KO !");
    }



    private static void updatePlayerAbilities(Player player, int newLevel ){
        HashMap<String, Integer> abilities = abilitiesPerTypeAndLevel().get(player.getAvatarClass()).get(newLevel);
        abilities.forEach((ability, level) -> {
            player.abilities.put(ability, abilities.get(ability));
        });

    }

}