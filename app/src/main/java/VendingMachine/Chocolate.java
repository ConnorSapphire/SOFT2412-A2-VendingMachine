package VendingMachine;

public class Chocolate extends Product {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     */
    public Chocolate(String name, String code, double price, int quantity, int totalSold) {
        super(name, code, "chocolate", price, quantity, totalSold);
    }
}
