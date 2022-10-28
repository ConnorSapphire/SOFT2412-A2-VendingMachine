package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.*;

public class CreateUserTests {

    @Test
    void getCustomerName(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, String> cards = new HashMap<>();
        cards.put("Name", "Number");
        UserCreator creator = new RegisteredCustomerCreator();
        User customer = creator.create("Name", "Password", ui, cards);

        assertEquals("Name", customer.getUsername());
    }

    @Test
    void getCustomerPassword(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, String> cards = new HashMap<>();
        cards.put("Name", "Number");
        UserCreator creator = new RegisteredCustomerCreator();
        User customer = creator.create("Name", "Password", ui, cards);

        assertEquals("Password", customer.getPassword());
    }

    @Test
    void getCustomerUI(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, String> cards = new HashMap<>();
        cards.put("Name", "Number");
        UserCreator creator = new RegisteredCustomerCreator();
        User customer = creator.create("Name", "Password", ui, cards);

        assertEquals(ui, customer.getUI());
    }

    @Test
    void getCustomerCards(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, String> cards = new HashMap<>();
        cards.put("Name", "Number");
        UserCreator creator = new RegisteredCustomerCreator();
        User customer = creator.create("Name", "Password", ui, cards);

        assertEquals(cards, customer.getCards());
    }

    @Test
    void storeCardTest(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, String> cards = new HashMap<>();
        cards.put("Name", "Number");
        UserCreator creator = new RegisteredCustomerCreator();
        User customer = creator.create("Name", "Password", ui, cards);

        assertEquals(cards, customer.getCards());
    }

    @Test
    void setGetUsers(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, User> users = new HashMap<>();
        
        UserCreator creator = new OwnerCreator();
        User customer = creator.create("Name", "Password", ui, null);
        
        customer.setUsers(users);
        assertEquals(users, customer.getUsers());

    }



}


