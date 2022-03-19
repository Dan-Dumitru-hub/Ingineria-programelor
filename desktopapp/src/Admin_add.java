
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

class Admin_add extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textFieldtip;
    private JTextField textFieldnume;
    private JTextField textFieldprenume;
    private JTextField textFieldcnp;
    private JTextField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;
    private JButton btnNewButton3;

    /**
     * Launch the application.
     */



    public Admin_add () {
        int size=20;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(true);
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
        textField.setFont(new Font("Tahoma", Font.PLAIN, size));
        textField.setBounds(481, 70, 281, 50);
        contentPane.add(textField);
        textField.setColumns(16);

        textFieldtip = new JTextField();
        textFieldtip.setFont(new Font("Tahoma", Font.PLAIN, size));
        textFieldtip.setBounds(481, 370, 281, 50);
        contentPane.add(textFieldtip);
        textField.setColumns(16);

        textFieldcnp = new JTextField();
        textFieldcnp.setFont(new Font("Tahoma", Font.PLAIN, size));
        textFieldcnp.setBounds(481, 270, 281, 50);
        contentPane.add(textFieldcnp);
        textFieldcnp.setColumns(16);

        textFieldnume = new JTextField();
        textFieldnume.setFont(new Font("Tahoma", Font.PLAIN, size));
        textFieldnume.setBounds(481, 470, 281, 50);
        contentPane.add(textFieldnume);
        textFieldnume.setColumns(16);

        textFieldprenume = new JTextField();
        textFieldprenume.setFont(new Font("Tahoma", Font.PLAIN, size));
        textFieldprenume.setBounds(481, 570, 281, 50);
        contentPane.add(textFieldprenume);
        textFieldprenume.setColumns(16);

        passwordField = new JTextField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, size));
        passwordField.setBounds(481, 186, 281, 50);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, size));
        lblUsername.setBounds(250, 66, 193, 50);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, size));
        lblPassword.setBounds(250, 186, 193, 50);
        contentPane.add(lblPassword);

        JLabel lblPassword1 = new JLabel("CNP");
        lblPassword1.setForeground(Color.BLACK);
        lblPassword1.setBackground(Color.CYAN);
        lblPassword1.setFont(new Font("Tahoma", Font.PLAIN, size));
        lblPassword1.setBounds(250, 286, 193, 50);
        contentPane.add(lblPassword1);

        JLabel lblPassword2 = new JLabel("tip_user");
        lblPassword2.setForeground(Color.BLACK);
        lblPassword2.setBackground(Color.CYAN);
        lblPassword2.setFont(new Font("Tahoma", Font.PLAIN, size));
        lblPassword2.setBounds(250, 386, 193, 50);
        contentPane.add(lblPassword2);

        JLabel lblPassword4 = new JLabel("nume");
        lblPassword4.setForeground(Color.BLACK);
        lblPassword4.setBackground(Color.CYAN);
        lblPassword4.setFont(new Font("Tahoma", Font.PLAIN, size));
        lblPassword4.setBounds(250, 486, 193, 50);
        contentPane.add(lblPassword4);

        JLabel lblPassword5 = new JLabel("prenume");
        lblPassword5.setForeground(Color.BLACK);
        lblPassword5.setBackground(Color.CYAN);
        lblPassword5.setFont(new Font("Tahoma", Font.PLAIN, size));
        lblPassword5.setBounds(250, 586, 193, 50);
        contentPane.add(lblPassword5);


        btnNewButton3 = new JButton("Add user");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, size));
        btnNewButton3.setBounds(350, 700, 200, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {



                String userName = textField.getText();
                String nume = textFieldnume.getText();
                String prenume = textFieldprenume.getText();
                String cnp = textFieldcnp.getText();
                String tip = textFieldtip.getText();
                String password = passwordField.getText();
                //v System.out.println(rs);
                System.out.println(password);
                System.out.println(cnp);
                System.out.println(userName);

                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("INSERT INTO utilizatori VALUES(?, ?,?,  ?, NULL, NULL, ?, ?, NULL) ");

                    //INSERT INTO utilizatori VALUES
                    //(1941024152511, 'admin', 'Administrescu', NULL, NULL, NULL, 'admin', 'admin', NULL); delete from utilizatori where nume_utilizator =?  and parola =?

                   /*
                   CREATE TABLE utilizatori (
	CNP				NUMBER(13),                      1
	nume_utilizator	VARCHAR2(50) NOT NULL UNIQUE,    2
	nume			VARCHAR2(50),                    3
	prenume			VARCHAR2(50),                    4
	email			VARCHAR2(50),                     5
	telefon			NUMBER(10),                      6
	tip_utilizator	VARCHAR2(10),                    7
	parola			VARCHAR2(20),                    8
	salt			VARCHAR2(20),                    9
	CONSTRAINT pk1_utilizatori PRIMARY KEY (cnp)
);
                   * */


                    st.setString(1, cnp);
                    st.setString(2, userName);
                    st.setString(3, nume);

                    st.setString(4, prenume);
                    st.setString(5, tip);
                    st.setString(6, password);

                    //System.out.println(st.);




                    ResultSet rs = st.executeQuery();
                    System.out.println(rs);
                  //  while (rs.next() )
                    {
                         dispose();




                    }
                } catch (SQLException sqlException) {
                    System.out.println("yes");
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