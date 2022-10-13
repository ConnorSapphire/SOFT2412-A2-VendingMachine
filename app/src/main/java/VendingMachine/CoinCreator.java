package VendingMachine;

public class CoinCreator implements ChangeCreator {
    /**
     * 
     * @param name
     * @param value
     * @param quantity
     * @return
     */
    public Change create(String name, double value, int quantity) {
        return new Coin(name, value, quantity);
    }
}
