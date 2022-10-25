package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.*;

public class FileManagerTest {
    private FileManager fileManager = new FileManager("usersTest", "stockTest", "creditCardsTest", "changeTest", "transactionTest");

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
        ProductCreator creator = new DrinkCreator();
        Product drink = creator.create("Sprite", "SPT", 5.0, 10);
        fileManager.updateDrinks(drink);
        HashMap<String[], Double[]> drinks = fileManager.lsDrinks();
        boolean updated = false;
        for (String[] drinkString : drinks.keySet()) {
            if (drinkString[0].equals("Sprite")) {
                if (drinks.get(drinkString)[0] == 5.0) {
                    if (drinks.get(drinkString)[1] == 10.0) {
                        updated = true;
                    }
                }
            }
        }
        assertTrue(updated);
    }

    @Test
    public void updateChocolatesTest() {
        ProductCreator creator = new ChipCreator();
        Product chocolate = creator.create("Mars", "MAS", 5.0, 10);
        fileManager.updateChocolates(chocolate);
        HashMap<String[], Double[]> chocolates = fileManager.lsChocolates();
        boolean updated = false;
        for (String[] chocolateString : chocolates.keySet()) {
            if (chocolateString[0].equals("Mars")) {
                if (chocolates.get(chocolateString)[0] == 5.0) {
                    if (chocolates.get(chocolateString)[1] == 10.0) {
                        updated = true;
                    }
                }
            }
        }
        assertTrue(updated);
    }

    @Test
    public void updateCandiesTest() {
        ProductCreator creator = new ChipCreator();
        Product candy = creator.create("Skittles", "SKI", 5.0, 10);
        fileManager.updateCandies(candy);
        HashMap<String[], Double[]> candies = fileManager.lsCandies();
        boolean updated = false;
        for (String[] candyString : candies.keySet()) {
            if (candyString[0].equals("Skittles")) {
                if (candies.get(candyString)[0] == 5.0) {
                    if (candies.get(candyString)[1] == 10.0) {
                        updated = true;
                    }
                }
            }
        }
        assertTrue(updated);
    }
}
