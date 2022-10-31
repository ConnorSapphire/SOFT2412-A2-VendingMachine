package VendingMachine;

import java.util.HashMap;

public class Owner extends User {
    /**
     * Create a new Owner.
     * @param username Unique name for the new Owner.
     * @param password Password to allow Owner access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public Owner(String username, String password, UserInterface ui, HashMap<String, String> cards) {
        super(username, password, "owner", ui, cards);
    } 

    // SELLER METHODS

    /**
     * 
     */
    public void displayDetailedStock() {
        displayStock();
        this.getUI().displayDetailedStock();
    }

    /**
     * 
     */
    public void displayStockSales() {
        displaySalesTable();
        this.getUI().displayStockSales();
    }

    /**
     * 
     * @param product
     * @param quantity
     * @return
     */
    public boolean fillProduct(Product product, int quantity) {
        if(quantity < 0){
            this.getUI().displayerrorMessage("Please provide a non-negative number of stock added");
            return false;
        }
        if(product.getQuantity() + quantity <= 15){
            product.setQuantity(product.getQuantity() + quantity);
            if (product.getCategory().equals("drink")) {
                this.getUI().getFileManager().updateDrinks(product);
            } else if (product.getCategory().equals("chocolate")) {
                this.getUI().getFileManager().updateChocolates(product);
            } else if (product.getCategory().equals("chip")) {
                this.getUI().getFileManager().updateChips(product);
            } else if (product.getCategory().equals("candy")) {
                this.getUI().getFileManager().updateCandies(product);
            }
            this.getUI().displaySuccessString("Vending machine now contains " + product.getQuantity() + " of " + product.getName() + ".");
            return true;
        } else {
            this.getUI().displayErrorString("The vending machine does not have enough space for this quantity.");
        }
        return false;
    }

    /**
     * 
     * @param product
     * @param newName
     * @return
     */
    public boolean modifyProductName(Product product, String name) {
        HashMap<String, Product> products = this.getProducts();
        for(String key : products.keySet()){
            if(key.equals(name)){
                if(products.get(key).equals(product)){
                    this.getUI().displayerrorMessage("Can not modify the name. Please give a different name.");
                }else{
                    this.getUI().displayerrorMessage("Name already exists.");
                }
                return false;
            }
        }
        if (this.getProducts().containsKey(product.getName())) {
            this.getProducts().remove(product.getName());
            this.getUI().getFileManager().removeProduct(product);
        }
        String oldName = product.getName();
        product.setName(name);
        this.getProducts().put(name, product);
        if (product.getCategory().equalsIgnoreCase("drink")) {
            this.getUI().getFileManager().updateDrinks(product);
        } else if (product.getCategory().equalsIgnoreCase("chocolate")) {
            this.getUI().getFileManager().updateChocolates(product);
        } else if (product.getCategory().equalsIgnoreCase("candy")) {
            this.getUI().getFileManager().updateCandies(product);
        } else if (product.getCategory().equalsIgnoreCase("chip")) {
            this.getUI().getFileManager().updateChips(product);
        }
        this.getUI().displaySuccessString("Product name succesfully changed from " + oldName + " to " + product.getName() + ".");
        return true;
    }

    /**
     * 
     * @param product
     * @param code
     * @return
     */
    public boolean modifyProductCode(Product product, String code) {
        HashMap<String, Product> products = this.getProducts();
        char[] check = code.toCharArray();
        if(check.length != 3){
            this.getUI().displayerrorMessage("Only 3 digit code accepted.");
            return false;
        }
        for(String str : products.keySet()){
            Product p = products.get(str);
            if(code.equals(p.getCode())){
                if(p.equals(product)){
                    this.getUI().displayerrorMessage("Can not modify the code. Please give a different code.");
                }else{
                    this.getUI().displayerrorMessage("Code already exists.");
                }
                return false;
            }
        }
        String oldCode = product.getCode();
        product.setCode(code.toUpperCase());
        if (product.getCategory().equalsIgnoreCase("drink")) {
            this.getUI().getFileManager().updateDrinks(product);
        } else if (product.getCategory().equalsIgnoreCase("chocolate")) {
            this.getUI().getFileManager().updateChocolates(product);
        } else if (product.getCategory().equalsIgnoreCase("candy")) {
            this.getUI().getFileManager().updateCandies(product);
        } else if (product.getCategory().equalsIgnoreCase("chip")) {
            this.getUI().getFileManager().updateChips(product);
        }
        this.getUI().displaySuccessString("Product code of " + product.getName() + " successfully changed from " + oldCode + " to " + product.getCode() + ".");
        return true;
    }

