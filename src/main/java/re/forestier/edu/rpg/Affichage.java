package re.forestier.edu.rpg;

import re.forestier.edu.rpg.Items.Item;
public class Affichage {

    public static String afficherJoueur(Player player) {
        StringBuilder sb = new StringBuilder();

        sb.append("Joueur ").append(player.Avatar_name)
          .append(" joué par ").append(player.playerName);

        sb.append("\nNiveau : ").append(player.retrieveLevel())
          .append(" (XP totale : ").append(player.xp).append(")");

        sb.append("\n\nCapacités :");
        player.abilities.forEach((stat, value) -> 
            sb.append("\n   ").append(stat).append(" : ").append(value)
        );

        sb.append("\n\nInventaire :");
        for (Item item : player.inventory) {
            sb.append("\n   ").append(item.getName());
        }

        return sb.toString();
    }
}
