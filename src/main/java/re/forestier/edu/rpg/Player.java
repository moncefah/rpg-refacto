package re.forestier.edu.rpg;
import re.forestier.edu.rpg.Character.Stats;
import re.forestier.edu.rpg.Items.Item;
import re.forestier.edu.rpg.Progression.LevelSystem;
import re.forestier.edu.rpg.Inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;
    protected int xp;

    public int level;
    public int healthpoints;
    public int currenthealthpoints;

    public HashMap<Stats, Integer> abilities;
    public Inventory inventory;

    private static final LevelSystem LEVEL_SYSTEM = new LevelSystem(100);

    public Player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<Item> initialInventory) {

        if (!avatarClass.equals("ARCHER")
                && !avatarClass.equals("ADVENTURER")
                && !avatarClass.equals("DWARF")
                && !avatarClass.equals("GOBLIN")) {
            throw new IllegalArgumentException("Invalid Class Type");
        }

        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        this.AvatarClass = avatarClass;

        this.money = money;
        this.inventory = new Inventory();

        if (initialInventory != null) {
            this.inventory.addAll(initialInventory);
        }

            // Level 1 abilities
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel()
                .get(AvatarClass)
                .get(1);
    }

    public String getAvatarClass() {
        return AvatarClass;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void removeMoney(int amount) {
        if (money - amount < 0)
            throw new IllegalArgumentException("Player can't have negative money!");

        money -= amount;
    }

    public int getXp() {
        return xp;
    }
    
    public int getMoney() {
        return money;
    }

    public void setXp(int xp) {
        this.xp = xp;
        this.level = LEVEL_SYSTEM.computeLevel(xp);
    }

    public int retrieveLevel() {
        return LEVEL_SYSTEM.computeLevel(xp);
    }


}