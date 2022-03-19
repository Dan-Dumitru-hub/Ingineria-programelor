
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

class Medic_view_more extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton5;
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */



    public Medic_view_more(String name, String cnpmed,String cnppac) {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnNewButton5 = new JButton("go back");
        btnNewButton5.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton5.setBounds(700, 100, 400, 100);
        btnNewButton5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                Medic frame = new Medic(name,cnpmed);
                frame.setVisible(true);


            }
        });

        contentPane.add(btnNewButton5);

        JLabel lblNewLabel = new JLabel("lista ");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

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
/*
        btnNewButton = new JButton("Listaadmin");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //String userName = textField.getText();
                //String password = passwordField.getText();
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DB19c", "scott", "tiger");

                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement("Select * from utilizatori ");





                    ResultSet rs = st.executeQuery();
                    while (rs.next()) {
                        // dispose();
                        int i=400;
                        int j=10;
                        System.out.println(rs);


                        JLabel lblNewLabel = new JLabel(rs.getString("nume"));
                        lblNewLabel.setForeground(Color.BLACK);
                        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
                        lblNewLabel.setBounds(++i, ++j, 273, 93);
                        contentPane.add(lblNewLabel);

                        JOptionPane.showMessageDialog(null,rs.getString("nume") );
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
*/
        // contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}