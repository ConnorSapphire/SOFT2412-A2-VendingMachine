package VendingMachine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class CardStrategyTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setStreams() {
        System.setOut(new PrintStream(out, true));
    }

    @AfterEach
    public void restoreInitialStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void storedCard() {
        String users = "usersTest";
        String stock = "stockTest";
        String cards = "creditCardsTest";
        String change = "changeTest";
        String transactions = "transactionsTest";
        String cancelled = "cancelledTransactionsTest";
        FileManager fm = new FileManager(users, stock, cards, change, transactions, cancelled);
        UserInterface ui = new UserInterface(fm);
        HashMap<String, String> cardsMap = new HashMap<String, String>();
        cardsMap.put("Charles", "40691");
        User user = new AnonymousCustomer(ui, cardsMap);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Drink("Pepsi", "PPS", 3.0, 4, 5));
        products.add(new Chocolate("M&M", "MAM", 2.5, 5, 1));
        products.add(new Candy("Lollipop", "POP", 1.5, 5, 1));
        products.add(new Chip("Smiths", "SMT", 3.5, 5, 1));
        user.setTransaction(new Transaction(new Date(), products, "card"));
        user.storeCard("Charles", "40691");
        PaymentContext context = new PaymentContext(new CardStrategy(user));
        context.pay();
        assertTrue(out.toString().contains("Processing transaction using previously saved card details..."));
    }

    @Test
    public void enterredCardValid() {
        InputStream in = System.in;
        try {
            System.setIn(new FileInputStream("src/test/java/VendingMachine/enterredCardValid.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String users = "usersTest";
        String stock = "stockTest";
        String cards = "creditCardsTest";
        String change = "changeTest";
        String transactions = "transactionsTest";
        String cancelled = "cancelledTransactionsTest";
        FileManager fm = new FileManager(users, stock, cards, change, transactions, cancelled);
        UserInterface ui = new UserInterface(fm);
        HashMap<String, String> cardsMap = new HashMap<String, String>();
        cardsMap.put("Charles", "40691");
        User user = new AnonymousCustomer(ui, cardsMap);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Drink("Pepsi", "PPS", 3.0, 4, 5));
        products.add(new Chocolate("M&M", "MAM", 2.5, 5, 1));
        products.add(new Candy("Lollipop", "POP", 1.5, 5, 1));
        products.add(new Chip("Smiths", "SMT", 3.5, 5, 1));
        user.setTransaction(new Transaction(new Date(), products, "card"));
        user.setCurrentTransaction(new MakeTransaction(user));
        PaymentContext context = new PaymentContext(new CardStrategy(user));
        context.pay();
        System.setIn(in);
        assertTrue(out.toString().contains("Purchase successful! Your card has been charged"));
    }

    @Test
    public void enterredCardInvalid() {
        InputStream in = System.in;
        try {
            System.setIn(new FileInputStream("src/test/java/VendingMachine/enterredCardInvalid.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String users = "usersTest";
        String stock = "stockTest";
        String cards = "creditCardsTest";
        String change = "changeTest";
        String transactions = "transactionsTest";
        String cancelled = "cancelledTransactionsTest";
        FileManager fm = new FileManager(users, stock, cards, change, transactions, cancelled);
        UserInterface ui = new UserInterface(fm);
        HashMap<String, String> cardsMap = new HashMap<String, String>();
        cardsMap.put("Charles", "40691");
        User user = new AnonymousCustomer(ui, cardsMap);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Drink("Pepsi", "PPS", 3.0, 4, 5));
        products.add(new Chocolate("M&M", "MAM", 2.5, 5, 1));
        products.add(new Candy("Lollipop", "POP", 1.5, 5, 1));
        products.add(new Chip("Smiths", "SMT", 3.5, 5, 1));
        user.setTransaction(new Transaction(new Date(), products, "card"));
        user.setCurrentTransaction(new MakeTransaction(user));
        PaymentContext context = new PaymentContext(new CardStrategy(user));
        context.pay();
        System.setIn(in);
        assertTrue(out.toString().contains("The provided card details were invalid. Have a great day!"));
    }

    @Test
    public void cancelledBeforeSelection() {
        InputStream in = System.in;
        try {
            System.setIn(new FileInputStream("src/test/java/VendingMachine/enterredCardValid.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String users = "usersTest";
        String stock = "stockTest";
        String cards = "creditCardsTest";
        String change = "changeTest";
        String transactions = "transactionsTest";
        String cancelled = "cancelledTransactionsTest";
        FileManager fm = new FileManager(users, stock, cards, change, transactions, cancelled);
        UserInterface ui = new UserInterface(fm);
        HashMap<String, String> cardsMap = new HashMap<String, String>();
        cardsMap.put("Charles", "40691");
        User user = new AnonymousCustomer(ui, cardsMap);
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Drink("Pepsi", "PPS", 3.0, 4, 5));
        products.add(new Chocolate("M&M", "MAM", 2.5, 5, 1));
        products.add(new Candy("Lollipop", "POP", 1.5, 5, 1));
        products.add(new Chip("Smiths", "SMT", 3.5, 5, 1));
        user.setTransaction(new Transaction(new Date(), products, "card"));
        MakeTransaction mt = new MakeTransaction(user);
        user.setCurrentTransaction(mt);
        mt.cancel();
        PaymentContext context = new PaymentContext(new CardStrategy(user));
        context.pay();
        System.setIn(in);
        assertFalse(out.toString().contains("Enter card number:"));
    }


}
