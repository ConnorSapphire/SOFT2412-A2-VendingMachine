package VendingMachine;

import java.util.LinkedHashMap;
import java.util.stream.StreamSupport;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.lang.reflect.AnnotatedWildcardType;
import java.util.HashMap;

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
        double cost = 0;
        for (Product product : transaction.getProducts()) {
            cost += product.getPrice();
        }

        LinkedHashMap<String, Integer> userChange = getUserChange(cost);
        LinkedHashMap<String, Integer> changeGiven = addQuantity(cost, userChange);
        transaction.setEndTime();
        double change = 0.0;
        // Update transaction history -> TODO: correct change
        ui.getFileManager().updateTransactionHistory(transaction.getEndTime(), transaction.getProducts(), cost, change, transaction.getPaymentMethod()); 
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
        // Update change in file and internal memory

    }

    // Empty hashmap of user cash inputs
    public LinkedHashMap<String, Integer> cashCount(){
        LinkedHashMap<String, Integer> userCash = new LinkedHashMap<>();
        userCash.put("$100", 0);
        userCash.put("$50", 0);
        userCash.put("$20", 0);
        userCash.put("$10", 0);
        userCash.put("$5", 0);
        userCash.put("$2", 0);
        userCash.put("$1", 0);
        userCash.put("50c", 0);
        userCash.put("20c", 0);
        userCash.put("10c", 0);
        userCash.put("5c", 0);
    
        return userCash;
    }

    // Update empty hashmap with quantities given by user
    public LinkedHashMap<String, Integer> getUserChange(Double amount){
        LinkedHashMap<String, Integer> userCash = cashCount();
        Double totalCost = 0.0;
        
        for (String item : userCash.keySet()){
            System.out.println("Enter the number of " + item + ": ");
            String input = ui.getInput();
            if (input.toLowerCase().equals("cancel")) {{
                user.cancelTransaction();
                return null;
            }}
            
            String denomination = "";
            if (item.charAt(0) == '$'){
                denomination = item.substring(1);
            } else if (item.charAt(item.length() - 1) == 'c'){
                denomination = item.substring(0, item.length() - 1);
            }

            Integer value = Integer.parseInt(denomination);

            Integer quantity = 0;
            try {
                quantity = Integer.parseInt(input);
            } catch (IllegalArgumentException iea){
                System.out.println("Please enter an integer quantity.");
            }

            userCash.merge(item, quantity, Integer::sum);
            // System.out.println("Item: " + item + " Quantity: " + userCash.get(item));
            totalCost = (double) (value * quantity); 
            
            if (totalCost >= amount) {
                System.out.println("Payment accepted"); 
                break;
            }            
        }

        return userCash;
    }

    // Add user coins to vending machine till
    public LinkedHashMap<String, Integer> addQuantity(Double cost, LinkedHashMap<String, Integer> userCash){
        LinkedHashMap<String, Change> vMChange = user.getChange();
        LinkedHashMap<String, Change> temp = new LinkedHashMap<>();
        temp.putAll(vMChange);

        for(String item: userCash.keySet()){
            Change change = temp.get(item);

            Integer addChange = change.getQuantity() + userCash.get(item);
            change.setQuantity(addChange);
        }

        Double change = totalInputCash(userCash) - cost;
        return giveChange(change, temp);
    }
    
    // Get total amount of user input cash
    public double totalInputCash(LinkedHashMap<String, Integer> userCash){
        Double cost = 0.0;

        String denomination = "";
        Integer quantity = 0;
        for(String item: userCash.keySet()){
            if (item.charAt(0) == '$'){
                denomination = item.substring(1);
            } else if (item.charAt(item.length() - 1) == 'c'){
                denomination = item.substring(0, item.length() - 1);
            }
            quantity = userCash.get(item);

            Integer value = Integer.parseInt(denomination);
            cost += (value * quantity);
        }

        return cost;
    }
    
    // Calculate change 
    public LinkedHashMap<String, Integer> giveChange(Double cost, LinkedHashMap<String, Change> allChange){
        LinkedHashMap<String, Integer> customerChange = cashCount();
        for (String item : allChange.keySet()) {
            Change change = allChange.get(item);
            Integer count = (int) (Math.floor(cost/change.getValue()));

            if (count <= change.getQuantity()) {
                customerChange.merge(item, count, Integer::sum);
                int newQuantity = change.getQuantity() - count;
                change.setQuantity(newQuantity);
            } else if (count > change.getQuantity()) {
                continue;
            }

            Double total = count * change.getValue();
            cost -= total;
        }

        if (cost != 0) {
            System.out.println("Not enough change in machine.");
            System.out.println("Please try again.");
        }
        else { 
            System.out.println("Here is your change: ");
            for (String item : customerChange.keySet()) {
                Integer quantity = customerChange.get(item);
                if (quantity != 0) {
                    System.out.println(item + ": " + quantity);
                }
            }

            // Doesn't affect change stored in CendingMachine <- needs to
            user.setChange(allChange);
            
        }
        return customerChange;
    }

    
}
