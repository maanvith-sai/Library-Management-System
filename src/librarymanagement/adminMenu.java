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
import java.util.Arrays;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class adminMenu extends javax.swing.JFrame{
    JFrame menuFrame;
    JLabel title;
    JButton add,update,delete,stuAdd,back;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    
    public adminMenu(){
        
        menuFrame = new JFrame("Admin Menu");
        menuFrame.setSize(4000,4000);
        menuFrame.setLayout(null);
        
        
        title = new JLabel("ADMIN MENU");
        title.setBounds(580,5,400,300);
        Font font6 = new Font("Arial Black", Font.BOLD, 35);
        title.setFont(font6);
        menuFrame.add(title);
        
        update = new JButton("Update a Book");
        update.setBounds(600,300,300,80);
        Font font4 = new Font("Serif", Font.BOLD, 25);
        update.setFont(font4);
        menuFrame.add(update);
        
        add = new JButton("Add a Book");
        add.setBounds(600,400,300,80);
        add.setFont(font4);
        menuFrame.add(add);
        
        
        delete = new JButton("Delete a Book");
        delete.setBounds(600,500,300,80);
        delete.setFont(font4);
        menuFrame.add(delete);
        
        stuAdd = new JButton("Add Student");
        stuAdd.setBounds(600,600,300,80);
        stuAdd.setFont(font4);
        menuFrame.add(stuAdd);
        
        back = new JButton("Logout");
        back.setBounds(600,700,300,80);
        back.setFont(font4);
        menuFrame.add(back);        
        
        
        
        update.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               update r = new update();
           }
        });
        
        add.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               addBook a = new addBook();
           }
        });
        
        delete.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
                delete s = new delete();
                
           }
        });
        
        stuAdd.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try {
                   addStudent r = new addStudent();
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(adminMenu.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
                   Logger.getLogger(adminMenu.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        
        
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
}
