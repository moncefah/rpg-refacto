package re.forestier.edu.rpg;

import re.forestier.edu.rpg.Items.Item;
public class Affichage {

    Affichage() {
        throw new IllegalStateException("Utility class");
     }

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

    public static String afficherJoueurMarkdown(Player player) {
        StringBuilder sb = new StringBuilder();

        // Title
        sb.append("# Joueur ").append(player.Avatar_name)
        .append(" (joué par ").append(player.playerName).append(")\n\n");

        // Level section
        sb.append("## Niveau\n");
        sb.append("* **Niveau actuel :** ").append(player.retrieveLevel()).append("\n");
        sb.append("* **XP totale :** ").append(player.xp).append("\n\n");

        // Abilities section
        sb.append("## Capacités\n");
        player.abilities.forEach((stat, value) -> 
            sb.append("* **").append(stat).append(" :** ").append(value).append("\n")
        );
        sb.append("\n");

        // Inventory section
        sb.append("## Inventaire\n");
        for (Item item : player.inventory) {
            sb.append("* ").append(item.getName()).append("\n");
        }

        return sb.toString();
    }

}
