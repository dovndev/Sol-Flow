package com.solflow;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import com.solflow.ui.AppFrame;

public class Main {
    public static void main(String[] args) {
        // Setup modern look and feel
        try {
            FlatLightLaf.setup();
            UIManager.put( "Component.arc", 12 );
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new AppFrame().setVisible(true));
    }
}