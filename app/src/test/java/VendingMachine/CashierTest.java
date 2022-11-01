package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.util.HashMap;
import java.util.LinkedHashMap;


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
    public void displayChangeTest(){
        ChangeCreator creator = new NoteCreator();
        Change change1 = creator.create("$10", 10.0, 1);
        Change change2 = creator.create("$5", 5.0, 2);
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        all.put("$10", change1);
        all.put("$5", change2);
        user.setChange(all);
        user.displayChange();
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
        user.displayChangeTable();
    }

    @Test
    public void displayTransactionHistoryTest(){
        ChangeCreator creator = new NoteCreator();
        Change change1 = creator.create("$10", 10.0, 1);
        Change change2 = creator.create("$5", 5.0, 2);
        LinkedHashMap<String, Change> all = new LinkedHashMap<>();
        all.put("$10", change1);
        all.put("$5", change2);
        user.setChange(all);
        user.displayTransactionHistory();
    }

    @Test
    public void displayHelpTest(){
        user.displayHelp();
    }
}
