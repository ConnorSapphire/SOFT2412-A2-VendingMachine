package VendingMachine;

import java.util.Date;
import java.util.ArrayList;

public class Transaction {
    private Date startTime;
    private Date endTime;
    private ArrayList<Product> products;
    private String paymentMethod;

    /**
     * @param startTime
     * @param products
     * @param paymentMethod
     */
    public Transaction(Date startTime, ArrayList<Product> products, String paymentMethod) {
        this.startTime = startTime;
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

    public void setEndTime() {
        this.endTime = new Date();
    }
}
