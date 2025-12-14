package re.forestier.edu.rpg;

import re.forestier.edu.rpg.Items.Item;

import java.util.Random;


public class Utilities {

     Utilities() {
        throw new IllegalStateException("Utility class");
     }

    static void restorerViePlayer(Player player) {
        player.currenthealthpoints = Math.min(player.currenthealthpoints, player.healthpoints);
    }

    static Item pickRandomObject(Item[] objectList){
        Random random = new Random();
        int randomNumber = random.nextInt(objectList.length);
        return objectList[randomNumber];
    }


}
