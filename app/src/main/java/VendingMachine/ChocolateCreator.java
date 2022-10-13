package VendingMachine;

public class ChocolateCreator implements ProductCreator {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     * @return
     */
    public Product create(String name, String code, double price, int quantity) {
        return new Chocolate(name, code, price, quantity);
    }
}
