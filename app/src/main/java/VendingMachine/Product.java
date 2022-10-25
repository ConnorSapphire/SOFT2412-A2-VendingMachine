package VendingMachine;

public abstract class Product {
    private String name;
    private String code;
    private String category;
    private double price;
    private int quantity;
    private int totalSold;

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
        this.totalSold = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int gettotalSold() {
        return totalSold;
    }

    public void addtotalSold(int num) {
        this.totalSold += num;
    }
}
