/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement;

/**
 *
 * @author Maanvith Sai
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class admin {
    JFrame loginFrame;
    JLabel titlelabel, usernametext, passwordtext, logintext;
    JTextField usernameinput;
    JPasswordField passwordinput;
    JButton loginButton, registerButton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    
    public admin(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }catch(ClassNotFoundException | SQLException E){
            JOptionPane.showMessageDialog(null,"Error in Class");
        }
        
        loginFrame = new JFrame("Library INTERFACE - Login Page");
        loginFrame.setSize(4000,4000);
        loginFrame.setLayout(null);
        
        titlelabel = new JLabel("Welcome to LIBRARY PORTAL");
        titlelabel.setBounds(500,5,700,200);
        titlelabel.setForeground(Color.BLACK);
        Font font1 = new Font("Serif", Font.BOLD, 35);
        titlelabel.setFont(font1);
        loginFrame.add(titlelabel);
        
        logintext = new JLabel("ADMIN LOGIN");
        logintext.setBounds(650,180,400,200);
        logintext.setForeground(Color.BLACK);
        Font font2 = new Font("Serif", Font.BOLD, 32);
        logintext.setFont(font2);
        loginFrame.add(logintext);
        
        usernametext = new JLabel("Username");
        usernametext.setBounds(500,400,150,40);
        usernametext.setForeground(Color.BLACK);
        Font font3 = new Font("Serif", Font.BOLD, 25);
        usernametext.setFont(font3);
        loginFrame.add(usernametext);
        
        passwordtext = new JLabel("Password");
        passwordtext.setBounds(500,500,100,40);
        passwordtext.setForeground(Color.BLACK);
        Font font4 = new Font("Serif", Font.BOLD, 25);
        passwordtext.setFont(font4);
        loginFrame.add(passwordtext);
        
        usernameinput = new JTextField();
        usernameinput.setBounds(700,400,300,40);
        usernameinput.setFont(font4);
        usernameinput.requestFocus();
        loginFrame.add(usernameinput);
        
        passwordinput = new JPasswordField();
        passwordinput.setBounds(700,500,300,40);
        passwordinput.setFont(font4);
        passwordinput.requestFocus();
        loginFrame.add(passwordinput);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(600,700,150,40);
        loginButton.setFont(font4);
        loginFrame.add(loginButton);
        
        registerButton = new JButton("Sign up");
        registerButton.setBounds(800,700,150,40);
        registerButton.setFont(font4);
        loginFrame.add(registerButton);
        
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(login()){
                    loginFrame.dispose();
                    try {
                        adminMenu sl = new adminMenu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Enter valid Details");
                    }
                }
                
            }
        });
        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    adminSignup as = new adminSignup();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
            
    }
    
    public boolean login(){
        try{
            String useri = usernameinput.getText();
            String pwd = passwordinput.getText();
            String q  = "select * from admin where username = ? and password = ?";
            PreparedStatement pst1 = conn.prepareStatement(q);
            pst1.setString(1,useri);
            pst1.setString(2,pwd);
            ResultSet rSet = pst1.executeQuery();
            
            if(rSet.next()){
                String u = "insert into current (no,username,password) values(?,?,?)";
                PreparedStatement pst4 = conn.prepareStatement(u);
                pst4.setString(1,"1");
                pst4.setString(2,useri);
                pst4.setString(3,pwd);
                pst4.execute();
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid username or password!!");
                usernameinput.setText("");
                passwordinput.setText("");
                usernameinput.requestFocus();
                return false;
            }
        }catch (HeadlessException | SQLException e3){
            System.out.println(e3);
            JOptionPane.showMessageDialog(null, "Error in Login Process");
            return false;
        }
    }
    
}
