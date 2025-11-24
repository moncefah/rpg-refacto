package re.forestier.edu.rpg;

public class Ability {
    private final String name;  // "INT", "DEF", "ATK"...
    private final int value;    // 1, 2, 3...

    public Ability(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public int getValue() { return value; }
}
