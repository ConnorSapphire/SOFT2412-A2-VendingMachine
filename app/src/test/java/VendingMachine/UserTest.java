package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class UserTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    User user;
    FileManager fm = new FileManager();
    UserInterface ui = new UserInterface(fm);

    @BeforeEach
    public void setupUser() {
        UserCreator userCreator = new AnonymousCustomerCreator();
        user = userCreator.create("", "", ui, new HashMap<String, String>());
    }

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(out, true));
    }

    @BeforeEach
    public void restoreDefault() {

    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void setGetChangeTest() {
        HashMap<String, Change> change= new HashMap<String, Change>();
        change.put("test", new Note("test", 0.0, 1));
        user.setChange(change);
        assertSame(change, user.getChange());
    }

    @Test
    public void cancelTransactionTest() {
        user.cancelTransaction();
        assertTrue(user.isTransactionCancelled());
    }

    @Test
    public void getUsernameTest() {
        assertEquals("", user.getUsername());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("", user.getPassword());
    }

    // @Test
    // public void getAccessLevelTest() {
    //     assertEquals("customer", user.getAccessLevel());
    // }

    @Test
    public void getUITest() {
        assertEquals(ui, user.getUI());
    }

    @Test
    public void fillProductTest() {
        assertFalse(user.fillProduct(null, 0));
    }

    @Test
    public void modifyProductNameTest() {
        assertFalse(user.modifyProductName(null, null));
    }

    @Test
    public void modifyProductCodeTest() {
        assertFalse(user.modifyProductCode(null, null));
    }

    @Test
    public void modifyProductCategoryTest() {
        assertFalse(user.modifyProductCategory(null, null));
    }

    @Test
    public void modifyProductPriceTest() {
        assertFalse(user.modifyProductPrice(null, 0));
    }

    @Test
    public void addProductTest() {
        assertFalse(user.addProduct(null, null, null, 0, 0));
    }

    @Test
    public void fillChangeTest() {
        assertFalse(user.fillChange(null, 0));
    }

    @Test
    public void removeChangeTest() {
        assertFalse(user.removeChange(null, 0));
    }

    @Test
    public void addChangeTest() {
        assertFalse(user.addChange(null, 0, 0));
    }

    @Test
    public void addUserTest() {
        assertFalse(user.addUser(null, null, null, ui));
    }

    @Test
    public void modifyUserAccessTest() {
        assertFalse(user.modifyUserAccess(null, null));
    }

    @Test
    public void modifyUserUsernameTest() {
        assertFalse(user.modifyUserUsername(null, null));
    }

    @Test
    public void modifyUserPasswordTest() {
        assertFalse(user.modifyUserPassword(null, null));
    }

    // TODO: displayUsers, displayCancelledTransactions, displayChange, displayTransactionHistory, 
    // displayDetailedStock, displayStock, selectPaymentMethod, selectProduct, makeTransaction,
    // displayStockSales
}
