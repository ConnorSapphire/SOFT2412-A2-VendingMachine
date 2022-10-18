package VendingMachine;

import java.util.HashMap;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class UserInterface {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private FileManager fm;

    public UserInterface(FileManager fm){
        this.fm = fm;
    }

    public void displayWelcomeMessage() {
        System.out.println(ANSI_CYAN + "Welcome to " + ANSI_BLUE + "ATLANTIS" + ANSI_CYAN + "vending machine." + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "For a list of all commands you have access to, type " + ANSI_WHITE_BACKGROUND + "'help'" + ANSI_RESET);
    }
    
    /**
     * Get input from the terminal.
     * @return String representation of input into the terminal.
     */
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        if(scanner.hasNextLine()){
            input = scanner.nextLine();
        }
        scanner.close();
        return input;
    }

    public String getInputPassword(){
        System.out.println("Enter your password: ");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        if(scanner.hasNextLine()){
            input = scanner.nextLine();
        }
        scanner.close();
        return input;
    }

    /**
     * Display text through terminal prompting user to select a product from the vending machine.
     */
    public void displaySelectProduct() {
        System.out.println("Please select a product: ");
    }

    /**
     * Display text through terminal prompting user to select a method of payment. Either card or cash.
     */
    public void displaySelectPaymentMethod() {

    }

    /**
     * Display text through terminal stating the login attempt failed.
     */
    public void displayLoginFailed() {

    }

    /**
     * Display text through terminal stating the login attempt was successful.
     * @param user The User account that was logged into.
     */
    public void displayLoginSuccess(User user) {

    }


    /**
     * Display text through terminal listing all current stock in the vending machine.
     */
    public void displayStock() {

    }

    /**
     * Display text through terminal with a detailed list of all current stock in the vending machine.
     * Includes product name, product code, category, price, and quantity.
     */
    public void displayDetailedStock() {

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

    }

    public void displayCashierHelp() {

    }

    public void displaySellerHelp() {

    }

    public void displayOwnerHelp() {

    }

    /**
     * Display text through terminal with the outcome of a given command.
     * @param commandName The name of the command.
     * @param outcome The outcome of the command.
     */
    public void displayCommandOutcome(String commandName, boolean outcome) {

    }

    /**
     * Display text through terminal with the given error message.
     * @param error Error message to display.
     */
    public void displayErrorString(String error) {

    }

    public void displayUnauthorisedAccess(String commandName) {

    }

    public void displayProductTable(){
        CommandLineTable ct = new CommandLineTable();
        ct.setHeaders("Category", "Name", "Price", "Quantity");
        HashMap<String, Double[]> Drinks = fm.lsDrinks();
        HashMap<String, Double[]> Chocolates = fm.lsChocolates();
        HashMap<String, Double[]> Chips = fm.lsChips();
        HashMap<String, Double[]> Candies = fm.lsCandies();
        for(String d : Drinks.keySet()){
            ct.addRow(d, "Drinks", Double.toString(Drinks.get(d)[0]), Double.toString(Drinks.get(d)[1]));
        }
        for(String d : Chocolates.keySet()){
            ct.addRow(d, "Chocolates", Double.toString(Chocolates.get(d)[0]), Double.toString(Chocolates.get(d)[1]));
        }
        for(String d : Chips.keySet()){
            ct.addRow(d, "Chips", Double.toString(Chips.get(d)[0]), Double.toString(Chips.get(d)[1]));
        }
        for(String d : Candies.keySet()){
            ct.addRow(d, "Candies", Double.toString(Candies.get(d)[0]), Double.toString(Candies.get(d)[1]));
        }
        ct.print();
    }
    
}
