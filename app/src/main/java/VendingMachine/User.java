package VendingMachine;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class User {
    private String username;
    private String password;
    private String accessLevel;
    private UserInterface ui;
    private MakeTransaction currentTransaction;
    private Transaction transaction;
    private boolean storedCard;
    private String cardName;
    private String cardNumber;
    private HashMap<String, Product> products;
    private LinkedHashMap<String, Change> change;
    private boolean cancelTransaction;

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
        this.storedCard = false;
        this.cancelTransaction = false;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public void setChange(HashMap<String, Change> change2) {
        this.change = (LinkedHashMap<String, Change>) change2;
    }

    public LinkedHashMap<String, Change> getChange() {
        return this.change;
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

    public MakeTransaction getCurrentTransaction() {
        return currentTransaction;
    }

    public boolean isCardStored() {
        return this.storedCard;
    }

    public String getCardName() {
        return this.cardName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void storeCard(String cardName, String cardNumber) {
        this.storedCard = true;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
    }

    public HashMap<String, String> getCards() {
        return this.cards;
    }

    public boolean isTransactionCancelled() {
        return this.cancelTransaction;
    }

    /**
     * 
     * @param product
     * @param paymentMethod
     * @return Whether the transaction was successful.
     */
    public boolean makeTransaction() {
        this.currentTransaction = new MakeTransaction(this);
        Thread t = new Thread(currentTransaction); // myRunnable does your calculations

        long startTime = System.currentTimeMillis();
        long endTime = startTime + 120 * 1000;

        t.start(); // Kick off calculations

        while (System.currentTimeMillis() < endTime && !currentTransaction.isCancelled()) {
            // Still within time theshold, wait a little longer
            if (currentTransaction.isCancelled()) {
                break;
            }
            try {
                Thread.sleep(500L);  // Sleep 1/2 second
            } catch (InterruptedException e) {
                // Someone woke us up during sleep, that's OK
            }
        }

        currentTransaction.cancel();
        t.interrupt();  // Tell the thread to stop
        ui.displayErrorString("\nTransaction Timed Out (Press Enter)");
        try {
            t.join();       // Wait for the thread to cleanup and finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void completeTransaction() {
        if (transaction.getPaymentMethod().contains("cash")) {
            PaymentContext context = new PaymentContext(new CashStrategy(this));
            context.pay();
        } else if (transaction.getPaymentMethod().contains("card")) {
            PaymentContext context = new PaymentContext(new CardStrategy(this));
            context.pay();
        }
    }

    public void cancelTransaction() {
        currentTransaction.cancel();
        ui.displayErrorString("Transaction has been cancelled.");
    }

    /**
     * 
     * @return The selected Product.
     */
    public Product selectProduct() {
        ui.displaySelectProduct();
        String product = ui.getInput();
        if (product.toLowerCase().equals("cancel")) {
            cancelTransaction();
        } else if (products.containsKey(product)) {
            return products.get(product);
        }
        return null;
    }

    /**
     * 
     * @return The selected payment method. Either "cash" or "card".
     */
    public String selectPaymentMethod() {
        ui.displaySelectPaymentMethod();
        String paymentMethod = ui.getInput();
        if (paymentMethod.toLowerCase().equals("cancel")) {
            cancelTransaction();
        } else if (paymentMethod.contains("card")) {
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
