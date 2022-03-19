
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

class Medic_delete_pacient extends JFrame {

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



    public Medic_delete_pacient(String name, String cnp) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Delete");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);



        JLabel lblUsername = new JLabel("CNP");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);




        btnNewButton3 = new JButton("Delete user");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton3.setBounds(350, 392, 200, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                String userName = textField.getText();

                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("delete from pacienti_per_medici  where cnp_medic =?  and cnp_pacient =?  ");
                    st.setString(1, cnp);
                    st.setString(2, userName);






                    ResultSet rs = st.executeQuery();
                    //while (rs.next() || !rs.next())
                    {
                        dispose();
                        System.out.println(rs);


                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
                Medic frame = new Medic(name,cnp);
                frame.setVisible(true);
            }
        });

        contentPane.add(btnNewButton3);



        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}