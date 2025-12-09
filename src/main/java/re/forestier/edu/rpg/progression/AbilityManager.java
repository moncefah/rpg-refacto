package re.forestier.edu.rpg.progression;

import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.UpdatePlayer;

import java.util.HashMap;

public class AbilityManager {

    public static void updatePlayerAbilities(Player player, int newLevel) {
        HashMap<String, Integer> newAbilities =
                UpdatePlayer.abilitiesPerTypeAndLevel()
                        .get(player.getAvatarClass())
                        .get(newLevel);

        newAbilities.forEach((ability, value) ->
                player.abilities.put(ability, value));
    }
}