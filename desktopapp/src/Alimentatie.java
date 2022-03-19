
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

class Alimentatie extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnNewButton2;
    private JButton btnNewButton3;//vezi medicatie generala
    private JButton btnNewButton4;//vezi alimentatie
    private JButton btnNewButton5;//vezi alimentatie
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */



    public Alimentatie(String name, String cnp) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Dashboard");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from alimentatii_per_pacienti   ");

            // st.setString(1, cnp);



            ResultSet rs = st.executeQuery();
            int i=0;
            int j=200;
            while (rs.next()) {
                // dispose();

                System.out.println(rs);


                JLabel lblNewLabel1 = new JLabel(rs.getString("cnp_pacient"));
                lblNewLabel1.setForeground(Color.BLACK);
                lblNewLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel1.setBounds(i, j, 300, 93);

                JLabel lblNewLabel2 = new JLabel(rs.getString("alimentatie"));
                lblNewLabel2.setForeground(Color.BLACK);
                lblNewLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel2.setBounds(i + 300, j, 350, 93);

                contentPane.add(lblNewLabel1);
                contentPane.add(lblNewLabel2);

                // JOptionPane.showMessageDialog(null,rs.getString("nume") );
                j+=100;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }





        btnNewButton2 = new JButton("adauga");
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton2.setBounds(700, 500, 400, 100);
        btnNewButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Alimentatie_add frame = new Alimentatie_add(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton2);


        btnNewButton3 = new JButton("Sterge alimentatie");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton3.setBounds(700, 300, 400, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Alimentatie_delete frame = new Alimentatie_delete(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton3);




        btnNewButton5 = new JButton("go back");
        btnNewButton5.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton5.setBounds(700, 100, 400, 100);
        btnNewButton5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medic frame = new Medic(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton5);










        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}