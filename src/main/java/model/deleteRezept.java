//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class deleteRezept extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static Connection conn = null;
    public static JButton einButton;
    public static JTextField nameTextField;
    public static JFrame rezeptFrame;
    public static Statement stmt = null;
    static String mySelectQuery;

    public deleteRezept() {
        String url = "jdbc:mysql://localhost/Kochbuch";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException | SQLException var7) {
            var7.printStackTrace();
            System.out.println("connection failed!!!");
        }

        rezeptFrame = new JFrame("Rezept löschen");
        rezeptFrame.setDefaultCloseOperation(2);
        ImageIcon img = new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\delete.png");
        rezeptFrame.setIconImage(img.getImage());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("     Name                  ");
        einButton = new JButton("Löschen");
        einButton.addActionListener(this);
        buttonPanel.add(einButton);
        nameTextField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        panel.add(namePanel);
        panel.add(buttonPanel);
        rezeptFrame.add(panel);
        rezeptFrame.setSize(350, 100);
        rezeptFrame.setLocationRelativeTo((Component)null);
        rezeptFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == einButton) {
            if (nameTextField.getText().equals("")) {
                System.out.println("fehlerhafte eingabe");
            } else {
                try {
                    stmt = conn.createStatement();
                    mySelectQuery = "Delete From hatzutaten Where hatzutaten.name = '" + nameTextField.getText() + "'";
                    stmt.executeUpdate(mySelectQuery);
                    mySelectQuery = "Delete From gericht Where gericht.name = '" + nameTextField.getText() + "'";
                    stmt.executeUpdate(mySelectQuery);
                    rezeptFrame.dispatchEvent(new WindowEvent(rezeptFrame, 201));
                } catch (SQLException var4) {
                    var4.printStackTrace();
                    System.out.println(mySelectQuery);
                    System.out.println("sql fault!!");
                    System.exit(-1);
                }
            }
        }

    }
}
