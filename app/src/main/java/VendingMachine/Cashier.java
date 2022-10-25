package VendingMachine;

import java.util.HashMap;

public class Cashier extends User {
    /**
     * Create a new Cashier.
     * @param username Unique name for the new Cashier.
     * @param password Password to allow Cashier access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public Cashier(String username, String password, UserInterface ui, HashMap<String, String> cards) {
        super(username, password, "cashier", ui, cards);
    }    

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean fillChange(Change change, int quantity) {
        if (quantity > 0) {
            change.setQuantity(change.getQuantity() + quantity);
            System.out.println("The vending machine now contains " + change.getQuantity() + " of " + change.getName() + ".");
            if (change.getClass().getSimpleName().equalsIgnoreCase("Note")) {
                this.getUI().getFileManager().updateNotes(change);
            } else if (change.getClass().getSimpleName().equalsIgnoreCase("Coin")) {
                this.getUI().getFileManager().updateCoins(change);
            }
            return true;
        }
        getUI().displayErrorString("Quantity provided is invalid. Cannot have negative or zero of a coin/note.");
        return false;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean removeChange(Change change, int quantity) {
        return false;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @param value
     * @return
     */
    public boolean addChange(Change change, int quantity, double value) {
        return false;
    }

    /**
     * 
     */
    public void displayChange() {
        this.getUI().displayChange();
    }

    /**
     * Display all previous transactions. Including transaction time, product name, amount paid, change given, and
     * payment method.
     */
    public void displayTransactionHistory() {
        this.getUI().displayTransactionHistory();
    }

    public void displayHelp() {
        this.getUI().displayCashierHelp();
    }
}
