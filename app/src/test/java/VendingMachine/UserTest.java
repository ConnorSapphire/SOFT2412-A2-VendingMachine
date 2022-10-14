package VendingMachine;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class UserTest {
    User user;
    UserInterface ui = new UserInterface();

    @BeforeEach
    public void setupUser() {
        UserCreator userCreator = new AnonymousCustomerCreator();
        user = userCreator.create("", "", ui);
    }

    @Test
    public void getUsernameTest() {
        assertEquals("", user.getUsername());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("", user.getPassword());
    }

    @Test
    public void getAccessLevelTest() {
        assertEquals("customer", user.getAccessLevel());
    }

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
