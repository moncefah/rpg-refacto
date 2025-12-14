package re.forestier.edu.rpg.Items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void equals_sameInstance_returnsTrue() {
        Item item = new Item("Sword", 5, 50);
        assertEquals(item, item);
    }

    @Test
    void equals_sameNameDifferentObject_returnsTrue() {
        Item item1 = new Item("Sword", 5, 50);
        Item item2 = new Item("Sword", 10, 100); // different fields, same name

        assertEquals(item1, item2);
    }

    @Test
    void equals_differentName_returnsFalse() {
        Item item1 = new Item("Sword", 5, 50);
        Item item2 = new Item("Shield", 5, 50);

        assertNotEquals(item1, item2);
    }

    @Test
    void equals_null_returnsFalse() {
        Item item = new Item("Sword", 5, 50);

        assertNotEquals(item, null);
    }

    @Test
    void equals_differentClass_returnsFalse() {
        Item item = new Item("Sword", 5, 50);

        assertNotEquals(item, "Sword");
    }

    @Test
    void hashCode_sameName_sameHashCode() {
        Item item1 = new Item("Sword", 5, 50);
        Item item2 = new Item("Sword", 10, 100);

        assertEquals(item1.hashCode(), item2.hashCode());
    }

    @Test
    void hashCode_differentName_differentHashCode() {
        Item item1 = new Item("Sword", 5, 50);
        Item item2 = new Item("Shield", 5, 50);

        assertNotEquals(item1.hashCode(), item2.hashCode());
    }

    @Test
    void toString_returnsExpectedFormat() {
        Item item = new Item(
                "Sword",
                "Sharp blade",
                5,
                50
        );

        String result = item.toString();

        assertEquals("Sword (Sharp blade, 5kg, 50$)", result);
    }
}
