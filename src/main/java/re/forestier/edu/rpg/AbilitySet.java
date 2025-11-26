package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import re.forestier.edu.rpg.Ability;

import javax.swing.*;

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
