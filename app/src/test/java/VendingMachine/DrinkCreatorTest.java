package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DrinkCreatorTest {
    @Test
    public void initialiseName() {
        String name = "Juice";
        ProductCreator creator = new DrinkCreator();
        Product drink = creator.create(name, null, 0, 0, 0);
        assertEquals(name, drink.getName());
    }

    @Test
    public void initialiseCode() {
        String code = "JUC";
        ProductCreator creator = new DrinkCreator();
        Product drink = creator.create(null, code, 0, 0, 0);
        assertEquals(code, drink.getCode());
    }

    @Test
    public void initialiseCategory() {
        String category = "drink";
        ProductCreator creator = new DrinkCreator();
        Product drink = creator.create(null, null, 0, 0, 0);
        assertEquals(category, drink.getCategory());
    }

    @Test
    public void initialisePrice() {
        double price = 5.0;
        ProductCreator creator = new DrinkCreator();
        Product drink = creator.create(null, null, price, 0, 0);
        assertEquals(price, drink.getPrice());
    }

    @Test
    public void initialiseQuantity() {
        int quantity = 5;
        ProductCreator creator = new DrinkCreator();
        Product drink = creator.create(null, null, 0, quantity, 0);
        assertEquals(quantity, drink.getQuantity());
    }

    @Test
    public void initialiseTotalSold() {
        int totalSold = 5;
        ProductCreator creator = new DrinkCreator();
        Product drink = creator.create(null, null, 0, 0, totalSold);
        assertEquals(totalSold, drink.getTotalSold());
    }
}
