/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Maanvith Sai
 */
public class interModule {
    JFrame loginFrame;
    JLabel titlelabel;
    JButton studentButton, adminLogin;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    Connection conn = null;
    
        public interModule(){

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); 
                conn = DriverManager.getConnection (DB_URL, user, password);

            }
            catch (ClassNotFoundException | SQLException e){
                System.out.println(e);
            }
            
            
            loginFrame = new JFrame("Library INTERFACE - Login Page");
            loginFrame.setSize(4000,4000);
            loginFrame.setLayout(null);

            titlelabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
            titlelabel.setBounds(350,30,850,200);
            titlelabel.setForeground(Color.BLACK);
            Font font1 = new Font("Serif", Font.BOLD, 50);
            titlelabel.setFont(font1);
            loginFrame.add(titlelabel);
            
            studentButton = new JButton("Continue as Student");
            studentButton.setBounds(200,300,500,300);
            Font font4 = new Font("Serif", Font.BOLD, 35);
            studentButton.setFont(font4);
            loginFrame.add(studentButton);
            
            adminLogin = new JButton("Continue as Admin");
            adminLogin.setBounds(800,300,500,300);
            adminLogin.setFont(font4);
            loginFrame.add(adminLogin);
            
            
            loginFrame.setVisible(true);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            
            
            adminLogin.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    
                    try{
                        String r = "TRUNCATE table current";
                        Statement stmt = conn.createStatement();
                        stmt.execute(r);
            
            
            
                    }catch(Exception ew){
                        JOptionPane.showMessageDialog(null,"Main Error");
                    }
                    
                    
                    admin sm = new admin();
                }
            });
            
            studentButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    
                    try{
                        String r = "TRUNCATE table current";
                        Statement stmt = conn.createStatement();
                        stmt.execute(r);
            
            
            
                    }catch(Exception er){
                        JOptionPane.showMessageDialog(null,"Main Error");
                    }
                    
                    
                    student s = new student();
                }
            });
            
        }
}
