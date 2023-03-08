/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class addStudent extends javax.swing.JFrame{
    JFrame signupFrame;
    JLabel titlelabel, usernametext, pintext, signuptext,idtext;
    JTextField usernameinput, amountinput, nameinput, accnoinput,idinput;
    JPasswordField pininput;
    String c[]={"CSE","CIVIL","MECHANICAL","EEE","ECE","FDT"};
    String y[] = {"I","II","III","IV"};
    JComboBox cb,yb;
    JButton backbutton, registerbutton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    
    public addStudent() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, user, password);
            //Statement stmt =conn.createStatement();
            /*String sql1 = "if not exists (select * from sys.databases where name = 'onlinereservationsystem')"
                    + "BEGIN create database onlinereservationsystem";
            stmt.executeUpdate(sql1);*/
            /*String sql2= "use onlinereservationsystem"
                    + "CREATE TABLE 'onlinereservationsystem'.'users' ('username' VARCHAR(100) NOT NULL , 'password' VARCHAR(100) NOT NULL ) ENGINE = InnoDB";
            stmt.executeUpdate(sql2);*/
            
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Something went wrong");
        }
        signupFrame = new JFrame("Student - Create Account");
        signupFrame.setSize(4000,4000);
        signupFrame.setLayout(null);
        
        titlelabel = new JLabel("SIGN UP -LIBRARY MANAGEMENT");
        titlelabel.setBounds(470,100,600,50);
        titlelabel.setForeground(Color.BLACK);
        Font font1 = new Font("Serif", Font.BOLD, 40);
        titlelabel.setFont(font1);
        signupFrame.add(titlelabel);
        
        signuptext = new JLabel("SIGNUP");
        signuptext.setBounds(680,200,300,50);
        signuptext.setForeground(Color.BLACK);
        Font font2 = new Font("Serif", Font.BOLD,30);
        signuptext.setFont(font2);
        signupFrame.add(signuptext);
        
       
        JLabel name = new JLabel("Full Name");
        name.setBounds(430,300,100,40);
        name.setForeground(Color.BLACK);
        Font font3 = new Font("Serif", Font.BOLD,20);
        name.setFont(font3);
        signupFrame.add(name);
        
        JLabel accno = new JLabel("Year");
        accno.setBounds(430,350,150,40);
        accno.setForeground(Color.BLACK);
        accno.setFont(font3);
        signupFrame.add(accno);
        
        
        JLabel amount = new JLabel("Department");
        amount.setBounds(430,400,150,40);
        amount.setForeground(Color.BLACK);
        amount.setFont(font3);
        signupFrame.add(amount);
        
        idtext = new JLabel("Student-ID");
        idtext.setBounds(430,450,100,40);
        idtext.setForeground(Color.BLACK);
        idtext.setFont(font3);
        signupFrame.add(idtext);
        
        usernametext = new JLabel("Username");
        usernametext.setBounds(430,500,100,40);
        usernametext.setForeground(Color.BLACK);
        usernametext.setFont(font3);
        signupFrame.add(usernametext);
        
        pintext = new JLabel("Password");
        pintext.setBounds(430,550,200,40);
        pintext.setForeground(Color.BLACK);
        pintext.setFont(font3);
        signupFrame.add(pintext);
        
        
        
        usernameinput = new JTextField();
        usernameinput.setBounds(650,450,300,40);
        usernameinput.setFont(font3);
        usernameinput.requestFocus();
        signupFrame.add(usernameinput);
        
        nameinput = new JTextField();
        nameinput.setBounds(650,300,300,40);
        nameinput.setFont(font3);
        signupFrame.add(nameinput);
        
        idinput = new JTextField();
        idinput.setBounds(650,500,300,40);
        idinput.setFont(font3);
        signupFrame.add(idinput);
        
        
        
        yb = new JComboBox<String>(y);
        yb.setBounds(650,350,300,40);
        yb.setFont(font3);
        signupFrame.add(yb);
        yb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == yb) {
                    yb.getSelectedItem();
                }
            }
        });
        
        
        
        cb = new JComboBox<String>(c);
        cb.setBounds(650,400,300,40);
        cb.setFont(font3);
        signupFrame.add(cb);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cb) {
                    cb.getSelectedItem();
                }
            }
        });

        
        pininput = new JPasswordField();
        pininput.setBounds(650,550,300,40);
        pininput.setFont(font3);
        signupFrame.add(pininput);
        
        registerbutton = new JButton("Register");
        registerbutton.setBounds(500,650,140,40);
        Font font4 = new Font("Serif", Font.BOLD,20);
        registerbutton.setFont(font4);
        signupFrame.add(registerbutton);
        
        registerbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e ){
                try {
                    insertintodatabase();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null,"Signup error");
                } catch (SQLException ex) {
                    Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        backbutton = new JButton("Back to Menu");
        backbutton.setBounds(750,650,150,40);
        backbutton.setFont(font4);
        signupFrame.add(backbutton);
       
        backbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                back();
            }
        });
        signupFrame.setVisible(true);
        signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void insertintodatabase() throws IllegalArgumentException, SQLException{
        try{
            String q1, q2;
            String usernamed, pind,named,idd,dept,yeard ;
            usernamed = usernameinput.getText();
            pind = pininput.getText();
            dept = cb.getSelectedItem().toString() ;
            yeard = yb.getSelectedItem().toString() ;
            idd = idinput.getText();
            named = nameinput.getText();
            
            
            if(usernamed.isEmpty() || pind.isEmpty() || dept.isEmpty() || yeard.isEmpty() || idd.isEmpty() || named.isEmpty()){
                throw new IllegalArgumentException();
            }
            
            q1 = "insert into users(username,password) values(?,?)";
            PreparedStatement pst1 = conn.prepareStatement(q1);
            pst1.setString(1,usernamed);
            pst1.setString(2,pind);
            pst1.executeUpdate();
            
            q2 = "insert into studentinfo(name,year,department,id,username) values(?,?,?,?,?)";
            PreparedStatement pst2 =conn.prepareStatement(q2);
            pst2.setString(1,named);
            pst2.setString(2,yeard);
            pst2.setString(3,dept);
            pst2.setString(4,idd);
            pst2.setString(5,usernamed);
            try{
                pst2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student Account Created");
                pininput.setText("");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Username or Student-ID already exists");
            }
            usernameinput.setText("");
            usernameinput.requestFocus();
            
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"Fill all the Details");
            System.out.println(e);
        }
    }
    public void back(){
        signupFrame.dispose();
    }
 
}