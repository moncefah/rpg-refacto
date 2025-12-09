package re.forestier.edu.rpg;

import re.forestier.edu.rpg.Items.Item;

public class Affichage {

    public static String afficherJoueur(Player player) {
        final String[] finalString = {"Joueur " + player.Avatar_name + " joué par " + player.playerName};
        finalString[0] += "\nNiveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\nCapacités :";
        player.abilities.forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });

        for (Item item :player.inventory ){
            finalString[0] += "\n   " + item.getName();
        }

        return finalString[0];
    }
}
