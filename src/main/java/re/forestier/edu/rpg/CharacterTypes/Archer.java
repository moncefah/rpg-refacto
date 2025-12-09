package re.forestier.edu.rpg.CharacterTypes;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterClass;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Player;

public class Archer extends CharacterClass {

    public Archer() {
    }

    @Override
    public CharacterProgression getProgression() {

        CharacterProgression archerMap = new CharacterProgression();

        archerMap.put(1, AbilitySet.of(
                Stats.INT, 1,
                Stats.ATK, 3,
                Stats.CHA, 1,
                Stats.VIS, 3
        ));

        archerMap.put(2, AbilitySet.of(
                Stats.DEF, 1,
                Stats.CHA, 2
        ));

        archerMap.put(3, AbilitySet.of(
                Stats.ATK, 3
        ));

        archerMap.put(4, AbilitySet.of(
                Stats.DEF, 2
        ));

        archerMap.put(5, AbilitySet.of(
                Stats.ATK, 4
        ));

        return archerMap;
    }

    @Override
    public void soigner(Player player){
        player.currenthealthpoints+=1;
        if(player.inventory.hasItem(new Item("Magic Bow","this is a Magic Bow", 1 , 1))) {
            player.currenthealthpoints+=player.currenthealthpoints/8-1;
        }
    }

}
