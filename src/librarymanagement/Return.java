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
import java.util.ArrayList;
import javax.swing.*;
class Return {
    JFrame returnFrame;
    JLabel title,line, idLabel;
    JButton Return,back;
    JComboBox  Books;
    ArrayList<String> rentIds = new ArrayList<>();
    String cu,rBook;
    Connection conn;

            
    
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    
    public Return(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
            //Statement stmt = conn.createStatement();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
        returnFrame = new JFrame("Return a Book - Library Management System");
        returnFrame.setSize(4000,4000);
        returnFrame.setLayout(null);
        returnFrame.setVisible(true);
        returnFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        title = new JLabel("Return a Book");
        title.setBounds(580,5,400,300);
        Font font1 = new Font("Arial Black", Font.BOLD, 35);
        title.setFont(font1);
        returnFrame.add(title);
        
         line = new JLabel("Choose the Book ID that you want to return");
        line.setBounds(400,150,700,300);
        Font font2 = new Font("Cascadia Code SemiLight", Font.BOLD, 30);
        line.setFont(font2);
        returnFrame.add(line);
        
        idLabel = new JLabel("Book ID : ");
        idLabel.setBounds(580,450,400,40);
        Font font3 = new Font("Arial Black", Font.BOLD, 25);
        idLabel.setFont(font3);
        returnFrame.add(idLabel);
        
        
        Return = new JButton("Return"); 
        Return.setBounds (450, 650, 200, 50); 
        Font font4 = new Font("Sierf", Font.PLAIN,20);
        Return.setFont(font4);
        returnFrame.add(Return);
        
        
        back = new JButton("Back"); 
        back.setBounds (750, 650, 200, 50); 
        back.setFont(font4);
        returnFrame.add(back);
        
        
        
        try{
            String c = "Select * from current where no = ? ";
            PreparedStatement ps1 = conn.prepareStatement(c);
            ps1.setString(1,"1");
            ResultSet rs1 = ps1.executeQuery();
            
            if(rs1.next()){
                cu = rs1.getString(2);
                
            }   
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,"Current Error");
        }
        
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM rent WHERE username = ?");
                    
            stmt.setString(1, cu);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                rentIds.add(rs.getString("id"));
            }
   
        }catch(Exception e1){
            JOptionPane.showMessageDialog(null,"Rent error");
        }
        String[] results = rentIds.toArray(new String[0]);
        try{
            Books = new JComboBox<Object>(results);
            Books.setBounds(750,450,150,40);
            Books.setFont(font3);
            returnFrame.add(Books);
            Books.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == Books) {
                        Books.getSelectedItem();
                    }
                }
            });
            rBook = Books.getSelectedItem().toString();
        }catch(Exception e3){
            JOptionPane.showMessageDialog(null,"No Books ere there to RETURN!!");
            returnFrame.dispose();
        }
        
        
        
        Return.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               returnBook();
           }
        });
        
        back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               returnFrame.dispose();
           }
        });
        
        
    }
    public void returnBook(){
        try{
            int response = JOptionPane.showConfirmDialog(returnFrame,"HAVE YOU PAID THE FEE?", "confirm",JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_NO_OPTION){
                String r = "DELETE FROM rent WHERE username = ? AND id = ?;";
                PreparedStatement pst2 = conn.prepareStatement(r);
                pst2 = conn.prepareStatement(r);
                pst2.setString(1,cu);
                pst2.setString(2,rBook);
                pst2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book Returned SUCCESFULLY!!");
            }
        }catch (Exception e1){
            System.out.println(e1);
            JOptionPane.showMessageDialog(null, "Returning of BOOK Failed");
        }
    }
}
