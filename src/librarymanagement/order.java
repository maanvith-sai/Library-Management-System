package librarymanagement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class order extends javax.swing.JFrame  {
    JFrame orderFrame;
    JLabel titleLabel, orderLabel;
    JButton viewButton, backButton;
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/library";
    String user = "root";
    String password = "";
    JTable historyTable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    String cu;

    public order() throws ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
        
        try{
            String c = "Select * from current where no = ? ";
            PreparedStatement ps1 = conn.prepareStatement(c);
            ps1.setString(1,"1");
            ResultSet rs1 = ps1.executeQuery();
            
            if(rs1.next()){
                cu = rs1.getString(2);
                
            }   
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,"Current Table Error");
        }
        
        
        orderFrame = new JFrame("View Orders by Maanvith"); 
        orderFrame.setSize(4000, 4000);
        orderFrame.setLayout(null);

        titleLabel = new JLabel("Library Management System"); 
        titleLabel.setBounds (450, 5, 900, 200); 
        Font font1 = new Font("Serif", Font.BOLD, 60);
        titleLabel.setForeground (Color.BLACK);
        titleLabel.setFont(font1);
        orderFrame.add(titleLabel);
        
        orderLabel = new JLabel("View Orders"); 
        orderLabel.setBounds(650, 170, 400, 200); 
        orderLabel.setForeground (Color.BLACK); 
        Font font2 = new Font("Serif", Font. BOLD, 50); 
        orderLabel.setFont(font2);
        orderFrame.add(orderLabel);
        
        
        /*enterPnrText = new JLabel("Enter UserName : ");
        enterPnrText.setBounds(400,300,500,40);
        enterPnrText.setForeground(Color.BLACK);
        Font font3 = new Font("Sierf", Font.BOLD,30);
        enterPnrText.setFont(font3);
        bookFrame.add(enterPnrText);
        
        pnrInput = new JTextField();
        pnrInput.setBounds(700,300,300,60);
        pnrInput.setFont(font3);
        pnrInput.requestFocus();
        bookFrame.add(pnrInput);*/
        

        viewButton = new JButton("View Orders"); 
        viewButton.setBounds (550, 400, 500, 80); 
        Font font4 = new Font("Sierf", Font.PLAIN,35);
        viewButton.setFont(font4);
        orderFrame.add(viewButton);

        backButton = new JButton("Back"); 
        backButton.setBounds (550, 550, 500, 80); 
        backButton.setFont(font4);
        orderFrame.add(backButton);
        
        viewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    //String user = pnrInput.getText();
                    tableModel = new DefaultTableModel();
                    //tableModel.setBounds(4000,4000);
                    historyTable = new JTable(tableModel);
                    historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 25));
                    historyTable.setFont(new Font("Arial", Font.PLAIN, 20));
                    
                    String[] columnNames = {"username","id", "name","days","cost","lastdate"};
                    tableModel.setColumnIdentifiers(columnNames);
                    
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM rent WHERE username = ?");
                    
                    stmt.setString(1, cu);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Object[] rowData = {rs.getString("username"), rs.getString("id"), rs.getString("name"),rs.getString("days"),rs.getString("cost"),rs.getString("lastdate")};
                        tableModel.addRow(rowData);
                    }
                    
                    
                    
                }catch(Exception e2){
                    JOptionPane.showMessageDialog(null,"View order Error");
                }
                
                scrollPane = new JScrollPane(historyTable);
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setPreferredSize(new Dimension(40, 0));
                add(scrollPane);
                pack();
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
            }
            
        });
        
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                orderFrame.dispose();
            }
        });
        
        orderFrame.setVisible(true);
        orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}