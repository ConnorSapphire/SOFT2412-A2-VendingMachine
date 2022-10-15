package VendingMachine;

import java.util.HashMap;

public class VendingMachine {
    private UserInterface ui;
    private FileManager fileManager;
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
        return null;
    }
}
