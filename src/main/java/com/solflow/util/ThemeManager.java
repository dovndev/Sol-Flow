package com.solflow.util;

import javax.swing.*;
import java.awt.*;

/**
 * Theme manager for handling light and dark themes
 */
public class ThemeManager {
    
    private static boolean isDarkMode = false;
    
    // Light theme colors
    private static final Color LIGHT_BACKGROUND = Color.WHITE;
    private static final Color LIGHT_FOREGROUND = Color.BLACK;
    private static final Color LIGHT_PANEL_BACKGROUND = new Color(245, 245, 245);
    private static final Color LIGHT_BORDER = new Color(200, 200, 200);
    
    // Dark theme colors
    private static final Color DARK_BACKGROUND = new Color(43, 43, 43);
    private static final Color DARK_FOREGROUND = new Color(220, 220, 220);
    private static final Color DARK_PANEL_BACKGROUND = new Color(60, 60, 60);
    private static final Color DARK_BORDER = new Color(80, 80, 80);
    
    public static void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }
    
    public static boolean isDarkMode() {
        return isDarkMode;
    }
    
    public static void applyTheme(Component component) {
        if (component instanceof JFrame) {
            applyThemeToFrame((JFrame) component);
        }
        applyThemeRecursively(component);
    }
    
    private static void applyThemeToFrame(JFrame frame) {
        Color backgroundColor = isDarkMode ? DARK_BACKGROUND : LIGHT_BACKGROUND;
        Color foregroundColor = isDarkMode ? DARK_FOREGROUND : LIGHT_FOREGROUND;
        
        frame.getContentPane().setBackground(backgroundColor);
        frame.getContentPane().setForeground(foregroundColor);
        
        // Apply to menu bar if present
        JMenuBar menuBar = frame.getJMenuBar();
        if (menuBar != null) {
            applyThemeToMenuBar(menuBar);
        }
    }
    
    private static void applyThemeToMenuBar(JMenuBar menuBar) {
        Color backgroundColor = isDarkMode ? DARK_PANEL_BACKGROUND : LIGHT_PANEL_BACKGROUND;
        Color foregroundColor = isDarkMode ? DARK_FOREGROUND : LIGHT_FOREGROUND;
        
        menuBar.setBackground(backgroundColor);
        menuBar.setForeground(foregroundColor);
        
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu != null) {
                applyThemeToMenu(menu);
            }
        }
    }
    
    private static void applyThemeToMenu(JMenu menu) {
        Color backgroundColor = isDarkMode ? DARK_PANEL_BACKGROUND : LIGHT_PANEL_BACKGROUND;
        Color foregroundColor = isDarkMode ? DARK_FOREGROUND : LIGHT_FOREGROUND;
        
        menu.setBackground(backgroundColor);
        menu.setForeground(foregroundColor);
        
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem item = menu.getItem(i);
            if (item != null) {
                item.setBackground(backgroundColor);
                item.setForeground(foregroundColor);
                
                if (item instanceof JMenu) {
                    applyThemeToMenu((JMenu) item);
                }
            }
        }
    }
    
    private static void applyThemeRecursively(Component component) {
        Color backgroundColor = getBackgroundColor(component);
        Color foregroundColor = getForegroundColor();
        Color panelBackground = getPanelBackgroundColor();
        Color borderColor = getBorderColor();
        
        if (component instanceof JPanel) {
            component.setBackground(panelBackground);
            component.setForeground(foregroundColor);
        } else if (component instanceof JTextArea || component instanceof JTextField) {
            component.setBackground(backgroundColor);
            component.setForeground(foregroundColor);
            if (component instanceof JTextArea) {
                JTextArea textArea = (JTextArea) component;
                textArea.setCaretColor(foregroundColor);
            }
        } else if (component instanceof JLabel) {
            component.setForeground(foregroundColor);
        } else if (component instanceof JButton) {
            JButton button = (JButton) component;
            button.setBackground(panelBackground);
            button.setForeground(foregroundColor);
            button.setBorder(BorderFactory.createLineBorder(borderColor));
        } else if (component instanceof JTable) {
            JTable table = (JTable) component;
            table.setBackground(backgroundColor);
            table.setForeground(foregroundColor);
            table.setGridColor(borderColor);
            table.getTableHeader().setBackground(panelBackground);
            table.getTableHeader().setForeground(foregroundColor);
        } else if (component instanceof JTree) {
            JTree tree = (JTree) component;
            tree.setBackground(backgroundColor);
            tree.setForeground(foregroundColor);
        } else if (component instanceof JTabbedPane) {
            JTabbedPane tabbedPane = (JTabbedPane) component;
            tabbedPane.setBackground(panelBackground);
            tabbedPane.setForeground(foregroundColor);
        } else if (component instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) component;
            scrollPane.getViewport().setBackground(backgroundColor);
            scrollPane.setBackground(backgroundColor);
        } else if (component instanceof JSplitPane) {
            JSplitPane splitPane = (JSplitPane) component;
            splitPane.setBackground(panelBackground);
        }
        
        // Recursively apply to child components
        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                applyThemeRecursively(child);
            }
        }
    }
    
    private static Color getBackgroundColor(Component component) {
        if (component instanceof JTextArea || component instanceof JTextField || 
            component instanceof JTable || component instanceof JTree) {
            return isDarkMode ? DARK_BACKGROUND : LIGHT_BACKGROUND;
        }
        return isDarkMode ? DARK_PANEL_BACKGROUND : LIGHT_PANEL_BACKGROUND;
    }
    
    private static Color getForegroundColor() {
        return isDarkMode ? DARK_FOREGROUND : LIGHT_FOREGROUND;
    }
    
    private static Color getPanelBackgroundColor() {
        return isDarkMode ? DARK_PANEL_BACKGROUND : LIGHT_PANEL_BACKGROUND;
    }
    
    private static Color getBorderColor() {
        return isDarkMode ? DARK_BORDER : LIGHT_BORDER;
    }
    
    public static Color getAccentColor() {
        return new Color(70, 130, 180); // Steel blue - works well in both themes
    }
    
    public static Color getSuccessColor() {
        return isDarkMode ? new Color(0, 200, 0) : new Color(0, 150, 0);
    }
    
    public static Color getErrorColor() {
        return isDarkMode ? new Color(255, 100, 100) : Color.RED;
    }
    
    public static Color getWarningColor() {
        return new Color(255, 140, 0); // Orange - works well in both themes
    }
}
