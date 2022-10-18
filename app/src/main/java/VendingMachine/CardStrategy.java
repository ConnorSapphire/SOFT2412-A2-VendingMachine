package VendingMachine;

import java.util.HashMap;

public class CardStrategy implements PaymentStrategy {
    private UserInterface ui;
    private HashMap<String, String> cards;

    public CardStrategy(UserInterface ui, HashMap<String, String> cards) {
        this.ui = ui;
        this.cards = cards;
    }

    public void pay() {
        
    }
}
