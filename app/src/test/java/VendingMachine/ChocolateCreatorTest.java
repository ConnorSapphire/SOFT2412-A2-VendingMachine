package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChocolateCreatorTest {
    @Test
    public void initialiseName() {
        String name = "M&M";
        ProductCreator creator = new ChocolateCreator();
        Product chocolate = creator.create(name, null, 0, 0, 0);
        assertEquals(name, chocolate.getName());
    }

    @Test
    public void initialiseCode() {
        String code = "MAM";
        ProductCreator creator = new ChocolateCreator();
        Product chocolate = creator.create(null, code, 0, 0, 0);
        assertEquals(code, chocolate.getCode());
    }

    @Test
    public void initialiseCategory() {
        String category = "chocolate";
        ProductCreator creator = new ChocolateCreator();
        Product chocolate = creator.create(null, null, 0, 0, 0);
        assertEquals(category, chocolate.getCategory());
    }

    @Test
    public void initialisePrice() {
        double price = 5.0;
        ProductCreator creator = new ChocolateCreator();
        Product chocolate = creator.create(null, null, price, 0, 0);
        assertEquals(price, chocolate.getPrice());
    }

    @Test
    public void initialiseQuantity() {
        int quantity = 5;
        ProductCreator creator = new ChocolateCreator();
        Product chocolate = creator.create(null, null, 0, quantity, 0);
        assertEquals(quantity, chocolate.getQuantity());
    }

    @Test
    public void initialiseTotalSold() {
        int totalSold = 5;
        ProductCreator creator = new ChocolateCreator();
        Product chocolate = creator.create(null, null, 0, 0, totalSold);
        assertEquals(totalSold, chocolate.getTotalSold());
    }
}