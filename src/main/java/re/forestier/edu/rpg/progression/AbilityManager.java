package re.forestier.edu.rpg.Progression;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Character.AbilitySet;

public class AbilityManager {


    AbilityManager() {
        throw new IllegalStateException("Utility class");
    }

    public static void updatePlayerAbilities(Player player, int newLevel) {
            AbilitySet newAbilities =
                    UpdatePlayer.abilitiesPerTypeAndLevel()
                            .get(player.getAvatarClass())
                            .get(newLevel);

            newAbilities.forEach((stat, value) ->
                    player.abilities.put(stat, value));
        }
}