package VendingMachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Date;
import java.util.ArrayList;

public class TransactionTest {
    @Test 
    public void initialiseStartTime() {
        Date startTime = new Date();
        Transaction transaction = new Transaction(startTime, null, null);
        assertEquals(startTime, transaction.getStartTime());
    }

    @Test
    public void initialiseProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        Transaction transaction = new Transaction(null, products, null);
        assertEquals(products, transaction.getProducts());
    }

    @Test
    public void initialisePaymentMethod() {
        String paymentMethod = "test";
        Transaction transaction = new Transaction(null, null, paymentMethod);
        assertEquals(paymentMethod, transaction.getPaymentMethod());
    }

    @Test
    public void setEndTime() {
        Transaction transaction = new Transaction(null, null, null);
        Date endTime = transaction.setEndTime();
        assertEquals(endTime, transaction.getEndTime());
    }
}
