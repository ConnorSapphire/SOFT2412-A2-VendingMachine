package VendingMachine;

import java.util.HashMap;

public interface UserCreator {
    /**
     * Create a new User.
     * @param username Unique name for the new User.
     * @param password Password to allow User access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     * @return A reference to the new User created.
     */
    public User create(String username, String password, UserInterface ui, HashMap<String, String> cards);
}
