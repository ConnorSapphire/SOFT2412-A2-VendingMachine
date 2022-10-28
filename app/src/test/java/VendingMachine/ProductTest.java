package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.HashMap;

public class ProductTest {
    @Test
    public void initialiseName() {
        String name = "Pepsi";
        Product product = new Drink(name, null, 0, 0, 0);
        assertEquals(name, product.getName());
    }

    @Test
    public void setName() {
        String name = "Pepsi";
        Product product = new Drink(null, null, 0, 0, 0);
        product.setName(name);
        assertEquals(name, product.getName());
    }

    @Test
    public void initialiseCode() {
        String code = "PEP";
        Product product = new Drink(null, code, 0, 0, 0);
        assertEquals(code, product.getCode());
    }

    @Test
    public void setCode() {
        String code = "PEP";
        Product product = new Drink(null, null, 0, 0, 0);
        product.setCode(code);
        assertEquals(code, product.getCode());
    }

    @Test
    public void initialiseCategory() {
        String category = "drink";
        Product product = new Drink(null, null, 0, 0, 0);
        assertEquals(category, product.getCategory());
    }

    @Test
    public void setCategory() {
        String category = "drink";
        Product product = new Chocolate(null, null, 0, 0, 0);
        product.setCategory(category);
        assertEquals(category, product.getCategory());
    }

    @Test
    public void initialiseQuantity() {
        int quantity = 5;
        Product product = new Drink(null, null, 0, quantity, 0);
        assertEquals(quantity, product.getQuantity());
    }

    @Test
    public void setQuantity() {
        int quantity = 5;
        Product product = new Drink(null, null, 0, 0, 0);
        product.setQuantity(quantity);
        assertEquals(quantity, product.getQuantity());
    }

    @Test
    public void initialisePrice() {
        double price = 5.0;
        Product product = new Drink(null, null, price, 0, 0);
        assertEquals(price, product.getPrice());
    }

    @Test
    public void setPrice() {
        double price = 5.0;
        Product product = new Drink(null, null, 0, 0, 0);
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }

    @Test
    public void initialiseTotalSold() {
        int totalSold = 5;
        Product product = new Drink(null, null, 0, 0, totalSold);
        assertEquals(totalSold, product.getTotalSold());
    }

    @Test
    public void setTotalSold() {
        int totalSold = 5;
        Product product = new Drink(null, null, 0, 0, 0);
        product.setTotalSold(totalSold);
        assertEquals(totalSold, product.getTotalSold());
    }
}
