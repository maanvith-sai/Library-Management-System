package librarymanagement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class searchbook extends javax.swing.JFrame{
    JFrame bookFrame;
    JLabel titleLabel, enterPnrText, bookingFormText, emailLabel, fromLabel, toLabel, dateLabel, timeLabel;
    JLabel pnr, name, email, locationinfo, datentime,trainName, trainNumber;
    JTextField firstNameInput, lastNameInput, emailInput, pnrInput, timeInput; JComboBox fromlistCities, tolistCities;
    JButton submitButton, backButton;
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";

    public searchbook() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        bookFrame = new JFrame("Library Management System - Search Book Details by Maanvith"); 
        bookFrame.setSize(4000, 4000);
        bookFrame.setLayout(null);

        titleLabel = new JLabel("Library Management System"); 
        titleLabel.setBounds (500, 5, 500, 200); 
        Font font1 = new Font("Serif", Font.BOLD, 35);
        titleLabel.setForeground (Color.BLACK);
        titleLabel.setFont(font1);
        bookFrame.add(titleLabel);
        
        bookingFormText = new JLabel("SEARCH BOOK"); 
        bookingFormText.setBounds(580, 60, 400, 200); 
        bookingFormText.setForeground (Color.BLACK); 
        Font font2 = new Font("Serif", Font. BOLD, 30); 
        bookingFormText.setFont(font2);
        bookFrame.add(bookingFormText);
        
        
        enterPnrText = new JLabel("Enter Book ID :");
        enterPnrText.setBounds(400,300,300,40);
        enterPnrText.setForeground(Color.BLACK);
        Font font3 = new Font("Sierf", Font.BOLD,30);
        enterPnrText.setFont(font3);
        bookFrame.add(enterPnrText);
        
        pnrInput = new JTextField();
        pnrInput.setBounds(640,300,300,40);
        pnrInput.setFont(font3);
        pnrInput.requestFocus();
        bookFrame.add(pnrInput);
        
        pnr = new JLabel("Book ID : ");
        pnr.setBounds(600,400,300,40);
        pnr.setForeground(Color.BLACK);
        Font font4 = new Font("Serif", Font.BOLD, 30);
        pnr.setFont(font4);
        bookFrame.add(pnr);
        
        
        name = new JLabel("Book Name : ");
        name.setBounds(600,450,800,40);
        name.setForeground(Color.BLACK);
        name.setFont(font4);
        bookFrame.add(name);
        
        
        email = new JLabel("Category : ");
        email.setBounds(600,500,800,40);
        email.setForeground(Color.BLACK);
        email.setFont(font4);
        bookFrame.add(email);
        
        
        locationinfo = new JLabel("Author : ");
        locationinfo.setBounds(600,550,800,40);
        locationinfo.setForeground(Color.BLACK);
        locationinfo.setFont(font4);
        bookFrame.add(locationinfo);
        
        
        datentime = new JLabel("Cost : ");
        datentime.setBounds(600,600,500,40);
        datentime.setForeground(Color.BLACK);
        datentime.setFont(font4);
        bookFrame.add(datentime);
        

        submitButton = new JButton("Search"); 
        submitButton.setBounds (520, 700, 140, 50); 
        submitButton.setFont(font4);
        bookFrame.add(submitButton);

        backButton = new JButton("Back"); 
        backButton.setBounds (700, 700, 140, 50);
        backButton.setFont(font4);
        bookFrame.add(backButton);
        
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String pnrNumber = pnrInput.getText().toString();
                    String q = "Select * from books where id = ?";
                    
                    PreparedStatement pst1 = conn.prepareStatement(q);
                    
                    pst1.setString(1,pnrNumber);
                   
                    ResultSet rSet = pst1.executeQuery();
                    if(rSet.next()){
                           pnr.setText("Book ID      : " +rSet.getString(1));
                        name.setText("Book Name : "+rSet.getString(2));
                        email.setText("Category     : "+ rSet.getString(3));
                   locationinfo.setText("Author        : "+rSet.getString(4));
                        datentime.setText("Cost             : "+rSet.getString(5));
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "BOOK Doesnot exits");
                        pnrInput.setText("");
                        pnrInput.requestFocus();
                        //firstNameInput.requestFocus();
                    }
                }catch (Exception e2){
                        System.out.println(e2);
                        JOptionPane.showMessageDialog(null, "Assignment Failed");
                    }
                }
            
        });
        
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                bookFrame.dispose();
            }
        });
        
        bookFrame.setVisible(true);
        bookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}