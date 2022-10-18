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
        double cost = 0;
        for (Product product : transaction.getProducts()) {
            cost += product.getPrice();
        }

        System.out.println("Please enter the amount to pay: ");
        String amount = ui.getInput();
        Double cashInput = Double.parseDouble(amount);
        
        if (cashInput > cost){
            Double change = cashInput - cost;
            System.out.println("Here is your change: $" + change);
        } else if (cashInput == cost){
            System.out.println("Transaction successful");
        } else {
            System.out.println("Incorrect amount entered. Purchase cancelled.");
        }
    }
}
