// DashboardFrame.java
package gui;
import javax.swing.*;
import java.awt.*;
import util.IconUtil;
import gui.components.TransactionPanel;
import models.User;
import util.FileManager;


public class DashboardFrame extends JFrame {
    private final User user;

    public DashboardFrame(User user) {
        this.user = user;
        setTitle("Welcome, " + user.getUsername());
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IconUtil.setWindowIcon(this);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(new Color(240, 248, 255)); // pastel background

        JLabel welcome = new JLabel("Welcome, " + user.getUsername(), JLabel.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton viewTransactionsBtn = new JButton("View Transactions");
        JButton changePassBtn = new JButton("Change Password");
        JButton loanBtn = new JButton("Request Loan");
        JButton statementBtn = new JButton("Generate Monthly Statement");

        JButton[] buttons = {
            depositBtn, withdrawBtn, transferBtn, viewTransactionsBtn,
            changePassBtn, loanBtn, statementBtn
        };

        for (JButton btn : buttons) {
            btn.setBackground(new Color(100, 149, 237));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            panel.add(btn);
        }

        add(welcome, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);

        // Button Actions
        depositBtn.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            if (amountStr != null) {
                try {
                    double amt = Double.parseDouble(amountStr);
                    if (amt <= 0) throw new Exception();
                    user.setBalance(user.getBalance() + amt);
                    FileManager.updateUser(user);
                    FileManager.logTransaction(user.getUsername(), "Deposit", amt);
                    JOptionPane.showMessageDialog(this, "Deposited ₹" + amt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount.");
                }
            }
        });

        withdrawBtn.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
            if (amountStr != null) {
                try {
                    double amt = Double.parseDouble(amountStr);
                    if (amt <= 0 || amt > user.getBalance()) throw new Exception();
                    user.setBalance(user.getBalance() - amt);
                    FileManager.updateUser(user);
                    FileManager.logTransaction(user.getUsername(), "Withdraw", amt);
                    JOptionPane.showMessageDialog(this, "Withdrawn ₹" + amt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid or insufficient amount.");
                }
            }
        });

        transferBtn.addActionListener(e -> {
            String toUser = JOptionPane.showInputDialog(this, "Enter recipient username:");
            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to transfer:");
            if (toUser != null && amountStr != null) {
                try {
                    double amt = Double.parseDouble(amountStr);
                    if (amt <= 0 || amt > user.getBalance()) throw new Exception();
                    User receiver = FileManager.findUser(toUser);
                    if (receiver == null || receiver.getUsername().equals(user.getUsername())) {
                        JOptionPane.showMessageDialog(this, "Invalid recipient.");
                        return;
                    }
                    user.setBalance(user.getBalance() - amt);
                    receiver.setBalance(receiver.getBalance() + amt);
                    FileManager.updateUser(user);
                    FileManager.updateUser(receiver);
                    FileManager.logTransaction(user.getUsername(), "Transfer to " + toUser, amt);
                    FileManager.logTransaction(toUser, "Transfer from " + user.getUsername(), amt);
                    JOptionPane.showMessageDialog(this, "Transferred ₹" + amt + " to " + toUser);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid transfer.");
                }
            }
        });

        viewTransactionsBtn.addActionListener(e -> TransactionPanel.showTransactionHistory(user.getUsername()));

        changePassBtn.addActionListener(e -> {
            String newPass = JOptionPane.showInputDialog(this, "Enter new password:");
            if (newPass != null && newPass.length() >= 4) {
                user.setPassword(newPass);
                FileManager.updateUser(user);
                JOptionPane.showMessageDialog(this, "Password updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Password too short.");
            }
        });

        loanBtn.addActionListener(e -> {
            String amtStr = JOptionPane.showInputDialog(this, "Enter loan amount:");
            if (amtStr != null) {
                try {
                    double amt = Double.parseDouble(amtStr);
                    FileManager.logTransaction(user.getUsername(), "Loan Requested", amt);
                    JOptionPane.showMessageDialog(this, "Loan request submitted for ₹" + amt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount.");
                }
            }
        });

        statementBtn.addActionListener(e -> {
            try {
                FileManager.generateStatement(user.getUsername());
                JOptionPane.showMessageDialog(this, "Monthly statement generated as statement_" + user.getUsername() + ".txt");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to generate statement.");
            }
        });
    }
}
