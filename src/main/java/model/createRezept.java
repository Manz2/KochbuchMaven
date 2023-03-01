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
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class createRezept extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static Connection conn = null;
    public static JButton einButton;
    public static JButton bildButton;
    public static JTextField nameTextField;
    public static JTextField zutatenTextField;
    public static String BildPath;
    public static JFrame rezeptFrame;
    public static Statement stmt = null;
    static String mySelectQuery;

    public createRezept() {
        String url = "jdbc:mysql://localhost/Kochbuch";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException | SQLException var10) {
            var10.printStackTrace();
            System.out.println("connection failed!!!");
        }

        BildPath = "false";
        rezeptFrame = new JFrame("Rezept einfügen");
        rezeptFrame.setDefaultCloseOperation(2);
        ImageIcon img = new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\ico.png");
        rezeptFrame.setIconImage(img.getImage());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JPanel zutatenPanel = new JPanel();
        zutatenPanel.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("     Name                  ");
        JLabel zutatenLabel = new JLabel("     Zutaten               ");
        einButton = new JButton("Einfügen");
        einButton.addActionListener(this);
        bildButton = new JButton("Bild auswählen");
        bildButton.addActionListener(this);
        nameTextField = new JTextField(20);
        zutatenTextField = new JTextField(20);
        JLabel emptyLabel = new JLabel("        ");
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        zutatenPanel.add(zutatenLabel);
        zutatenPanel.add(zutatenTextField);
        buttonPanel.add(bildButton);
        buttonPanel.add(emptyLabel);
        buttonPanel.add(einButton);
        panel.add(namePanel);
        panel.add(zutatenPanel);
        panel.add(buttonPanel);
        rezeptFrame.add(panel);
        rezeptFrame.setSize(350, 150);
        rezeptFrame.setLocationRelativeTo((Component)null);
        rezeptFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        int res;
        if (source == bildButton) {
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", new String[]{"jpg", "png"});
            file.addChoosableFileFilter(filter);
            res = file.showSaveDialog((Component)null);
            if (res == 0) {
                File selFile = file.getSelectedFile();
                BildPath = selFile.getAbsolutePath();
            }
        } else if (source == einButton) {
            if (!BildPath.equals("false") && !nameTextField.getText().equals("") && !zutatenTextField.getText().equals("")) {
                try {
                    stmt = conn.createStatement();
                    BildPath = BildPath.replace("\\", "\\\\");
                    mySelectQuery = "INSERT IGNORE INTO gericht values('" + nameTextField.getText() + "','" + BildPath + "')";
                    stmt.executeUpdate(mySelectQuery);
                    String[] zutatenArray = zutatenTextField.getText().split(";");
                    String[] var7 = zutatenArray;
                    int var11 = zutatenArray.length;

                    for(res = 0; res < var11; ++res) {
                        String string = var7[res];
                        mySelectQuery = "INSERT IGNORE INTO zutaten values('" + string + "')";
                        stmt.executeUpdate(mySelectQuery);
                        mySelectQuery = "INSERT IGNORE INTO hatZutaten values('" + nameTextField.getText() + "','" + string + "')";
                        stmt.executeUpdate(mySelectQuery);
                    }

                    rezeptFrame.dispatchEvent(new WindowEvent(rezeptFrame, 201));
                } catch (SQLException var8) {
                    var8.printStackTrace();
                    System.out.println(mySelectQuery);
                    System.out.println("sql fault!!");
                    System.exit(-1);
                }
            } else {
                System.out.println("fehlerhafte eingabe");
            }
        }

    }
}
