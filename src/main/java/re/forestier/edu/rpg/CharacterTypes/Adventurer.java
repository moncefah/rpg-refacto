package re.forestier.edu.rpg.CharacterTypes;
import re.forestier.edu.rpg.Character.AbilitySet;
import re.forestier.edu.rpg.Character.CharacterClass;
import re.forestier.edu.rpg.Character.CharacterProgression;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Player;

public class Adventurer extends CharacterClass {

  public Adventurer() {
  }

  @Override
  public CharacterProgression getProgression() {
    CharacterProgression adventurerMap = new CharacterProgression();

      adventurerMap.put(1, AbilitySet.of(
              Stats.INT, 1,
              Stats.DEF, 1,
              Stats.ATK, 3,
              Stats.CHA, 2
      ));

      adventurerMap.put(2, AbilitySet.of(
              Stats.INT, 2,
              Stats.CHA, 3
      ));

      adventurerMap.put(3, AbilitySet.of(
              Stats.ATK, 5,
              Stats.ALC, 1
      ));

      adventurerMap.put(4, AbilitySet.of(
              Stats.DEF, 3
      ));

      adventurerMap.put(5, AbilitySet.of(
              Stats.VIS, 1,
              Stats.DEF, 4
      ));


    return adventurerMap;
}
  @Override
  public void soigner(Player player) {
    player.currenthealthpoints += 2;
    if (player.retrieveLevel() < 3) {
      player.currenthealthpoints -= 1;
    }
  }



}
