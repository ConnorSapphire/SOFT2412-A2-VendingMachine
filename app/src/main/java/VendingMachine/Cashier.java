package VendingMachine;

public class Cashier extends User {
    /**
     * Create a new Cashier.
     * @param username Unique name for the new Cashier.
     * @param password Password to allow Cashier access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public Cashier(String username, String password, UserInterface ui) {
        super(username, password, "cashier", ui);
    }    
}
