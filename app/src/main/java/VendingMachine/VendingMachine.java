package VendingMachine;

import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VendingMachine {
    private FileManager fileManager = new FileManager();
    private UserInterface ui = new UserInterface(fileManager);
    private User user;
    private HashMap<String, User> users;
    private HashMap<String, Product> products;
    private HashMap<String, Change> change;
    private HashMap<String, String> cards;
    private JSONObject json;
    private boolean quit;

    public VendingMachine() {
        UserCreator userCreator = new AnonymousCustomerCreator();
        this.cards = fileManager.getCreditCards();
        this.user = userCreator.create("", "", ui, cards);
        this.quit = false;
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        // display welcome message
        // display stock
        vm.ui.displayProductTable();
        // display help
        while (!vm.quit) {
            vm.handleInput();
        }
    }

    public User login(){
        // Get username and password
        System.out.print("Enter username: ");
        String username = ui.getInput();
        
        String password = ui.getInputPassword();
    
        // Check username exists in system
        if (!users.containsKey(username)) {
            System.out.println("Username does not exist.");
            return null;
        }

        if (password != users.get(username).getPassword()) {
            System.out.println("Password is incorrect");
            return null;
        }

        user = users.get(username);
        return user;
    }

    public User newRegisteredCustomer() {
        UserCreator customerCreator = new RegisteredCustomerCreator();
        System.out.println("Enter your username");
        String newUsername = ui.getInput();
        if(newUsername.equals("")){
            System.out.println("No user name entered.");
            return null;
        }
        if (users.containsKey(newUsername)) {
            System.out.println("Username already exists.");
            return null;
        }

        String newPassword = ui.getInputPassword();

        User customer = customerCreator.create(newUsername, newPassword, ui, cards);
        users.put(newUsername, customer);
        user = customer;
        return customer;
    }

    public String getInput() {
        return ui.getInput();
    }

    public void handleInput() {
        String input = this.getInput();
        if (input.contains("help")) {
            user.displayHelp();
        }  else if (input.contains("buy")) {
            user.makeTransaction();
        } else if (input.contains("cancel")) {
            user.cancelTransaction();
        } else if (input.contains("login")) {
            this.login();
        } else if (input.contains("register")) {
            this.newRegisteredCustomer();
        } else if (input.contains("display")) {
            user.displayStock();
        } else if (input.contains("quit")) {
            this.quit = true;
        }
    }
}
