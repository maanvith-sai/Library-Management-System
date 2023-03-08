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
import javax.swing.*;
public class update extends javax.swing.JFrame{
    JFrame addFrame;
    JLabel bTitle,bookId,bAuthor,bcost,title,category;
    JTextField TbTitle,TbookId,TbAuthor,Tbcost;
    JButton add,back,get;
    JComboBox cb;
    Font font3;
    String c[]={"","CSE","CIVIL","MECHANICAL","EEE","ECE","NOVELS"};
    String iii,nnn,ccc,aaa,coo;
   
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    
    public update(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }catch(ClassNotFoundException | SQLException E){
            JOptionPane.showMessageDialog(null,"Error in Class");
        }
        
        addFrame = new JFrame("Add a Book");
        addFrame.setSize(4000,4000);
        addFrame.setLayout(null);
        
        title = new JLabel("Add a Book");
        title.setBounds(620,0,300,300);
        title.setForeground(Color.BLACK);
        Font font1 = new Font("Black Cooper", Font.BOLD,50);
        title.setFont(font1);
        addFrame.add(title);
        
        
        
        
        bookId = new JLabel("Book ID");
        bookId.setBounds(500,250,150,40);
        bookId.setForeground(Color.BLACK);
        Font f = new Font("Serif", Font.BOLD, 30);
        font3 = new Font("Serif", Font.BOLD, 25);
        bookId.setFont(f);
        addFrame.add(bookId);
        
        TbookId = new JTextField();
        TbookId.setBounds(800,250,400,40);
        TbookId.setForeground(Color.BLACK);
        TbookId.setFont(f);
        addFrame.add(TbookId);
        
        
        bTitle = new JLabel("");
        bTitle.setBounds(500,380,150,40);
        bTitle.setForeground(Color.BLACK);
        bTitle.setFont(font3);
        addFrame.add(bTitle);
//        
//        TbTitle = new JTextField();
//        TbTitle.setBounds(800,380,300,50);
//        TbTitle.setForeground(Color.BLACK);
//        TbTitle.setFont(font3);
//        addFrame.add(TbTitle);
//        
//        
//        
        bAuthor = new JLabel("");
        bAuthor.setBounds(500,460,150,40);
        bAuthor.setForeground(Color.BLACK);
        bAuthor.setFont(font3);
        addFrame.add(bAuthor);
//        
//        TbAuthor = new JTextField();
//        TbAuthor.setBounds(800,460,300,50);
//        TbAuthor.setForeground(Color.BLACK);
//        TbAuthor.setFont(font3);
//        addFrame.add(TbAuthor);
//        
//        
        bcost = new JLabel("");
        bcost.setBounds(500,540,150,40);
        bcost.setForeground(Color.BLACK);
        bcost.setFont(font3);
        addFrame.add(bcost);
//        
//        Tbcost = new JTextField();
//        Tbcost.setBounds(800,540,300,50);
//        Tbcost.setForeground(Color.BLACK);
//        Tbcost.setFont(font3);
//        addFrame.add(Tbcost);
//        
        category = new JLabel ("");
        category.setBounds(500,620,150,40);
        category.setForeground(Color.BLACK);
        category.setFont(font3);
        addFrame.add(category);
//        
//                
//        cb = new JComboBox<String>(c);
//        cb.setBounds(800,620,150,40);
//        addFrame.add(cb);
//        cb.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == cb) {
//                    cb.getSelectedItem();
//                }
//            }
//        });
        
        get = new JButton("Get");
        get.setBounds(1300,250,100,50);
        get.setFont(font3);
        addFrame.add(get);
        
        add = new JButton("Update");
        add.setBounds(600,720,150,50);
        add.setFont(font3);
        addFrame.add(add);
        
        back = new JButton("Back");
        back.setBounds(800,720,100,50);
        back.setFont(font3);
        addFrame.add(back);
        
        
        get.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                   get();
               }catch(Exception b){
                   JOptionPane.showMessageDialog(null,"error in get");
               }
               try{
                    String i;
                    i = TbookId.getText();
                   
                    String q = "delete from books where id = ?";
                    PreparedStatement pst1 = conn.prepareStatement(q);
                    pst1 = conn.prepareStatement(q);
                    pst1.setString(1,i);
                    pst1.executeUpdate();
                   
                           
               }catch(Exception d){
                   JOptionPane.showMessageDialog(null,"Get deletion Error");
               }
           }
        });
        
        
        add.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                   insertintodatabase();
               }catch(Exception b){
                   JOptionPane.showMessageDialog(null,"error in back");
               }
           }
        });
        
        
        
        back.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               try{
                   addFrame.dispose();
               }catch(Exception b){
                   JOptionPane.showMessageDialog(null,"error in back");
               }
           }
        });
        
        addFrame.setVisible(true);
        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void get(){
        
        
        
        
        TbTitle = new JTextField();
        TbTitle.setBounds(800,380,300,50);
        TbTitle.setForeground(Color.BLACK);
        TbTitle.setFont(font3);
        addFrame.add(TbTitle);
        
        
        
        
        
        TbAuthor = new JTextField();
        TbAuthor.setBounds(800,460,300,50);
        TbAuthor.setForeground(Color.BLACK);
        TbAuthor.setFont(font3);
        addFrame.add(TbAuthor);
        
        
        
        
        Tbcost = new JTextField();
        Tbcost.setBounds(800,540,300,50);
        Tbcost.setForeground(Color.BLACK);
        Tbcost.setFont(font3);
        addFrame.add(Tbcost);
        
        
        
                
        cb = new JComboBox<String>(c);
        cb.setBounds(800,620,150,40);
        addFrame.add(cb);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cb) {
                    cb.getSelectedItem();
                }
            }
        });
        
        
        
        
        
        
        
        
        
        String i;
        i = TbookId.getText();
        try{
            String gg = "Select * from books where id = ?";
            PreparedStatement pst1 = conn.prepareStatement(gg);
            pst1.setString(1,i);
            ResultSet rset = pst1.executeQuery();
            
            if(rset.next()){
                iii = rset.getString(1);
                nnn = rset.getString(2);
                ccc = rset.getString(3);
                aaa = rset.getString(4);
                coo = rset.getString(5);
            }
            
            try{
                bTitle.setText("Book Name");
                bAuthor.setText("Author");
                bcost.setText("Cost");
                category.setText("Category");
                TbTitle.setText(nnn);
                TbAuthor.setText(aaa);
                Tbcost.setText(coo);
                cb.setSelectedItem(ccc);
            }catch(Exception u){
                JOptionPane.showMessageDialog(null,"Error in updation of get");
            }
            
        }catch(Exception g){
            JOptionPane.showMessageDialog(null,"get Method error");
        }
    }
        public void insertintodatabase() throws IllegalArgumentException, SQLException{
            String q1,q2;
            String name, id,category, author,cost;
            
            name = TbTitle.getText();
            id = TbookId.getText();
            category = cb.getSelectedItem().toString() ;
            author = TbAuthor.getText();
            cost = Tbcost.getText();
            
            try{
                q1 = "insert into books(id,name,category,author,cost) values(?,?,?,?,?)";
                PreparedStatement pst1 = conn.prepareStatement(q1);
                pst1.setString(1,id);
                pst1.setString(2,name);
                pst1.setString(3,category);
                pst1.setString(4,author);
                pst1.setString(5,cost);
                pst1.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Book Updated Succesfully");
                
                TbTitle.setText("");
                TbookId.setText("");
                TbAuthor.setText("");
                Tbcost.setText("");
                cb.setSelectedItem("");
               
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Book ID Must be Unique");
                
            }
    }
}


