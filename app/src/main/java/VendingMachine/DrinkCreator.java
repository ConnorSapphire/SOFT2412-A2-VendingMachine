package VendingMachine;

public class DrinkCreator implements ProductCreator {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     * @return
     */
    public Product create(String name, String code, double price, int quantity) {
        return new Drink(name, code, price, quantity);
    }
}
