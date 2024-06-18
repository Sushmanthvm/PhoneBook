import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;

public class PhoneBookUI extends JFrame {
    private JFormattedTextField formattedTextField1;
    private JButton addButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JPanel MainPanel;
    private JFormattedTextField formattedTextField2;
    private JLabel output;


    public PhoneBookUI(){
        setContentPane(MainPanel);
        setTitle("PhoneBook");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);



        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "admin";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Connection finalConn = conn;
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String name = formattedTextField1.getText();
                String phone = formattedTextField2.getText();

                TenDigitNumber validNumber = null;
                try {
                    validNumber = new TenDigitNumber(phone);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(PhoneBookUI.this,e.getMessage());
                }
                try {
                    JOptionPane.showMessageDialog(PhoneBookUI.this,PhoneBook.insertRecord(finalConn, name, validNumber));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }



            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String name = formattedTextField1.getText();

                ResultSet rs;
                try {
                    rs = PhoneBook.findRecord(finalConn, name);
                    String out="";
                    while (rs.next()) {
                        out+="Name: "+rs.getString("name")+"\n"+"Phone No: "+rs.getString("contact_no");
                    }
                    JOptionPane.showMessageDialog(PhoneBookUI.this,out);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }



            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String name = formattedTextField1.getText();
                String phone = formattedTextField2.getText();
                TenDigitNumber validNumber = null;
                try {
                    validNumber = new TenDigitNumber(phone);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(PhoneBookUI.this,e.getMessage());
                }
                try {
                    JOptionPane.showMessageDialog(PhoneBookUI.this,PhoneBook.updateRecord(finalConn, name, validNumber));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }



            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String name = formattedTextField1.getText();
                try {
                    JOptionPane.showMessageDialog(PhoneBookUI.this,PhoneBook.deleteRecord(finalConn, name));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String out = "";
                try {
                    ResultSet rs = PhoneBook.viewRecord(finalConn);
                    while (rs.next()) {
                        out+="Name: "+rs.getString("name")+"\n"+"Phone No: "+rs.getString("contact_no")+"\n\n";
                    }

                    JOptionPane.showMessageDialog(PhoneBookUI.this,out);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }


}
