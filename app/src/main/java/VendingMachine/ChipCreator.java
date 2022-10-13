package VendingMachine;

public class ChipCreator implements ProductCreator {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     * @return
     */
    public Product create(String name, String code, double price, int quantity) {
        return new Chip(name, code, price, quantity);
    }
}
