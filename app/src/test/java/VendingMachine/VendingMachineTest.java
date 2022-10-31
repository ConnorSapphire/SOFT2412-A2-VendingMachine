package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class VendingMachineTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    User user;
    FileManager fm = new FileManager("usersTest", "stockTest", "creditCardsTest", "changeTest", "transactionsTest", "cancelledTransactionsTest");
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

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void initialises() {
        VendingMachine vm = new VendingMachine();
        assertFalse(vm.isQuit());
    }

    @Test
    public void helpTest() {
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("help");
        assertTrue(out.toString().contains("Exit the application."));
    }

    @Test
    public void buyTest() {
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("buy");
        assertTrue(out.toString().contains("Enter product"));
    }

    @Test
    public void cancelTest() {
        try {
            System.setIn(new FileInputStream("src/test/java/VendingMachine/cancelTest.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("buy");
        vm.handleInput("cancel");
        assertTrue(out.toString().contains("Transaction has been cancelled."));
    }

    @Test
    public void loginTest() {
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("login");
        assertTrue(out.toString().contains("Enter username"));
    }

    @Test
    public void logoutTest() {
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("logout");
        assertTrue(out.toString().contains("You have logged out!"));
    }

    @Test
    public void registerTest() {
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("register");
        assertTrue(out.toString().contains("Enter your username"));
    }

    @Test
    public void displayProductsTest() {
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("display products");
        assertTrue(out.toString().contains("Name"));
    }

    @Test
    public void quitTest() {
        VendingMachine vm = new VendingMachine(fm);
        vm.handleInput("quit");
        assertTrue(vm.isQuit());
    }
}
