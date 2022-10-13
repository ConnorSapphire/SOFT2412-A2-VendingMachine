package VendingMachine;

public class Owner extends User {
    /**
     * Create a new Owner.
     * @param username Unique name for the new Owner.
     * @param password Password to allow Owner access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public Owner(String username, String password, UserInterface ui) {
        super(username, password, "owner", ui);
    } 
}
