package VendingMachine;

public class NoteCreator implements ChangeCreator {
    /**
     * 
     * @param name
     * @param value
     * @param quantity
     * @return
     */
    public Change create(String name, double value, int quantity) {
        return new Note(name, value, quantity);
    }
}
