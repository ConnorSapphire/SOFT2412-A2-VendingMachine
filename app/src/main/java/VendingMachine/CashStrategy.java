package VendingMachine;

public class CashStrategy implements PaymentStrategy {
    private UserInterface ui;

    public CashStrategy(UserInterface ui) {
        this.ui = ui;
    }

    public void pay() {
        // get the amount the person will give
        
    }
}
