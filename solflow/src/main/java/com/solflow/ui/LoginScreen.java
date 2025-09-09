package com.solflow.ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

public class LoginScreen extends JPanel {

    public LoginScreen(Runnable onBack, BiConsumer<String, char[]> onLogin) {
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Login", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 22f));
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        gc.gridx = 0; gc.gridy = 0; gc.weightx = 0; form.add(new JLabel("Username / Email:"), gc);
        gc.gridx = 1; gc.weightx = 1; form.add(userField, gc);

        gc.gridx = 0; gc.gridy = 1; gc.weightx = 0; form.add(new JLabel("Password:"), gc);
        gc.gridx = 1; gc.weightx = 1; form.add(passField, gc);

        add(form, BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton back = new JButton("Back");
        JButton login = new JButton("Login");
        back.addActionListener(e -> onBack.run());
        login.addActionListener(e -> onLogin.accept(userField.getText(), passField.getPassword()));
        actions.add(back);
        actions.add(login);
        add(actions, BorderLayout.SOUTH);

        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
    }
}