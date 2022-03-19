
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

class Pacient_alimentatie extends JFrame {

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



    public Pacient_alimentatie(String cnppac) {
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

        btnNewButton5 = new JButton("go back");
        btnNewButton5.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton5.setBounds(700, 100, 400, 100);
        btnNewButton5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Pacient frame = new Pacient(cnppac);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton5);







        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");



            PreparedStatement st1 = (PreparedStatement) connection
                    .prepareStatement("Select * from alimentatii_per_pacienti  where cnp_pacient=? ");
            st1.setString(1, cnppac);






            ResultSet rs1 = st1.executeQuery();

            int i=200;
            int j=50;


            while (rs1.next() )
            {//j+=100;
                // dispose();

              //  System.out.println(rs);

                JLabel lblNewLabel7 = new JLabel("alimentatie:"+rs1.getString("alimentatie"));
                lblNewLabel7.setForeground(Color.BLACK);
                lblNewLabel7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel7.setBounds(i, j+50, 350, 93);

                contentPane.add(lblNewLabel7);

                // JOptionPane.showMessageDialog(null,rs.getString("nume") );
                j+=100;
            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }











        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}