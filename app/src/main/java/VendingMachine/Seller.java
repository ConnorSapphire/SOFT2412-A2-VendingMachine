package VendingMachine;

import java.util.HashMap;

public class Seller extends User {
    /**
     * Create a new Seller.
     * @param username Unique name for the new Seller.
     * @param password Password to allow Seller access to their account.
     * @param ui Reference to the UserInterface to allow interaction with terminal
     */
    public Seller(String username, String password, UserInterface ui, HashMap<String, String> cards) {
        super(username, password, "seller", ui, cards);
    }


    /**
     * 
     */
    public void displayDetailedStock() {
        this.getUI().displayDetailedStock();
    }

    /**
     * 
     */
    public void displayStockSales() {
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
            System.out.println("Please provide a non-negative number of stock added");
            return false;
        }
        if(product.getQuantity() + quantity <= 15){
            product.setQuantity(product.getQuantity() + quantity);
            return true;
        }
        return false;
    }

    /**
     * 
     * @param product
     * @param newName
     * @return
     */
    public boolean modifyProductName(Product product, String name, HashMap<String, Product> products) {
        for(String key : products.keySet()){
            if(key.equals(name)){
                if(products.get(key).equals(product)){
                    System.out.println("Can not modify the name. Please give a different name.");
                }else{
                    System.out.println("Name already exists.");
                }
                return false;
            }
        }
        product.setName(name);
        return true;
    }

    /**
     * 
     * @param product
     * @param code
     * @return
     */
    public boolean modifyProductCode(Product product, String code, HashMap<String, Product> products) {
        char[] check = code.toCharArray();
        if(check.length != 3){
            System.out.println("Only 3 digit code accepted.");
            return false;
        }
        for(String str : products.keySet()){
            Product p = products.get(str);
            if(code.equals(p.getCode())){
                if(p.equals(product)){
                    System.out.println("Can not modify the code. Please give a different code.");
                }else{
                    System.out.println("Code already exists.");
                }
                return false;
            }
        }
        product.setCode(code);
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
            System.out.println("Please give a positive price.");
            return false;
        }
        product.setPrice(price);
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
        for(String cat : all){
            if(category.toLowerCase() == cat.toLowerCase()){
                product.setCategory(cat);
                return true;
            }
        }
        System.out.println("Please give a valid category!");
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
    public boolean addProduct(String name, String code, String category, int quantity, double price, HashMap<String, Product> products) {
        if(price <= 0){
            System.out.println("Price must be positive!");
            return false;
        }
        String[] all  = new String[]{"Drinks", "Chocolates", "Chips", "Candies"};
        boolean find = false;
        for(String cat : all){
            if(category.toLowerCase() == cat.toLowerCase()){
                find = true;
            }
        }
        if(!find){
            System.out.println("Category not valid!");
            return false;
        }
        for(String key : products.keySet()){
            Product pro = products.get(key);
            if(key.equals(name)){
                System.out.println("Name exists!");
                return false;
            }
            if(pro.getCode().equals(code)){
                System.out.println("Code exists!");
                return false;
            }
        }
        // How to create a new product here?
        return true;
    }

    public void displayHelp() {
        this.getUI().displaySellerHelp();
    }
}
