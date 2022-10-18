package VendingMachine;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.HashMap;

public abstract class User {
    private String username;
    private String password;
    private String accessLevel;
    private UserInterface ui;
    private Transaction currentTransaction;
    private HashMap<String, String> cards;

    /**
     * Create a new User.
     * @param username Unique name for the new User.
     * @param password Password to allow User access to their account.
     * @param accessLevel String representation of the User's access level.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public User(String username, String password, String accessLevel, UserInterface ui, HashMap<String, String> cards) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.ui = ui;
        this.cards = cards;
    }

    /**
     * 
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 
     * @return
     */
    public String getAccessLevel() {
        return this.accessLevel;
    }

    /**
     * 
     * @return
     */
    public UserInterface getUI() {
        return this.ui;
    }

    public HashMap<String, String> getCards() {
        return this.cards;
    }

    /**
     * 
     * @param product
     * @param paymentMethod
     * @return Whether the transaction was successful.
     */
    public boolean makeTransaction() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // cancel transaction
            }
        }, 120000);
        Date startTime = new Date();
        ArrayList<Product> products = new ArrayList<Product>();
        Product product = selectProduct();
        while(product != null) {
            products.add(product);
            product = selectProduct();
        }
        if (products.isEmpty()) {
            return false;
        }
        String paymentMethod = selectPaymentMethod();
        Transaction transaction = new Transaction(startTime, products, paymentMethod);
        currentTransaction = transaction;
        transaction.setEndTime();
        completeTransaction();
        return true;
    }
    
    public void completeTransaction() {
        if (currentTransaction.getPaymentMethod().contains("cash")) {
            PaymentContext context = new PaymentContext(new CashStrategy(ui));
            context.pay();
        } else if (currentTransaction.getPaymentMethod().contains("card")) {
            PaymentContext context = new PaymentContext(new CardStrategy(ui, cards));
            context.pay();
        }
    }

    public void cancelTransaction() {

    }

    /**
     * 
     * @return The selected Product.
     */
    public Product selectProduct() {
        ui.displaySelectProduct();
        ui.getInput();
        return null;
    }

    /**
     * 
     * @return The selected payment method. Either "cash" or "card".
     */
    public String selectPaymentMethod() {
        ui.displaySelectPaymentMethod();
        String paymentMethod = ui.getInput();
        if (paymentMethod.contains("card")) {
            return "card";
        } else if (paymentMethod.contains("cash")) {
            return "cash";
        }
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
        ui.displayUnauthorisedAccess("displayDetailedStock");
    }

    /**
     * Display all stock, with details about how much was sold of each. "product code; product name; quantity sold".
     */
    public void displayStockSales() {
        ui.displayUnauthorisedAccess("displayStockSales");
    }

    /**
     * Display all previous transactions. Including transaction time, product name, amount paid, change given, and
     * payment method.
     */
    public void displayTransactionHistory() {
        ui.displayUnauthorisedAccess("displayTransactionHistory");
    }

    /**
     * 
     * @param product
     * @param quantity
     * @return
     */
    public boolean fillProduct(Product product, int quantity) {
        ui.displayUnauthorisedAccess("fillProduct");
        return false;
    }

    /**
     * 
     * @param product
     * @param newName
     * @return
     */
    public boolean modifyProductName(Product product, String name) {
        ui.displayUnauthorisedAccess("modifyProductName");
        return false;
    }

    /**
     * 
     * @param product
     * @param code
     * @return
     */
    public boolean modifyProductCode(Product product, String code) {
        ui.displayUnauthorisedAccess("modifyProductCode");
        return false;
    }

    /**
     * 
     * @param product
     * @param price
     * @return
     */
    public boolean modifyProductPrice(Product product, double price) {
        ui.displayUnauthorisedAccess("modifyProductPrice");
        return false;
    }

    /**
     * 
     * @param product
     * @param category
     * @return
     */
    public boolean modifyProductCategory(Product product, String category) {
        ui.displayUnauthorisedAccess("modifyProductCategory");
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
        ui.displayUnauthorisedAccess("addProduct");
        return false;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean fillChange(Change change, int quantity) {
        ui.displayUnauthorisedAccess("fillChange");
        return false;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean removeChange(Change change, int quantity) {
        ui.displayUnauthorisedAccess("removeChange");
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
        ui.displayUnauthorisedAccess("addChange");
        return false;
    }

    /**
     * 
     */
    public void displayChange() {
        ui.displayUnauthorisedAccess("displayChange");
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
        ui.displayUnauthorisedAccess("addUser");
        return false;
    }

    /**
     * 
     * @param user
     * @param accessLevel
     * @return
     */
    public boolean modifyUserAccess(User user, String accessLevel) {
        ui.displayUnauthorisedAccess("modifyUserAccess");
        return false;
    }

    /**
     * 
     * @param user
     * @param username
     * @return
     */
    public boolean modifyUserUsername(User user, String username) {
        ui.displayUnauthorisedAccess("modifyUserUsername");
        return false;
    }

    /**
     * 
     * @param user
     * @param password
     * @return
     */
    public boolean modifyUserPassword(User user, String password) {
        ui.displayUnauthorisedAccess("modifyUserPassword");
        return false;
    }

    /**
     * 
     */
    public void displayUsers() {
        ui.displayUnauthorisedAccess("displayUsers");
    }

    /**
     * 
     */
    public void displayCancelledTransactions() {
        ui.displayUnauthorisedAccess("displayCancelledTransactions");
    }

    public void displayHelp() {
        ui.displayCustomerHelp();
    }
}
