package VendingMachine;

public abstract class Product {
    private String name;
    private String code;
    private String category;
    private double price;
    private int quantity;

    /**
     * 
     * @param name
     * @param code
     * @param category
     * @param price
     * @param quantity
     */
    public Product(String name, String code, String category, double price, int quantity) {
        this.name = name;
        this.code = code;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}
