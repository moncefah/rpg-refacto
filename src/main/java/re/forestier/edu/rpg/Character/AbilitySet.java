package re.forestier.edu.rpg.Character;

import java.util.HashMap;


public class AbilitySet  extends HashMap <Stats, Integer> {

  public static AbilitySet of(Object... kv) {
        if (kv.length % 2 != 0)
                throw new IllegalArgumentException("Arguments must be in pairs");

        AbilitySet set = new AbilitySet();
        for (int i = 0; i < kv.length; i += 2) {
                set.put((Stats) kv[i], (Integer) kv[i + 1]);
        }
        return set;
}


}
