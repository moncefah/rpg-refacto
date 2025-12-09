package re.forestier.edu.rpg.CharacterTypes;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterClass;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Player;

public class Dwarf extends CharacterClass {

    public Dwarf() {
    }

    @Override
    public CharacterProgression getProgression() {

        CharacterProgression dwarfMap = new CharacterProgression();

        dwarfMap.put(1, AbilitySet.of(
                Stats.ALC, 4,
                Stats.INT, 1,
                Stats.ATK, 3
        ));

        dwarfMap.put(2, AbilitySet.of(
                Stats.DEF, 1,
                Stats.ALC, 5
        ));

        dwarfMap.put(3, AbilitySet.of(
                Stats.ATK, 4
        ));

        dwarfMap.put(4, AbilitySet.of(
                Stats.DEF, 2
        ));

        dwarfMap.put(5, AbilitySet.of(
                Stats.CHA, 1
        ));

        return dwarfMap;
    }
    
    @Override
    public void soigner(Player player) {


        if(player.inventory.hasItem(new Item("Holy Elixir", "this a Holy Elixir", 1 , 1 ))) {

            player.currenthealthpoints += 1;
        }
        player.currenthealthpoints += 1;
    }


}
