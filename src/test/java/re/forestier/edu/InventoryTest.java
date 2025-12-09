package re.forestier.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.inventory.Inventory;
import re.forestier.edu.rpg.Player;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class InventoryTest {

    private Inventory inventory;
    private Player player;



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
        // maxWeight = 20
        Item heavyItem = new Item("Anvil", 19, 100);
        Item tooHeavy = new Item("Rock", 5, 10);
        Inventory inventory = new Inventory();

        assertTrue(inventory.addItem(heavyItem));
        assertFalse(inventory.addItem(tooHeavy));  // would exceed 20
        assertEquals(1, inventory.size());
        assertFalse(inventory.contains(tooHeavy));
    }

    @Test
    void hasItem_shouldReturnTrueWhenItemWithSameNameExists() {
        Item sword1 = new Item("Sword", 5, 50);
        Item sword2 = new Item("Sword", 3, 30); // different obj, same name
        Inventory inventory = new Inventory();

        inventory.addItem(sword1);

        assertTrue(inventory.hasItem(sword2)); // compares by name
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
        Inventory inventory = new Inventory();
        Player player = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());

        assertFalse(inventory.sell(sword, player));
        assertEquals(0, player.getMoney());
    }

    @Test
    void sell_shouldRemoveItemFromInventoryAndGiveMoneyToPlayer() {
        Item sword = new Item("Sword", 5, 50);
        
        Player player = new Player("John", "Avatar1", "ARCHER", 0, new ArrayList<>());

        // add item to player inventory
        assertTrue(player.inventory.addItem(sword));
        assertTrue(player.inventory.contains(sword));

        boolean result = player.inventory.sell(sword, player);

        assertTrue(result);
        assertFalse(player.inventory.contains(sword));
        assertEquals(50, player.getMoney());
    }

    @Test
    void constructorWithMaxWeight_shouldLimitCapacity() {
        Inventory smallInventory = new Inventory(5); // max total weight 5
        Item item1 = new Item("Stone", 3, 10);
        Item item2 = new Item("Rock", 3, 15);

        assertTrue(smallInventory.addItem(item1));
        assertFalse(smallInventory.addItem(item2)); // would exceed 5
        assertEquals(1, smallInventory.size());
    }
}
