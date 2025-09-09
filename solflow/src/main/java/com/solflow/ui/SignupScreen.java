package com.solflow.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;

public class SignupScreen extends JPanel {

    public SignupScreen(Runnable onBack, Consumer<String> onSignup) {
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Sign Up", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 22f));
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JTextField userField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();

        int row = 0;
        gc.gridx = 0;
        gc.gridy = row;
        gc.weightx = 0;
        form.add(new JLabel("Username:"), gc);
        gc.gridx = 1;
        gc.weightx = 1;
        form.add(userField, gc);
        row++;

        gc.gridx = 0;
        gc.gridy = row;
        gc.weightx = 0;
        form.add(new JLabel("Email:"), gc);
        gc.gridx = 1;
        gc.weightx = 1;
        form.add(emailField, gc);
        row++;

        gc.gridx = 0;
        gc.gridy = row;
        gc.weightx = 0;
        form.add(new JLabel("Password:"), gc);
        gc.gridx = 1;
        gc.weightx = 1;
        form.add(passField, gc);
        row++;

        gc.gridx = 0;
        gc.gridy = row;
        gc.weightx = 0;
        form.add(new JLabel("Confirm:"), gc);
        gc.gridx = 1;
        gc.weightx = 1;
        form.add(confirmField, gc);
        row++;

        add(form, BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton back = new JButton("Back");
        JButton signup = new JButton("Create Account");
        back.addActionListener(e -> onBack.run());
        signup.addActionListener(e -> {
            if (!Arrays.equals(passField.getPassword(), confirmField.getPassword())) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            onSignup.accept(userField.getText());
        });
        actions.add(back);
        actions.add(signup);
        add(actions, BorderLayout.SOUTH);

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }
}