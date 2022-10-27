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
        this.transaction = user.getTransaction();
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
            ui.displayQuestionString("Enter cardholder name: ");
            String cardName = ui.getPlainInput();
            if (cardName.toLowerCase().equals("cancel")) {
                user.cancelTransaction();
                return;
            }
            if (user.getCurrentTransaction().isCancelled()) {
                return;
            }
            ui.displayQuestionString("Enter card number: ");
            String cardNumber = ui.getPlainInput();
            if (cardNumber.toLowerCase().equals("cancel")) {
                user.cancelTransaction();
                return;
            }
            if (user.getCurrentTransaction().isCancelled()) {
                return;
            }
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
                    ui.displayQuestionString("Would you like to save this card to your account? [Y/N]\n");
                    String response = ui.getInput();
                    if (response.toLowerCase().contains("y")) {
                        user.storeCard(cardName, cardNumber);
                        System.out.println("Card details successfully stored to your account. Have a great day!");
                    } else if (response.toLowerCase().contains("n")) {
                        System.out.println("No worries! Have a great day!");
                    } else {
                        ui.displayErrorString("Unrecognised input. Protecting card details...");
                        System.out.println("Card details have not been saved. Have a great a day!");
                    }
                }
            }
        }
        transaction.setEndTime();
        // Update transaction history in file
        ui.getFileManager().updateTransactionHistory(transaction.getEndTime(), transaction.getProducts(), cost, 0.0, transaction.getPaymentMethod());
        // Update products in file and internal memory
        for (Product product : transaction.getProducts()) {
            product.setQuantity(product.getQuantity() - 1);
            product.setTotalSold(product.getTotalSold() + 1);
            if (product.getCategory().equals("candy")) {
                ui.getFileManager().updateCandies(product);
            } else if (product.getCategory().equals("chocolate")) {
                ui.getFileManager().updateChocolates(product);
            } else if (product.getCategory().equals("chip")) {
                ui.getFileManager().updateChips(product);
            } else if (product.getCategory().equals("drink")) {
                ui.getFileManager().updateDrinks(product);
            }
        }
    }
}
