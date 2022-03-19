
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

class Admin_view_more extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton5;
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */



    public Admin_view_more(String cnp) {


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
                Admin frame = new Admin();
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
            st.setString(1, cnp);




            ResultSet rs = st.executeQuery();
            int i=200;
            int j=50;
            while (rs.next())
            {
                // dispose();

                System.out.println(rs);


                JLabel lblNewLabel1 = new JLabel("nume_utlizator: "+rs.getString("nume_utilizator"));
                lblNewLabel1.setForeground(Color.BLACK);
                lblNewLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel1.setBounds(i, j, 273, 93);

                contentPane.add(lblNewLabel1);

                JLabel lblNewLabel2 = new JLabel("parola: "+rs.getString("parola"));
                lblNewLabel2.setForeground(Color.BLACK);
                lblNewLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel2.setBounds(i, j+50, 350, 93);

                contentPane.add(lblNewLabel2);

                JLabel lblNewLabel3 = new JLabel("cnp: "+rs.getString("cnp"));
                lblNewLabel3.setForeground(Color.BLACK);
                lblNewLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel3.setBounds(i, j+100, 350, 93);

                contentPane.add(lblNewLabel3);

                JLabel lblNewLabel4 = new JLabel("tip_utilizator: "+rs.getString("tip_utilizator"));
                lblNewLabel4.setForeground(Color.BLACK);
                lblNewLabel4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel4.setBounds(i, j+150, 350, 93);

                contentPane.add(lblNewLabel4);

                JLabel lblNewLabel5 = new JLabel("nume: "+rs.getString("nume"));
                lblNewLabel5.setForeground(Color.BLACK);
                lblNewLabel5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel5.setBounds(i, j+200, 350, 93);

                contentPane.add(lblNewLabel5);


                JLabel lblNewLabel6 = new JLabel("prenume: "+rs.getString("prenume"));
                lblNewLabel6.setForeground(Color.BLACK);
                lblNewLabel6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblNewLabel6.setBounds(i, j+250, 350, 93);

                contentPane.add(lblNewLabel6);

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