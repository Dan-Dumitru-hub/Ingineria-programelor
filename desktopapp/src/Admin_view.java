
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Admin_view extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton5;
    private JButton btnNewButton6;
    private JLabel label;
    private JPanel contentPane;


    /**
     * Launch the application.
     */


/*
    public Admin_view() {

        setTitle("JPanel with JScrollPane");
        setLayout(new FlowLayout());
        //setJPanelandComponent();

        // JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
       // final JFrame frame = new JFrame("JScrollbar Demo");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //setContentPane(contentPane);
        contentPane.setLayout(null);

        btnNewButton5 = new JButton("go back");
        btnNewButton5.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton5.setBounds(700, 100, 400, 100);
        btnNewButton5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin frame = new Admin();
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton5);

        JLabel lblNewLabel = new JLabel("lista useri");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from utilizatori ");





            ResultSet rs = st.executeQuery();
            int i=200;
            int j=1000;
            while (rs.next()) {
                // dispose();

                System.out.println(rs);
                String cnp = rs.getString("cnp");

                btnNewButton6 = new JButton(rs.getString("cnp"));
                btnNewButton6.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnNewButton6.setBounds(i, j, 200, 100);
                btnNewButton6.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        Admin_view_more frame = new Admin_view_more(cnp);
                        frame.setVisible(true);

                    }
                });

                contentPane.add(btnNewButton6);


                j+=100;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        JScrollPane js = new JScrollPane(contentPane,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setPreferredSize(new Dimension(1000,1000));
        add(js);
        setSize(1000,1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }*/
    Admin_view()
    {
        setTitle("");
        setLayout(new FlowLayout());
        setJPanelandComponent();
        setSize(700,350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setJPanelandComponent()
    {
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(400,400));
        btnNewButton5 = new JButton("go back");
        btnNewButton5.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton5.setBounds(700, 100, 400, 100);
        btnNewButton5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin frame = new Admin();
                frame.setVisible(true);


            }
        });

        jp.add(btnNewButton5);


        JLabel lblNewLabel = new JLabel("lista useri");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 700, 93);
        jp.add(lblNewLabel);


        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from utilizatori ");


            ResultSet rs = st.executeQuery();
            int i=200;
            int j=1000;
            while (rs.next()) {
                // dispose();

                System.out.println(rs);
                String cnp = rs.getString("cnp");

                btnNewButton6 = new JButton(rs.getString("cnp"));
                btnNewButton6.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnNewButton6.setBounds(i, j, 200, 100);
                btnNewButton6.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        Admin_view_more frame = new Admin_view_more(cnp);
                        frame.setVisible(true);

                    }
                });

                jp.add(btnNewButton6);


                j+=100;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }


        JScrollPane js = new JScrollPane(jp,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        js.setPreferredSize(new Dimension(800,800));
        add(js);
    }
}

