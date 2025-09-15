package com.solflow.ui;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    public HomeScreen(Runnable onLogin, Runnable onSignup) {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Welcome", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 28f));

        JPanel buttons = new JPanel();
        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");
        loginBtn.addActionListener(e -> onLogin.run());
        signupBtn.addActionListener(e -> onSignup.run());
        buttons.add(loginBtn);
        buttons.add(signupBtn);

        add(title, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
    }
}