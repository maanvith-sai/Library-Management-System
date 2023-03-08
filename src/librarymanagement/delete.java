package librarymanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class delete extends javax.swing.JFrame {
    JFrame cancelFrame;
    JLabel titleLabel, showLabel, enterPnrText;
    JTextField pnrInput;
    JButton submitButton, backButton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    
    public delete(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
            Statement stmt = conn.createStatement();
            String sql = "use library";
            stmt.executeUpdate(sql);
        }catch (Exception e){
            System.out.println(e);
        }
        
        cancelFrame = new JFrame("Library Management System - delete book by Maanvith");
        cancelFrame.setSize(4000,4000);
        cancelFrame.setLayout(null);
        
        titleLabel = new JLabel("Library Management System"); 
        titleLabel.setBounds(500, 5, 500, 200);
        titleLabel.setForeground (Color.BLACK);
        Font font1 = new Font("Serif", Font. BOLD, 35);
        titleLabel.setFont(font1);
        cancelFrame.add(titleLabel);

        showLabel = new JLabel("DELETE A BOOK");
        showLabel.setBounds (560, 150, 400, 200);
        showLabel.setForeground (Color.BLACK);
        Font font2 = new Font("Serif", Font. BOLD, 30);
        showLabel.setFont(font2);
        cancelFrame.add(showLabel);

        enterPnrText = new JLabel("Enter Book-ID :");
        enterPnrText.setBounds (370, 400, 300, 40);
        enterPnrText.setForeground (Color.BLACK);
        Font font3 = new Font("Serif", Font. BOLD, 30);
        enterPnrText.setFont(font3);
        cancelFrame.add(enterPnrText);
        
        pnrInput = new JTextField(); 
        pnrInput.setBounds(600, 400, 300, 60);
        pnrInput.setFont(font3);
        pnrInput.requestFocus(); 
        cancelFrame.add(pnrInput);
        
        submitButton = new JButton("Submit"); 
        submitButton.setBounds(550, 600, 150, 80); 
        submitButton.setFont(font3);
        cancelFrame.add(submitButton);

        backButton = new JButton("Back"); 
        backButton.setBounds (800, 600, 150, 80); 
        backButton.setFont(font3);
        cancelFrame.add(backButton);

        submitButton.addActionListener(new ActionListener(){ 
            public void actionPerformed (ActionEvent e) {
                deletedata();
            }
        });

        backButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed (ActionEvent e) { 
                cancelFrame.dispose();
            }
         });
        
        cancelFrame.setVisible(true); 
        cancelFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
    public void deletedata (){
        try{
            int pnrNumber = Integer.parseInt(pnrInput.getText());
            String q = "select * from books where id = ?";
            PreparedStatement pst1 = conn.prepareStatement(q);
            pst1.setInt(1,pnrNumber); 
            ResultSet rSet = pst1.executeQuery();
            if(rSet.next()){ 
                try{
                    int response = JOptionPane.showConfirmDialog(cancelFrame,"Do you want to delete the book for sure?", "confirm",JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(response == JOptionPane.YES_NO_OPTION){
                        q = "delete from books where id = ?";
                        pst1 = conn.prepareStatement(q);
                        pst1.setInt(1,pnrNumber);
                        pst1.executeUpdate();
                        JOptionPane.showMessageDialog(null, "BOOK Deleted Succesfully");
                    }
                }catch (Exception e1){
                    System.out.println(e1);
                    JOptionPane.showMessageDialog(null, "something went wrong");
                }
            }else{
                JOptionPane.showMessageDialog(null, "ID number doesnot exist");
            }
            pnrInput.setText("");
            pnrInput.requestFocus();
        } catch(Exception e2){
            System.out.println(e2);
            JOptionPane.showMessageDialog(null, "SOmething went wrong");
        }
    }
}
