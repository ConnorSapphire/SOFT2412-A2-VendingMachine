package VendingMachine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class UserInterfaceTest {

    FileManager fm = new FileManager();
    UserInterface ui = new UserInterface(fm);

    @Test
    public void getUsernameTest() {
        System.out.println("Input any thing:");
        Scanner scan = new Scanner(System.in);
        String input = "";
        if(scan.hasNextLine()){
            input = scan.nextLine();
        }
        scan.close();
        assertEquals(input, ui.getInput());
    }
}
