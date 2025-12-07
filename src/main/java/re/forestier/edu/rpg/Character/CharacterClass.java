package re.forestier.edu.rpg.Character;

import re.forestier.edu.rpg.Player;

public abstract   class CharacterClass {
     protected CharacterProgression progression;

     public abstract CharacterProgression getProgression();
     public abstract void soigner (Player player);



}
