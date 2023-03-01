//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.createRezept;
import model.deleteRezept;

public class GUI extends JFrame implements ActionListener {
    public static JMenuItem menuItem;
    public static JMenuItem menuItem1;
    public static JMenuItem menuItem2;
    public static JMenuItem menuItem3;
    public static JMenuItem nameSuchenMenu;
    public static JMenuItem zutatenSuchenMenu;
    public static JMenu menu;
    public static JMenu suchen;
    public static JMenuBar menuBar;
    public static JFileChooser fc;
    public static File picture;
    public static File einkaufsliste;
    public static JFrame frame;
    public static JPanel panel;
    public static JPanel picPanel;
    public static JLabel picLabel;
    public static Connection conn = null;
    public static BufferedReader in;
    public static Statement stmt;
    public static ResultSet rset;
    public static JPanel[] panelArray;
    public static JList[] zutatenListArray;
    public static JScrollPane[] scrPaneArray;
    public static JLabel[] labelArray;
    public static JButton[] buttonArray;
    public static JLabel[] nameLabelArray;
    public static String[] bilderArray;
    public static int total;
    static LinkedList<String> einkaufenListe;

    static {
        in = new BufferedReader(new InputStreamReader(System.in));
        stmt = null;
        rset = null;
        total = 0;
    }

    public GUI() {
        frame = new JFrame("Kochbuch");
        frame.setDefaultCloseOperation(3);
        String url = "jdbc:mysql://localhost/Kochbuch";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException | SQLException var4) {
            var4.printStackTrace();
            System.out.println("connection failed!!!");
        }

