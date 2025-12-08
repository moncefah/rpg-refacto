package re.forestier.edu.rpg.CharacterTypes;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterClass;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.Player;

public class Archer extends CharacterClass {

    public Archer() {
    }

    @Override
    public CharacterProgression getProgression(){

        CharacterProgression archerMap = new  CharacterProgression();
        AbilitySet archerLevel1 = new AbilitySet();
        archerLevel1.put("INT", 1);
        archerLevel1.put("ATK", 3);
        archerLevel1.put("CHA", 1);
        archerLevel1.put("VIS", 3);
        archerMap.put(1, archerLevel1);

        AbilitySet archerLevel2 = new AbilitySet();
        archerLevel2.put("DEF", 1);
        archerLevel2.put("CHA", 2);
        archerMap.put(2, archerLevel2);

        AbilitySet archerLevel3 = new AbilitySet();
        archerLevel3.put("ATK", 3);
        archerMap.put(3, archerLevel3);

        AbilitySet archerLevel4 = new AbilitySet();
        archerLevel4.put("DEF", 2);
        archerMap.put(4, archerLevel4);

        AbilitySet archerLevel5 = new AbilitySet();
        archerLevel5.put("ATK", 4);
        archerMap.put(5, archerLevel5);


        return archerMap;


    };
    @Override
    public void soigner(Player player){
        player.currenthealthpoints+=1;
        if(player.hasItem(new Item("Magic Bow","this is a Magic Bow", 1 , 1))) {
            player.currenthealthpoints+=player.currenthealthpoints/8-1;
        }
    }

}
