package re.forestier.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class GlobalTest {

    @Test
    void testAffichageBase() {

        ArrayList<String> inventory;
        inventory = new ArrayList<>();
        inventory.add("Sword");
        inventory.add("Shield");
        inventory.add("Potion");

        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200,inventory );
        UpdatePlayer.addXp(player, 20);
        
        System.out.println(Affichage.afficherJoueur(player));
    }
}
