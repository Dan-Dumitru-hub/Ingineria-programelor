
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

class Admin_delete extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;
    private JButton btnNewButton3;

    /**
     * Launch the application.
     */



    public Admin_delete() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Admin");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);
/*
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);*/

        JLabel lblUsername = new JLabel("cnp:");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        /*
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);*/



        btnNewButton3 = new JButton("Delete user");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton3.setBounds(350, 392, 200, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                String cnp = textField.getText();
               // String password = passwordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

                    PreparedStatement statement = connection.prepareStatement("DELETE FROM Pacienti_per_medici p " +
                            "WHERE p.cnp_medic = ? OR p.cnp_pacient = ?");
                    statement.setString(1, (cnp));
                    statement.setString(2, (cnp));
                    ResultSet rs1 = statement.executeQuery();
                  //  statement.executeUpdate();
                    //statement.close();

                    statement = connection.prepareStatement("DELETE FROM Medicatii_per_pacienti m " +
                            "WHERE m.cnp_pacient = ?");
                    statement.setString(1,(cnp));
                    ResultSet rs2 = statement.executeQuery();
                    //statement.executeUpdate();
                   // statement.close();

                    statement = connection.prepareStatement("DELETE FROM Alimentatii_per_pacienti a " +
                            "WHERE a.cnp_pacient = ?");
                    statement.setString(1, (cnp));
                    ResultSet rs3 = statement.executeQuery();
                    //statement.executeUpdate();
                    //statement.close();

                    statement = connection.prepareStatement("DELETE FROM Utilizatori WHERE cnp = ?");
                    statement.setString(1, (cnp));
                    ResultSet rs4 = statement.executeQuery();
                    dispose();
                    Admin frame = new Admin();
                    frame.setVisible(true);
                    //statement.executeUpdate();
                   // statement.close();
                     //while (rs.next() || !rs.next())
                    {
                         dispose();
                       /// System.out.println(rs);


                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
                dispose();
                Admin frame = new Admin();
                frame.setVisible(true);
            }
        });

        contentPane.add(btnNewButton3);



        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}