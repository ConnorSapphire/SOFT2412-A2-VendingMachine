package VendingMachine;

import java.util.ArrayList;
import java.util.Date;

public class MakeTransaction implements Runnable {

    private boolean cancelTransaction;
    private Transaction currentTransaction;
    private User user;

    public MakeTransaction(User user) {
        this.user = user;
        this.cancelTransaction = false;
    }

    public void cancel(String reason) {
        this.cancelTransaction = true;
        Date endTime = new Date();
        this.user.getUI().getFileManager().updateCancelTransaction(user, endTime, reason);
    }
 
    public void cancel() {
        cancel("Cancelled by user.");
    }

    public void finish() {
        this.cancelTransaction = true;
    }

    public boolean isCancelled() {
        return this.cancelTransaction;
    }

    public void run() {
        Date startTime = new Date();
        ArrayList<Product> prods = new ArrayList<Product>();
        user.displayStock();
        Product product = user.selectProduct();
        while(product != null && !cancelTransaction) {
            prods.add(product);
            product = user.selectProduct();
        }
        double cost = 0;
        for (Product prod : prods) {
            cost += prod.getPrice();
        }
        String paymentMethod = "";
        if (!cancelTransaction) {
            if (prods.isEmpty()) {
                user.getUI().displayErrorString("No products selected. Please view available stock and try again.");
                cancel();
                return;
            } else {
                System.out.println("Selection complete, total price is $" + cost + ".");
            }
            paymentMethod = user.selectPaymentMethod();
        }
        if (!cancelTransaction) {
            currentTransaction = new Transaction(startTime, prods, paymentMethod);
            user.setTransaction(currentTransaction);
            user.completeTransaction();
        }
        finish();
        return;
    }
}
