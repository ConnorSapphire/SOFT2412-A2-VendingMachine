package VendingMachine;

import java.util.LinkedHashMap;
import java.util.HashMap;

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

        String amount = ui.getInput();
        if (amount.toLowerCase().equals("cancel")) {{
            user.cancelTransaction();
            return;
        }}

        getUserChange(cost);
        
        // Adjustments to be made below 
        Double cashInput = Double.parseDouble(amount);
        
        
        transaction.setEndTime();
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
                System.out.println("here");
                denomination = item.substring(1);
            } else if (item.charAt(item.length() - 1) == 'c'){
                System.out.println("over here");
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
                System.out.println("Cash accepted"); 
                // Change this message
                break;
            }            
        }

        return userCash;
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
    
    public void giveChange(Double cost, LinkedHashMap<String, Integer> userCash){
        
    }

    public HashMap<String, Change> addQuantity(LinkedHashMap<String, Integer> userCash){
        HashMap<String, Change> vMChange = user.getChange();
        HashMap<String, Change> temp = new HashMap<>();
        temp.putAll(vMChange);

        for(String item: userCash.keySet()){
            Change change = temp.get(item);

            Integer addChange = change.getQuantity() + userCash.get(item);
            change.setQuantity(addChange);
        }

        return temp;
    }
}
