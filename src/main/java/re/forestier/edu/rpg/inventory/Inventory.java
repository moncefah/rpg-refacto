package re.forestier.edu.rpg.Inventory;

import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.Items.Item;

import java.util.ArrayList;

public class Inventory extends ArrayList<Item> {

    private int maxWeight = 20;

    public Inventory() {}

    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addItem(Item item) {
        int totalWeight = this.stream().mapToInt(Item::getWeight).sum();
        if (totalWeight + item.getWeight() > maxWeight) return false;
        return super.add(item); // ArrayList add()
    }

    public boolean hasItem(Item item) {
        return this.stream().anyMatch(i -> i.getName().equals(item.getName()));
    }

    public boolean sell(Item item, Player player) {
    if (!player.inventory.contains(item)) return false;

    player.inventory.remove(item);
    player.addMoney(item.getValue());
    return true;
}

}
