package VendingMachine;

import java.util.Scanner;
import org.json.simple.JSONObject;

public class UserInterface {

    private FileManager fm;

    public UserInterface(FileManager fm){
        this.fm = fm;
    }
    
    /**
     * Get input from the terminal.
     * @return String representation of input into the terminal.
     */
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    public String getInputPassword(){
        String password = ("Enter your password: ");
        Scanner scanner = new Scanner(System.in);
        // User string builder
        String input = scanner.nextLine();
        
        scanner.close();
        return input;
    }

    /**
     * Display text through terminal prompting user to select a product from the vending machine.
     */
    public void displaySelectProduct() {
        
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

    public void displayProduct(){
        
    }
}
