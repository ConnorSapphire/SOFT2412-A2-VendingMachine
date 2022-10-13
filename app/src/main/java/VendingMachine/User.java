package VendingMachine;

public abstract class User {
    private String username;
    private String password;
    private String accessLevel;
    private UserInterface ui;

    /**
     * Create a new User.
     * @param username Unique name for the new User.
     * @param password Password to allow User access to their account.
     * @param accessLevel String representation of the User's access level.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public User(String username, String password, String accessLevel, UserInterface ui) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.ui = ui;
    }

    /**
     * 
     * @param product
     * @param paymentMethod
     * @return Whether the transaction was successful.
     */
    public boolean makeTransaction(Product product, String paymentMethod) {
        return true;
    }

    /**
     * 
     * @return The selected Product.
     */
    public Product selectProduct() {
        ui.displaySelectProduct();
        return null;
    }

    /**
     * 
     * @return The selected payment method. Either "cash" or "card".
     */
    public String selectPaymentMethod() {
        ui.displaySelectPaymentMethod();
        return null;
    }

    /**
     * Display all the current stock in the vending machine.
     */
    public void displayStock() {
        ui.displayStock();
    }

    /**
     * Display all the current stock in the vending machine. Including detailed product information
     * such as product code, category, price, and quantity.
     */
    public void displayDetailedStock() {
        ui.displayDetailedStock();
    }

    /**
     * 
     */
    public void displayTransactionHistory() {
        ui.displayTransactionHistory();
    }

    /**
     * 
     * @param product
     * @param quantity
     * @return
     */
    public boolean fillProduct(Product product, int quantity) {
        return true;
    }

    /**
     * 
     * @param product
     * @param newName
     * @return
     */
    public boolean modifyProductName(Product product, String name) {
        return true;
    }

    /**
     * 
     * @param product
     * @param code
     * @return
     */
    public boolean modifyProductCode(Product product, String code) {
        return true;
    }

    /**
     * 
     * @param product
     * @param price
     * @return
     */
    public boolean modifyProductPrice(Product product, double price) {
        return true;
    }

    /**
     * 
     * @param product
     * @param category
     * @return
     */
    public boolean modifyProductCategory(Product product, String category) {
        return true;
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
        return true;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean fillChange(Change change, int quantity) {
        return true;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean removeChange(Change change, int quantity) {
        return true;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @param value
     * @return
     */
    public boolean addChange(Change change, int quantity, double value) {
        return true;
    }

    /**
     * 
     */
    public void displayChange() {
        ui.displayChange();
    }

    /**
     * 
     * @param username
     * @param password
     * @param accessLevel
     * @param ui
     * @return
     */
    public boolean addUser(String username, String password, String accessLevel, UserInterface ui) {
        return true;
    }

    /**
     * 
     * @param user
     * @param accessLevel
     * @return
     */
    public boolean modifyUserAccess(User user, String accessLevel) {
        return true;
    }

    /**
     * 
     * @param user
     * @param username
     * @return
     */
    public boolean modifyUserUsername(User user, String username) {
        return true;
    }

    /**
     * 
     * @param user
     * @param password
     * @return
     */
    public boolean modifyUserPassword(User user, String password) {
        return true;
    }

    /**
     * 
     */
    public void displayUsers() {
        ui.displayUsers();
    }

    /**
     * 
     */
    public void displayCancelledTransactions() {
        ui.displayCancelledTransactions();
    }
}
