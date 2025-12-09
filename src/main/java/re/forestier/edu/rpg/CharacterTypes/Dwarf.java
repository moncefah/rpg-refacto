package re.forestier.edu.rpg.CharacterTypes;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterClass;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Player;

public class Dwarf extends CharacterClass {

    public Dwarf() {
    }

    @Override
    public  CharacterProgression getProgression(){

        CharacterProgression dwarfMap = new CharacterProgression();

        AbilitySet dwarfLevel1 = new AbilitySet();
        dwarfLevel1.put("ALC", 4);
        dwarfLevel1.put("INT", 1);
        dwarfLevel1.put("ATK", 3);
        dwarfMap.put(1, dwarfLevel1);

        AbilitySet dwarfLevel2 = new AbilitySet();
        dwarfLevel2.put("DEF", 1);
        dwarfLevel2.put("ALC", 5);
        dwarfMap.put(2, dwarfLevel2);

        AbilitySet dwarfLevel3 = new AbilitySet();
        dwarfLevel3.put("ATK", 4);
        dwarfMap.put(3, dwarfLevel3);

        AbilitySet dwarfLevel4 = new AbilitySet();
        dwarfLevel4.put("DEF", 2);
        dwarfMap.put(4, dwarfLevel4);

        AbilitySet dwarfLevel5 = new AbilitySet();
        dwarfLevel5.put("CHA", 1);
        dwarfMap.put(5, dwarfLevel5);

        return dwarfMap;


    };
    @Override
    public void soigner(Player player) {


        if(player.inventory.hasItem(new Item("Holy Elixir", "this a Holy Elixir", 1 , 1 ))) {

            player.currenthealthpoints += 1;
        }
        player.currenthealthpoints += 1;
    }


}
