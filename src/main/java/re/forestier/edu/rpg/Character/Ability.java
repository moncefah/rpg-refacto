package re.forestier.edu.rpg.Character;

public class Ability {
    private final Stats stat;
    private final int value;

    public Ability(Stats stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public Stats getStat() {
        return stat;
    }

    public int getValue() {
        return value;
    }
}
