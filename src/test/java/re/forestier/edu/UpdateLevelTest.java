package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;

public class UpdateLevelTest {

    // -------------------------------
    // TESTS POUR addXp()
    // -------------------------------

    @Test
    void addXpLevelUpTest(){
        Player p = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
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
        Player p = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
        boolean leveledUp = UpdatePlayer.addXp(p, 1);
        assertFalse(leveledUp);
    }

    // -------------------------------
    // TESTS POUR majFinDeTour()
    // -------------------------------

    @Test
    void testPlayerKO() {
        Player p = new Player("John", "DWARF", "DWARF", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(0, p.currenthealthpoints, "Le joueur KO doit rester à 0 HP");
    }

    @Test
    void testDwarfWithNoPotionHalfHealth(){
        Player p = new Player("John", "DWARF", "DWARF", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints, "DWARF sans potion devrait +1");
    }

    @Test
    void testDwarfWithPotionHalfHealth(){
        Player p = new Player("John", "DWARF", "DWARF", 0, new ArrayList<>());
        p.inventory.add("Holy Elixir");
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(42, p.currenthealthpoints, "DWARF avec potion devrait +2");
    }

    @Test
    void testArcherWithBowHalfHealth(){
        Player p = new Player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
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
        Player p = new Player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints, "ARCHER sans arc : +1 attendu");
    }
    @Test
    void testArcherMagicBowWithVeryLowHP() {
        // Test le calcul (currenthp + 1) / 8 - 1 avec HP très bas
        Player p = new Player("Archer", "Archer", "ARCHER", 0, new ArrayList<>());
        p.inventory.add("Magic Bow");
        p.healthpoints = 100;
        p.currenthealthpoints = 8; // Cas limite : 8/8 = 1

        UpdatePlayer.majFinDeTour(p);

        // currenthp = 8 + 1 = 9, puis bonus = 9/8 - 1 = 1 - 1 = 0
        assertEquals(9, p.currenthealthpoints);
    }
    @Test
    void testArcherMagicBowWithHP1() {
        Player p = new Player("Archer", "Archer", "ARCHER", 0, new ArrayList<>());
        p.inventory.add("Magic Bow");
        p.healthpoints = 100;
        p.currenthealthpoints = 1; // Cas extrême

        UpdatePlayer.majFinDeTour(p);

        // currenthp = 1 + 1 = 2, puis bonus = 2/8 - 1 = 0 - 1 = -1 (négatif!)
        // Donc au final : 2 + (-1) = 1
        assertEquals(1, p.currenthealthpoints, "Le Magic Bow peut causer un malus si HP trop bas");
    }


    @Test
    void testAdventurerLowLevelHalfHealth(){
        Player p = new Player("John", "ADVENTURER", "ADVENTURER", 0, new ArrayList<>()) {
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
        Player p = new Player("John", "ADVENTURER", "ADVENTURER", 0, new ArrayList<>()) {
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
        Player p = new Player("John", "ADVENTURER", "ADVENTURER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 140;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(100, p.currenthealthpoints, "HP ne doit pas dépasser le max");
    }

    // 🆕 TESTS AJOUTÉS : POUR TUER LES MUTANTS RESTANTS

    // Cas frontière : exactement la moitié des HP (pour tuer mutation < → <=)
    @Test
    void testPlayerExactlyHalfHP() {
        Player p = new Player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 50;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints,
                "À mi-vie, le joueur ne doit pas être soigné (cas frontière)");
    }

    // Cas joueur quasi-plein mais < max
    @Test
    void testPlayerAlmostFullHP() {
        Player p = new Player("John", "DWARF", "DWARF", 0, new ArrayList<>());
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
        Player p = new Player("John", "ARCHER", "ARCHER", 0, new ArrayList<>());
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

    void testUnknownClassInMajFinDeTour() {
        // Créer un joueur avec une classe "valide" puis la modifier (hack pour tester)
        Player p = new Player("Test", "Test", "ARCHER", 0, new ArrayList<>()) {
            @Override
            public String getAvatarClass() {
                return "INVALID_CLASS";
            }
        };


        // Ne devrait pas planter, mais afficher "Classe inconnue !"
        UpdatePlayer.majFinDeTour(p);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> UpdatePlayer.majFinDeTour(p));
        assertEquals("expected messages", exception.getMessage());

        // Aucun soin ne devrait être appliqué (sauf le cap au max)
    }
    @Test
    void testPlayerWithExactlyMaxHP() {
        Player p = new Player("Full", "Full", "DWARF", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 100;

        UpdatePlayer.majFinDeTour(p);

        assertEquals(100, p.currenthealthpoints, "Ne doit pas dépasser le max");
    }

    @Test
    void testPlayerJustAboveHalfHP() {
        Player p = new Player("Test", "Test", "ADVENTURER", 0, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 51; // Juste au-dessus de 50%

        UpdatePlayer.majFinDeTour(p);

        assertEquals(51, p.currenthealthpoints, "Ne devrait pas être soigné si > 50%");
    }
    @Test
    void testMultipleLevelUpsInSequence() {
        Player p = new Player("Hero", "Hero", "ARCHER", 0, new ArrayList<>());

        // Level 1 → 2
        UpdatePlayer.addXp(p, 10);
        assertEquals(2, p.retrieveLevel());
        assertEquals(1, p.inventory.size());

        // Level 2 → 3
        UpdatePlayer.addXp(p, 17);
        assertEquals(3, p.retrieveLevel());
        assertEquals(2, p.inventory.size());

        // Level 3 → 4
        UpdatePlayer.addXp(p, 30);
        assertEquals(4, p.retrieveLevel());
        assertEquals(3, p.inventory.size());
    }

    @Test
    void testAllClassesHaveLevel1Through5() {
        HashMap<String, CharacterProgression> abilities =
                UpdatePlayer.abilitiesPerTypeAndLevel();

        String[] classes = {"ADVENTURER", "ARCHER", "DWARF"};

        for (String avatarClass : classes) {
            assertTrue(abilities.containsKey(avatarClass),
                    "La classe " + avatarClass + " doit exister");

            for (int level = 1; level <= 5; level++) {
                assertTrue(abilities.get(avatarClass).containsKey(level),
                        avatarClass + " doit avoir les capacités du niveau " + level);
            }
        }
    }
}
