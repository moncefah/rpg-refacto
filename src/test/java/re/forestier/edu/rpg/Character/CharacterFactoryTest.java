package re.forestier.edu.rpg.Character;

import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.CharacterTypes.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterFactoryTest {

    
    @Test
    void constructor_throwsException() {
    assertThrows(IllegalStateException.class, CharacterFactory::new);
    }

    @Test
    void create_shouldReturnArcher() {
        CharacterClass character = CharacterFactory.create("ARCHER");
        assertInstanceOf(Archer.class, character);
    }

    @Test
    void create_shouldReturnAdventurer() {
        CharacterClass character = CharacterFactory.create("ADVENTURER");
        assertInstanceOf(Adventurer.class, character);
    }

    @Test
    void create_shouldReturnDwarf() {
        CharacterClass character = CharacterFactory.create("DWARF");
        assertInstanceOf(Dwarf.class, character);
    }

    @Test
    void create_shouldReturnGoblin() {
        CharacterClass character = CharacterFactory.create("GOBLIN");
        assertInstanceOf(Goblin.class, character);
    }

    @Test
    void create_withUnknownClass_shouldThrowException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> CharacterFactory.create("MAGE")
        );

        assertTrue(ex.getMessage().contains("Classe inconnue"));
    }
}
