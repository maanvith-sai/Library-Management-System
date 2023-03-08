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
public class studentMenu extends javax.swing.JFrame{
    JFrame menuFrame;
    JLabel title;
    JButton Return,rent,search,categories,back,orders;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    
    public studentMenu(){
        
        menuFrame = new JFrame("Student Menu");
        menuFrame.setSize(4000,4000);
        menuFrame.setLayout(null);
        
        
        title = new JLabel("STUDENT MENU");
        title.setBounds(580,5,400,300);
        Font font6 = new Font("Arial Black", Font.BOLD, 35);
        title.setFont(font6);
        menuFrame.add(title);
        
        rent = new JButton("Rent a Book");
        rent.setBounds(600,300,300,80);
        Font font4 = new Font("Serif", Font.BOLD, 25);
        rent.setFont(font4);
        menuFrame.add(rent);
        
        Return = new JButton("Return a Book");
        Return.setBounds(600,400,300,80);
        Return.setFont(font4);
        menuFrame.add(Return);
        
        
        search = new JButton("Search for a Book");
        search.setBounds(600,500,300,80);
        search.setFont(font4);
        menuFrame.add(search);
        
        categories = new JButton("Categories");
        categories.setBounds(600,600,300,80);
        categories.setFont(font4);
        menuFrame.add(categories);
        
        orders = new JButton("View Rented Book");
        orders.setBounds(600,700,300,80);
        orders.setFont(font4);
        menuFrame.add(orders);

        back = new JButton("Logout");
        back.setBounds(1100,700,300,80);
        back.setFont(font4);
        menuFrame.add(back);
        
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        rent.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               rent r = new rent();
           }
        });
        
        Return.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               Return ra = new Return();
           }
        });
        
        search.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               searchbook r = new searchbook();
           }
        });
        
        categories.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               categories r = new categories();
           }
        });
        
        
        orders.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try {
                   order r = new order();
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(studentMenu.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        });
        
        back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                   menuFrame.dispose();
                   library l = new library();
               }catch(Exception b){
                   JOptionPane.showMessageDialog(null,"error in back");
               }
           }
        });
        
        
    }
    
}
