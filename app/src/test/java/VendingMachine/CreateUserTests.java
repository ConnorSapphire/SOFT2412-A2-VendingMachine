package VendingMachine;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import javax.annotation.meta.Exhaustive;

import org.junit.jupiter.api.*;

public class CreateUserTests {

    @Test
    void getCustomerName(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, String> cards = new HashMap<>();
        cards.put("Name", "Number");
        UserCreator creator = new RegisteredCustomerCreator();
        User customer = creator.create(null, "Password", ui, cards);

        customer.setUsername("Name");

        assertEquals("Name", customer.getUsername());
    }

    @Test
    void getCustomerPassword(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, String> cards = new HashMap<>();
        cards.put("Name", "Number");
        UserCreator creator = new RegisteredCustomerCreator();
        User customer = creator.create("Name", null, ui, cards);

        customer.setPassword("Password");

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
    void setGetUsersOwner(){
        UserInterface ui = new UserInterface(null);
        HashMap<String, User> users = new HashMap<>();
        
        UserCreator creator = new OwnerCreator();
        User customer = creator.create("Name", "Password", ui, null);
        
        customer.setUsers(users);
        assertEquals(users, customer.getUsers());

    }

    @Test
    void storeNewCard(){
        UserCreator creator = new OwnerCreator();
        User customer = creator.create("Name", "Password", null, null);
        customer.storeCard("CardName", "123456");

        assertTrue(customer.isCardStored());
    }

    @Test
    void getCardNameTest(){
        UserCreator creator = new OwnerCreator();
        User customer = creator.create("Name", "Password", null, null);
        customer.storeCard("CardName", "123456");

        assertEquals("CardName", customer.getCardName());
    }

    @Test
    void getCardNumberTest(){
        UserCreator creator = new OwnerCreator();
        User customer = creator.create("Name", "Password", null, null);
        customer.storeCard("CardName", "123456");

        assertEquals("123456", customer.getCardNumber());
    }

    @Test 
    void getAccessTest(){
        UserCreator creator = new OwnerCreator();
        User customer = creator.create("Name", "Password", null, null);
        
        customer.setAccessLevel("noAccess");
        assertEquals("noAccess", customer.getAccessLevel());
    }

    @Test 
    void transactionTest(){
        UserCreator creator = new OwnerCreator();
        User customer = creator.create("Name", "Password", null, null);
        customer.storeCard("CardName", "123456");

        assertEquals("123456", customer.getCardNumber());
    }


}




