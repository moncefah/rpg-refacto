package re.forestier.edu.rpg;
import re.forestier.edu.rpg.Stats;
public class Ability {
    private final String stat;  // "INT", "DEF", "ATK"...
    private final int value;    // 1, 2, 3...

    public Ability(String stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public String getStat() {
        return stat;
    }

    public int getValue() { return value; }
}
