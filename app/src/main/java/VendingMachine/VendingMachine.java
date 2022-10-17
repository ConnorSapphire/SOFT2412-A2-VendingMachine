package VendingMachine;

import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class VendingMachine {
    private FileManager fileManager = new FileManager();
    private UserInterface ui = new UserInterface(fileManager);
    private User user;
    private HashMap<String, User> users;
    private HashMap<String, Product> products;
    private HashMap<String, Change> change;
    private JSONObject json;

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.login();
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

    public User newRegisteredCustomer(String username, String password, UserInterface ui){
        UserCreator customerCreator = new RegisteredCustomerCreator();
        System.out.println("Enter your username");
        String newUsername = ui.getInput();
        
        if (users.containsKey(newUsername)) {
            System.out.println("Username already exists.");
            return null;
        }

        String newPassword = ui.getInputPassword();

        User customer = customerCreator.create(newUsername, newPassword, ui);
        users.put(username, customer);
        user = customer;
        return customer;
    }
}
