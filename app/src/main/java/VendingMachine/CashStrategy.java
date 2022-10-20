package VendingMachine;

import java.util.HashMap;

import javax.security.auth.kerberos.DelegationPermission;

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
        if (amount.toLowerCase().equals("cancel")) {{
            user.cancelTransaction();
            return;
        }}
        test(cost);
        Double cashInput = Double.parseDouble(amount);
        
        if (cashInput > cost){
            Double change = cashInput - cost;
            System.out.println("Here is your change: $" + change);
        } else if (cashInput == cost){
            System.out.println("Transaction successful");
        } else {
            System.out.println("Incorrect amount entered. Purchase cancelled.");
        }
        transaction.setEndTime();
    }

    public HashMap<String, Integer> cashCount(){
        HashMap<String, Integer> userCash = new HashMap<>();
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

    public HashMap<String, Integer> test(Double amount){
        HashMap<String, Integer> userCash = cashCount();
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
            } else if (item.charAt(input.length() - 1) == 'c'){
                System.out.println("over here");
                denomination = item.substring(0, input.length() - 1);
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
                System.out.println("Amount inputted is correct"); 
                // Change this message
                break;
            }            
        }

        return userCash;
    }

    public void addQuantity(HashMap<String, Integer> userCash){
        
        for(String item: userCash.keySet()){
            
        }

    }


}
