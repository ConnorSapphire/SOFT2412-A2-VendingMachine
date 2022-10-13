package VendingMachine;

public abstract class Change {
    private String name;
    private double value;
    private int quantity;

    /**
     * 
     * @param name
     * @param value
     * @param quantity
     */
    public Change(String name, double value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }
}
