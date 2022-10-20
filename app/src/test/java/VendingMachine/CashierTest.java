package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.util.HashMap;


public class CashierTest {
    User user;
    FileManager fm = new FileManager();
    UserInterface ui = new UserInterface(fm);

    @BeforeEach
    public void setupCashier() {
        UserCreator userCreator = new CashierCreator();
        user = userCreator.create("", "", ui, new HashMap<String, String>());
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
    public void fillChangeTest() {
        ChangeCreator creator = new NoteCreator();
        Change change = creator.create("$10", 10.0, 1);
        boolean filled = user.fillChange(change, 10);
        assertTrue(filled);
    }
}
