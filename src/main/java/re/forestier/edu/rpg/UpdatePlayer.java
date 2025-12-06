package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

import re.forestier.edu.rpg.CharacterTypes.Adventurer;
import re.forestier.edu.rpg.CharacterTypes.Archer;
import re.forestier.edu.rpg.CharacterTypes.Dwarf;
import re.forestier.edu.rpg.Utilities;
import re.forestier.edu.rpg.CharacterTypes.Adventurer;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    public static HashMap<String, CharacterProgression> abilitiesPerTypeAndLevel() {
        HashMap<String, CharacterProgression> map = new HashMap<>();
        map.put("ARCHER", CharacterFactory.create("ARCHER").getProgression());
        map.put("ADVENTURER", CharacterFactory.create("ARCHER").getProgression());
        map.put("DWARF", CharacterFactory.create("DWARF").getProgression());
        return map;
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