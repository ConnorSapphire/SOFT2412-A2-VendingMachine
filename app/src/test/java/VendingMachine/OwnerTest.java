package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class OwnerTest {
    Owner user;
    FileManager fm = new FileManager();
    UserInterface ui = new UserInterface(fm);
    HashMap<String, String> cards = new HashMap<>();

    @BeforeEach
    public void setupOwner() {
        OwnerCreator userCreator = new OwnerCreator();
        user = (Owner) userCreator.create("", "", ui, cards);
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
    public void fillProductTest1() {
        Product p = new Drink("happy", "HPH", 1.0, 5, 0);
        boolean filled = user.fillProduct(p, 5);
        assertTrue(filled);
    }

    @Test
    public void fillProductTest2() {
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean filled = user.fillProduct(p, 5);
        assertTrue(filled);
    }

    @Test
    public void fillProductTest3() {
        Product p = new Chip("happy", "HPH", 1.0, 5, 0);
        boolean filled = user.fillProduct(p, 5);
        assertTrue(filled);
    }

    @Test
    public void fillProductTest4() {
        Product p = new Candy("happy", "HPH", 1.0, 5, 0);
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
        user.setProducts(products);
        boolean modified = user.modifyProductName(p, "sad");
        assertFalse(modified);
    }

    @Test
    public void modifyProductNameSame(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductName(p, "happy");
        assertFalse(modified);
    }

    @Test
    public void modifyProductName1(){
        Product p = new Drink("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductName(p, "sad");
        assertTrue(modified);
    }

    @Test
    public void modifyProductName2(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductName(p, "sad");
        assertTrue(modified);
    }

    @Test
    public void modifyProductName3(){
        Product p = new Chip("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductName(p, "sad");
        assertTrue(modified);
    }

    @Test
    public void modifyProductName4(){
        Product p = new Candy("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductName(p, "sad");
        assertTrue(modified);
    }

    @Test
    public void modifyProductCodeExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        Product r = new Chocolate("sad", "SAD", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        products.put("sad", r);
        user.setProducts(products);
        boolean modified = user.modifyProductCode(p, "SAD");
        assertFalse(modified);
    }

    @Test
    public void modifyProductCodeSame(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductCode(p, "HPH");
        assertFalse(modified);
    }

    @Test
    public void modifyProductCodeNon_3_Digit(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductCode(p, "HHPP");
        assertFalse(modified);
    }

    @Test
    public void modifyProductCode1(){
        Product p = new Drink("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductCode(p, "SAD");
        assertTrue(modified);
    }

    @Test
    public void modifyProductCode2(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductCode(p, "SAD");
        assertTrue(modified);
    }

    @Test
    public void modifyProductCode3(){
        Product p = new Chip("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductCode(p, "SAD");
        assertTrue(modified);
    }

    @Test
    public void modifyProductCode4(){
        Product p = new Candy("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean modified = user.modifyProductCode(p, "SAD");
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
    public void modifyProductPrice1(){
        Product p = new Drink("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductPrice(p, 20.0);
        assertTrue(modified);
    }

    @Test
    public void modifyProductPrice2(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductPrice(p, 20.0);
        assertTrue(modified);
    }

    @Test
    public void modifyProductPrice3(){
        Product p = new Chip("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductPrice(p, 20.0);
        assertTrue(modified);
    }

    @Test
    public void modifyProductPrice4(){
        Product p = new Candy("happy", "HPH", 1.0, 5, 0);
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
    public void modifyProductCategory1(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductCategory(p, "drink");
        assertTrue(modified);
    }

    @Test
    public void modifyProductCategory2(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductCategory(p, "chocolate");
        assertTrue(modified);
    }

    @Test
    public void modifyProductCategory3(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductCategory(p, "chip");
        assertTrue(modified);
    }

    @Test
    public void modifyProductCategory4(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        boolean modified = user.modifyProductCategory(p, "candy");
        assertTrue(modified);
    }

    @Test
    public void addProductNameExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        Product r = new Chocolate("sad", "SAD", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        products.put("sad", r);
        user.setProducts(products);
        boolean added = user.addProduct("happy", "HPP", "chocolate", 5, 1);
        assertFalse(added);
    }

    @Test
    public void addProductCodeExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 0);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "chocolate", 5, 1);
        assertFalse(added);
    }

    @Test
    public void addProductCodeNon3Digits(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPHH", "chocolate", 5, 1);
        assertFalse(added);
    }

    @Test
    public void addProductCategoryInvalid(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "hh", 5, 1);
        assertFalse(added);
    }

    @Test
    public void addProductNegativeQuantity(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "chocolate", -5, 1);
        assertFalse(added);
    }
    
    @Test
    public void addProductZeroQuantity(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "chocolate", 0, 1);
        assertTrue(added);
    }

    @Test
    public void addProductNegativePrice(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "chocolate", 8, -1);
        assertFalse(added);
    }
    
    @Test
    public void addProductZeroPrice(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "chocolate", 9, 0);
        assertFalse(added);
    }

    @Test
    public void addProduct1(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "drink", 9, 9);
        assertTrue(added);
    }

    @Test
    public void addProduct2(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "chocolate", 9, 9);
        assertTrue(added);
    }

    @Test
    public void addProduct3(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "chip", 9, 9);
        assertTrue(added);
    }

    @Test
    public void addProduct4(){
        HashMap<String, Product> products = new HashMap<>();
        user.setProducts(products);
        boolean added = user.addProduct("hh", "HPH", "candy", 9, 9);
        assertTrue(added);
    }

    @Test
    public void testRemoveProductNotExists(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 5);
        Product r = new Chocolate("sad", "SAD", 5.0, 3, 3);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        assertFalse(user.removeProduct(r));
    }

    @Test
    public void testRemoveProduct(){
        Product p = new Chocolate("happy", "HPH", 1.0, 5, 5);
        HashMap<String, Product> products = new HashMap<>();
        products.put("happy", p);
        user.setProducts(products);
        assertTrue(user.removeProduct(p));
    }

    @Test
    public void fillChangeNegativeTest() {
        ChangeCreator creator = new NoteCreator();
        Change change = creator.create("$10", 10.0, 1);
        boolean filled = user.fillChange(change, -10);
        assertFalse(filled);
    }

    @Test
    public void fillChangeZeroTest() {
        ChangeCreator creator = new NoteCreator();
        Change change = creator.create("$10", 10.0, 1);
        boolean filled = user.fillChange(change, 0);
        assertFalse(filled);
    }

    @Test
    public void fillChangeNote() {
        ChangeCreator creator = new NoteCreator();
        Change change = creator.create("$10", 10.0, 1);
        boolean filled = user.fillChange(change, 10);
        assertTrue(filled);
    }

    @Test
    public void fillChangeCoin() {
        ChangeCreator creator = new CoinCreator();
        Change change = creator.create("$10", 10.0, 1);
        boolean filled = user.fillChange(change, 10);
        assertTrue(filled);
    }

    @Test
    public void removeChangeNotExist(){
        ChangeCreator creator = new NoteCreator();
        Change change1 = creator.create("$10", 10.0, 1);
        Change change2 = creator.create("$5", 5.0, 2);
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        all.put("$5", change2);
        user.setChange(all);
        boolean removed = user.removeChange(change1);
        assertFalse(removed);
    }

    @Test
    public void removeChangeTest(){
        ChangeCreator creator = new NoteCreator();
        Change change = creator.create("$10", 10.0, 1);
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        all.put("$10", change);
        user.setChange(all);
        boolean removed = user.removeChange(change);
        assertTrue(removed);
    }

    @Test
    public void addChangeNameExists(){
        ChangeCreator creator = new NoteCreator();
        Change change = creator.create("$10", 10.0, 1);
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        all.put("$10", change);
        user.setChange(all);
        boolean added = user.addChange("$10", 1, 10.0, "note");
        assertFalse(added);
    }

    @Test
    public void addChangeQuantityNegative(){
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        user.setChange(all);
        boolean added = user.addChange("$10", -1, 10.0, "note");
        assertFalse(added);
    }

    @Test
    public void addChangeQuantityZero(){
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        user.setChange(all);
        boolean added = user.addChange("$10", 0, 10.0, "note");
        assertFalse(added);
    }

    @Test
    public void addChangeValueNegative(){
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        user.setChange(all);
        boolean added = user.addChange("$10", 1, -1.0, "note");
        assertFalse(added);
    }

    @Test
    public void addChangeValueZero(){
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        user.setChange(all);
        boolean added = user.addChange("$10", 1, 0.0, "note");
        assertFalse(added);
    }

    @Test
    public void addChangeTypeInvalid(){
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        user.setChange(all);
        boolean added = user.addChange("$10", 1, 10.0, "hh");
        assertFalse(added);
    }

    @Test
    public void addChangeTypeCoin(){
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        user.setChange(all);
        boolean added = user.addChange("$10", 1, 10.0, "coin");
        assertTrue(added);
    }

    @Test
    public void addChangeTypeNote(){
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        user.setChange(all);
        boolean added = user.addChange("$10", 1, 10.0, "note");
        assertTrue(added);
    }

    @Test
    public void displayChangeTableTest(){
        ChangeCreator creator = new NoteCreator();
        Change change1 = creator.create("$10", 10.0, 1);
        Change change2 = creator.create("$5", 5.0, 2);
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        all.put("$10", change1);
        all.put("$5", change2);
        user.setChange(all);
        assertTrue(user.displayChangeTable());
    }

    @Test
    public void displayHelpTest(){
        assertTrue(user.displayHelp());
    }
}
