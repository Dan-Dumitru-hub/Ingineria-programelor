
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

class Pacient extends JFrame {

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



    public Pacient(String cnppac) {
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




        btnNewButton2 = new JButton("Medicatie");
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton2.setBounds(700, 500, 400, 100);
        btnNewButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Pacient_medicatie frame = new Pacient_medicatie(cnppac);
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
                Pacient_alimentatie frame = new Pacient_alimentatie(cnppac);
                frame.setVisible(true);



            }
        });

        contentPane.add(btnNewButton3);





        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from utilizatori where cnp=? ");
            st.setString(1, cnppac);

            PreparedStatement st1 = (PreparedStatement) connection
                    .prepareStatement("Select * from medicatii_per_pacienti  where cnp_pacient=? ");
            st1.setString(1, cnppac);

            PreparedStatement st2 = (PreparedStatement) connection
                    .prepareStatement("Select * from alimentatii_per_pacienti   where cnp_pacient=? ");
            st2.setString(1, cnppac);




            ResultSet rs = st.executeQuery();
            ResultSet rs1 = st1.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            int i=200;
            int j=50;
            while (rs.next() )
            {
                // dispose();

                System.out.println(rs);


                JLabel lblNewLabel3 = new JLabel("cnp:"+rs.getString("cnp"));
                lblNewLabel3.setForeground(Color.BLACK);
                lblNewLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel3.setBounds(i, j, 350, 93);

                contentPane.add(lblNewLabel3);


                JLabel lblNewLabel5 = new JLabel("nume:"+rs.getString("nume"));
                lblNewLabel5.setForeground(Color.BLACK);
                lblNewLabel5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel5.setBounds(i, j+50, 350, 93);

                contentPane.add(lblNewLabel5);


                JLabel lblNewLabel6 = new JLabel("prenume:"+rs.getString("prenume"));
                lblNewLabel6.setForeground(Color.BLACK);
                lblNewLabel6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel6.setBounds(i, j+100, 350, 93);

                contentPane.add(lblNewLabel6);
                j+=100;
            }


            while (rs1.next() )
            {//j+=100;
                // dispose();

                System.out.println(rs);
                JLabel lblNewLabel55 = new JLabel("Medicatie: ");
                lblNewLabel55.setForeground(Color.BLACK);
                lblNewLabel55.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel55.setBounds(i, j+50, 273, 93);

                contentPane.add(lblNewLabel55);

                JLabel lblNewLabel1 = new JLabel("nume medicatie: "+rs1.getString("nume_medicatie"));
                lblNewLabel1.setForeground(Color.BLACK);
                lblNewLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel1.setBounds(i, j+100, 273, 93);

                contentPane.add(lblNewLabel1);

                JLabel lblNewLabel2 = new JLabel("doza_medicatie:"+rs1.getString("doza_medicatie"));
                lblNewLabel2.setForeground(Color.BLACK);
                lblNewLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel2.setBounds(i, j+150, 350, 93);

                contentPane.add(lblNewLabel2);
                JLabel lblNewLabel4 = new JLabel("frecventa_administrare:"+rs1.getString("frecventa_administrare"));
                lblNewLabel4.setForeground(Color.BLACK);
                lblNewLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel4.setBounds(i, j+200, 350, 93);

                contentPane.add(lblNewLabel4);
                j+=200;
            } while (rs2.next() )
            {//j+=100;
                // dispose();

                System.out.println(rs);

                JLabel lblNewLabel7 = new JLabel("alimentatie:"+rs2.getString("alimentatie"));
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