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
        System.out.print("Enter username: ");
        String username = ui.getPlainInput();
        System.out.print("Enter password: ");
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
        System.out.print("Enter your username: ");
        String newUsername = ui.getPlainInput();
        if(newUsername.equals("")){
            System.out.println("No user name entered.");
            return user;
        }
        if (users.containsKey(newUsername)) {
            System.out.println("Username already exists.");
            return user;
        }
        System.out.print("Enter your password: ");
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
                for (String ch : change.keySet()) {
                    System.out.print(ch + " ");
                }
                System.out.print("Which change do you want to fill? ");
                String changeName = ui.getPlainInput();
                Change selected;
                if (change.containsKey(changeName)) {
                    selected = change.get(changeName);
                } else {
                    ui.displayErrorString("Provided change, " + changeName + ", does not exist.");
                    return;
                }
                System.out.print("How many " + changeName + " do you wish to store? ");
                int quantity = Integer.parseInt(ui.getPlainInput());
                user.fillChange(selected, quantity);
            } else if (input.contains("display change")) {
                user.displayChange();
            } else if (input.contains("display transactions")) {
                user.displayTransactionHistory();
            }
        }
        if (user.getAccessLevel().equals("seller") || user.getAccessLevel().equals("owner")) {
            if (input.contains("fill product")) {
                System.out.println("The products available to fill include: ");
                for (String product : products.keySet()) {
                    System.out.print(product + " ");
                }
                System.out.print("Which product do you want to fill? ");
                String productName = ui.getPlainInput();
                Product selected;
                if (products.containsKey(productName)) {
                    selected = products.get(productName);
                } else {
                    ui.displayErrorString("Provided change, " + productName + ", does not exist.");
                    return;
                }
                System.out.print("How many " + productName + " do you wish to store? ");
                int quantity = Integer.parseInt(ui.getPlainInput());
                user.fillProduct(selected, quantity);
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
                System.out.print("Username: ");
                String username = ui.getPlainInput();
                System.out.print("Password: ");
                String password = ui.getInputPassword();
                System.out.print("Access level: ");
                String access = ui.getPlainInput();
                user.addUser(username, password, access, ui);
            }
        }
    }
}
