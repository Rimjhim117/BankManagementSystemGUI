package gui;

import models.User;
import util.FileManager;
import util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupFrame extends JFrame {
    public SignupFrame() {
        setTitle("User Signup");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IconUtil.setWindowIcon(this);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(new Color(245, 245, 245));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JLabel depositLabel = new JLabel("Initial Balance:");
        JTextField balanceField = new JTextField();

        JButton registerBtn = new JButton("Register");
        registerBtn.setBackground(new Color(0, 140, 100));
        registerBtn.setForeground(Color.white);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(depositLabel);
        panel.add(balanceField);
        panel.add(new JLabel());
        panel.add(registerBtn);

        add(panel);
        setVisible(true);

        registerBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            String balanceStr = balanceField.getText().trim();

            if (username.isEmpty() || password.length() < 4 || balanceStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields (Password ≥ 4 chars)");
                return;
            }

            try {
                double balance = Double.parseDouble(balanceStr);
                if (balance < 0) throw new Exception();

                if (FileManager.findUser(username) != null) {
                    JOptionPane.showMessageDialog(this, "Username already exists.");
                    return;
                }

                User newUser = new User(username, password, balance);
                FileManager.addUser(newUser);
                JOptionPane.showMessageDialog(this, "Signup successful!");
                new LoginFrame();
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid balance.");
            }
        });
    }
}
