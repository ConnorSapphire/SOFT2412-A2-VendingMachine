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
    private LinkedHashMap<String, Change> change;
    private HashMap<String, String> cards;
    private JSONObject json;
    private boolean quit;


    public VendingMachine() {
        UserCreator userCreator = new AnonymousCustomerCreator();
        this.cards = fileManager.getCreditCards();
        this.user = userCreator.create("", "", ui, cards);
        users = new HashMap<String, User>();
        HashMap<String, String[]> userFileInfo = fileManager.lsUsers();
        for (String username : userFileInfo.keySet()) {
            String password = userFileInfo.get(username)[0];
            String cardName = userFileInfo.get(username)[1];
            String cardNumber = userFileInfo.get(username)[2];
            String access = userFileInfo.get(username)[3];
            if (access.equals("customer")) {
                userCreator = new RegisteredCustomerCreator();
            } else if (access.equals("seller")) {
                userCreator = new SellerCreator();
            } else if (access.equals("cashier")) {
                userCreator = new CashierCreator();
            } else if (access.equals("owner")) {
                userCreator = new OwnerCreator();
            }
            User current = userCreator.create(username, password, ui, cards);
            if (!cardName.equals("")) {
                current.storeCard(cardName, cardNumber);
            }
            users.put(username, current);
        }
        products = new HashMap<String, Product>();
        HashMap<String[], Double[]> drinks = fileManager.lsDrinks();
        ProductCreator productCreator = new DrinkCreator();
        for (String[] drink : drinks.keySet()) {
            products.put(drink[0], productCreator.create(drink[0], drink[1], drinks.get(drink)[0], (int) Math.round(drinks.get(drink)[1]), (int) Math.round(drinks.get(drink)[2])));
        }
        HashMap<String[], Double[]> chocolates = fileManager.lsChocolates();
        productCreator = new ChocolateCreator();
        for (String[] chocolate : chocolates.keySet()) {
            products.put(chocolate[0], productCreator.create(chocolate[0], chocolate[1], chocolates.get(chocolate)[0], (int) Math.round(chocolates.get(chocolate)[1]), (int) Math.round(chocolates.get(chocolate)[2])));
        }
        HashMap<String[], Double[]> candies = fileManager.lsCandies();
        productCreator = new CandyCreator();
        for (String[] candy : candies.keySet()) {
            products.put(candy[0], productCreator.create(candy[0], candy[1], candies.get(candy)[0], (int) Math.round(candies.get(candy)[1]), (int) Math.round(candies.get(candy)[2])));
        }
        HashMap<String[], Double[]> chips = fileManager.lsChips();
        productCreator = new ChipCreator();
        for (String[] chip : chips.keySet()) {
            products.put(chip[0], productCreator.create(chip[0], chip[1], chips.get(chip)[0], (int) Math.round(chips.get(chip)[1]), (int) Math.round(chips.get(chip)[2])));
        }
        change = new LinkedHashMap<String, Change>();
        HashMap<String, Double[]> notes = fileManager.lsNotes();
        ChangeCreator changeCreator = new NoteCreator();
        for (String note : notes.keySet()) {
            change.put(note, changeCreator.create(note, notes.get(note)[0], (int) Math.round(notes.get(note)[1])));
        }
        HashMap<String, Double[]> coins = fileManager.lsCoins();
        changeCreator = new CoinCreator();
        for (String coin : coins.keySet()) {
            change.put(coin, changeCreator.create(coin, coins.get(coin)[0], (int) Math.round(coins.get(coin)[1])));
        }
        this.quit = false;
        ui.displayWelcomeMessage();
    }

    public User getUser() {
        return this.user;
    }

    public HashMap<String, User> getUsers() {
        return this.users;
    }

    public HashMap<String, Product> getProducts() {
        return this.products;
    }

    public LinkedHashMap<String, Change> getChange() {
        return this.change;
    }

    public boolean isQuit() {
        return quit;
    }

    public User login(){
        // Get username and password
        ui.displayQuestionString("Enter username: ");
        String username = ui.getPlainInput();
        if (username.equalsIgnoreCase("cancel")) {
            return user;
        }
        ui.displayQuestionString("Enter password: ");
        String password = ui.getInputPassword();
        // Check username exists in system  
        boolean exists = false;      
        for (String name : users.keySet()){
            if (name.equals(username)) {
                exists = true;
            }
        }
        if (!exists){
            ui.displayLoginFailed();
            return user;
        }
        if (!password.equals(users.get(username).getPassword())) {
            ui.displayLoginFailed();
            return user;
        }
        user = users.get(username);
        ui.displayLoginSuccess(user);
        return user;
    }

    public User newRegisteredCustomer() {
        UserCreator customerCreator = new RegisteredCustomerCreator();
        ui.displayErrorString("Enter your username: ");
        String newUsername = ui.getPlainInput();
        if (newUsername.equalsIgnoreCase("cancel")) {
            return user;
        }
        if(newUsername.equals("")){
            ui.displayErrorString("No user name entered.");
            return user;
        }
        if (users.containsKey(newUsername)) {
            ui.displayErrorString("Username already exists.");
            return user;
        }
        ui.displayQuestionString("Enter your password: ");
        String newPassword = ui.getInputPassword();
        User customer = customerCreator.create(newUsername, newPassword, ui, cards);
        users.put(newUsername, customer);
        fileManager.updateUsers(customer);
        user = customer;
        return customer;
    }

    public String getInput() {
        return ui.getInput();
    }

    public void handleInput(String input) {
        if (input.contains("help")) {
            user.displayHelp();
        }  else if (input.contains("buy")) {
            user.makeTransaction();
        } else if (input.contains("cancel")) {
            if (user.getTransaction() != null) {
                user.cancelTransaction();
            }
        } else if (input.contains("login")) {
            user = this.login();
        } else if (input.contains("logout")) {
            ui.displayLogout();
            UserCreator creator = new AnonymousCustomerCreator();
            user = creator.create("", "", ui, cards);
        } else if (input.contains("register")) {
            user = this.newRegisteredCustomer();
        } else if (input.contains("display products")) {
            user.displayStock();
        } else if (input.contains("quit")) {
            this.quit = true;
            System.exit(0);
        }
        if (user.getAccessLevel().equals("cashier") || user.getAccessLevel().equals("owner")) {
            if (input.contains("fill change")) {
                System.out.println("The change available to fill include: ");
                user.displayChangeTable();
                ui.displayQuestionString("Enter change: ");
                String changeName = ui.getPlainInput();
                if (changeName.equalsIgnoreCase("cancel")) {
                    return;
                }
                Change selected;
                if (change.containsKey(changeName)) {
                    selected = change.get(changeName);
                } else {
                    ui.displayErrorString("Provided change, " + changeName + ", does not exist.");
                    return;
                }
                ui.displayQuestionString("Enter quantity to store: ");
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(ui.getPlainInput());
                } catch (NumberFormatException e) {
                    ui.displayErrorString("Quantity must be an integer.");
                    return;
                }
                user.fillChange(selected, quantity);
            } else if (input.contains("add change")) {
                ui.displayQuestionString("Enter change name: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                ui.displayQuestionString("Enter change type: ");
                String type = ui.getPlainInput();
                if (type.equalsIgnoreCase("cancel")) {
                    return;
                }
                ui.displayQuestionString("Enter change quantity: ");
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(ui.getPlainInput());
                } catch (NumberFormatException e) {
                    ui.displayErrorString("Quantity must be an integer.");
                    return;
                }
                ui.displayQuestionString("Enter change value: ");
                double value = 0.0;
                try {
                    value = Double.parseDouble(ui.getPlainInput());
                } catch (NumberFormatException e) {
                    ui.displayErrorString("Value must be a number.");
                    return;
                }
                user.addChange(name, quantity, value, type);
            } else if (input.contains("remove change")) {
                System.out.println("The change available to fill include: ");
                user.displayChangeTable();
                ui.displayQuestionString("Enter change name: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                if (change.containsKey(name)) {
                    user.removeChange(change.get(name));
                } else {
                    ui.displayErrorString("No change found by that name.");
                    return;
                }
            } else if (input.contains("display change")) {
                user.displayChange();
            } else if (input.contains("display transactions")) {
                user.displayTransactionHistory();
            }
        }
        if (user.getAccessLevel().equals("seller") || user.getAccessLevel().equals("owner")) {
            if (input.contains("fill product")) {
                System.out.println("The products available to fill include: ");
                user.displayStock();
                ui.displayQuestionString("Enter product: ");
                String productName = ui.getPlainInput();
                if (productName.equalsIgnoreCase("cancel")) {
                    return;
                }
                Product selected;
                if (products.containsKey(productName)) {
                    selected = products.get(productName);
                } else if (user.getShortProducts().containsKey(productName.toUpperCase())) {
                    selected = user.getShortProducts().get(productName.toUpperCase());
                } else {
                    ui.displayErrorString("Provided product, " + productName + ", does not exist.");
                    return;
                }
                ui.displayQuestionString("Enter quantity to store: ");
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(ui.getPlainInput());
                } catch (NumberFormatException e) {
                    ui.displayErrorString("Quantity must be an integer.");
                    return;
                }
                user.fillProduct(selected, quantity);
            } else if (input.contains("add product")) {
                ui.displayQuestionString("Enter product name: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                ui.displayQuestionString("Enter product code: ");
                String code = ui.getPlainInput();
                if (code.equalsIgnoreCase("cancel")) {
                    return;
                }
                ui.displayQuestionString("Enter product category: ");
                String category = ui.getPlainInput();
                if (category.equalsIgnoreCase("cancel")) {
                    return;
                }
                ui.displayQuestionString("Enter quantity: ");
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(ui.getPlainInput());
                } catch (NumberFormatException e) {
                    ui.displayErrorString("Quantity must be an integer.");
                    return;
                }
                ui.displayQuestionString("Enter price: ");
                double price = 0.0;
                try {
                    price = Double.parseDouble(ui.getPlainInput());
                } catch (NumberFormatException e) {
                    ui.displayErrorString("Price must be a number.");
                    return;
                } 
                user.addProduct(name, code, category, quantity, price);
            } else if (input.contains("modify product name")) {
                System.out.println("The products available to change include:");
                user.displayStock();
                ui.displayQuestionString("Enter product: ");
                String productName = ui.getPlainInput();
                if (productName.equalsIgnoreCase("cancel")) {
                    return;
                }
                Product product = null;
                if (products.containsKey(productName)) {
                    product = products.get(productName);
                } else if (user.getShortProducts().containsKey(productName.toUpperCase())) {
                    product = user.getShortProducts().get(productName.toUpperCase());
                } else {
                    ui.displayErrorString("Product " + productName + " not found.");
                    return;
                }
                ui.displayQuestionString("Enter new name: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                user.modifyProductName(product, name);
            } else if (input.contains("modify product code")) {
                System.out.println("The products available to change include:");
                user.displayStock();
                ui.displayQuestionString("Enter product: ");
                String productName = ui.getPlainInput();
                if (productName.equalsIgnoreCase("cancel")) {
                    return;
                }
                Product product = null;
                if (products.containsKey(productName)) {
                    product = products.get(productName);
                } else if (user.getShortProducts().containsKey(productName.toUpperCase())) {
                    product = user.getShortProducts().get(productName.toUpperCase());
                } else {
                    ui.displayErrorString("Product " + productName + " not found.");
                    return;
                }
                ui.displayQuestionString("Enter new code: ");
                String code = ui.getPlainInput();
                if (code.equalsIgnoreCase("cancel")) {
                    return;
                }
                user.modifyProductCode(product, code);
            } else if (input.contains("modify product price")) {
                System.out.println("The products available to change include:");
                user.displayStock();
                ui.displayQuestionString("Enter product: ");
                String productName = ui.getPlainInput();
                if (productName.equalsIgnoreCase("cancel")) {
                    return;
                }
                Product product = null;
                if (products.containsKey(productName)) {
                    product = products.get(productName);
                } else if (user.getShortProducts().containsKey(productName.toUpperCase())) {
                    product = user.getShortProducts().get(productName.toUpperCase());
                } else {
                    ui.displayErrorString("Product " + productName + " not found.");
                    return;
                }
                ui.displayQuestionString("Enter new price: ");
                double price = 0.0;
                try {
                    price = Double.parseDouble(ui.getPlainInput());
                } catch (NumberFormatException e) {
                    ui.displayErrorString("Price must be a number.");
                    return;
                }
                user.modifyProductPrice(product, price);
            } else if (input.contains("modify product category")) {
                System.out.println("The products available to change include:");
                user.displayStock();
                ui.displayQuestionString("Enter product: ");
                String productName = ui.getPlainInput();
                if (productName.equalsIgnoreCase("cancel")) {
                    return;
                }
                Product product = null;
                if (products.containsKey(productName)) {
                    product = products.get(productName);
                } else if (user.getShortProducts().containsKey(productName.toUpperCase())) {
                    product = user.getShortProducts().get(productName.toUpperCase());
                } else {
                    ui.displayErrorString("Product " + productName + " not found.");
                    return;
                }
                ui.displayQuestionString("Enter new category: ");
                String category = ui.getPlainInput();
                if (category.equalsIgnoreCase("cancel")) {
                    return;
                }
                user.modifyProductCategory(product, category);
            } else if (input.contains("remove product")) {
                System.out.println("The products available to remove include:");
                user.displayStock();
                ui.displayQuestionString("Enter product: ");
                String productName = ui.getPlainInput();
                if (productName.equalsIgnoreCase("cancel")) {
                    return;
                }
                Product product = null;
                if (products.containsKey(productName)) {
                    product = products.get(productName);
                } else if (user.getShortProducts().containsKey(productName.toUpperCase())) {
                    product = user.getShortProducts().get(productName.toUpperCase());
                } else {
                    ui.displayErrorString("Product " + productName + " not found.");
                    return;
                }
                user.removeProduct(product);
            } else if (input.contains("display stock")) {
                user.displayDetailedStock();
            } else if (input.contains("display sales")) {
                user.displayStockSales();
            }
        }
        if (user.getAccessLevel().equals("owner")) {
            if (input.contains("display cancelled transactions")) {
                user.displayCancelledTransactions();
            } else if (input.contains("display users")) {
                user.displayUsers();
            } else if (input.contains("add user")) {
                ui.displayQuestionString("Enter username: ");
                String username = ui.getPlainInput();
                if (username.equalsIgnoreCase("cancel")) {
                    return;
                }
                ui.displayQuestionString("Enter password: ");
                String password = ui.getInputPassword();
                if (password.equalsIgnoreCase("cancel")) {
                    return;
                }
                ui.displayQuestionString("Enter access level: ");
                String access = ui.getPlainInput();
                if (access.equalsIgnoreCase("cancel")) {
                    return;
                }
                user.addUser(username, password, access, ui);
            } else if (input.contains("remove user")) {
                System.out.println("The users available to remove include: ");
                user.displayUsersTable();
                ui.displayQuestionString("Enter user: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                if (users.containsKey(name)) {
                    user.removeUser(users.get(name));
                } else {
                    ui.displayErrorString("User " + name + " not found.");
                    return;
                }
            } else if (input.contains("modify user name")) {
                System.out.println("The users available to modify include: ");
                user.displayUsersTable();
                ui.displayQuestionString("Enter user: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                User selected = null;
                if (users.containsKey(name)) {
                    selected = users.get(name);
                } else {
                    ui.displayErrorString("User " + name + " not found.");
                    return;
                }
                ui.displayQuestionString("Enter new name: ");
                String newName = ui.getPlainInput();
                if (newName.equalsIgnoreCase("cancel")) {
                    return;
                }
                user.modifyUserUsername(selected, newName);
            } else if (input.contains("modify user password")) {
                System.out.println("The users available to modify include: ");
                user.displayUsersTable();
                ui.displayQuestionString("Enter user: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                User selected = null;
                if (users.containsKey(name)) {
                    selected = users.get(name);
                } else {
                    ui.displayErrorString("User " + name + " not found.");
                    return;
                }
                ui.displayQuestionString("Enter new password: ");
                String password = ui.getInputPassword();
                user.modifyUserPassword(selected, password);
            } else if (input.contains("modify user role")) {
                System.out.println("The users available to modify include: ");
                user.displayUsersTable();
                ui.displayQuestionString("Enter user: ");
                String name = ui.getPlainInput();
                if (name.equalsIgnoreCase("cancel")) {
                    return;
                }
                User selected = null;
                if (users.containsKey(name)) {
                    selected = users.get(name);
                } else {
                    ui.displayErrorString("User " + name + " not found.");
                    return;
                }
                ui.displayQuestionString("Enter new role: ");
                String role = ui.getPlainInput();
                if (role.equalsIgnoreCase("cancel")) {
                    return;
                }
                user.modifyUserAccess(selected, role);
            }
        }
    }
}
