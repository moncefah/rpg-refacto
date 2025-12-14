package re.forestier.edu.rpg;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Character.Stats;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AffichageTest {

    @Test
    void constructor_throwsException() {
    assertThrows(IllegalStateException.class, Affichage::new);
    }

    private Player createPlayer() {
        Player p = new Player(
                "Alice",
                "Hero",
                "ADVENTURER",
                0,
                new ArrayList<>()
        );

        p.setXp(150); // ensures level > 1
        p.abilities.put(Stats.ATK, 3);
        p.abilities.put(Stats.INT, 2);
        p.inventory.add(new Item("Sword", 5, 50));
        p.inventory.add(new Item("Shield", 7, 70));

        return p;
    }

    @Test
    void afficherJoueur_containsBasicPlayerInfo() {
        Player p = createPlayer();

        String result = Affichage.afficherJoueur(p);

        assertTrue(result.contains("Joueur Hero joué par Alice"));
        assertTrue(result.contains("Niveau"));
        assertTrue(result.contains("XP totale"));
    }

    @Test
    void afficherJoueur_listsAbilitiesAndInventory() {
        Player p = createPlayer();

        String result = Affichage.afficherJoueur(p);

        assertTrue(result.contains("ATK"));
        assertTrue(result.contains("INT"));
        assertTrue(result.contains("Sword"));
        assertTrue(result.contains("Shield"));
    }

    @Test
    void afficherJoueurMarkdown_containsMarkdownHeaders() {
        Player p = createPlayer();

        String result = Affichage.afficherJoueurMarkdown(p);

        assertTrue(result.startsWith("# Joueur Hero"));
        assertTrue(result.contains("## Niveau"));
        assertTrue(result.contains("## Capacités"));
        assertTrue(result.contains("## Inventaire"));
    }

    @Test
    void afficherJoueurMarkdown_usesMarkdownFormattingForAbilities() {
        Player p = createPlayer();

        String result = Affichage.afficherJoueurMarkdown(p);

        assertTrue(result.contains("**ATK :**"));
        assertTrue(result.contains("**INT :**"));
        assertTrue(result.contains("* Sword"));
        assertTrue(result.contains("* Shield"));
    }
}
