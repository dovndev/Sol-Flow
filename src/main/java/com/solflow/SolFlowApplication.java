package com.solflow;

import com.solflow.ui.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main application class for Sol-Flow
 * A modern Java Swing application
 */
public class SolFlowApplication {
    
    public static void main(String[] args) {
        // Set system look and feel for better native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Failed to set system look and feel: " + e.getMessage());
        }
        
        // Run the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                new MainFrame().setVisible(true);
            } catch (Exception e) {
                System.err.println("Failed to start application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
