package re.forestier.edu.rpg;

import re.forestier.edu.rpg.CharacterTypes.*;

public class CharacterFactory {

    public static CharacterClass create(String className) {
        switch (className) {
            case "ARCHER":
                return new Archer();
            case "ADVENTURER":
                return new Adventurer();
            case "DWARF":
                return new Dwarf();
            /*case "GOBLIN":
                return new Goblin();*/
            default:
                throw new IllegalArgumentException("Classe inconnue : " + className);
        }
    }
}