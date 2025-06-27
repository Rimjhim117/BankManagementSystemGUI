package gui;

import models.User;
import util.FileManager;
import gui.components.TransactionPanel;
import util.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdminDashboard extends JFrame {
    private final User user;

    public AdminDashboard(User user) {
        this.user = user;
        IconUtil.setWindowIcon(this);
        initUI();
    }

    private void initUI() {
        setTitle("Admin Dashboard");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        JButton viewAllUsersBtn = new JButton("View All Users");
        JButton viewTransactionsBtn = new JButton("View All Transactions");
        JButton viewUserTransactionsBtn = new JButton("View Transaction by User");
        JButton deleteUserBtn = new JButton("Delete a User");
        JButton logoutBtn = new JButton("Logout");

        panel.add(viewAllUsersBtn);
        panel.add(viewTransactionsBtn);
        panel.add(viewUserTransactionsBtn);
        panel.add(deleteUserBtn);
        panel.add(logoutBtn);

        add(panel, BorderLayout.CENTER);

        // ✅ View all users
        viewAllUsersBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            List<User> users = FileManager.loadUsers();
            for (User u : users) {
                sb.append(u.toString()).append("\n");
            }
            JTextArea area = new JTextArea(sb.toString());
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(this, scroll, "All Users", JOptionPane.INFORMATION_MESSAGE);
        });

        // ✅ View all transactions
        viewTransactionsBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            List<String> transactions = FileManager.getAllTransactions();
            for (String t : transactions) {
                sb.append(t).append("\n");
            }
            JTextArea area = new JTextArea(sb.toString());
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(this, scroll, "All Transactions", JOptionPane.INFORMATION_MESSAGE);
        });

        // ✅ View a user's transactions
        viewUserTransactionsBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Enter username:");
            if (username != null && !username.trim().isEmpty()) {
                TransactionPanel.showTransactionHistory(username.trim());
            }
        });

        // ✅ Delete a user
        deleteUserBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Enter username to delete:");
            if (username != null && !username.trim().isEmpty()) {
                boolean deleted = FileManager.deleteUser(username.trim());
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "User deleted.");
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.");
                }
            }
        });

        // ✅ Logout
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
