package VendingMachine;

import java.util.HashMap;

public class CardStrategy implements PaymentStrategy {
    private UserInterface ui;
    private Transaction transaction;
    private HashMap<String, String> cards;
    private User user;

    public CardStrategy(User user) {
        this.user = user;
        this.ui = user.getUI();
        this.transaction = user.getCurrentTransaction();
        this.cards = user.getCards();
    }
    
    public void pay() {
        double cost = 0;
        for (Product product : transaction.getProducts()) {
            cost += product.getPrice();
        }
        if (user.isCardStored()) {
            System.out.println("Processing transaction using previously saved card details...");
            System.out.println("Purchase successful! Your card has been charged $" + cost + ".");
        } else {
            System.out.print("Input cardholder name: ");
            String cardName = ui.getPlainInput();
            System.out.println();
            System.out.print("Input credit card number: ");
            String cardNumber = ui.getPlainInput();
            System.out.println();
            boolean cardValid = false;
            if (cards.containsKey(cardName)) {
                if (cards.get(cardName).equals(cardNumber)) {
                    cardValid = true;
                }
            }
            if (!cardValid) {
                ui.displayErrorString("The provided card details were invalid. Have a great day!");
            } else {
                System.out.println("Purchase successful! Your card has been charged $" + cost + ".");
                if (!user.getAccessLevel().contains("anonymous")) {
                    System.out.println("Would you like to save this card to your account? [Y/N]");
                    String response = ui.getInput();
                    if (response.toLowerCase().contains("y")) {
                        user.storeCard(cardName, cardNumber);
                        System.out.println("Card details successfully stored to your account. Have a great day!");
                    } else if (response.toLowerCase().contains("n")) {
                        System.out.println("No worries! Have a great day!");
                    }
                }
            }
        }
    }
}
