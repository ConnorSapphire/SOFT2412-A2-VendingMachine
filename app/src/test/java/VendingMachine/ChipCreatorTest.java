package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ChipCreatorTest {
    @Test
    public void initialiseName() {
        String name = "Smiths";
        ProductCreator creator = new ChipCreator();
        Product chip = creator.create(name, null, 0, 0, 0);
        assertEquals(name, chip.getName());
    }

    @Test
    public void initialiseCode() {
        String code = "SMT";
        ProductCreator creator = new ChipCreator();
        Product chip = creator.create(null, code, 0, 0, 0);
        assertEquals(code, chip.getCode());
    }

    @Test
    public void initialiseCategory() {
        String category = "chip";
        ProductCreator creator = new ChipCreator();
        Product chip = creator.create(null, null, 0, 0, 0);
        assertEquals(category, chip.getCategory());
    }

    @Test
    public void initialisePrice() {
        double price = 5.0;
        ProductCreator creator = new ChipCreator();
        Product chip = creator.create(null, null, price, 0, 0);
        assertEquals(price, chip.getPrice());
    }

    @Test
    public void initialiseQuantity() {
        int quantity = 5;
        ProductCreator creator = new ChipCreator();
        Product chip = creator.create(null, null, 0, quantity, 0);
        assertEquals(quantity, chip.getQuantity());
    }

    @Test
    public void initialiseTotalSold() {
        int totalSold = 5;
        ProductCreator creator = new ChipCreator();
        Product chip = creator.create(null, null, 0, 0, totalSold);
        assertEquals(totalSold, chip.getTotalSold());
    }
}