package VendingMachine;

import java.util.HashMap;

public class RegisteredCustomer extends Customer {
    /**
     * Create a new RegisteredCustomer.
     * @param username Unique name for the new RegisteredCustomer.
     * @param password Password to allow RegisteredCustomer access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public RegisteredCustomer(String username, String password, UserInterface ui, HashMap<String, String> cards) {
        super(username, password, "registered", ui, cards);
    }

}
