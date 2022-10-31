package VendingMachine;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class CashStrategy implements PaymentStrategy {
    private User user;
    private UserInterface ui;
    private Transaction transaction;

    public CashStrategy(User user) {
        this.user = user;
        this.ui = user.getUI();
        this.transaction = user.getTransaction();
    }

    public void pay() {
        user.sortChangeHashMap();

        double cost = 0;
        for (Product product : transaction.getProducts()) {
            cost += product.getPrice();
        }

        LinkedHashMap<String, Integer> userCash = getUserCashInput(cost);
        if (userCash == null) {
            return;
        }
        addChangeToMachine(userCash);
        Double paid = totalPaid(userCash);
        Double change = paid - cost;
        boolean changeGiven = giveChange(change, user.getChange());
        transaction.setEndTime();
        if (changeGiven) {
            // Update transaction history
            ui.getFileManager().updateTransactionHistory(transaction.getEndTime(), transaction.getProducts(), cost, paid, change, transaction.getPaymentMethod()); 
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
        } else {
            removeChangeFromMachine(userCash);
        }
    }

    // Empty hashmap of user cash inputs
    public LinkedHashMap<String, Integer> cashCount(){
        LinkedHashMap<String, Integer> userCash = new LinkedHashMap<>();
        for (String changeName : user.getChange().keySet()) {
            userCash.put(changeName, 0);
        }
        return userCash;
    }

    // Update empty hashmap with quantities given by user
    public LinkedHashMap<String, Integer> getUserCashInput(Double amount){
        LinkedHashMap<String, Integer> userCash = cashCount();
        Double totalPaid = 0.0;
        boolean completelyPaid = false;
        
        for (String item : userCash.keySet()){
            ui.displayQuestionString("Enter the number of " + item + ": ");
            String input = ui.getPlainInput();
            if (input.toLowerCase().equals("cancel")) {{
                user.cancelTransaction();
                return null;
            }}
            
            double value = user.getChange().get(item).getValue();

            Integer quantity = 0;
            if (!input.equals("")) {
                try {
                    quantity = Integer.parseInt(input);
                } catch (IllegalArgumentException iea){
                    ui.displayErrorString("Quantity must be an integer.");
                }
            }

            userCash.merge(item, quantity, Integer::sum);
            // System.out.println("Item: " + item + " Quantity: " + userCash.get(item));
            totalPaid += (double) (value * quantity); 
            
            if (totalPaid >= amount) {
                completelyPaid = true;
                ui.displaySuccessString("Payment accepted"); 
                break;
            }            
        }
        if (completelyPaid) {
            return userCash;
        } else {
            ui.displayErrorString("Not enough money was provided.");
            user.cancelTransaction("Not enough money was provided.");
            return null;
        }
    }

    // Add user coins to vending machine till
    public void addChangeToMachine(LinkedHashMap<String, Integer> userCash){
        LinkedHashMap<String, Change> vMChange = user.getChange();

        for(String item: userCash.keySet()){
            Change change = vMChange.get(item);

            Integer addChange = change.getQuantity() + userCash.get(item);
            change.setQuantity(addChange);
            if (change.getClass().getSimpleName().equalsIgnoreCase("Note")) {
                this.user.getUI().getFileManager().updateNotes(change);
            } else if (change.getClass().getSimpleName().equalsIgnoreCase("Coin")) {
                this.user.getUI().getFileManager().updateCoins(change);
            }
        }
    }

    // Refund user coins from vending machine till
    public void removeChangeFromMachine(LinkedHashMap<String, Integer> userCash) {
        LinkedHashMap<String, Change> vMChange = user.getChange();

        for(String item: userCash.keySet()){
            Change change = vMChange.get(item);

            Integer removeChange = change.getQuantity() - userCash.get(item);
            change.setQuantity(removeChange);
            if (change.getClass().getSimpleName().equalsIgnoreCase("Note")) {
                this.user.getUI().getFileManager().updateNotes(change);
            } else if (change.getClass().getSimpleName().equalsIgnoreCase("Coin")) {
                this.user.getUI().getFileManager().updateCoins(change);
            }
        }
    }
    
    // Get total amount of user input cash
    public double totalPaid(LinkedHashMap<String, Integer> userCash){
        Double paid = 0.0;

        for(String item: userCash.keySet()){
            int quantity = userCash.get(item);
            double value = user.getChange().get(item).getValue();
            paid += (value * quantity);
        }

        return paid;
    }
    
    // Calculate change 
    public boolean giveChange(Double cost, LinkedHashMap<String, Change> allChange){
        boolean changeGiven = true;
        LinkedHashMap<String, Integer> customerChange = cashCount();
        for (String item : allChange.keySet()) {
            Change change = allChange.get(item);
            long costInt = Math.round(cost * 100);
            long changeInt = Math.round(change.getValue() * 100);
            Integer count = (int) (Math.floor(costInt/changeInt));
            if (count == 0) {
                continue;
            } else if (count <= change.getQuantity()) {
                customerChange.merge(item, count, Integer::sum);
            } else if (count > change.getQuantity()) {
                count = change.getQuantity();
                customerChange.merge(item, count, Integer::sum);
            }
            
            long total = count * changeInt;
            costInt -= total;
            cost = costInt / 100d;
            if (cost == 0) {
                break;
            }
        }

        if (cost != 0) {
            ui.displayErrorString("Not enough change in machine.");
            ui.displaySuccessString("Payment refunded.");
            user.cancelTransaction("Not enough change in machine.");
            customerChange = cashCount();
            changeGiven = false;
        }
        else { 
            transaction.complete();
            ui.displaySuccessString("Here is your change: ");
            for (String item : customerChange.keySet()) {
                Integer quantity = customerChange.get(item);
                if (quantity != 0) {
                    System.out.println(item + ": " + quantity);
                    Change current = this.user.getChange().get(item);
                    current.setQuantity(current.getQuantity() + quantity);
                    if (current.getClass().getSimpleName().equalsIgnoreCase("Note")) {
                        this.user.getUI().getFileManager().updateNotes(current);
                    } else if (current.getClass().getSimpleName().equalsIgnoreCase("Coin")) {
                        this.user.getUI().getFileManager().updateCoins(current);
                    }
                }
            }
        }
        return changeGiven;
    }

    
}
