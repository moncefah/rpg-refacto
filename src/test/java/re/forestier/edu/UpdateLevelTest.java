package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import org.junit.jupiter.api.Test;
public class UpdateLevelTest {
    @Test 
    void addXpLevelUpTest(){
        //pb avec la methode addXp elle ne save pas les anciens stats non update comme INT
        player p = new player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
        boolean leveledUp = UpdatePlayer.addXp(p, 30); // xp >= 27 → Niveau 3
        assertTrue(leveledUp,"le jouere a level up");
        assertEquals(3, p.retrieveLevel(), "Le joueur devrait être niveau 3");
        HashMap<String , Integer> expectedAbilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(3);
        expectedAbilities.forEach((ability, value) ->
                assertEquals(value, p.abilities.get(ability),"Capacité " + ability + " incorrecte"));

        // ajoute deux quantite d'XP 
        //1..xp suffisant pour change level 
        //2..xp non suffisant 
        //1.1. calcul le new level ;recup les valeur de la version update du gars ; assertion equals les deux hash
        //2.1. check le false 

    }   
        @Test
        void addXpNoLevelUpTest(){
            player p = new player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
            boolean leveledUp = UpdatePlayer.addXp(p, 1); 
            assertFalse(leveledUp);

        }

        @Test 
        void majFinDeTourPlayerDeadTest(){
            player p = new player("Jhon" , "Mitchel","ARCHER",0,new ArrayList<>());
            p.healthpoints = 10;
            p.currenthealthpoints = 0;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(0, p.currenthealthpoints,"pb");
            
        }
        
        @Test
        void testDwarfWithNoPotionHalfHealth(){
            player p = new player("John", "Avatar1", "DWARF", 0, new ArrayList<>());
            p.healthpoints = 100;
            p.currenthealthpoints = 40;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(p.currenthealthpoints,41 );
        }

        @Test
        void testDwarfWithPotionHalfHealth(){
            player p = new player("John", "Avatar1", "DWARF", 0, new ArrayList<>());
            p.inventory.add("Holy Elixir");
            p.healthpoints = 100;
            p.currenthealthpoints = 40;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(p.currenthealthpoints,42 );
        }
        @Test
        void testArcherWithBowHalfHealth(){
            player p = new player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
            p.inventory.add("Magic Bow");
            p.healthpoints = 100;
            p.currenthealthpoints = 40;
            UpdatePlayer.majFinDeTour(p);
            
            assertEquals(p.currenthealthpoints,40+40/8 );
        }
        @Test
        void testArcherWithOutBowHalfHealth(){
            player p = new player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
            p.healthpoints = 100;
            p.currenthealthpoints = 40;
            UpdatePlayer.majFinDeTour(p);
            
            assertEquals(p.currenthealthpoints,41);
        }
        @Test
        void testAdventurerLowLevelHalfHealth(){
            player p = new player("John", "Avatar1", "ADVENTURER", 0, new ArrayList<>());
            p.level = 3;
            p.healthpoints = 100;
            p.currenthealthpoints = 40;
            UpdatePlayer.majFinDeTour(p);
            System.out.println("Niveau du joueur: " + p.retrieveLevel());
            assertEquals(41,p.currenthealthpoints,"Attendu: 41, Obtenu: " + p.currenthealthpoints);
        }
        @Test
        void testAdventurerHighLevelHalfHealth(){
            player p = new player("John", "Avatar1", "ADVENTURER", 0, new ArrayList<>());
            UpdatePlayer.addXp(p, 50);
            p.healthpoints = 100;
            p.currenthealthpoints = 40;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(42,p.currenthealthpoints);
        }
        @Test
        void testHighHealth(){
            player p = new player("John", "Avatar1", "ADVENTURER", 0, new ArrayList<>());
            p.healthpoints = 100;
            p.currenthealthpoints = 140;
            UpdatePlayer.majFinDeTour(p);
            assertEquals(100,p.currenthealthpoints);
        }

        


}
