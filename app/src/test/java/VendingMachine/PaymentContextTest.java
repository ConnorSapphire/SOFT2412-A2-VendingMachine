package VendingMachine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class PaymentContextTest {
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
    public void pay() {
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
        user.setTransaction(new Transaction(new Date(), products, "card"));
        user.storeCard("Charles", "40691");
        PaymentContext context = new PaymentContext(new CardStrategy(user));
        context.pay();
        assertTrue(out.toString().contains("Processing transaction using previously saved card details..."));
    }
}
