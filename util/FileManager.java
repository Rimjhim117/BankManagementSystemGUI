
package util;

import models.User;
import java.io.*;
import java.util.*;
import java.text.*;

public class FileManager {
    private static final String USER_FILE = "data/users.txt";
    private static final String TRANSACTION_FILE = "data/transactions.txt";

    // Load all users
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(User.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Save all users
    private static void saveUsers(List<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User u : users) {
                writer.println(u.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Find user by username
    public static User findUser(String username) {
        for (User u : loadUsers()) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    // Add a new user
    public static void addUser(User newUser) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE, true))) {
            writer.println(newUser.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update a user's record
    public static void updateUser(User user) {
        List<User> users = loadUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                break;
            }
        }
        saveUsers(users);
    }

    // Authenticate login
    public static User authenticate(String username, String password) {
        for (User u : loadUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    // Log a transaction
    public static void logTransaction(String user, String type, double amount) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.println(user + "," + type + "," + amount + "," + date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all transactions (admin view)
    public static List<String> getAllTransactions() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) list.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Delete a user
    public static boolean deleteUser(String username) {
        List<User> users = loadUsers();
        boolean removed = users.removeIf(u -> u.getUsername().equals(username));
        if (removed) saveUsers(users);
        return removed;
    }

    // Generate a text statement
    public static void generateStatement(String username) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter("statement_" + username + ".txt")) {
            writer.println("Monthly Statement for: " + username);
            writer.println("---------------------------------------");
            for (String l : lines) {
                writer.println(l);
            }
            writer.println("---------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
