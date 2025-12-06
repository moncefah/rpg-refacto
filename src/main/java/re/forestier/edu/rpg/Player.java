package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;
    private Float __real_money__;

    private static final Map<Integer, Integer> LEVEL_XP = generateLevelsXP(100);

    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public Player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }

        this.playerName = playerName;
        Avatar_name = avatar_name;
        AvatarClass = avatarClass;
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);
    }

    public String getAvatarClass () {
        return AvatarClass;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money = Integer.parseInt(money.toString()) - amount;
    }
    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
        //le value == null est un code mort!
    }

   public static HashMap<Integer, Integer> generateLevelsXP(int maxLevel) {
        HashMap<Integer, Integer> levels = new HashMap<>();

        // xp threshold for level 2
        int previousXp = 0; 

        for (int lvl = 2; lvl <= maxLevel; lvl++) {
            int xp = (lvl - 1) * 10 + Math.round((lvl * previousXp) / 4f);
            levels.put(lvl, xp);
            previousXp = xp;
    }

    return levels;
}

   public int retrieveLevel() {
        List<Integer> sortedLevels = new ArrayList<>(LEVEL_XP.keySet());
        Collections.sort(sortedLevels);

        int lastLevel = 1;

        for (int lvl : sortedLevels) {
            int threshold = LEVEL_XP.get(lvl);

            if (xp < threshold) {
                return lvl - 1;
            }

            lastLevel = lvl;
        }

        return lastLevel;
    }


    public int getXp() {
        return this.xp;
    }

    public void setXp( int xp ) {
        this.xp=xp;
       
    }
    /*
    Ингредиенты:
        Для теста:

            250 г муки
            125 г сливочного масла (холодное)
            70 г сахара
            1 яйцо
            1 щепотка соли
     */

}