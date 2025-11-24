package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Map;

public class AbilitySet {
    private final Map<String, Integer> abilities = new HashMap<>();
    public AbilitySet add(String abilityName, int value) {
        abilities.put(abilityName, value);
        return this;
        //pour chainer les .add()
    }
    public Map<String, Integer> getAbilities() {
        return new HashMap<>(abilities);
    }
    public int getValue(String abilityName) {
        return abilities.getOrDefault(abilityName, 0);
    }

    public boolean hasAbility(String abilityName) {
        return abilities.containsKey(abilityName);
    }

    // Factory methods pour création rapide
    public static AbilitySet create() {
        return new AbilitySet();
    }

}
