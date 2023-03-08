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
public class addBook extends javax.swing.JFrame{
    //JFrame addFrame;
    JLabel bTitle,bookId,bAuthor,bcost,title;
    JTextField TbTitle,TbookId,TbAuthor,Tbcost;
    JButton add,back;
    JComboBox cb;
    String c[]={"","CSE","CIVIL","MECHANICAL","EEE","ECE","NOVELS"};
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    
    public addBook(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }catch(ClassNotFoundException | SQLException E){
            JOptionPane.showMessageDialog(null,"Error in Class");
        }
        
        JFrame addFrame = new JFrame("Add a Book");
        addFrame.setSize(4000,4000);
        addFrame.setLayout(null);
        
        title = new JLabel("Add a Book");
        title.setBounds(600,0,300,300);
        title.setForeground(Color.BLACK);
        Font font1 = new Font("Black Cooper", Font.BOLD,40);
        title.setFont(font1);
        addFrame.add(title);
        
        
        
        
        bookId = new JLabel("Book ID");
        bookId.setBounds(500,300,150,40);
        bookId.setForeground(Color.BLACK);
        Font font3 = new Font("Serif", Font.BOLD, 25);
        bookId.setFont(font3);
        addFrame.add(bookId);
        
        TbookId = new JTextField();
        TbookId.setBounds(800,300,300,50);
        TbookId.setForeground(Color.BLACK);
        TbookId.setFont(font3);
        addFrame.add(TbookId);
        
        
        bTitle = new JLabel("Book Name");
        bTitle.setBounds(500,380,150,40);
        bTitle.setForeground(Color.BLACK);
        bTitle.setFont(font3);
        addFrame.add(bTitle);
        
        TbTitle = new JTextField();
        TbTitle.setBounds(800,380,300,50);
        TbTitle.setForeground(Color.BLACK);
        TbTitle.setFont(font3);
        addFrame.add(TbTitle);
        
        
        
        bAuthor = new JLabel("Author");
        bAuthor.setBounds(500,460,150,40);
        bAuthor.setForeground(Color.BLACK);
        bAuthor.setFont(font3);
        addFrame.add(bAuthor);
        
        TbAuthor = new JTextField();
        TbAuthor.setBounds(800,460,300,50);
        TbAuthor.setForeground(Color.BLACK);
        TbAuthor.setFont(font3);
        addFrame.add(TbAuthor);
        
        
        bcost = new JLabel("Cost");
        bcost.setBounds(500,540,150,40);
        bcost.setForeground(Color.BLACK);
        bcost.setFont(font3);
        addFrame.add(bcost);
        
        Tbcost = new JTextField();
        Tbcost.setBounds(800,540,300,50);
        Tbcost.setForeground(Color.BLACK);
        Tbcost.setFont(font3);
        addFrame.add(Tbcost);
        
        JLabel category = new JLabel ("Category");
        category.setBounds(500,620,150,40);
        category.setForeground(Color.BLACK);
        category.setFont(font3);
        addFrame.add(category);
        
                
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
        
        add = new JButton("Add");
        add.setBounds(600,700,100,50);
        add.setFont(font3);
        addFrame.add(add);
        
        back = new JButton("Back");
        back.setBounds(800,700,100,50);
        back.setFont(font3);
        addFrame.add(back);
        
        
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
                
                JOptionPane.showMessageDialog(null, "Book Added Succesfully");
                
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


