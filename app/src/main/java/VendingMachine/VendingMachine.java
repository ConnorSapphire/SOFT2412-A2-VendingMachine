package VendingMachine;

import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import javax.swing.plaf.synth.SynthSeparatorUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VendingMachine extends Frame implements ActionListener,WindowListener, TextListener{
    private FileManager fileManager = new FileManager();
    private UserInterface ui = new UserInterface(fileManager);
    private User user;
    private HashMap<String, User> users;
    private HashMap<String, Product> products;
    private HashMap<String, Change> change;
    private JSONObject json;

    public VendingMachine(String str){
		super(str);
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine("VendingMachine");
        vm.login();
        // vm.setSize(240,240);
		// vm.setBackground(Color.pink);
		// vm.setVisible(true);
        // vm.addWindowListener(vm);
    }

    public void login(){
        Login log = new Login("Login");
        if(log.u != null){
            user = log.u;
        }
    }

    public User newRegisteredCustomer(String username, String password, UserInterface ui){
        UserCreator customerCreator = new RegisteredCustomerCreator();
        System.out.println("Enter your username");
        String newUsername = ui.getInput();
        
        if (users.containsKey(newUsername)) {
            System.out.println("Username already exists.");
            return null;
        }

        String newPassword = ui.getInputPassword();

        User customer = customerCreator.create(newUsername, newPassword, ui);
        users.put(username, customer);
        user = customer;
        return customer;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void textValueChanged(TextEvent e) {
        // TODO Auto-generated method stub
        
    }
}
