package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CandyCreatorTest {
    @Test
    public void initialiseName() {
        String name = "Skittle";
        ProductCreator creator = new CandyCreator();
        Product candy = creator.create(name, null, 0, 0, 0);
        assertEquals(name, candy.getName());
    }

    @Test
    public void initialiseCode() {
        String code = "SKT";
        ProductCreator creator = new CandyCreator();
        Product candy = creator.create(null, code, 0, 0, 0);
        assertEquals(code, candy.getCode());
    }

    @Test
    public void initialiseCategory() {
        String category = "candy";
        ProductCreator creator = new CandyCreator();
        Product candy = creator.create(null, null, 0, 0, 0);
        assertEquals(category, candy.getCategory());
    }

    @Test
    public void initialisePrice() {
        double price = 5.0;
        ProductCreator creator = new CandyCreator();
        Product candy = creator.create(null, null, price, 0, 0);
        assertEquals(price, candy.getPrice());
    }

    @Test
    public void initialiseQuantity() {
        int quantity = 5;
        ProductCreator creator = new CandyCreator();
        Product candy = creator.create(null, null, 0, quantity, 0);
        assertEquals(quantity, candy.getQuantity());
    }

    @Test
    public void initialiseTotalSold() {
        int totalSold = 5;
        ProductCreator creator = new CandyCreator();
        Product candy = creator.create(null, null, 0, 0, totalSold);
        assertEquals(totalSold, candy.getTotalSold());
    }
}
