# 🏦 Bank Management System

This is a proof-of-concept for a **basic banking management system** developed in **Java**.  
The application features a simple **graphical user interface (GUI)** for both user and administrative functions, with all data securely stored in **local text files**.  

It demonstrates a complete system that handles **user authentication, transaction processing, and data persistence** without relying on a formal database.

---

## ✨ Key Features

- **👤 User Management**  
  Allows new users to securely sign up and create an account (`SignupFrame.java`).

- **🔐 Secure Login**  
  Users and administrators can log in to their respective dashboards using a simple, credentials-based authentication system (`LoginFrame.java`).

- **📋 User Dashboard**  
  Provides a clear GUI for logged-in users to check their balance, make deposits, and withdraw funds (`DashboardFrame.java`).

- **🛠️ Administrative Interface**  
  A separate admin dashboard provides system-wide oversight and user management (`AdminDashboard.java`).

- **💾 File-Based Data Persistence**  
  Stores all application data (users, transactions, and statements) in plain text files:  
  - `users.txt`  
  - `transactions.txt`  
  - `statement_[username].txt`

---

## 📂 Project Structure

- **`Main.java`** → Primary entry point, launches the login screen.  
- **`LoginFrame.java`** → Manages login interface and validates credentials from `users.txt`.  
- **`SignupFrame.java`** → Handles registration form and stores new user data in `users.txt`.  
- **`DashboardFrame.java`** → Main user interface for authenticated banking operations.  
- **`AdminDashboard.java`** → GUI and logic for administrative control.  
- **`users.txt`** → Stores user credentials. Each line = one user record.  
- **`transactions.txt`** → Central log file recording all transactions.  
- **`statement_[username].txt`** → Individual transaction history for each user.  

---

## 🚀 Getting Started

### ✅ Prerequisites
- Install **Java Development Kit (JDK) 8 or newer**.

### ⚙️ Compiling & Running

1. Open a terminal/command prompt and navigate to the project directory.  
2. Compile the source files:  

   ```bash
   javac Main.java AdminDashboard.java DashboardFrame.java LoginFrame.java SignupFrame.java
   ```
3. 📂 Ensure data files exist:  
   - `users.txt`  
   - `transactions.txt`  
   - `statement_[username].txt` (for each user)  

   > 🆕 If running for the first time, create **empty `users.txt` and `transactions.txt`** files.

4. ▶️ Run the main class:  

   ```bash
   java Main
This will launch the login screen, where you can **sign up as a new user** or **log in with existing credentials**.

---

## 📊 Data Files  

- `users.txt` → Stores user credentials (**username & password**)  
- `transactions.txt` → Records every banking transaction  
- `statement_[username].txt` → Individual bank statement file for each user  

---

## 💡 Future Enhancements  

🔒 **Password Hashing**  
Replace plain text storage with secure hashing for stronger authentication.  

🗄️ **Database Integration**  
Upgrade from text files to a relational or NoSQL database for scalability.  

📱 **Improved GUI**  
Enhance the user experience with modern UI libraries or frameworks.  

📊 **Reporting Tools**  
Generate monthly/annual transaction reports for users and admins.  

---

## 👩‍💻 Author  

**Rimjhim Srivastava**  
GitHub: [rimjhim117](https://github.com/rimjhim117)  

