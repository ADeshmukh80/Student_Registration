package net.javaguides.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField username;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserRegistration frame = new UserRegistration();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserRegistration() {
        setTitle("User Registration");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(173, 216, 230); // Light blue
                Color color2 = new Color(240, 248, 255); // Lighter blue
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Fix label text and width to fit properly
        JLabel lblNewUserRegister = new JLabel("Student Registration");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD, 42));
        lblNewUserRegister.setBounds(300, 52, 450, 50); // Adjusted width
        lblNewUserRegister.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First Name");
        lblName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblLastName.setBounds(58, 243, 110, 29);
        contentPane.add(lblLastName);

        JLabel lblEmail = new JLabel("Email Address");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmail.setBounds(58, 324, 124, 36);
        contentPane.add(lblEmail);

        firstname = new JTextField();
        firstname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        firstname.setBounds(214, 151, 228, 50);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lastname.setBounds(214, 235, 228, 50);
        contentPane.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();
        email.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        email.setBounds(214, 320, 228, 50);
        contentPane.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        username.setBounds(707, 151, 228, 50);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Mobile Number");
        lblMobileNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblMobileNumber.setBounds(542, 329, 139, 26);
        contentPane.add(lblMobileNumber);

        mob = new JTextField();
        mob.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        mob.setBounds(707, 320, 228, 50);
        contentPane.add(mob);
        mob.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        passwordField.setBounds(707, 235, 228, 50);
        passwordField.setEchoChar('*');
        contentPane.add(passwordField);

        btnNewButton = new JButton("Register");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 22)); // Bold text
        btnNewButton.setForeground(Color.BLACK); // Black text color
        btnNewButton.setBackground(new Color(126, 200, 227)); // #7EC8E3
        btnNewButton.setBounds(399, 447, 259, 74);
        contentPane.add(btnNewButton);


        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String emailId = email.getText();
                String userName = username.getText();
                String mobileNumber = mob.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (!mobileNumber.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(btnNewButton, "Enter a valid 10-digit mobile number");
                    return;
                }
                //
                try (Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/swing_demo?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "password"
                )) {
                    String query = "INSERT INTO account (first_name, last_name, user_name, password, email_id, mobile_number) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, userName);
                    preparedStatement.setString(4, password);
                    preparedStatement.setString(5, emailId);
                    preparedStatement.setString(6, mobileNumber);

                    int x = preparedStatement.executeUpdate();
                    if (x > 0) {
                        JOptionPane.showMessageDialog(btnNewButton, "Welcome, " + firstName + "! Your account is successfully created.");
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "This user already exists.");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(btnNewButton, "Error: Unable to register. Try again later.");
                }
            }
        });
    }
}
