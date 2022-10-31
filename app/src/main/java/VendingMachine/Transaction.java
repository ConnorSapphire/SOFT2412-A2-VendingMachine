package VendingMachine;

import java.util.Date;
import java.util.ArrayList;

public class Transaction {
    private Date startTime;
    private Date endTime;
    private ArrayList<Product> products;
    private String paymentMethod;
    private boolean isComplete;

    /**
     * @param startTime
     * @param products
     * @param paymentMethod
     */
    public Transaction(Date startTime, ArrayList<Product> products, String paymentMethod) {
        this.startTime = startTime;
        this.products = products;
        this.paymentMethod = paymentMethod;
        this.isComplete = false;
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

    public Date setEndTime() {
        this.endTime = new Date();
        return this.endTime;
    }

    public void complete() {
        this.isComplete = true;
    }

    public boolean isComplete() {
        return this.isComplete;
    }
}
