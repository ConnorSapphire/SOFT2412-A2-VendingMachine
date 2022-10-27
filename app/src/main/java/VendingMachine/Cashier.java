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
            this.getUI().displaySuccessString("The vending machine now contains " + change.getQuantity() + " of " + change.getName() + ".");
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
    public boolean removeChange(Change change) {
        if (this.getChange().containsKey(change.getName())) {
            this.getChange().remove(change.getName());
            this.getUI().getFileManager().removeChange(change);
            this.getUI().displaySuccessString("Successfully removed change " + change.getName() + ".");
            return true;
        }
        this.getUI().displayErrorString("Unable to remove change " + change.getName() + " as it does not exist in the vending machine.");
        return false;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @param value
     * @return
     */
    public boolean addChange(String name, int quantity, double value, String type) {
        if (this.getChange().containsKey(name)) {
            this.getUI().displayErrorString("Cannot add new change, name already exists.");
            return false;
        }
        if (quantity <= 0) {
            this.getUI().displayErrorString("Invalid quantity, must be greater than zero.");
            return false;
        }
        if (value <= 0) {
            this.getUI().displayErrorString("Invalid value, must be greater than zero.");
            return false;
        }
        ChangeCreator creator = new CoinCreator();
        if (type.equalsIgnoreCase("coin")) {
            Change change = creator.create(name, value, quantity);
            this.getChange().put(name, change);
            this.getUI().getFileManager().updateCoins(change);
            this.getUI().displaySuccessString("Successfully added change type " + name + " " + type + " to the machine." );
            return true;
        } else if (type.equalsIgnoreCase("note")) {
            creator = new NoteCreator();
            Change change = creator.create(name, value, quantity);
            this.getChange().put(name, change);
            this.getUI().getFileManager().updateNotes(change);
            this.getUI().displaySuccessString("Successfully added change type " + name + " " + type + " to the machine." );
            return true;
        }
        this.getUI().displayErrorString("Invalid type, please enter either 'coin' or 'note'.");
        return false;
    }

    /**
     * 
     */
    public void displayChange() {
        displayChangeTable();
        this.getUI().displayChange();
    }

    public void displayChangeTable() {
        this.getUI().displayChangeTable(this.getChange());
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