        this.selectAll();
        einkaufenListe = new LinkedList();
        ImageIcon img = new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\ico.png");
        frame.setIconImage(img.getImage());
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menu = new JMenu("Menü");
        menuBar.add(menu);
        suchen = new JMenu("Suchen");
        menuBar.add(suchen);
        menuItem = new JMenuItem("Neues Rezept einfügen", new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\icon.png"));
        menu.add(menuItem);
        menu.addSeparator();
        menuItem.addActionListener(this);
        menuItem1 = new JMenuItem("Aktualisieren", new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\refresh.png"));
        menu.add(menuItem1);
        menu.addSeparator();
        menuItem1.addActionListener(this);
        menuItem2 = new JMenuItem("Rezept löschen", new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\delete.png"));
        menu.add(menuItem2);
        menu.addSeparator();
        menuItem2.addActionListener(this);
        menuItem3 = new JMenuItem("Einkaufsliste speichern", new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\liste.png"));
        menu.add(menuItem3);
        menuItem3.addActionListener(this);
        nameSuchenMenu = new JMenuItem("Nach Name suchen");
        suchen.add(nameSuchenMenu);
        suchen.addSeparator();
        nameSuchenMenu.addActionListener(this);
        zutatenSuchenMenu = new JMenuItem("Nach Zutaten suchen");
        suchen.add(zutatenSuchenMenu);
        zutatenSuchenMenu.addActionListener(this);
        JScrollPane scrPane = new JScrollPane(panel);
        scrPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrPane);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo((Component)null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String Path = "C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\enkaufsliste.txt";
        if (source == menuItem) {
            new createRezept();
        } else if (source == menuItem1) {
            frame.dispose();
            new GUI();
        } else if (source == menuItem2) {
            new deleteRezept();
        } else if (source == menuItem3) {
            JFileChooser file = new JFileChooser();
            int res = file.showSaveDialog((Component)null);
            if (res == 0) {
                File selFile = file.getSelectedFile();
                Path = selFile.getAbsolutePath();
            }

            einkaufsliste = new File(Path);

            try {
                einkaufsliste.createNewFile();
                FileWriter myWriter = new FileWriter(Path);
                Iterator var8 = einkaufenListe.iterator();

                while(var8.hasNext()) {
                    String string = (String)var8.next();
                    myWriter.write(string + "\n");
                }

                myWriter.close();
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        }

    }

    public ImageIcon resize(String imgPath) {
        ImageIcon path = new ImageIcon(imgPath);
        Image img = path.getImage();
        Image newImg = img.getScaledInstance(250, 250, 1);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public ImageIcon resizeBig(String imgPath) {
        ImageIcon path = new ImageIcon(imgPath);
        Image img = path.getImage();
        Image newImg = img.getScaledInstance(700, 700, 1);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void selectAll() {
        try {
            stmt = conn.createStatement();
            String mySelectQuery = "SELECT count(*) AS total FROM gericht";

            for(rset = stmt.executeQuery(mySelectQuery); rset.next(); total = rset.getInt("total")) {
            }

            panelArray = new JPanel[total];
            labelArray = new JLabel[total];
            buttonArray = new JButton[total];
            nameLabelArray = new JLabel[total];
            zutatenListArray = new JList[total];
            scrPaneArray = new JScrollPane[total];
            panel = new JPanel();
            panel.setLayout(new GridLayout(total, 1));
            mySelectQuery = "SELECT * FROM gericht";
            rset = stmt.executeQuery(mySelectQuery);

            for(int counter = 0; rset.next(); ++counter) {
                String name = rset.getString("name");
                final String bild = rset.getString("Bild");
                panelArray[counter] = new JPanel();
                panelArray[counter].setLayout(new GridLayout(1, 3));
                panelArray[counter].setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
                labelArray[counter] = new JLabel();
                buttonArray[counter] = new JButton(this.resize(bild));
                buttonArray[counter].setBorder(BorderFactory.createEmptyBorder());
                buttonArray[counter].setContentAreaFilled(false);
                buttonArray[counter].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame rezeptFrame = new JFrame("");
                        rezeptFrame.setDefaultCloseOperation(2);
                        ImageIcon img = new ImageIcon("C:\\Users\\User\\Documents\\Kochbuch\\Kochbuch\\Pictures\\ico.png");
                        rezeptFrame.setIconImage(img.getImage());
                        JLabel picLabel = new JLabel(GUI.this.resizeBig(bild));
                        rezeptFrame.add(picLabel);
                        rezeptFrame.setSize(700, 700);
                        rezeptFrame.setLocationRelativeTo((Component)null);
                        rezeptFrame.setVisible(true);
                    }
                });
                int total2 = 0;
                Statement stmt1 = conn.createStatement();
                String mySelectQuery2 = "SELECT count(*) AS total FROM hatZutaten where hatzutaten.name ='" + name + "'";

                ResultSet rset2;
                for(rset2 = stmt1.executeQuery(mySelectQuery2); rset2.next(); total2 = rset2.getInt("total")) {
                }

                if (total2 != 0) {
                    mySelectQuery2 = "SELECT zutatenName FROM hatZutaten where hatzutaten.name ='" + name + "'";
                    rset2 = stmt1.executeQuery(mySelectQuery2);
                    String[] zutaten = new String[total2];

                    for(int counter2 = 0; rset2.next(); ++counter2) {
                        String zutatenName = rset2.getString("ZutatenName");
                        zutaten[counter2] = zutatenName;
                    }

                    zutatenListArray[counter] = new JList(zutaten);
                    zutatenListArray[counter].setFont(new Font("Dialog", 0, 17));
                    zutatenListArray[counter].setBackground(Color.lightGray);
                    zutatenListArray[counter].addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            JList list = (JList)evt.getSource();
                            if (evt.getClickCount() == 2) {
                                int index = list.locationToIndex(evt.getPoint());
                                String out = (String)list.getModel().getElementAt(index);
                                GUI.einkaufenListe.add(out);
                            }

                        }
                    });
                }

                nameLabelArray[counter] = new JLabel(name);
                nameLabelArray[counter].setFont(new Font("Dialog", 0, 17));
                panelArray[counter].add(buttonArray[counter]);
                panelArray[counter].add(nameLabelArray[counter]);
                panelArray[counter].add(new JScrollPane(zutatenListArray[counter]));
                panel.add(panelArray[counter]);
            }
        } catch (SQLException var12) {
            var12.printStackTrace();
            System.out.println("sql fault!!");
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        new GUI();
    }
}
