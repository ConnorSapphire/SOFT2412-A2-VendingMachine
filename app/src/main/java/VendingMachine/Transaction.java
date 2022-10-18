package VendingMachine;

import javax.xml.crypto.Data;
import java.util.*;

public class Transaction {
    private Date startTime;
    private Date endTime;
    private Product product;
    private String paymentMethod;

    /**
     * @param startTime
     * @param endTime
     * @param product
     * @param paymentMethod
     */
    public Transaction(Date startTime, Date endTime, Product product, String paymentMethod) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.product = product;
        this.paymentMethod = paymentMethod;
    }

    
}
