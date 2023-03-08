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
import javax.swing.table.DefaultTableModel;

class categories extends javax.swing.JFrame {
    JFrame categories;
    JLabel titleLabel,orderLabel, catLabel;
    JTable historyTable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JButton viewButton,backButton;
    String[] c = {"","CSE","CIVIL","MECHANICAL","EEE","ECE","OTHERS"};
    JComboBox ccb;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    String branch;
    
    public categories(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Connection Error");
        }
        
        categories = new JFrame("Search By Categories");
        categories.setSize(4000,4000);
        categories.setLayout(null);
        
        categories.setVisible(true);
        categories.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        titleLabel = new JLabel("Library Management System"); 
        titleLabel.setBounds (420, 5, 900, 200); 
        Font font1 = new Font("Serif", Font.BOLD, 60);
        titleLabel.setForeground (Color.BLACK);
        titleLabel.setFont(font1);
        categories.add(titleLabel);
        
        orderLabel = new JLabel("Categories Based Search"); 
        orderLabel.setBounds(550, 130, 600, 200); 
        orderLabel.setForeground (Color.BLACK); 
        Font font2 = new Font("Serif", Font. BOLD, 40); 
        orderLabel.setFont(font2);
        categories.add(orderLabel);
        
        catLabel = new JLabel("Choose Category : "); 
        catLabel.setBounds(550, 420, 250, 40); 
        catLabel.setForeground (Color.BLACK); 
        Font font3 = new Font("Serif", Font. BOLD, 30); 
        catLabel.setFont(font3);
        categories.add(catLabel);
        
        
        ccb = new JComboBox<String>(c);
        ccb.setBounds(830,420,150,40);
        Font font5 = new Font("Serif", Font. BOLD, 20); 
        ccb.setFont(font5);
        categories.add(ccb);
        ccb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ccb) {
                    ccb.getSelectedItem();
                }
            }
        });
        
        viewButton = new JButton("View Orders"); 
        viewButton.setBounds (400, 600, 200, 50); 
        Font font4 = new Font("Sierf", Font.PLAIN,30);
        viewButton.setFont(font4);
        categories.add(viewButton);

        backButton = new JButton("Back"); 
        backButton.setBounds (800, 600, 200, 50); 
        backButton.setFont(font4);
        categories.add(backButton);
        
        
        
        viewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    branch = ccb.getSelectedItem().toString();
                    switch(branch){
                        case "CSE":
                            display("CSE");
                            break;

                        case "CIVIL":
                            display("CIVIL");
                            break;


                        case "MECHANICAL":
                            display("MECHANICAL");
                            break;

                        case "EEE" :
                            display("EEE");
                            break;

                        case "ECE" :
                            display("ECE");
                            break;

                        case "OTHERS" :
                            display("OTHERS");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,"Select a Valid Option");

                    }

                }catch(Exception e2){
                    JOptionPane.showMessageDialog(null,"Combobox Error");
                }
            }   
        });
        
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                categories.dispose();
            }
        });
 
        
        
    }
    
    public void display(String a){
        
        String d = a;
        
        
                try{
                    //String user = pnrInput.getText();
                    tableModel = new DefaultTableModel();
                    //tableModel.setBounds(4000,4000);
                    historyTable = new JTable(tableModel);
                    historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 25));
                    historyTable.setFont(new Font("Arial", Font.PLAIN, 20));
                    
                    String[] columnNames = {"ID", "Name","Category","Author","Cost"};
                    tableModel.setColumnIdentifiers(columnNames);
                    
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE category = ?");
                    
                    stmt.setString(1, d);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Object[] rowData = {rs.getString("id"), rs.getString("name"),rs.getString("category"),rs.getString("author"),rs.getString("cost")};
                        tableModel.addRow(rowData);
                    }
                    
                    
                    
                }catch(Exception e2){
                    JOptionPane.showMessageDialog(null,"View  Error");
                }
                
                scrollPane = new JScrollPane(historyTable);
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setPreferredSize(new Dimension(40, 0));
                add(scrollPane);
                pack();
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
                
            
            
        
        
    }    
}