    /**
     * 
     * @param product
     * @param price
     * @return
     */
    public boolean modifyProductPrice(Product product, double price) {
        if(price <= 0){
            this.getUI().displayerrorMessage("Please give a positive price.");
            return false;
        }
        double oldPrice = product.getPrice();
        product.setPrice(price);
        if (product.getCategory().equalsIgnoreCase("drink")) {
            this.getUI().getFileManager().updateDrinks(product);
        } else if (product.getCategory().equalsIgnoreCase("chocolate")) {
            this.getUI().getFileManager().updateChocolates(product);
        } else if (product.getCategory().equalsIgnoreCase("candy")) {
            this.getUI().getFileManager().updateCandies(product);
        } else if (product.getCategory().equalsIgnoreCase("chip")) {
            this.getUI().getFileManager().updateChips(product);
        }
        this.getUI().displaySuccessString("Product price of " + product.getName() + " successfully changed from $" + oldPrice + " to $" + product.getPrice() + ".");
        return true;
    }

    /**
     * 
     * @param product
     * @param category
     * @return
     */
    public boolean modifyProductCategory(Product product, String category) {
        String[] all  = new String[]{"Drinks", "Chocolates", "Chips", "Candies"};
        String oldCategory = product.getCategory();
        for(String cat : all){
            if(category.toLowerCase().equals(cat.toLowerCase())){
                product.setCategory(cat);
                if (product.getCategory().equalsIgnoreCase("drink")) {
                    this.getUI().getFileManager().updateDrinks(product);
                } else if (product.getCategory().equalsIgnoreCase("chocolate")) {
                    this.getUI().getFileManager().updateChocolates(product);
                } else if (product.getCategory().equalsIgnoreCase("candy")) {
                    this.getUI().getFileManager().updateCandies(product);
                } else if (product.getCategory().equalsIgnoreCase("chip")) {
                    this.getUI().getFileManager().updateChips(product);
                }
                this.getUI().displaySuccessString("Product category of " + product.getName() + " successfully changed from " + oldCategory + " to " + product.getCategory() + ".");
                return true;
            }
        }
        this.getUI().displayerrorMessage("Please give a valid category!");
        return false;
    }

    /**
     * 
     * @param name
     * @param code
     * @param category
     * @param quantity
     * @param price
     * @return
     */
    public boolean addProduct(String name, String code, String category, int quantity, double price) {
        HashMap<String, Product> products = this.getProducts();
        if(price <= 0){
            this.getUI().displayerrorMessage("Price must be positive!");
            return false;
        }
        if(quantity < 0){
            this.getUI().displayerrorMessage("Quantity must be non-negative!");
            return false;
        }
        if(code.length() != 3){
            this.getUI().displayerrorMessage("Only 3 digit code accepted.");
            return false;
        }
        ProductCreator pc = null;
        String[] all  = new String[]{"Drinks", "Chocolates", "Chips", "Candies"};
        boolean find = false;
        for(String cat : all){
            if(category.toLowerCase().equals(cat.toLowerCase())){
                find = true;
                if(cat.equals(all[0])){
                    pc = new DrinkCreator();
                }else if(cat.equals(all[1])){
                    pc = new ChocolateCreator();
                }else if(cat.equals(all[2])){
                    pc = new ChipCreator();
                }else{
                    pc = new CandyCreator();
                }
            }
        }
        if(!find){
            this.getUI().displayerrorMessage("Category not valid!");
            return false;
        }
        for(String key : products.keySet()){
            Product pro = products.get(key);
            if(key.equals(name)){
                this.getUI().displayerrorMessage("Name exists!");
                return false;
            }
            if(pro.getCode().equals(code)){
                this.getUI().displayerrorMessage("Code exists!");
                return false;
            }
        }
        if (pc != null) {
            Product newProduct = pc.create(name, code, price, quantity, 0);
            this.getProducts().put(name, newProduct);
            if (newProduct.getCategory().equalsIgnoreCase("drink")) {
                this.getUI().getFileManager().updateDrinks(newProduct);
            } else if (newProduct.getCategory().equalsIgnoreCase("chocolate")) {
                this.getUI().getFileManager().updateChocolates(newProduct);
            } else if (newProduct.getCategory().equalsIgnoreCase("candy")) {
                this.getUI().getFileManager().updateCandies(newProduct);
            } else if (newProduct.getCategory().equalsIgnoreCase("chip")) {
                this.getUI().getFileManager().updateChips(newProduct);
            }
        }
        this.getUI().displaySuccessString("New product successfully created.");
        return true;
    }

    public boolean removeProduct(Product product) {
        if (this.getProducts().containsKey(product.getName())) {
            this.getProducts().remove(product.getName());
            this.getUI().getFileManager().removeProduct(product);
            this.getUI().displaySuccessString("Successfully removed product " + product.getName() + ".");
            return true;
        }
        this.getUI().displayErrorString("Product " + product.getName() + " not found in vending machine. Could not be removed.");
        return false;
    }

