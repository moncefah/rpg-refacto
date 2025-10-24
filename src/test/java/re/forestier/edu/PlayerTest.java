package re.forestier.edu.rpg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class PlayerTest {
     @Test
    void testValidConstructor() {
        var inv = new ArrayList<String>();
        Player p = new Player("John", "Avatar1", "ARCHER", 100, inv);
        assertEquals("ARCHER", p.getAvatarClass());
        assertEquals(100, p.money);
    }

    @Test
    void testInvalidConstructor() {
        var inv = new ArrayList<String>();
        Player p = new Player("Jane", "Avatar2", "MAGE", 50, inv);
        // Ici, le constructeur "return" sans set → AvatarClass reste null
        assertNull(p.getAvatarClass());
    }

    @Test
    void testRemoveMoneySuccess() {
        Player p = new Player("John", "Avatar1", "DWARF", 100, new ArrayList<>());
        p.removeMoney(40);
        assertEquals(60, p.money);
    }

    @Test
    void testRemoveMoneyFailure() {
        Player p = new Player("John", "Avatar1", "DWARF", 30, new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> p.removeMoney(40));
    }

    @Test
    void testAddMoney() {
        Player p = new Player("John", "Avatar1", "ADVENTURER", 10, new ArrayList<>());
        p.addMoney(20);
        assertEquals(30, p.money);
        
    }
    void testAddNegativeAmount() {
    Player p = new Player("John", "Avatar1", "ARCHER", 100, new ArrayList<>());
    p.addMoney(-30);
    assertEquals(70, p.money);
    }
    @Test
    void testRetrieveLevel() {
        Player p = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());

        p.xp = 0;  assertEquals(1, p.retrieveLevel());
        p.xp = 15; assertEquals(2, p.retrieveLevel());
        p.xp = 30; assertEquals(3, p.retrieveLevel());
        p.xp = 60; assertEquals(4, p.retrieveLevel());
        p.xp = 120; assertEquals(5, p.retrieveLevel());
    }

    @Test
    void testGetXp() {
        Player p = new Player("John", "Avatar1", "DWARF", 0, new ArrayList<>());
        assertEquals(0, p.getXp());
        p.xp = 42;
        assertEquals(42, p.getXp());
    }
}
