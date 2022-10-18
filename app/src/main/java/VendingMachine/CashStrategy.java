package VendingMachine;

public class CashStrategy implements PaymentStrategy {
    private User user;
    private UserInterface ui;
    private Transaction transaction;

    public CashStrategy(User user) {
        this.user = user;
        this.ui = user.getUI();
        this.transaction = user.getCurrentTransaction();
    }

    public void pay() {

    }
}
