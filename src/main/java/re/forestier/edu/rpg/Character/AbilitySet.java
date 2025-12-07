package re.forestier.edu.rpg.Character;

import java.util.HashMap;
import java.util.List;

public class AbilitySet  extends HashMap <String, Integer> {
        private void setAbility(Ability ability){
                this.put(ability.getStat(), ability.getValue());
        }
        private void setAbility(List<Ability> listAbility){
                for (Ability ability : listAbility){
                        this.put(ability.getStat(), ability.getValue());

                }
        }

}
