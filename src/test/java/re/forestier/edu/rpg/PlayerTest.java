package re.forestier.edu.rpg;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Character.Stats;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testValidConstructor() {
        ArrayList<Item> inv = new ArrayList<>();
        Player p = new Player("John", "Avatar1", "ARCHER", 100, inv);

        assertEquals("ARCHER", p.getAvatarClass());
        assertEquals(100, p.getMoney());
        assertNotNull(p.inventory);
        assertNotNull(p.abilities);
    }

    @Test
    void constructor_whenInitialInventoryIsNotNull_addsItemsToInventory() {
        ArrayList<Item> init = new ArrayList<>();
        init.add(new Item("Sword", 5, 50));
        init.add(new Item("Shield", 7, 70));

        Player p = new Player("John", "Avatar1", "ARCHER", 100, init);

        assertEquals(2, p.inventory.size());
        assertTrue(p.inventory.hasItem(new Item("Sword", 0, 0)));
        assertTrue(p.inventory.hasItem(new Item("Shield", 0, 0)));
    }

    @Test
    void constructor_whenInitialInventoryIsNull_doesNotThrowAndInventoryStaysEmpty() {
        Player p = new Player("John", "Avatar1", "ARCHER", 100, null);

        assertNotNull(p.inventory);
        assertTrue(p.inventory.isEmpty());
    }

    @Test
    void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new Player("Jane", "Avatar2", "MAGE", 50, new ArrayList<>()));
    }

    @Test
    void constructor_initializesLevel1Abilities() {
        Player p = new Player("John", "Avatar1", "ADVENTURER", 0, new ArrayList<>());

        assertFalse(p.abilities.isEmpty());
        assertTrue(p.abilities.containsKey(Stats.ATK));
    }

    @Test
    void testAddMoney() {
        Player p = new Player("John", "Avatar1", "ADVENTURER", 10, new ArrayList<>());
        p.addMoney(20);
        assertEquals(30, p.getMoney());
    }

    @Test
    void testAddNegativeAmount() {
        Player p = new Player("John", "Avatar1", "ARCHER", 100, new ArrayList<>());
        p.addMoney(-30);
        assertEquals(70, p.getMoney());
    }

    @Test
    void testRemoveMoneySuccess() {
        Player p = new Player("John", "Avatar1", "DWARF", 100, new ArrayList<>());
        p.removeMoney(40);
        assertEquals(60, p.getMoney());
    }

    @Test
    void testRemoveMoneyFailure() {
        Player p = new Player("John", "Avatar1", "DWARF", 30, new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> p.removeMoney(40));
    }

    @Test
    void testGetXpAndSetXp() {
        Player p = new Player("John", "Avatar1", "DWARF", 0, new ArrayList<>());

        assertEquals(0, p.getXp());

        p.setXp(42);
        assertEquals(42, p.getXp());
        assertTrue(p.level >= 1);
    }

    @Test
    void retrieveLevel_matchesLevelComputedInSetXp() {
        Player p = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());

        p.setXp(1234);
        assertEquals(p.level, p.retrieveLevel());
    }
}
