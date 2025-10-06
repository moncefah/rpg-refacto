package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class UpdateLevelTest {

    // -------------------------------
    // TESTS POUR addXp()
    // -------------------------------

    @Test
    void addXpLevelUpTest(){
        player p = new player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
        boolean leveledUp = UpdatePlayer.addXp(p, 30);
        assertTrue(leveledUp, "Le joueur a level up");
        assertEquals(3, p.retrieveLevel(), "Le joueur devrait être niveau 3");

        HashMap<String , Integer> expectedAbilities =
                UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(3);
        expectedAbilities.forEach((ability, value) ->
                assertEquals(value, p.abilities.get(ability),
                        "Capacité " + ability + " incorrecte"));
    }

    @Test
    void addXpNoLevelUpTest(){
        player p = new player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
        boolean leveledUp = UpdatePlayer.addXp(p, 1);
        assertFalse(leveledUp);
    }

    // -------------------------------
    // TESTS POUR majFinDeTour()
    // -------------------------------

    @Test
    void testPlayerKO() {
        player p = new player("John", "DWARF", "DWARF", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(0, p.currenthealthpoints, "Le joueur KO doit rester à 0 HP");
    }

    @Test
    void testDwarfWithNoPotionHalfHealth(){
        player p = new player("John", "DWARF", "DWARF", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints, "DWARF sans potion devrait +1");
    }

    @Test
    void testDwarfWithPotionHalfHealth(){
        player p = new player("John", "DWARF", "DWARF", 0, new ArrayList<>());
        p.inventory.add("Holy Elixir");
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(42, p.currenthealthpoints, "DWARF avec potion devrait +2");
    }

    @Test
    void testArcherWithBowHalfHealth(){
        player p = new player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
        p.inventory.add("Magic Bow");
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        int expected = 40 + 1 + (40 + 1) / 8 - 1;
        assertEquals(expected, p.currenthealthpoints,
                "ARCHER avec arc magique devrait gagner un bonus");
    }

    @Test
    void testArcherWithoutBowHalfHealth(){
        player p = new player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints, "ARCHER sans arc : +1 attendu");
    }

    @Test
    void testAdventurerLowLevelHalfHealth(){
        player p = new player("John", "ADVENTURER", "ADVENTURER", 0, new ArrayList<>()) {
            @Override
            public int retrieveLevel() { return 2; } // niveau < 3
        };
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints, "+2 puis -1 = +1 attendu");
    }

    @Test
    void testAdventurerHighLevelHalfHealth(){
        player p = new player("John", "ADVENTURER", "ADVENTURER", 0, new ArrayList<>()) {
            @Override
            public int retrieveLevel() { return 3; }
        };
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(42, p.currenthealthpoints, "+2 sans malus attendu");
    }

    @Test
    void testHighHealth(){
        player p = new player("John", "ADVENTURER", "ADVENTURER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 140;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(100, p.currenthealthpoints, "HP ne doit pas dépasser le max");
    }

    // 🆕 TESTS AJOUTÉS : POUR TUER LES MUTANTS RESTANTS

    // Cas frontière : exactement la moitié des HP (pour tuer mutation < → <=)
    @Test
    void testPlayerExactlyHalfHP() {
        player p = new player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 50;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints,
                "À mi-vie, le joueur ne doit pas être soigné (cas frontière)");
    }

    // Cas joueur quasi-plein mais < max
    @Test
    void testPlayerAlmostFullHP() {
        player p = new player("John", "DWARF", "DWARF", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 99;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(99, p.currenthealthpoints,
                "Si le joueur gagne 1 HP, il doit être limité à son max");
    }

    // Cas joueur sans objet ni classe connue


    // Cas pour le dernier if (hp >= max)
    @Test
    void testPlayerHealedBeyondMax() {
        player p = new player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
        p.healthpoints = 50;
        p.currenthealthpoints = 49;
        UpdatePlayer.majFinDeTour(p);
        assertTrue(p.currenthealthpoints <= 50,
                "Les HP ne doivent jamais dépasser le max");
    }

    // Vérifie la structure abilitiesPerTypeAndLevel()
    @Test
    void testAbilitiesStructure() {
        var map = UpdatePlayer.abilitiesPerTypeAndLevel();
        assertTrue(map.containsKey("DWARF"));
        assertTrue(map.containsKey("ARCHER"));
        assertTrue(map.containsKey("ADVENTURER"));
        assertTrue(map.get("DWARF").containsKey(1));
    }
}
