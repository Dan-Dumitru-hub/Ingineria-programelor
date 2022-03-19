
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

class Medic_adauga_medicatie extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textFieldfrecventa;
    private JTextField textFieldtip;
    private JTextField textFieldtip2;
    private JLabel label;
    private JPanel contentPane;
    private JButton btnNewButton3;

    /**
     * Launch the application.
     */



    public Medic_adauga_medicatie (String name, String cnp) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Medic");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 70, 281, 68);
        contentPane.add(textField);
        textField.setColumns(16);

        textFieldtip = new JTextField();
        textFieldtip.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textFieldtip.setBounds(481, 370, 281, 68);
        contentPane.add(textFieldtip);
        textField.setColumns(16);

        textFieldtip2 = new JTextField();
        textFieldtip2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textFieldtip2.setBounds(481, 270, 281, 68);
        contentPane.add(textFieldtip2);
        textFieldtip2.setColumns(16);


        textFieldfrecventa = new JTextField();
        textFieldfrecventa.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textFieldfrecventa.setBounds(481, 170, 281, 68);
        contentPane.add(textFieldfrecventa);
        textFieldfrecventa.setColumns(16);



        JLabel lblUsername = new JLabel("cnp");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 66, 193, 52);
        contentPane.add(lblUsername);





        JLabel lblPassword2 = new JLabel("medicatie");
        lblPassword2.setForeground(Color.BLACK);
        lblPassword2.setBackground(Color.CYAN);
        lblPassword2.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword2.setBounds(250, 386, 193, 52);
        contentPane.add(lblPassword2);

        JLabel lblPassword3 = new JLabel("doza");
        lblPassword3.setForeground(Color.BLACK);
        lblPassword3.setBackground(Color.CYAN);
        lblPassword3.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword3.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword3);

        JLabel lblPassword4 = new JLabel("frecventa");
        lblPassword4.setForeground(Color.BLACK);
        lblPassword4.setBackground(Color.CYAN);
        lblPassword4.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword4.setBounds(250, 186, 193, 52);
        contentPane.add(lblPassword4);



        btnNewButton3 = new JButton("Add medicatie to pacient");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton3.setBounds(350, 200, 200, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {



                String userName = textField.getText(); //cnp
                // String cnp = textFieldcnp.getText();
                String tip = textFieldtip.getText(); // medicatie
                String frecventa = textFieldfrecventa.getText();
                String doza = textFieldtip2.getText();
                // String password = passwordField.getText();
                //v System.out.println(rs);
                // System.out.println(password);
                System.out.println(cnp);
                System.out.println(userName);

                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("INSERT INTO medicatii_per_pacienti  VALUES(?, ? ,?,?) ");

                    //INSERT INTO utilizatori VALUES INSERT INTO medicatii VALUES
                    //('pizdocalmin', 100);
                    //(1941024152511, 'admin', 'Administrescu', NULL, NULL, NULL, 'admin', 'admin', NULL); delete from utilizatori where nume_utilizator =?  and parola =?
                    //st.setString(1, cnp);
                    st.setString(1, userName);
                    st.setString(2, tip);
                    st.setString(3, doza);
                    st.setString(4, frecventa);

                    //st.setString(4, password);
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
                Medicatie frame = new Medicatie(name,cnp);
                frame.setVisible(true);
            }
        });

        contentPane.add(btnNewButton3);



        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}