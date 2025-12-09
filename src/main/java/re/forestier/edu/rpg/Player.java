package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;
    private Float __real_money__;


    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<Item> inventory;
    private static final Map<Integer, Integer> LEVEL_XP = null;
    public int maxWeight = 20;
    public Player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<Item> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF")&& !avatarClass.equals("GOBLIN"))  {
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
    public int retrieveLevel() {
        // (lvl-1) * 10 + round((lvl * xplvl-1)/4)
        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2,10); // 1*10 + ((2*0)/4)
        levels.put(3,27); // 2*10 + ((3*10)/4)
        levels.put(4,57); // 3*10 + ((4*27)/4)
        levels.put(5,111); // 4*10 + ((5*57)/4)
        //TODO : ajouter les prochains niveaux

        if (xp < levels.get(2)) {
            return 1;
        }
        else if (xp < levels.get(3)) {return 2;
        }
        if (xp < levels.get(4)) {
            return 3;
        }
        if (xp < levels.get(5)) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }

    public boolean hasItem(Item item ){
        //TODO : complete the hasItem method , try to update all the classes to impplement the new method in soigner()

        for (Item i : inventory){
            if (i.getName().equals(item.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean  addItem(Item newItem){
        int totalWeight = 0;
        for (Item item : inventory) {
            totalWeight = item.getWeight();
        }
        totalWeight += newItem.getWeight();
        if(totalWeight > maxWeight) {return false ;}

        inventory.add(newItem);
        return true;
    }
    public void sell(Item item){
        if (inventory.contains(item)){
            inventory.remove(item);
            addMoney(item.getValue());
        }
    }




}