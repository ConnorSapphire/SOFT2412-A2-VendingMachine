package VendingMachine;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.*;
import java.io.IOException;
import java.awt.Desktop;

public class UserInterface {
    public static final String RESET = "\033[0m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private FileManager fm;
    private Scanner scanner;

    public UserInterface(FileManager fm){
        this.fm = fm;
        this.scanner = new Scanner(System.in);
    }

    public FileManager getFileManager() {
        return this.fm;
    }
        
    public boolean displayWelcomeMessage() {
        System.out.println(ANSI_CYAN + "Welcome to " + ANSI_BLUE + "Lite Snacks" + ANSI_CYAN + " vending machine." + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "For a list of all commands you have access to, type 'help'." + ANSI_RESET);
        return true;
    }
    
    /**
     * Get color from the terminal.
     * @return color of interface into the terminal.
     */
    public static String getANSIFont(String content){
        return "\033["+GREEN+"m"+content+"\033[0m";
    }
    
    /**
     * Get input from the terminal.
     * @return String representation of input into the terminal.
     */
    public String getInput() {
        System.out.print("> ");
        String input = "";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }

    public String getPlainInput() {
        String input = "";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }

    public String getInputPassword() {
        PasswordHider et = new PasswordHider(" ");
        Thread mask = new Thread(et);
        mask.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            password = in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // stop masking
        et.stopMasking();
        // return the password entered by the user
        return password;
    }  

    /**
     * Display text through terminal prompting user to select a product from the vending machine.
     */
    public boolean displaySelectProduct() {
        System.out.print(ANSI_CYAN + "Enter product (type " + ANSI_YELLOW + "'done'" + ANSI_CYAN + " to finish): " + ANSI_RESET);
        return true;
    }

    /**
     * Display text through terminal prompting user to select a method of payment. Either card or cash.
     */
    public boolean displaySelectPaymentMethod() {
        System.out.print(ANSI_CYAN + "Enter payment method (" + ANSI_YELLOW  + "'cash'" + ANSI_CYAN + " or " + ANSI_YELLOW  + "'card'" + ANSI_CYAN + "): " + ANSI_RESET);
        return true;
    }

    /**
     * Display text through terminal stating the login attempt failed.
     */
    public boolean displayLoginFailed() {
        System.out.println(ANSI_RED + "Incorrect credentials provided. Login failed." + ANSI_RESET);
        return true;
    }

    /**
     * Display text through terminal stating the login attempt was successful.
     * @param user The User account that was logged into.
     */
    public boolean displayLoginSuccess(User user) {
        System.out.println(ANSI_GREEN + "Login success! Welcome " + ANSI_CYAN + user.getUsername() + ANSI_GREEN + "!" + ANSI_RESET);
        return true;
    }

    public boolean displayLogout() {
        System.out.println(ANSI_RED + "You have been logged out! You are now an anonymous customer..." + ANSI_RESET);
        return true;
    }

    /**
     * Display text through terminal listing all current stock in the vending machine.
     */
    public boolean displayStock() {
        displayProductTable();
        return true;
    }

