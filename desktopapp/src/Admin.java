
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class Admin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnNewButton2;
    private JButton btnNewButton3;
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */



    public Admin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Dashboard");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

/*
        JLabel lblUsername = new JLabel("Delete_user");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("add_user");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);*/

        btnNewButton = new JButton("List");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(150, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //String userName = textField.getText();
                //String password = passwordField.getText();








                   // while (rs.next())
                    {
                        dispose();



                        Admin_view frame = new Admin_view();
                        frame.setVisible(true);
                    }

            }
        });

        contentPane.add(btnNewButton);

        btnNewButton2 = new JButton("Adauga_user");
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton2.setBounds(250, 286, 200, 100);
        btnNewButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin_add frame = new Admin_add();
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton2);


        btnNewButton3 = new JButton("Delete user");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton3.setBounds(350, 392, 200, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin_delete frame = new Admin_delete();
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton3);






        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}