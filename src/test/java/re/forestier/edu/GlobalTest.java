package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class GlobalTest {

    @Test
    void testAffichageBase() {

        ArrayList<Item> inventory;
        inventory = new ArrayList<>();
        inventory.add(new Item("Sowrd", "it's a sword", 1 , 1));
        inventory.add(new Item("Shield", "it's a Shield", 1 , 1));
        inventory.add(new Item("Potion", "it's a Potion", 1 , 1));


        Player player = new Player("Florian", "Gnognak le Barbare", "ADVENTURER", 200,inventory );
        UpdatePlayer.addXp(player, 20);
        
        System.out.println(Affichage.afficherJoueur(player));
    }
}
