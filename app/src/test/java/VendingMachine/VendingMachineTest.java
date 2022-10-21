package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class VendingMachineTest {
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
    public void fillNonexistentChangeTest() {

    }

    @Test
    public void fillChangeTest() {

    }

    @Test
    public void displayChangeTest() {

    }

    @Test
    public void displayTransactionHistoryTest() {
        
    } 
}
