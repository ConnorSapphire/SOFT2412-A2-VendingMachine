package VendingMachine;

import java.util.HashMap;

public class Owner extends User {
    /**
     * Create a new Owner.
     * @param username Unique name for the new Owner.
     * @param password Password to allow Owner access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public Owner(String username, String password, UserInterface ui, HashMap<String, String> cards) {
        super(username, password, "owner", ui, cards);
    } 

    // SELLER METHODS

    /**
     * 
     */
    public void displayDetailedStock() {
        this.getUI().displayDetailedStock();
    }

    /**
     * 
     */
    public void displayStockSales() {
        this.getUI().displayStockSales();
    }

    /**
     * 
     * @param product
     * @param quantity
     * @return
     */
    public boolean fillProduct(Product product, int quantity) {
        return false;
    }

    /**
     * 
     * @param product
     * @param newName
     * @return
     */
    public boolean modifyProductName(Product product, String name) {
        return false;
    }

    /**
     * 
     * @param product
     * @param code
     * @return
     */
    public boolean modifyProductCode(Product product, String code) {
        return false;
    }

    /**
     * 
     * @param product
     * @param price
     * @return
     */
    public boolean modifyProductPrice(Product product, double price) {
        return false;
    }

    /**
     * 
     * @param product
     * @param category
     * @return
     */
    public boolean modifyProductCategory(Product product, String category) {
        return false;
    }

    /**
     * 
     * @param name
     * @param code
     * @param category
     * @param quantity
     * @param price
     * @return
     */
    public boolean addProduct(String name, String code, String category, int quantity, double price) {
        return false;
    }

    // CASHIER METHODS

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean fillChange(Change change, int quantity) {
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

    // OWNER METHODS

    /**
     * 
     * @param username
     * @param password
     * @param accessLevel
     * @param ui
     * @return
     */
    public boolean addUser(String username, String password, String accessLevel, UserInterface ui) {
        UserCreator creator = new RegisteredCustomerCreator();
        if (accessLevel.equalsIgnoreCase("seller")) {
            creator = new SellerCreator();
        } else if (accessLevel.equalsIgnoreCase("cashier")) {
            creator = new CashierCreator();
        } else if (accessLevel.equalsIgnoreCase("owner")) {
            creator = new OwnerCreator();
        }
        for (User exists : this.getUsers().values()) {
            if (exists.getUsername().equals(username)) {
                this.getUI().displayErrorString("Username already exists. Unable to create new user.");
                return false;
            }
        }
        User newUser = creator.create(username, password, this.getUI(), this.getCards());
        this.getUsers().put(username, newUser);
        this.getUI().getFileManager().updateUsers(newUser);
        this.getUI().displaySuccessString("Successfully created new user!");
        return true;
    }

    public boolean removeUser(User user) {
        if (this.getUsers().containsKey(user.getUsername())) {
            this.getUsers().remove(user.getUsername());
            this.getUI().getFileManager().removeUser(user);
            this.getUI().displaySuccessString("Successfully removed user " + user.getUsername() + ".");
            return true;
        }
        this.getUI().displayerrorMessage("Cannot remove user, as " + user.getUsername() + " does not exist in vending machine.");
        return false;
    }

    /**
     * 
     * @param user
     * @param accessLevel
     * @return
     */
    public boolean modifyUserAccess(User user, String accessLevel) {
        return false;
    }

    /**
     * 
     * @param user
     * @param username
     * @return
     */
    public boolean modifyUserUsername(User user, String username) {
        return false;
    }

    /**
     * 
     * @param user
     * @param password
     * @return
     */
    public boolean modifyUserPassword(User user, String password) {
        return false;
    }

    /**
     * 
     */
    public void displayUsers() {
        this.getUI().displayUsers();
    }

    /**
     * 
     */
    public void displayCancelledTransactions() {
        this.getUI().displayCancelledTransactions();
    }

    public void displayHelp() {
        this.getUI().displayOwnerHelp();
    }
}
