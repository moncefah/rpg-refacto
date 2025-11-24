package re.forestier.edu.rpg;

import java.util.Random;


public class Utilities {
     static void soignerAdventurer(Player player){
        player.currenthealthpoints+=2;
        if(player.retrieveLevel() < 3) {
            player.currenthealthpoints-=1;
        }
    }
    static void soignerDwarf(Player player){
        if(player.inventory.contains("Holy Elixir")) {
            player.currenthealthpoints+=1;
        }
        player.currenthealthpoints+=1;
    }
    static void soignerArcher(Player player){
        player.currenthealthpoints+=1;
        if(player.inventory.contains("Magic Bow")) {
            player.currenthealthpoints+=player.currenthealthpoints/8-1;
        }
    }
    static void restorerViePlayer(Player player) {
        player.currenthealthpoints = Math.min(player.currenthealthpoints, player.healthpoints);
    }

    static String pickRandomObject(String[] objectList){
        Random random = new Random();
        int randomNumber = random.nextInt(objectList.length);
        return objectList[randomNumber];
    }
















}
