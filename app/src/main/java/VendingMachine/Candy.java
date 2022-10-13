package VendingMachine;

public class Candy extends Product {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     */
    public Candy(String name, String code, double price, int quantity) {
        super(name, code, "candy", price, quantity);
    }
}
