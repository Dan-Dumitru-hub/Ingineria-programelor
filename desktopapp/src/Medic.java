
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

class Medic extends JFrame {

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



    public Medic(String name, String cnp) {
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
                    .prepareStatement("Select * from pacienti_per_medici where cnp_medic=?  ");

            st.setString(1, cnp);
                //cnp_pacient


            ResultSet rs = st.executeQuery();
            int i=200;
            int j=200;
            while (rs.next()) {
                // dispose();

                System.out.println(rs);
                String cnppac = rs.getString("cnp_pacient");

                btnNewButton = new JButton(rs.getString("cnp_pacient"));
                btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
                btnNewButton.setBounds(i, j, 200, 100);
                btnNewButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        Medic_view_more frame = new Medic_view_more(name,cnp,cnppac);
                        frame.setVisible(true);

                    }
                });

                contentPane.add(btnNewButton);


                j+=100;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }





        btnNewButton2 = new JButton("Medicatie");
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton2.setBounds(700, 500, 400, 100);
        btnNewButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medicatie frame = new Medicatie(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton2);


        btnNewButton3 = new JButton("Alimentatie");
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton3.setBounds(700, 300, 400, 100);
        btnNewButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Alimentatie frame = new Alimentatie(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton3);




        btnNewButton4 = new JButton("adauga pacient in lista");
        btnNewButton4.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton4.setBounds(700, 200, 400, 100);
        btnNewButton4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medic_adauga_pacient frame = new Medic_adauga_pacient(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton4);

        btnNewButton5 = new JButton("delete pacient in lista");
        btnNewButton5.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton5.setBounds(700, 100, 400, 100);
        btnNewButton5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medic_delete_pacient frame = new Medic_delete_pacient(name,cnp);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton5);










        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}