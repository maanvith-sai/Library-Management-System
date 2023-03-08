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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author Maanvith Sai
 */
class rent {
    JFrame rentFrame;
    JLabel nameLabel,idLabel,daysLabel,titleLabel,showLabel;
    JTextField Tname, Tid;
    JComboBox  TFdays, TFMonths, TFyears, TTdays, TTMonths,TTyears;
    String fdays, fMonths, fyears, tdays, tMonths,tyears, cu,Bname,cost,lastdate;
    JLabel Days, Months, Year, From, To,rentCost;
    JButton calculate, viewButton, backButton;
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    long daysBetween;
    public rent(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
        
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = Integer.toString(i+1);
        }
        String[] months = new String[31];
        for (int i = 0; i < 12; i++) {
            months[i] = Integer.toString(i+1);
        }
        String year[]={"","2020","2021","2022","2023","2024","2025"};
        
        
        rentFrame = new JFrame("Library Management System - Rent a book by Maanvith");
        rentFrame.setSize(4000,4000);
        rentFrame.setLayout(null);
        
        titleLabel = new JLabel("Library Management System"); 
        titleLabel.setBounds(500, 0, 500, 200);
        titleLabel.setForeground (Color.BLACK);
        Font font1 = new Font("Serif", Font. BOLD, 35);
        titleLabel.setFont(font1);
        rentFrame.add(titleLabel);

        showLabel = new JLabel("RENT A BOOK");
        showLabel.setBounds (600, 150, 400, 50);
        showLabel.setForeground (Color.BLACK);
        Font font2 = new Font("Serif", Font. BOLD, 30);
        showLabel.setFont(font2);
        rentFrame.add(showLabel);
        
        
        nameLabel = new JLabel("Book Name");
        nameLabel.setBounds (510, 250, 400, 50);
        nameLabel.setForeground (Color.BLACK);
        nameLabel.setFont(font2);
        rentFrame.add(nameLabel);
        
        
        Tname = new JTextField();
        Tname.setBounds(750,250,300,40);
        //Font font2 = new Font("Serif", Font. BOLD, 30);
        Tname.setForeground(Color.BLACK);
        Tname.setFont(font2);
        rentFrame.add(Tname);
        
        
        idLabel = new JLabel("Book Id");
        idLabel.setBounds (510, 300, 400, 50);
        idLabel.setForeground (Color.BLACK);
        idLabel.setFont(font2);
        rentFrame.add(idLabel);
        
        Tid = new JTextField();
        Tid.setBounds(750,300,300,40);
        Tid.setForeground(Color.BLACK);
        Tid.setFont(font2);
        rentFrame.add(Tid);
        
        
        daysLabel = new JLabel();
        daysLabel.setBounds (1100, 600, 400, 50);
        daysLabel.setForeground (Color.BLACK);
        daysLabel.setFont(font2);
        rentFrame.add(daysLabel);


        rentCost = new JLabel();
        rentCost.setBounds (1100, 680, 400, 50);
        rentCost.setForeground (Color.BLACK);
        rentCost.setFont(font2);
        rentFrame.add(rentCost);
        
        
        Days = new JLabel("Date");
        Days.setBounds (450, 410, 400, 50);
        Days.setForeground (Color.BLACK);
        Days.setFont(font2);
        rentFrame.add(Days);

        
        Months = new JLabel("Month");
        Months.setBounds (650, 410, 400, 50);
        Months.setForeground (Color.BLACK);
        Months.setFont(font2);
        rentFrame.add(Months);
        
