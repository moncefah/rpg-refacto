package re.forestier.edu.rpg.Inventory;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void addItem_withinMaxWeight_shouldSucceed() {
        Item lightItem = new Item("Feather", 2, 5);
        Item mediumItem = new Item("Sword", 5, 50);
        Inventory inventory = new Inventory();

        assertTrue(inventory.addItem(lightItem));
        assertTrue(inventory.addItem(mediumItem));
        assertEquals(2, inventory.size());
    }

    @Test
    void addItem_exceedMaxWeight_shouldFailAndNotAdd() {
        Item heavyItem = new Item("Anvil", 19, 100);
        Item tooHeavy = new Item("Rock", 5, 10);
        Inventory inventory = new Inventory();

        assertTrue(inventory.addItem(heavyItem));
        assertFalse(inventory.addItem(tooHeavy));
        assertEquals(1, inventory.size());
        assertFalse(inventory.contains(tooHeavy));
    }

    @Test
    void hasItem_shouldReturnTrueWhenItemWithSameNameExists() {
        Item sword1 = new Item("Sword", 5, 50);
        Item sword2 = new Item("Sword", 3, 30);
        Inventory inventory = new Inventory();

        inventory.addItem(sword1);

        assertTrue(inventory.hasItem(sword2));
    }

    @Test
    void hasItem_shouldReturnFalseWhenItemNotPresent() {
        Item sword = new Item("Sword", 5, 50);
        Item shield = new Item("Shield", 7, 70);
        Inventory inventory = new Inventory();

        inventory.addItem(sword);

        assertFalse(inventory.hasItem(shield));
    }

    @Test
    void sell_shouldReturnFalseIfPlayerDoesNotHaveItem() {
        Item sword = new Item("Sword", 5, 50);
        Player player = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());

        assertFalse(player.inventory.sell(sword, player));
        assertEquals(0, player.getMoney());
    }

    @Test
    void sell_shouldRemoveItemFromInventoryAndGiveMoneyToPlayer() {
        Item sword = new Item("Sword", 5, 50);
        Player player = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());

        assertTrue(player.inventory.addItem(sword));
        assertTrue(player.inventory.contains(sword));

        boolean result = player.inventory.sell(sword, player);

        assertTrue(result);
        assertFalse(player.inventory.contains(sword));
        assertEquals(50, player.getMoney());
    }

    @Test
    void sell_shouldWorkWithDifferentItemInstanceSameName() {
        Player p = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());
        p.inventory.addItem(new Item("Sword", 5, 50));

        assertTrue(p.inventory.sell(new Item("Sword", 5, 50), p));
    }

    @Test
    void constructorWithMaxWeight_shouldLimitCapacity() {
        Inventory smallInventory = new Inventory(5);
        Item item1 = new Item("Stone", 3, 10);
        Item item2 = new Item("Rock", 3, 15);

        assertTrue(smallInventory.addItem(item1));
        assertFalse(smallInventory.addItem(item2));
        assertEquals(1, smallInventory.size());
    }

    
}
