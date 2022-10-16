package VendingMachine;

import java.util.*;

public class VendingMachine {
    private UserInterface ui = new UserInterface();
    private FileManager fileManager = new FileManager();
    private User user;
    private HashMap<String, Product> products;
    private HashMap<String, Change> change;

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        boolean run = true;
        while(run){
            Scanner scan = new Scanner(System.in);
            String input = "";
            String output = "";
            while(scan.hasNextLine()){
                input = scan.nextLine();
                output = machine.ui.getInput(input);
            }
        }
    }
}
