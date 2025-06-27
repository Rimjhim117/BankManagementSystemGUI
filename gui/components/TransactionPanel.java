package gui.components;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class TransactionPanel {
    public static void showTransactionHistory(String username) {
        List<String> transactions = new ArrayList<>();

        try {
            File file = new File("transactions.txt");
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts[0].equals(username)) {
                    transactions.add(parts[1] + " ₹" + parts[2] + " on " + parts[3]);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No transactions found.");
        } else {
            JTextArea area = new JTextArea(String.join("\n", transactions));
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(400, 250));
            JOptionPane.showMessageDialog(null, scroll, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
