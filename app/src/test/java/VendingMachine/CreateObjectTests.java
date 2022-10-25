package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CreateObjectTests {
    
    @Test 
    void createRegCustTest(){
        UserCreator customerCreator = new RegisteredCustomerCreator();
    }

}
