package VendingMachine;

public interface ProductCreator {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     * @return
     */
    public Product create(String name, String code, double price, int quantity);
}
