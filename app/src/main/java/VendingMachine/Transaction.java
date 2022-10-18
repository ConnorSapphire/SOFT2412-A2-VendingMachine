package VendingMachine;

import javax.xml.crypto.Data;
import java.util.*;

public class Transaction {
    private Date startTime;
    private Date endTime;
    private ArrayList<Product> products;
    private String paymentMethod;

    /**
     * @param startTime
     * @param endTime
     * @param products
     * @param paymentMethod
     */
    public Transaction(Date startTime, Date endTime, ArrayList<Product> products, String paymentMethod) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.products = products;
        this.paymentMethod = paymentMethod;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void makePayment() {
        
    }
}