        Year = new JLabel("Year");
        Year.setBounds (850, 410, 400, 50);
        Year.setForeground (Color.BLACK);
        Year.setFont(font2);
        rentFrame.add(Year);
        
        
        From = new JLabel("From");
        From.setBounds (350, 460, 400, 50);
        From.setForeground (Color.BLACK);
        From.setFont(font2);
        rentFrame.add(From);
        
        
        To = new JLabel("TO");
        To.setBounds (350, 560 , 400, 50);
        To.setForeground (Color.BLACK);
        To.setFont(font2);
        rentFrame.add(To);
        
        
        TFdays = new JComboBox<String>(days);
        TFdays.setBounds(450,480,150,40);
        rentFrame.add(TFdays);
        TFdays.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == TFdays) {
                    TFdays.getSelectedItem();
                }
            }
        });
        
        
        
        TFMonths = new JComboBox<String>(months);
        TFMonths.setBounds(650,480,150,40);
        rentFrame.add(TFMonths);
        TFMonths.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == TFMonths) {
                    TFMonths.getSelectedItem();
                }
            }
        });
        
        
        TFyears = new JComboBox<String>(year);
        TFyears.setBounds(850,480,150,40);
        rentFrame.add(TFyears);
        TFyears.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == TFyears) {
                    TFyears.getSelectedItem();
                }
            }
        });
        
        
        
        
        TTdays = new JComboBox<String>(days);
        TTdays.setBounds(450,560,150,40);
        rentFrame.add(TTdays);
        TTdays.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == TTdays) {
                    TTdays.getSelectedItem();
                }
            }
        });
        
        
        TTMonths = new JComboBox<String>(months);
        TTMonths.setBounds(650,560,150,40);
        rentFrame.add(TTMonths);
        TTMonths.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == TTMonths) {
                    TTMonths.getSelectedItem();
                }
            }
        });
        
        
        TTyears = new JComboBox<String>(year);
        TTyears.setBounds(850,560,150,40);
        rentFrame.add(TTyears);
        TTyears.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == TTyears) {
                    TTyears.getSelectedItem();
                }
            }
        });
        
        
        calculate = new JButton("Get Days");
        calculate.setBounds(1100,500,200,50);
        calculate.setFont(font2);
        rentFrame.add(calculate);
        
        
        viewButton = new JButton("Submit"); 
        viewButton.setBounds (400, 730, 200, 50); 
        Font font4 = new Font("Sierf", Font.PLAIN,30);
        viewButton.setFont(font4);
        rentFrame.add(viewButton);

        backButton = new JButton("Back"); 
        backButton.setBounds (800, 730, 200, 50); 
        backButton.setFont(font4);
        rentFrame.add(backButton);
        
        
        calculate.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               
               
               try{
                    fdays = TFdays.getSelectedItem().toString();
                    int fd = Integer.parseInt(fdays);
                    
                    fMonths = TFMonths.getSelectedItem().toString();
                    int fm = Integer.parseInt(fMonths);
                    
                    fyears = TFyears.getSelectedItem().toString();
                    int fy = Integer.parseInt(fyears);
                    
                    tdays= TTdays.getSelectedItem().toString();
                    int td = Integer.parseInt(tdays);
                    
                    tMonths = TTMonths.getSelectedItem().toString();
                    int tm = Integer.parseInt(tMonths);
                    
                    tyears = TTyears.getSelectedItem().toString();
                    int ty = Integer.parseInt(tyears);
                    
                    lastdate = fdays+"-"+fMonths+"-"+fyears;
                    
                    LocalDate date1 = LocalDate.of(fy, fm, fd);
                    LocalDate date2 = LocalDate.of(ty, tm, td);
                    daysBetween = ChronoUnit.DAYS.between(date1, date2);
                    

                    daysLabel.setText("Days : "+daysBetween);
                    
                    
                    int cc = (int)daysBetween;
                    double bc = (double)cc *0.7;
                    cost = String.valueOf(bc);
                    
                    rentCost.setText("Cost : "+cost+" Rupees");
                    
                    
                    
                    
                    
                    
                    
                    
               }catch(Exception b){
                   JOptionPane.showMessageDialog(null,"Error in back");
               }
               
               
           }
        });
        
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rentFrame.dispose();
            }
        });
        
        viewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                   insertintodatabase();
               }catch(Exception e4){
                   JOptionPane.showMessageDialog(null,"Insertion to Database Error");
               }
            }
        });
        
        
        rentFrame.setVisible(true);
        rentFrame.setLayout(null);
        
        
    }
    public void insertintodatabase(){
        
        try{
            String c = "Select * from current where no = ?";
            PreparedStatement ps1 = conn.prepareStatement(c);
            ps1.setString(1,"1");
            ResultSet rs1 = ps1.executeQuery();

            if(rs1.next()){
                cu = rs1.getString(2);

            }
        }catch(Exception e1){
            JOptionPane.showMessageDialog(null,"Error in fetching Current");
        }
        
        try{
            String b = "Select * from books where id = ? ";
            PreparedStatement ps2 = conn.prepareStatement(b);
            ps2.setString(1,Tid.getText());
            ResultSet rs2 = ps2.executeQuery();

            if(rs2.next()){
                Bname = rs2.getString(2);

            }
        }catch(Exception e2){
            JOptionPane.showMessageDialog(null,"No Book Found for that ID");
        }
        
        
           
        
        try{
        String q2= "insert into rent (username,id,name, days,cost,lastdate) values(?,?,?,?,?,?)";
        PreparedStatement pst4 = conn.prepareStatement(q2);
        pst4.setString(1,cu);
        pst4.setString(2,Tid.getText());
        pst4.setString(3,Bname);
        pst4.setString(4,String.valueOf(daysBetween));
        pst4.setString(5,cost);
        pst4.setString(6,lastdate);
        pst4.executeUpdate();
        JOptionPane.showMessageDialog(null,"Book Rented Succesfully Check your orders");
        }catch(Exception e4){
            JOptionPane.showMessageDialog(null,"Insertion into rent error");
        }
    }
    
}
