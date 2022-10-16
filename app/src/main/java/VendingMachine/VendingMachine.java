package VendingMachine;

import java.util.*;

public class VendingMachine {
    private UserInterface ui = new UserInterface();
    private FileManager fileManager = new FileManager();
    private User user;
    private HashMap<String, User> users;
    private HashMap<String, Product> products;
    private HashMap<String, Change> change;

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.ui = new UserInterface();
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
        String newUsername = ui.getInput();
        String newPassword = ui.getInputPassword();
        
        if (users.containsKey(newUsername)) {
            System.out.println("Username already exists.");
            return null;
        }

        User customer = customerCreator.create(newUsername, newPassword, ui);
        users.put(username, customer);
        user = customer;
        return customer;
    }
}
