package VendingMachine;

public class CandyCreator implements ProductCreator {
    /**
     * 
     * @param name
     * @param code
     * @param price
     * @param quantity
     * @return
     */
    public Product create(String name, String code, double price, int quantity, int totalSold) {
        return new Candy(name, code, price, quantity, totalSold);
    }
}
