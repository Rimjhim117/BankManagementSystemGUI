Bank Management System

This is a proof-of-concept for a basic banking management system developed in Java. The application features a simple graphical user interface (GUI) for user and administrative functions, with all data securely stored in local text files. This project is a great example of a complete system that handles user authentication, transaction processing, and data persistence without a formal database.

✨ Key Features

    User Management: Allows new users to securely sign up and create an account (SignupFrame.java).

    Secure Login: Users and administrators can log in to their respective dashboards using a simple, credentials-based authentication system (LoginFrame.java).

    User Dashboard: Provides a clear GUI for logged-in users to perform essential banking operations, such as checking their balance, making deposits, and initiating withdrawals (DashboardFrame.java).

    Administrative Interface: A separate administrative dashboard offers control over the system, likely including user management and system-wide data oversight (AdminDashboard.java).

    File-Based Data Persistence: The system stores all application data (user accounts, transaction history, and statements) in plain text files, demonstrating a simple yet effective method of data handling (users.txt, transactions.txt, statement_username.txt).

📂 Project Structure

    Main.java: The primary entry point for the application, which launches the initial login screen.

    LoginFrame.java: Manages the user login interface and validates credentials against the users.txt file.

    SignupFrame.java: Handles the registration form for new users and saves their information to the users.txt file.

    DashboardFrame.java: Implements the main user interface where authenticated users can interact with their accounts.

    AdminDashboard.java: Contains the GUI and business logic for the administrative user's view.

    users.txt: A plain text file that stores user credentials. Each line likely corresponds to a user record.

    transactions.txt: A central log file that records all banking transactions.

    statement_[username].txt: A file generated for each user, which serves as their individual transaction history or bank statement.

🚀 Getting Started

To run this application, you need to have a Java Development Kit (JDK) installed on your system.

Prerequisites

    Java Development Kit (JDK) 8 or newer.

Compiling and Running

    Open your terminal or command prompt and navigate to the project directory.

    Compile all the Java source files using the Java compiler:
    Bash

javac Main.java AdminDashboard.java DashboardFrame.java LoginFrame.java SignupFrame.java

Ensure that the necessary data files (users.txt, transactions.txt, and statement_username.txt for any existing users) are present in the same directory. If you are running the application for the first time, you may need to create empty users.txt and transactions.txt files.

Run the main class to start the application:
Bash

        java Main

This will launch the login screen, where you can sign up a new user or log in with existing credentials.

📊 Data Files

The application uses these text files to store data:

    users.txt: This file contains user data. Each line likely represents a user with their username and password.

    transactions.txt: This file records every transaction, possibly including the user ID, transaction type, amount, and timestamp.