    /**
     * Display text through terminal with a detailed list of all current stock in the vending machine.
     * Includes product name, product code, category, price, and quantity.
     */
    public boolean displayDetailedStock() {
        HashMap<String[], Double[]> drinks = fm.lsDrinks();
        HashMap<String[], Double[]> candies = fm.lsCandies();
        HashMap<String[], Double[]> chocolates = fm.lsChocolates();
        HashMap<String[], Double[]> chips = fm.lsChips();
        fm.writeProductsFile("src/main/java/VendingMachine/products.txt", drinks, candies, chocolates, chips);
        Desktop desktop = Desktop.getDesktop();
        try { 
            desktop.open(new File("src/main/java/VendingMachine/products.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Display text through terminal with a list of each product and the total sold.
     */
    public boolean displayStockSales() {
        HashMap<String[], Double[]> drinks = fm.lsDrinks();
        HashMap<String[], Double[]> candies = fm.lsCandies();
        HashMap<String[], Double[]> chocolates = fm.lsChocolates();
        HashMap<String[], Double[]> chips = fm.lsChips();
        fm.writeSalesFile("src/main/java/VendingMachine/sales.txt", drinks, candies, chocolates, chips);
        Desktop desktop = Desktop.getDesktop();
        try { 
            desktop.open(new File("src/main/java/VendingMachine/sales.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * Display text through terminal with a list of all previous successful transactions.
     */
    public boolean displayTransactionHistory() {
        ArrayList<ArrayList<String>> transactions = fm.lsTransactionHistory();
        fm.writeTransactionFile("src/main/java/VendingMachine/transaction.txt", transactions);
        displayTransactionsTable();
        Desktop desktop = Desktop.getDesktop();
        try { 
            desktop.open(new File("src/main/java/VendingMachine/transaction.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Display text through terminal with a list of all previous unsuccessful transactions.
     */
    public boolean displayCancelledTransactions() {
        ArrayList<ArrayList<String>> cancelledTransactions = fm.lsCancelledTransactions();
        fm.writeCancelledTransactionFile("src/main/java/VendingMachine/cancelledTransactions.txt", cancelledTransactions);
        displayCancelledTransactionsTable();
        Desktop desktop = Desktop.getDesktop();
        try { 
            desktop.open(new File("src/main/java/VendingMachine/cancelledTransactions.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Display text through terminal with a list of all the change currently in the vending machine.
     */
    public boolean displayChange() {
        LinkedHashMap<String, Double[]> coins = fm.lsCoins();
        LinkedHashMap<String, Double[]> notes = fm.lsNotes();
        fm.writeChangeFile("src/main/java/VendingMachine/change.txt", coins, notes);
        Desktop desktop = Desktop.getDesktop();
        try { 
            desktop.open(new File("src/main/java/VendingMachine/change.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean displayChangeTable(LinkedHashMap<String, Change> change) {
        CommandLineTable table = new CommandLineTable();
        table.setHeaders("Name","Category", "Value", "Quantity");
        for (Change unit : change.values()) {
            table.addRow(unit.getName(), unit.getClass().getSimpleName(), String.valueOf(unit.getValue()), String.valueOf(unit.getQuantity()));
        }
        table.print();
        return true;
    }

    /**
     * Display text through terminal with a list of all registered users. Includes sellers, cashiers and owners,
     * and displays their access level.
     */
    public boolean displayUsers() {
        HashMap<String, String[]> users = fm.lsUsers();
        fm.writeUsersFile("src/main/java/VendingMachine/users.txt", users);
        Desktop desktop = Desktop.getDesktop();
        try { 
            desktop.open(new File("src/main/java/VendingMachine/users.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean displayUsersTable(HashMap<String, User> users) {
        CommandLineTable table = new CommandLineTable();
        table.setHeaders("Name", "Role");
        for (User user : users.values()) {
            table.addRow(user.getUsername(), user.getClass().getSimpleName());
        }
        table.print();
        return true;
    }

    /**
     * Display text through terminal with a list of all available commands.
     */
    public boolean displayCustomerHelp() {
        System.out.println(ANSI_CYAN + "Your current access level is " + ANSI_BLUE + "customer" + ANSI_CYAN + ". You have access to the following commands:" + ANSI_RESET);
        System.out.println("> login" + ANSI_YELLOW + "\n\tLogin to a registered account." + ANSI_RESET);
        System.out.println("> register" + ANSI_YELLOW + "\n\tRegister a new account." + ANSI_RESET);
        System.out.println("> logout" + ANSI_YELLOW + "\n\tLogout of the current account." + ANSI_RESET);
        System.out.println("> display products" + ANSI_YELLOW + "\n\tDisplay all stock in the vending machine." + ANSI_RESET);
        System.out.println("> buy" + ANSI_YELLOW + "\n\tSelect products to purchase and make payment." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
        return true;
    }

    public boolean displayCashierHelp() {
        System.out.println(ANSI_CYAN + "Your current access level is " + ANSI_BLUE + "cashier" + ANSI_CYAN + ". You have access to the following commands:" + ANSI_RESET);
        System.out.println("> login" + ANSI_YELLOW + "\n\tLogin to a registered account." + ANSI_RESET);
        System.out.println("> register" + ANSI_YELLOW + "\n\tRegister a new account." + ANSI_RESET);
        System.out.println("> logout" + ANSI_YELLOW + "\n\tLogout of the current account." + ANSI_RESET);
        System.out.println("> display products" + ANSI_YELLOW + "\n\tDisplay all stock in the vending machine." + ANSI_RESET);
        System.out.println("> buy" + ANSI_YELLOW + "\n\tSelect products to purchase and make payment." + ANSI_RESET);
        System.out.println("> display transactions" + ANSI_YELLOW + "\n\tDisplay all successful previous transactions." + ANSI_RESET);
        System.out.println("> display change" + ANSI_YELLOW + "\n\tDisplay all change in the vending machine." + ANSI_RESET);
        System.out.println("> fill change" + ANSI_YELLOW + "\n\tFill the vending machine with a selected change to a selected quantity." + ANSI_RESET);
        System.out.println("> add change" + ANSI_YELLOW + "\n\tAdd a new type of change to the machine." + ANSI_RESET);
        System.out.println("> remove change" + ANSI_YELLOW + "\n\tCompletely remove a type of change from the machine." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
        return true;
    }

    public boolean displaySellerHelp() {
        System.out.println(ANSI_CYAN + "Your current access level is " + ANSI_BLUE + "seller" + ANSI_CYAN + ". You have access to the following commands:" + ANSI_RESET);
        System.out.println("> login" + ANSI_YELLOW + "\n\tLogin to a registered account." + ANSI_RESET);
        System.out.println("> register" + ANSI_YELLOW + "\n\tRegister a new account." + ANSI_RESET);
        System.out.println("> logout" + ANSI_YELLOW + "\n\tLogout of the current account." + ANSI_RESET);
        System.out.println("> display products" + ANSI_YELLOW + "\n\tDisplay all stock in the vending machine." + ANSI_RESET);
        System.out.println("> buy" + ANSI_YELLOW + "\n\tSelect products to purchase and make payment." + ANSI_RESET);
        System.out.println("> display stock" + ANSI_YELLOW + "\n\tDisplay a detailed summary of stock." + ANSI_RESET);
        System.out.println("> display sales" + ANSI_YELLOW + "\n\tDisplay a detailed summary of stock and stock flow." + ANSI_RESET);
        System.out.println("> fill product" + ANSI_YELLOW + "\n\tFill the vending machine with a selected product to a selected quantity." + ANSI_RESET);
        System.out.println("> add product" + ANSI_YELLOW + "\n\tAdd a completely new product to the vending machine." + ANSI_RESET);
        System.out.println("> remove product" + ANSI_YELLOW + "\n\tCompletely remove a product from the vending machine." + ANSI_RESET);
        System.out.println("> modify product name" + ANSI_YELLOW + "\n\tChange the name of the selected product." + ANSI_RESET);
        System.out.println("> modify product code" + ANSI_YELLOW + "\n\tChange the code of the selected product." + ANSI_RESET);
        System.out.println("> modify product price" + ANSI_YELLOW + "\n\tChange the price of the selected product." + ANSI_RESET);
        System.out.println("> modify product category" + ANSI_YELLOW + "\n\tChange the category of the selected product." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
        return true;
    }

    public boolean displayOwnerHelp() {
        // INCOMPLETE
        System.out.println(ANSI_CYAN + "Your current access level is " + ANSI_BLUE + "owner" + ANSI_CYAN + ". You have access to the following commands:" + ANSI_RESET);
        System.out.println("> login" + ANSI_YELLOW + "\n\tLogin to a registered account." + ANSI_RESET);
        System.out.println("> register" + ANSI_YELLOW + "\n\tRegister a new account." + ANSI_RESET);
        System.out.println("> logout" + ANSI_YELLOW + "\n\tLogout of the current account." + ANSI_RESET);
        System.out.println("> display products" + ANSI_YELLOW + "\n\tDisplay all stock in the vending machine." + ANSI_RESET);
        System.out.println("> buy" + ANSI_YELLOW + "\n\tSelect products to purchase and make payment." + ANSI_RESET);
        System.out.println("> display transactions" + ANSI_YELLOW + "\n\tDisplay all successful previous transactions." + ANSI_RESET);
        System.out.println("> display change" + ANSI_YELLOW + "\n\tDisplay all change in the vending machine." + ANSI_RESET);
        System.out.println("> fill change" + ANSI_YELLOW + "\n\tFill the vending machine with a selected change to a selected quantity." + ANSI_RESET);
        System.out.println("> add change" + ANSI_YELLOW + "\n\tAdd a new type of change to the machine." + ANSI_RESET);
        System.out.println("> remove change" + ANSI_YELLOW + "\n\tCompletely remove a type of change from the machine." + ANSI_RESET);
        System.out.println("> display stock" + ANSI_YELLOW + "\n\tDisplay a detailed summary of stock and stock flow." + ANSI_RESET);
        System.out.println("> fill product" + ANSI_YELLOW + "\n\tFill the vending machine with a selected product to a selected quantity." + ANSI_RESET);
        System.out.println("> add product" + ANSI_YELLOW + "\n\tAdd a completely new product to the vending machine." + ANSI_RESET);
        System.out.println("> remove product" + ANSI_YELLOW + "\n\tCompletely remove a product from the vending machine." + ANSI_RESET);
        System.out.println("> modify product name" + ANSI_YELLOW + "\n\tChange the name of the selected product." + ANSI_RESET);
        System.out.println("> modify product code" + ANSI_YELLOW + "\n\tChange the code of the selected product." + ANSI_RESET);
        System.out.println("> modify product price" + ANSI_YELLOW + "\n\tChange the price of the selected product." + ANSI_RESET);
        System.out.println("> modify product category" + ANSI_YELLOW + "\n\tChange the category of the selected product." + ANSI_RESET);
        System.out.println("> add user" + ANSI_YELLOW + "\n\tAdd a user of any access level to the vending machine." + ANSI_RESET);
        System.out.println("> remove user" + ANSI_YELLOW + "\n\tRemove a user from the vending machine." + ANSI_RESET);
        System.out.println("> modify user name" + ANSI_YELLOW + "\n\tChange a user's name." + ANSI_RESET);
        System.out.println("> modify user password" + ANSI_YELLOW + "\n\tChange a user's password." + ANSI_RESET);
        System.out.println("> modify user role" + ANSI_YELLOW + "\n\tChange a user's role." + ANSI_RESET);
        System.out.println("> display users" + ANSI_YELLOW + "\n\tDisplay all users and their role." + ANSI_RESET);
        System.out.println("> display cancelled transactions" + ANSI_YELLOW + "\n\tDisplay all cancelled previous transactions." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
        return true;
    }

    /**
     * Display text through terminal with the outcome of a given command.
     * @param commandName The name of the command.
     * @param outcome The outcome of the command.
     */
    public boolean displayCommandOutcome(String commandName, boolean outcome) {
        if (outcome) {
            System.out.println(ANSI_GREEN + "The command " + ANSI_WHITE_BACKGROUND + "'" + commandName + "'" + ANSI_RESET + ANSI_GREEN + "was successful!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "The command " + ANSI_WHITE_BACKGROUND + "'" + commandName + "'" + ANSI_RESET + ANSI_RED + "was unsuccessful!" + ANSI_RESET);
        }
        return true;
    }

    /**
     * Display text through terminal with the given error message.
     * @param error Error message to display.
     */
    public boolean displayErrorString(String error) {
        System.out.println(ANSI_RED + error + ANSI_RESET);
        return true;
    }

    public boolean displaySuccessString(String success) {
        System.out.println(ANSI_GREEN + success + ANSI_RESET);
        return true;
    }

    public boolean displayQuestionString(String question) {
        System.out.print(ANSI_CYAN + question + ANSI_RESET);
        return true;
    }

    public boolean displayUnauthorisedAccess(String commandName) {
        System.out.println(ANSI_RED + "You do not have a high enough access level to access the command " + ANSI_YELLOW + commandName + ANSI_RED + "." + ANSI_RESET);
        return true;
    }

    public boolean displayCancelledTransactionsTable() {
        ArrayList<ArrayList<String>> transactions = fm.lsCancelledTransactions();
        CommandLineTable table = new CommandLineTable();
        table.setHeaders("Date", "Time", "User", "Reason");
        for (ArrayList<String> transaction : transactions) {
            table.addRow(transaction.get(0), transaction.get(1), transaction.get(2), transaction.get(3));
        }
        table.print();
        return true;
    }

    public boolean displayTransactionsTable() {
        ArrayList<ArrayList<String>> transactions = fm.lsTransactionHistory();
        CommandLineTable table = new CommandLineTable();
        table.setHeaders("Date", "Time", "Products", "Cost", "Paid", "Change", "Payment Method");
        for (ArrayList<String> transaction : transactions) {
            table.addRow(transaction.get(0), transaction.get(1), transaction.get(2), transaction.get(3), transaction.get(4), transaction.get(5), transaction.get(6));
        }
        table.print();
        return true;
    }

    public boolean displaySalesTable(HashMap<String, Product> products) {
        CommandLineTable st = new CommandLineTable();
        st.setHeaders("Code", "Name", "Total Quantity Sold");
        for(Product p : products.values()){
            st.addRow(p.getCode(), p.getName(), Integer.toString(p.getTotalSold()));
        }
        st.print();
        return true;
    }

    public boolean displayProductTable(){
        CommandLineTable ct = new CommandLineTable();
        ct.setHeaders("Name", "Category", "Price", "Quantity", "Shortcode");
        HashMap<String[], Double[]> Drinks = fm.lsDrinks();
        HashMap<String[], Double[]> Chocolates = fm.lsChocolates();
        HashMap<String[], Double[]> Chips = fm.lsChips();
        HashMap<String[], Double[]> Candies = fm.lsCandies();
        for(String[] d : Drinks.keySet()){
            ct.addRow(d[0], "Drinks", Double.toString(Drinks.get(d)[0]), Double.toString(Drinks.get(d)[1]), d[1]);
        }
        for(String[] d : Chocolates.keySet()){
            ct.addRow(d[0], "Chocolates", Double.toString(Chocolates.get(d)[0]), Double.toString(Chocolates.get(d)[1]), d[1]);
        }
        for(String[] d : Chips.keySet()){
            ct.addRow(d[0], "Chips", Double.toString(Chips.get(d)[0]), Double.toString(Chips.get(d)[1]), d[1]);
        }
        for(String[] d : Candies.keySet()){
            ct.addRow(d[0], "Candies", Double.toString(Candies.get(d)[0]), Double.toString(Candies.get(d)[1]), d[1]);
        }
        ct.print();
        return true;
    }

    public void last5Transactions(String username){
        ArrayList<String> trans = fm.getLastTransactions(username);
        CommandLineTable cm = new CommandLineTable();
        cm.setHeaders("Last Transactions for " + username);
        for(String str : trans){
            cm.addRow(str);
        }
        cm.print();
    }

    public boolean displayerrorMessage(String str){
        System.out.println(ANSI_RED + str + ANSI_RESET);
        return true;
    }
}
