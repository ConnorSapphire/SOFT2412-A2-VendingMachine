package VendingMachine;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.util.*;

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

    private static final int DEFAULT = 39;//default
    private static final int BLACK = 30;//black
    private static final int RED = 31;//red
    private static final int GREEN = 32;//green
    private static final int YELLOW = 33;//yellow
    private static final int BLUE = 34;//blue
    private static final int MAGENTA = 35;//purple
    private static final int CYAN = 36;//cyan
    private static final int WHITE = 37;//white

    private FileManager fm;
    private Scanner scanner;

    public UserInterface(FileManager fm){
        this.fm = fm;
        this.scanner = new Scanner(System.in);
    }
        
    public void displayWelcomeMessage() {
        System.out.println(ANSI_CYAN + "Welcome to " + ANSI_BLUE + "ATLANTIS" + ANSI_CYAN + " vending machine." + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "For a list of all commands you have access to, type 'help'." + ANSI_RESET);
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
        // StringBuilder input = new StringBuilder();
        // while (scanner.hasNext()) {
        //     input.append(scanner.next());
        // }
        // return input.toString();
        
        // String pass = getPlainInput();
        // // String a = asterisk(pass);
        // // System.out.println(a);
        // return pass;
        
    //     PrintStream out = System.out;
    //     try {
    //         System.setOut(new PrintStream("src/main/java/VendingMachine/attempt.txt"));
    //     } catch (FileNotFoundException fnfe) {
    //         fnfe.printStackTrace();
    //     }
    //     String password = getInput();
    //     System.setOut(out);
        return null;
    }  
        
        
    public String getInputPassword(String password) {
        String newPassword = "";
        for (int i = 0; i < password.length(); i++) {
            newPassword = newPassword + "*";
        }
        return newPassword;
    
    }

    public static String asterisk(String password) {
        if (password == null) {
            return null;
        }

        char[] chs = new char[password.length()];
        for (int i = 1; i < chs.length; i++) {
            chs[i] = '*';
        }
        chs[0] = password.charAt(0);
        chs[chs.length - 1] = password.charAt(password.length() - 1);
        return new String(chs);
    }


    /**
     * Display text through terminal prompting user to select a product from the vending machine.
     */
    public void displaySelectProduct() {
        System.out.println(ANSI_CYAN + "Please select a product (type 'done' to finish selection): " + ANSI_RESET);
    }

    /**
     * Display text through terminal prompting user to select a method of payment. Either card or cash.
     */
    public void displaySelectPaymentMethod() {
        System.out.println(ANSI_CYAN + "Please enter your payment method, either " + ANSI_YELLOW  + "'cash'" + ANSI_CYAN + " or " + ANSI_YELLOW  + "'card'" + ANSI_CYAN + "." + ANSI_RESET);
    }

    /**
     * Display text through terminal stating the login attempt failed.
     */
    public void displayLoginFailed() {
        System.out.println(ANSI_RED + "Incorrect credentials provided. Login failed." + ANSI_RESET);
    }

    /**
     * Display text through terminal stating the login attempt was successful.
     * @param user The User account that was logged into.
     */
    public void displayLoginSuccess(User user) {
        System.out.println(ANSI_CYAN + "Login success! Welcome " + ANSI_BLUE + user.getUsername() + ANSI_CYAN + "!" + ANSI_RESET);
    }

    public void displayLogout() {
        System.out.println(ANSI_RED + "You have logged out! You are now an anonymous customer..." + ANSI_RESET);
    }

    /**
     * Display text through terminal listing all current stock in the vending machine.
     */
    public void displayStock() {
        displayProductTable();
    }

    /**
     * Display text through terminal with a detailed list of all current stock in the vending machine.
     * Includes product name, product code, category, price, and quantity.
     */
    public void displayDetailedStock() {
        List<String> dataList = fm.readTextFile("src/main/java/VendingMachine/product.txt","utf-8");  //file can be utf8 or gbk
        for(String data:dataList){
            System.out.println(data);
        }
    }

    /**
     * Display text through terminal with a list of each product and the total sold.
     */
    public void displayStockSales() {

    }


    /**
     * Display text through terminal with a list of all previous successful transactions.
     */
    public void displayTransactionHistory() {
        List<String> dataList = fm.readTextFile("src/main/java/VendingMachine/transaction.txt","utf-8");  //file can be utf8 or gbk
        for(String data:dataList){
            System.out.println(data);
        }
    }

    /**
     * Display text through terminal with a list of all previous unsuccessful transactions.
     */
    public void displayCancelledTransactions() {

    }

    /**
     * Display text through terminal with a list of all the change currently in the vending machine.
     */
    public void displayChange() {
        List<String> dataList = fm.readTextFile("src/main/java/VendingMachine/change.txt","utf-8");  
        for(String data:dataList){
            System.out.println(data);
        }
    }

    /**
     * Display text through terminal with a list of all registered users. Includes sellers, cashiers and owners,
     * and displays their access level.
     */
    public void displayUsers() {

    }

    /**
     * Display text through terminal with a list of all available commands.
     */
    public void displayCustomerHelp() {
        System.out.println(ANSI_CYAN + "Your current access level is " + ANSI_BLUE + "customer" + ANSI_CYAN + ". You have access to the following commands:" + ANSI_RESET);
        System.out.println("> login" + ANSI_YELLOW + "\n\tLogin to a registered account." + ANSI_RESET);
        System.out.println("> register" + ANSI_YELLOW + "\n\tRegister a new account." + ANSI_RESET);
        System.out.println("> logout" + ANSI_YELLOW + "\n\tLogout of the current account." + ANSI_RESET);
        System.out.println("> display products" + ANSI_YELLOW + "\n\tDisplay all stock in the vending machine." + ANSI_RESET);
        System.out.println("> buy" + ANSI_YELLOW + "\n\tSelect products to purchase and make payment." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
    }

    public void displayCashierHelp() {
        // INCOMPLETE
        System.out.println(ANSI_CYAN + "Your current access level is " + ANSI_BLUE + "cashier" + ANSI_CYAN + ". You have access to the following commands:" + ANSI_RESET);
        System.out.println("> login" + ANSI_YELLOW + "\n\tLogin to a registered account." + ANSI_RESET);
        System.out.println("> register" + ANSI_YELLOW + "\n\tRegister a new account." + ANSI_RESET);
        System.out.println("> logout" + ANSI_YELLOW + "\n\tLogout of the current account." + ANSI_RESET);
        System.out.println("> display products" + ANSI_YELLOW + "\n\tDisplay all stock in the vending machine." + ANSI_RESET);
        System.out.println("> buy" + ANSI_YELLOW + "\n\tSelect products to purchase and make payment." + ANSI_RESET);
        System.out.println("> display transactions" + ANSI_YELLOW + "\n\tDisplay all successful previous transactions." + ANSI_RESET);
        System.out.println("> display change" + ANSI_YELLOW + "\n\tDisplay all change in the vending machine." + ANSI_RESET);
        System.out.println("> fill change" + ANSI_YELLOW + "\n\tFill the vending machine with a selected change to a selected quantity." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
    }

    public void displaySellerHelp() {
        // INCOMPLETE
        System.out.println(ANSI_CYAN + "Your current access level is " + ANSI_BLUE + "seller" + ANSI_CYAN + ". You have access to the following commands:" + ANSI_RESET);
        System.out.println("> login" + ANSI_YELLOW + "\n\tLogin to a registered account." + ANSI_RESET);
        System.out.println("> register" + ANSI_YELLOW + "\n\tRegister a new account." + ANSI_RESET);
        System.out.println("> logout" + ANSI_YELLOW + "\n\tLogout of the current account." + ANSI_RESET);
        System.out.println("> display products" + ANSI_YELLOW + "\n\tDisplay all stock in the vending machine." + ANSI_RESET);
        System.out.println("> buy" + ANSI_YELLOW + "\n\tSelect products to purchase and make payment." + ANSI_RESET);
        System.out.println("> display stock" + ANSI_YELLOW + "\n\tDisplay a detailed summary of stock and stock flow." + ANSI_RESET);
        System.out.println("> fill product" + ANSI_YELLOW + "\n\tFill the vending machine with a selected product to a selected quantity." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
    }

    public void displayOwnerHelp() {
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
        System.out.println("> display stock" + ANSI_YELLOW + "\n\tDisplay a detailed summary of stock and stock flow." + ANSI_RESET);
        System.out.println("> fill product" + ANSI_YELLOW + "\n\tFill the vending machine with a selected product to a selected quantity." + ANSI_RESET);
        System.out.println("> help" + ANSI_YELLOW + "\n\tDisplay all available commands." + ANSI_RESET);
        System.out.println("> quit" + ANSI_YELLOW + "\n\tExit the application." + ANSI_RESET);
    }

    /**
     * Display text through terminal with the outcome of a given command.
     * @param commandName The name of the command.
     * @param outcome The outcome of the command.
     */
    public void displayCommandOutcome(String commandName, boolean outcome) {
        if (outcome) {
            System.out.println(ANSI_GREEN + "The command " + ANSI_WHITE_BACKGROUND + "'" + commandName + "'" + ANSI_RESET + ANSI_GREEN + "was successful!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "The command " + ANSI_WHITE_BACKGROUND + "'" + commandName + "'" + ANSI_RESET + ANSI_RED + "was unsuccessful!" + ANSI_RESET);
        }
    }

    /**
     * Display text through terminal with the given error message.
     * @param error Error message to display.
     */
    public void displayErrorString(String error) {
        System.out.println(ANSI_RED + error + ANSI_RESET);
    }

    public void displayUnauthorisedAccess(String commandName) {
        System.out.println(ANSI_RED + "You do not have a high enough access level to access this feature." + ANSI_RESET);
    }

    public void displayProductTable(){
        CommandLineTable ct = new CommandLineTable();
        ct.setHeaders("Category", "Name", "Price", "Quantity", "Shortcode");
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

    public void displayerrorMessage(String str){
        System.out.println(ANSI_RED + str);
    }
}
