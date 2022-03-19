
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
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the frame.
     */
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();
                try {

                      final HttpClient httpClient = HttpClient.newBuilder()
                            .authenticator(new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(
                                            userName,
                                            password.toCharArray());
                                }

                            })
                            .connectTimeout(Duration.ofSeconds(10))
                            .build();



                    HttpRequest request = HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:8080/auth"))
                             // add request header
                            .build();

                        HttpResponse<String> rs = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    JSONObject jsonObj = new JSONObject(rs.body());
                    System.out.println(jsonObj);
                    String segments[] = rs.body().split(",");
                    boolean isFound = segments[segments.length -1].indexOf("admin") !=-1? true: false;
                    boolean isFound1 = segments[segments.length -1].indexOf("medic") !=-1? true: false;
                    boolean isFound2 = segments[segments.length -1].indexOf("pacient") !=-1? true: false;

                    if (rs.statusCode()==200) {

                        dispose();
                        System.out.println(userName);
                        if(isFound)
                        {
                            Admin frame = new Admin();
                            frame.setVisible(true);
                        }

                        if(isFound1)
                        {
                            String cnp = segments[0].substring(segments[0].indexOf(":")+1);
                            StringBuffer sb= new StringBuffer(cnp);
//invoking the method
                            sb.deleteCharAt(sb.length()-1);
                            cnp = cnp.substring(1, cnp.length() - 1);

                            System.out.println(cnp);

                            Medic frame = new Medic("medic" , cnp);
                            frame.setVisible(true);
                        }


                        if(isFound2)
                        {
                            String cnp = segments[0].substring(segments[0].indexOf(":")+1);
                            StringBuffer sb= new StringBuffer(cnp);
//invoking the method
                            sb.deleteCharAt(sb.length()-1);
                            cnp = cnp.substring(1, cnp.length() - 1);

                            System.out.println(cnp);

                            Pacient frame = new Pacient(cnp);
                            frame.setVisible(true);
                        }


                        System.out.println(rs.body());




                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (IOException | InterruptedException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }


}