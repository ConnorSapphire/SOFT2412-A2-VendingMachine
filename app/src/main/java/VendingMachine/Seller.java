package VendingMachine;

public class Seller extends User {
    /**
     * Create a new Seller.
     * @param username Unique name for the new Seller.
     * @param password Password to allow Seller access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public Seller(String username, String password, UserInterface ui) {
        super(username, password, "seller", ui);
    }
}
