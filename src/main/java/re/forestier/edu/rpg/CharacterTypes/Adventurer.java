package re.forestier.edu.rpg.CharacterTypes;
import re.forestier.edu.rpg.AbilitySet;
import re.forestier.edu.rpg.CharacterClass;
import re.forestier.edu.rpg.CharacterProgression;

public class Adventurer extends CharacterClass {

  public Adventurer() {
  }

  @Override
      protected CharacterProgression getProgression(){

        CharacterProgression adventurerMap = new CharacterProgression();
        AbilitySet adventurerLevel1 = new AbilitySet();
        adventurerLevel1.put("INT", 1);
        adventurerLevel1.put("DEF", 1);
        adventurerLevel1.put("ATK", 3);
        adventurerLevel1.put("CHA", 2);
        adventurerMap.put(1, adventurerLevel1);

        AbilitySet adventurerLevel2 = new AbilitySet();
        adventurerLevel2.put("INT", 2);
        adventurerLevel2.put("CHA", 3);
        adventurerMap.put(2, adventurerLevel2);

        AbilitySet adventurerLevel3 = new AbilitySet();
        adventurerLevel3.put("ATK", 5);
        adventurerLevel3.put("ALC", 1);
        adventurerMap.put(3, adventurerLevel3);

        AbilitySet adventurerLevel4 = new AbilitySet();
        adventurerLevel4.put("DEF", 3);
        adventurerMap.put(4, adventurerLevel4);

        AbilitySet adventurerLevel5 = new AbilitySet();
        adventurerLevel5.put("VIS", 1);
        adventurerLevel5.put("DEF", 4);
        adventurerMap.put(5, adventurerLevel5);

        return adventurerMap;


    };



}
