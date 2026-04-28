package ui;

import javax.swing.*;
import java.awt.*;

public class LoginUI {

    public static void show() {

        JFrame frame = new JFrame("CodePet Login");
        frame.setSize(450, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Same colorful background as StudentUI
        frame.getContentPane().setBackground(Theme.BACKGROUND);

        // Title
        JLabel title = new JLabel("🐾 CodePet Login", SwingConstants.CENTER);
        title.setBounds(100, 20, 250, 40);
        title.setFont(Theme.TITLE_FONT);

        // Username Label
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(80, 80, 100, 25);
        userLabel.setFont(Theme.NORMAL_FONT);

        // Username Field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(80, 110, 280, 35);
        usernameField.setFont(Theme.NORMAL_FONT);

        // Password Label
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(80, 160, 100, 25);
        passLabel.setFont(Theme.NORMAL_FONT);

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(80, 190, 280, 35);
        passwordField.setFont(Theme.NORMAL_FONT);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(145, 270, 150, 40);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 18));

        // Login Action
        loginBtn.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Simple demo login
            if (username.equals("student") && password.equals("123")) {
                JOptionPane.showMessageDialog(frame, "Student Login Success!");
                frame.dispose();
                StudentUI.show();

            } else if (username.equals("teacher") && password.equals("123")) {
                JOptionPane.showMessageDialog(frame, "Teacher Login Success!");
                frame.dispose();
                TeacherUI.show();

            } else {
                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Username or Password"
                );
            }
        });

        // Add components
        frame.add(title);
        frame.add(userLabel);
        frame.add(usernameField);
        frame.add(passLabel);
        frame.add(passwordField);
        frame.add(loginBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}