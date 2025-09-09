package com.solflow.util;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UIUtils {

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        // Additional button styling can be added here
        return button;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        // Additional label styling can be added here
        return label;
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField(20);
        // Additional text field styling can be added here
        return textField;
    }
}