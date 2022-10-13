package VendingMachine;

public class Chip extends Product {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     */
    public Chip(String name, String code, double price, int quantity) {
        super(name, code, "chip", price, quantity);
    }    
}
