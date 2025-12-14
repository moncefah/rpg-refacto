package re.forestier.edu.rpg.Character;

import java.util.HashMap;
import java.util.List;

public class AbilitySet  extends HashMap <Stats, Integer> {
        private void setAbility(Ability ability){
                this.put(ability.getStat(), ability.getValue());
        }
        private void setAbility(List<Ability> listAbility){
                for (Ability ability : listAbility){
                        this.put(ability.getStat(), ability.getValue());

                }
        }

        public static AbilitySet of(Object... kv) {
        AbilitySet set = new AbilitySet();
        for (int i = 0; i < kv.length; i += 2) {
            Stats stat = (Stats) kv[i];
            int value = (int) kv[i + 1];
            set.put(stat, value);
        }
        return set;
    }

}
