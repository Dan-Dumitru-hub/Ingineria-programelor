
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

class Medicatie extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnNewButton2;
    private JButton btnNewButton3;//vezi medicatie generala
    private JButton btnNewButton6;//vezi alimentatie
    private JButton btnNewButton5;//vezi alimentatie
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */



    public Medicatie(String name, String cnp) {
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
                    .prepareStatement("Select * from medicatii  ");

           // st.setString(1, cnp);



            ResultSet rs = st.executeQuery();
            int i=200;
            int j=200;
            while (rs.next()) {
                // dispose();

                System.out.println(rs);


                JLabel lblNewLabel1 = new JLabel(rs.getString("nume"));
                lblNewLabel1.setForeground(Color.BLACK);
                lblNewLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel1.setBounds(i, j, 350, 93);

                contentPane.add(lblNewLabel1);


                JLabel lblNewLabel2 = new JLabel(rs.getString("doza"));
                lblNewLabel2.setForeground(Color.BLACK);
                lblNewLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel2.setBounds(i+300, j, 350, 93);

                contentPane.add(lblNewLabel2);

                // JOptionPane.showMessageDialog(null,rs.getString("nume") );
                j+=100;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }





        btnNewButton2 = new JButton("adauga medicatie generala");
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton2.setBounds(700, 500, 400, 100);
        btnNewButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medicatie_adauga frame = new Medicatie_adauga(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton2);

        btnNewButton6 = new JButton("adauga medicatie la un pacient");
        btnNewButton6.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton6.setBounds(700, 400, 400, 100);
        btnNewButton6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medic_adauga_medicatie frame = new Medic_adauga_medicatie(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton6);


        btnNewButton3 = new JButton("Sterge medicatie generala");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton3.setBounds(700, 300, 400, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medicatie_delete frame = new Medicatie_delete(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton3);

        btnNewButton = new JButton("Sterge medicatie unui pacient");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(700, 600, 400, 100);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medic_sterge_medicatie frame = new Medic_sterge_medicatie(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton);




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