package VendingMachine;

public interface ChangeCreator {
    /**
     * 
     * @param name
     * @param value
     * @param quantity
     * @return
     */
    public Change create(String name, double value, int quantity);
}
