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
    private FileManager fileManager;
    private UserInterface ui;
    private User user;
    private HashMap<String, User> users;
    private JLabel err;
    private JTextField username, pw;
    private JButton login, register;
    private HashMap<String, Product> products;
    private HashMap<String, Change> change;

    public VendingMachine(String str){
		super(str);
        fileManager = new FileManager();
        ui = new UserInterface(fileManager);
        this.user = null;
        users = new HashMap<>();
        products = new HashMap<>();
        change = new HashMap<>();
        err = new JLabel();
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine("VendingMachine");
        vm.login();
        vm.setSize(240,240);
		vm.setBackground(Color.pink);
		vm.setVisible(true);
        vm.addWindowListener(vm);
    }

    public void login(){

        JPanel jp0 = new JPanel();
        jp0.setLayout(new FlowLayout());
        jp0.add(err);

        // Get username and password
        JPanel jp1 = new JPanel();
		JLabel jl1 = new JLabel("Username");
		username = new JTextField(10);
 
	    jp1.setLayout(new FlowLayout());
	    jp1.add(jl1);
	    jp1.add(username);
	    
	    JPanel jp2 = new JPanel();
	    JLabel jl2 = new JLabel("Password");
	    pw = new JTextField(10);
	    jp2.setLayout(new FlowLayout());
	    jp2.add(jl2);
	    jp2.add(pw);
 
	    login = new JButton("Login");
        login.addActionListener(this);
	    register = new JButton("Register");
        register.addActionListener(this);
	    JPanel jp3 = new JPanel();
	    jp3.setLayout(new FlowLayout());
	    jp3.add(login);
	    jp3.add(register);
	    
	    this.setLayout(new GridLayout(4,1));
        this.add(jp0);
	    this.add(jp1);
	    this.add(jp2);
	    this.add(jp3);
	    this.setAlwaysOnTop(true);
	    this.setSize(240, 240);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.pack();
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
        return;
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
        if(e.getSource() == login || e.getSource() == register){
            if(user != null){
                return;
            }
            err.setText("");
            String name = username.getText();
            String password = pw.getText();
            if(e.getSource() == login){
                for(String u : users.keySet()){
                    if(name.equals(u)){
                        if(password.equals(users.get(u).getPassword())){
                            user = users.get(u);
                        }else{
                            err.setText("Password is incorrect");
                            return;
                        }
                        break;
                    }
                }
                if (user == null) {
                    err.setText("Username does not exist.");
                }
            }else if(e.getSource() == register){
                for(String user : users.keySet()){
                    if(name.equals(user)){
                        err.setText("Username already exists.");
                        return;
                    }
                }
                User customer = new RegisteredCustomerCreator().create(name, password, ui);
                users.put(name, customer);
                user = customer;
            }
        }
        
        
    }

    @Override
    public void textValueChanged(TextEvent e) {
        // TODO Auto-generated method stub
        
    }
}
