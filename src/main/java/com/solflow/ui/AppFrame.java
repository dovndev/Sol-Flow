package com.solflow.ui;

import javax.swing.*;
import java.awt.*;
import com.solflow.util.UserStore;

public class AppFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final JPanel cards = new JPanel(layout);

    private static final String HOME = "home";
    private static final String LOGIN = "login";
    private static final String SIGNUP = "signup";

    private final UserStore userStore = new UserStore();

    public AppFrame() {
        setTitle("SolFlow");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 400);
        setLocationRelativeTo(null);

        HomeScreen home = new HomeScreen(this::showLogin, this::showSignup);
        LoginScreen login = new LoginScreen(this::showHome, this::handleLogin);
        SignupScreen signup = new SignupScreen(this::showHome, this::handleSignup);

        cards.add(home, HOME);
        cards.add(login, LOGIN);
        cards.add(signup, SIGNUP);

        setContentPane(cards);
        showHome();
    }

    private void handleLogin(String user, char[] pass) {
        if (user == null || user.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter username", "Login", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!userStore.exists(user)) {
            JOptionPane.showMessageDialog(this, "User not found", "Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (userStore.validate(user, pass)) {
            JOptionPane.showMessageDialog(this, "Welcome back, " + user + "!", "Login", JOptionPane.INFORMATION_MESSAGE);
            showHome();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials", "Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSignup(String user) {
        if (user == null || user.isBlank()) {
            JOptionPane.showMessageDialog(this, "Username required", "Sign Up", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (userStore.exists(user)) {
            JOptionPane.showMessageDialog(this, "Username already exists", "Sign Up", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (userStore.createUser(user, user.toCharArray())) {
            JOptionPane.showMessageDialog(this, "Account created for " + user, "Sign Up", JOptionPane.INFORMATION_MESSAGE);
            showLogin();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create user", "Sign Up", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void show(String key) { layout.show(cards, key); }
    public void showHome() { show(HOME); }
    public void showLogin() { show(LOGIN); }
    public void showSignup() { show(SIGNUP); }
}