    public void displaySalesTable(){
        this.getUI().displaySalesTable(this.getProducts());
    }
    // CASHIER METHODS

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean fillChange(Change change, int quantity) {
        if (quantity > 0) {
            change.setQuantity(change.getQuantity() + quantity);
            this.getUI().displaySuccessString("The vending machine now contains " + change.getQuantity() + " of " + change.getName() + ".");
            if (change.getClass().getSimpleName().equalsIgnoreCase("Note")) {
                this.getUI().getFileManager().updateNotes(change);
            } else if (change.getClass().getSimpleName().equalsIgnoreCase("Coin")) {
                this.getUI().getFileManager().updateCoins(change);
            }
            return true;
        }
        getUI().displayErrorString("Quantity provided is invalid. Cannot have negative or zero of a coin/note.");
        return false;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @return
     */
    public boolean removeChange(Change change) {
        if (this.getChange().containsKey(change.getName())) {
            this.getChange().remove(change.getName());
            this.getUI().getFileManager().removeChange(change);
            this.getUI().displaySuccessString("Successfully removed change " + change.getName() + ".");
            return true;
        }
        this.getUI().displayErrorString("Unable to remove change " + change.getName() + " as it does not exist in the vending machine.");
        return false;
    }

    /**
     * 
     * @param change
     * @param quantity
     * @param value
     * @return
     */
    public boolean addChange(String name, int quantity, double value, String type) {
        if (this.getChange().containsKey(name)) {
            this.getUI().displayErrorString("Cannot add new change, name already exists.");
            return false;
        }
        if (quantity <= 0) {
            this.getUI().displayErrorString("Invalid quantity, must be greater than zero.");
            return false;
        }
        if (value <= 0) {
            this.getUI().displayErrorString("Invalid value, must be greater than zero.");
            return false;
        }
        ChangeCreator creator = new CoinCreator();
        if (type.equalsIgnoreCase("coin")) {
            Change change = creator.create(name, value, quantity);
            this.getChange().put(name, change);
            this.sortChangeHashMap();
            this.getUI().getFileManager().updateCoins(change);
            this.getUI().displaySuccessString("Successfully added change type " + name + " " + type + " to the machine." );
            return true;
        } else if (type.equalsIgnoreCase("note")) {
            creator = new NoteCreator();
            Change change = creator.create(name, value, quantity);
            this.getChange().put(name, change);
            this.sortChangeHashMap();
            this.getUI().getFileManager().updateNotes(change);
            this.getUI().displaySuccessString("Successfully added change type " + name + " " + type + " to the machine." );
            return true;
        }
        this.getUI().displayErrorString("Invalid type, please enter either 'coin' or 'note'.");
        return false;
    }

    /**
     * 
     */
    public void displayChange() {
        displayChangeTable();
        this.getUI().displayChange();
    }

    public void displayChangeTable() {
        this.getUI().displayChangeTable(this.getChange());
    }

    /**
     * Display all previous transactions. Including transaction time, product name, amount paid, change given, and
     * payment method.
     */
    public void displayTransactionHistory() {
        this.getUI().displayTransactionHistory();
    }


    // OWNER METHODS

    /**
     * 
     * @param username
     * @param password
     * @param accessLevel
     * @param ui
     * @return
     */
    public boolean addUser(String username, String password, String accessLevel, UserInterface ui) {
        UserCreator creator = new RegisteredCustomerCreator();
        if (accessLevel.equalsIgnoreCase("seller")) {
            creator = new SellerCreator();
        } else if (accessLevel.equalsIgnoreCase("cashier")) {
            creator = new CashierCreator();
        } else if (accessLevel.equalsIgnoreCase("owner")) {
            creator = new OwnerCreator();
        }
        for (User exists : this.getUsers().values()) {
            if (exists.getUsername().equals(username)) {
                this.getUI().displayErrorString("Username already exists. Unable to create new user.");
                return false;
            }
        }
        User newUser = creator.create(username, password, this.getUI(), this.getCards());
        this.getUsers().put(username, newUser);
        this.getUI().getFileManager().updateUsers(newUser);
        this.getUI().displaySuccessString("Successfully created new user!");
        return true;
    }

    public boolean removeUser(User user) {
        if (this.getUsers().containsKey(user.getUsername())) {
            this.getUsers().remove(user.getUsername());
            this.getUI().getFileManager().removeUser(user);
            this.getUI().displaySuccessString("Successfully removed user " + user.getUsername() + ".");
            return true;
        }
        this.getUI().displayerrorMessage("Cannot remove user, as " + user.getUsername() + " does not exist in vending machine.");
        return false;
    }

    /**
     * 
     * @param user
     * @param accessLevel
     * @return
     */
    public boolean modifyUserAccess(User user, String accessLevel) {
        String oldAccessLevel = user.getAccessLevel();
        UserCreator creator = new RegisteredCustomerCreator();
        if (accessLevel.equalsIgnoreCase("seller")) {
            creator = new SellerCreator();
            User newUser = creator.create(user.getUsername(), user.getPassword(), getUI(), getCards());
            this.getUsers().remove(user.getUsername());
            this.getUI().getFileManager().removeUser(user);
            this.getUsers().put(newUser.getUsername(), newUser);
            this.getUI().getFileManager().updateUsers(newUser);
            this.getUI().displaySuccessString("Successfully changed user " + user.getUsername() + " from " + oldAccessLevel + " to " + accessLevel.toLowerCase() + ".");
            return true;
        } else if (accessLevel.equalsIgnoreCase("owner")) {
            creator = new OwnerCreator();
            User newUser = creator.create(user.getUsername(), user.getPassword(), getUI(), getCards());
            this.getUsers().remove(user.getUsername());
            this.getUI().getFileManager().removeUser(user);
            this.getUsers().put(newUser.getUsername(), newUser);
            this.getUI().getFileManager().updateUsers(newUser);
            this.getUI().displaySuccessString("Successfully changed user " + user.getUsername() + " from " + oldAccessLevel + " to " + accessLevel.toLowerCase() + ".");
            return true;
        } else if (accessLevel.equalsIgnoreCase("cashier")) {
            creator = new CashierCreator();
            User newUser = creator.create(user.getUsername(), user.getPassword(), getUI(), getCards());
            this.getUsers().remove(user.getUsername());
            this.getUI().getFileManager().removeUser(user);
            this.getUsers().put(newUser.getUsername(), newUser);
            this.getUI().getFileManager().updateUsers(newUser);
            this.getUI().displaySuccessString("Successfully changed user " + user.getUsername() + " from " + oldAccessLevel + " to " + accessLevel.toLowerCase() + ".");
            return true;
        } else if (accessLevel.equalsIgnoreCase("customer") || accessLevel.toLowerCase().contains("registered")) {
            creator = new RegisteredCustomerCreator();
            User newUser = creator.create(user.getUsername(), user.getPassword(), getUI(), getCards());
            this.getUsers().remove(user.getUsername());
            this.getUI().getFileManager().removeUser(user);
            this.getUsers().put(newUser.getUsername(), newUser);
            this.getUI().getFileManager().updateUsers(newUser);
            this.getUI().displaySuccessString("Successfully changed user " + user.getUsername() + " from " + oldAccessLevel + " to " + accessLevel.toLowerCase() + ".");
            return true;
        }
        this.getUI().displayErrorString("Role " + accessLevel + " not recognised.");
        return false;
    }

    /**
     * 
     * @param user
     * @param username
     * @return
     */
    public boolean modifyUserUsername(User user, String username) {
        if (this.getUsers().containsKey(username)) {
            this.getUI().displayErrorString("The username " + username + " is already in use.");
            return false;
        }
        UserCreator creator = new RegisteredCustomerCreator();
        if (user.getAccessLevel().equalsIgnoreCase("seller")) {
            creator = new SellerCreator();
        } else if (user.getAccessLevel().equalsIgnoreCase("owner")) {
            creator = new OwnerCreator();
        } else if (user.getAccessLevel().equalsIgnoreCase("cashier")) {
            creator = new CashierCreator();
        }
        User newUser = creator.create(username, user.getPassword(), getUI(), getCards());
        this.getUsers().remove(user.getUsername());
        this.getUI().getFileManager().removeUser(user);
        this.getUsers().put(username, newUser);
        this.getUI().getFileManager().updateUsers(newUser);
        this.getUI().displaySuccessString("Successfully changed user's name from " + user.getUsername() + " to " + username + ".");
        return true;
    }

    /**
     * 
     * @param user
     * @param password
     * @return
     */
    public boolean modifyUserPassword(User user, String password) {
        user.setPassword(password);
        this.getUI().getFileManager().updateUsers(user);
        this.getUI().displaySuccessString("Password of " + user.getUsername() + " successfully updated.");
        return true;
    }

    /**
     * 
     */
    public void displayUsers() {
        displayUsersTable();
        this.getUI().displayUsers();
    }

    public void displayUsersTable() {
        this.getUI().displayUsersTable(this.getUsers());
    }

    /**
     * 
     */
    public void displayCancelledTransactions() {
        this.getUI().displayCancelledTransactions();
    }

    public void displayHelp() {
        this.getUI().displayOwnerHelp();
    }
}
