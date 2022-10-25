package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.HashMap;

public class SellerTest {

    Seller user;
    FileManager fm = new FileManager();
    UserInterface ui = new UserInterface(fm);
    HashMap<String, String> cards = new HashMap<>();

    @BeforeEach
    public void setupCashier() {
        SellerCreator userCreator = new SellerCreator();
        user = (Seller) userCreator.create("", "", ui, cards);
    }

    @Test
    public void fillProductNegativeTest() {
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean filled = user.fillProduct(p, -10);
        assertFalse(filled);
    }

    @Test
    public void fillProductGreater15Test() {
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean filled = user.fillProduct(p, 11);
        assertFalse(filled);
    }

    @Test
    public void fillProductTest() {
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean filled = user.fillProduct(p, 5);
        assertTrue(filled);
    }

    @Test
    public void modifyProductNameExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        Product r = new Chocolate("sad", "SAD", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        products.put("sad", r);
        boolean modified = user.modifyProductName(p, "sad", products);
        assertFalse(modified);
    }

    @Test
    public void modifyProductName(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        boolean modified = user.modifyProductName(p, "sad", products);
        assertTrue(modified);
    }

    @Test
    public void modifyProductCodeExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        Product r = new Chocolate("sad", "SAD", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        products.put("sad", r);
        boolean modified = user.modifyProductCode(p, "SAD", products);
        assertFalse(modified);
    }

    @Test
    public void modifyProductCodeSame(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        boolean modified = user.modifyProductCode(p, "HPH", products);
        assertFalse(modified);
    }

    @Test
    public void modifyProductCodeNon_3_Digit(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        boolean modified = user.modifyProductCode(p, "HHPP", products);
        assertFalse(modified);
    }

    @Test
    public void modifyProductCode(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        boolean modified = user.modifyProductCode(p, "SAD", products);
        assertTrue(modified);
    }

    @Test
    public void modifyProductPriceNegative(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductPrice(p, -1);
        assertFalse(modified);
    }

    @Test
    public void modifyProductPriceZero(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductPrice(p, 0.0);
        assertFalse(modified);
    }

    @Test
    public void modifyProductPrice(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductPrice(p, 20.0);
        assertTrue(modified);
    }

    @Test
    public void modifyProductCategoryNot(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductCategory(p, "hh");
        assertFalse(modified);
    }

    @Test
    public void modifyProductCategory(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductCategory(p, "candies");
        assertTrue(modified);
    }

    @Test
    public void addProductNameExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        boolean added = user.addProduct("happy", "HPP", "chocolates", 5, 1, products);
        assertFalse(added);
    }

    @Test
    public void addProductCodeExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        boolean added = user.addProduct("hh", "HPH", "chocolates", 5, 1, products);
        assertFalse(added);
    }

    @Test
    public void addProductCategoryInvalid(){
        HashMap<String, Product> products = new HashMap<>();
        boolean added = user.addProduct("hh", "HPH", "hh", 5, 1, products);
        assertFalse(added);
    }

    @Test
    public void addProductNegativeQuantity(){
        HashMap<String, Product> products = new HashMap<>();
        boolean added = user.addProduct("hh", "HPH", "chocolates", -5, 1, products);
        assertFalse(added);
    }
    
    @Test
    public void addProductZeroQuantity(){
        HashMap<String, Product> products = new HashMap<>();
        boolean added = user.addProduct("hh", "HPH", "chocolates", 0, 1, products);
        assertTrue(added);
    }

    @Test
    public void addProductNegativePrice(){
        HashMap<String, Product> products = new HashMap<>();
        boolean added = user.addProduct("hh", "HPH", "chocolates", 8, -1, products);
        assertFalse(added);
    }
    
    @Test
    public void addProductZeroPrice(){
        HashMap<String, Product> products = new HashMap<>();
        boolean added = user.addProduct("hh", "HPH", "chocolates", 9, 0, products);
        assertFalse(added);
    }

    @Test
    public void addProduct(){
        HashMap<String, Product> products = new HashMap<>();
        boolean added = user.addProduct("hh", "HPH", "chocolates", 9, 9, products);
        assertTrue(added);
    }
}
