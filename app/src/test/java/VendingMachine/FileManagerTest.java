package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.*;

public class FileManagerTest {
    private FileManager fileManager = new FileManager("usersTest", "stockTest", "creditCardsTest", "changeTest");

    @Test
    public void lsNotesTest() {

    }

    @Test
    public void lsCoinsTest() {

    }

    @Test
    public void updateChipsTest() {
        ProductCreator creator = new ChipCreator();
        Product chip = creator.create("Smith", "SMH", 5.0, 10);
        fileManager.updateChips(chip);
        HashMap<String[], Double[]> chips = fileManager.lsChips();
        boolean updated = false;
        for (String[] chipString : chips.keySet()) {
            if (chipString[0].equals("Smith")) {
                if (chips.get(chipString)[0] == 5.0) {
                    if (chips.get(chipString)[1] == 10.0) {
                        updated = true;
                    }
                }
            }
        }
        assertTrue(updated);
    }

    @Test
    public void updateDrinksTest() {

    }

    @Test
    public void updateChocolatesTest() {

    }

    @Test
    public void updateCandiesTest() {
        
    }
}
