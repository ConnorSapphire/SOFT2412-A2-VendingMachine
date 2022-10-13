package VendingMachine;

public class RegisteredCustomerCreator implements UserCreator {
    /**
     * Create a new RegisteredCustomer stored under its parent class User.
     * @param username Unique name for the new User.
     * @param password Password to allow User access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     * @return A reference to the new User object created.
    */
    public User create(String username, String password, UserInterface ui) {
        return new RegisteredCustomer(username, password, ui);
    }
}
