package VendingMachine;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import javafx.scene.control.Label;
public class Login extends Frame implements ActionListener, WindowListener{

    private JTextField username;
    private JTextField pw;
    private JButton login, register;
    private HashMap<String, User> users;
    public User u = null;
    private JLabel err = new JLabel();
    public Frame f = new Frame("Login");

    public Login(String str){
        super(str);

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
        f.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(u != null){
            return;
        }
        err.setText("");;
        String name = username.getText();
        String password = pw.getText();
        if(e.getSource() == login){
            for(String user : users.keySet()){
                if(name.equals(user)){
                    if(password.equals(users.get(user).getPassword())){
                        u = users.get(user);
                    }else{
                        err.setText("Password is incorrect");
                        return;
                    }
                    break;
                }
            }
            if (u == null) {
                err.setText("Username does not exist.");
            }
        }else if(e.getSource() == register){
            for(String user : users.keySet()){
                if(name.equals(user)){
                    err.setText("Username already exists.");
                    return;
                }
            }
        }
    }

    public void setUsers(HashMap<String, User> users){
        this.users = users;
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
    
}
