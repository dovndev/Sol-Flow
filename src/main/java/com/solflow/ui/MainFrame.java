package com.solflow.ui;

import com.solflow.ui.components.SidePanel;
import com.solflow.ui.components.MainContentPanel;
import com.solflow.ui.components.StatusBar;
import com.solflow.util.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main application window for Sol-Flow
 */
public class MainFrame extends JFrame {
    
    private static final String APP_TITLE = "Sol-Flow - Modern Java Application";
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 800;
    
    private SidePanel sidePanel;
    private MainContentPanel contentPanel;
    private StatusBar statusBar;
    
    public MainFrame() {
        initializeFrame();
        createComponents();
        layoutComponents();
        setupEventHandlers();
        applyTheme();
    }
    
    private void initializeFrame() {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));
        
        // Set application icon (you can add an icon file later)
        try {
            // setIconImage(Toolkit.getDefaultToolkit().getImage("path/to/icon.png"));
        } catch (Exception e) {
            // Icon not found, use default
        }
    }
    
    private void createComponents() {
        sidePanel = new SidePanel();
        contentPanel = new MainContentPanel();
        statusBar = new StatusBar();
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Create main panel with side panel and content
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(sidePanel);
        splitPane.setRightComponent(contentPanel);
        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.0);
        splitPane.setBorder(null);
        
        add(splitPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        
        // Create menu bar
        setJMenuBar(createMenuBar());
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(createMenuItem("New", "Create a new project", e -> newProject()));
        fileMenu.add(createMenuItem("Open", "Open an existing project", e -> openProject()));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Save", "Save current project", e -> saveProject()));
        fileMenu.add(createMenuItem("Save As...", "Save project with new name", e -> saveAsProject()));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Exit", "Exit application", e -> exitApplication()));
        
        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(createMenuItem("Undo", "Undo last action", e -> undo()));
        editMenu.add(createMenuItem("Redo", "Redo last action", e -> redo()));
        editMenu.addSeparator();
        editMenu.add(createMenuItem("Cut", "Cut selection", e -> cut()));
        editMenu.add(createMenuItem("Copy", "Copy selection", e -> copy()));
        editMenu.add(createMenuItem("Paste", "Paste from clipboard", e -> paste()));
        
        // View menu
        JMenu viewMenu = new JMenu("View");
        JCheckBoxMenuItem darkModeItem = new JCheckBoxMenuItem("Dark Mode");
        darkModeItem.addActionListener(e -> toggleDarkMode(darkModeItem.isSelected()));
        viewMenu.add(darkModeItem);
        
        // Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(createMenuItem("About", "About Sol-Flow", e -> showAbout()));
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        
        return menuBar;
    }
    
    private JMenuItem createMenuItem(String text, String tooltip, java.awt.event.ActionListener action) {
        JMenuItem item = new JMenuItem(text);
        item.setToolTipText(tooltip);
        item.addActionListener(action);
        return item;
    }
    
    private void setupEventHandlers() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
    }
    
    private void applyTheme() {
        ThemeManager.applyTheme(this);
    }
    
    // Menu action methods
    private void newProject() {
        contentPanel.showNewProjectDialog();
        statusBar.setStatus("New project created");
    }
    
    private void openProject() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            statusBar.setStatus("Project opened: " + fileChooser.getSelectedFile().getName());
        }
    }
    
    private void saveProject() {
        statusBar.setStatus("Project saved");
    }
    
    private void saveAsProject() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            statusBar.setStatus("Project saved as: " + fileChooser.getSelectedFile().getName());
        }
    }
    
    private void exitApplication() {
        int result = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to exit?",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    private void undo() {
        statusBar.setStatus("Undo performed");
    }
    
    private void redo() {
        statusBar.setStatus("Redo performed");
    }
    
    private void cut() {
        statusBar.setStatus("Cut to clipboard");
    }
    
    private void copy() {
        statusBar.setStatus("Copied to clipboard");
    }
    
    private void paste() {
        statusBar.setStatus("Pasted from clipboard");
    }
    
    private void toggleDarkMode(boolean enabled) {
        ThemeManager.setDarkMode(enabled);
        ThemeManager.applyTheme(this);
        statusBar.setStatus("Theme changed to " + (enabled ? "dark" : "light") + " mode");
    }
    
    private void showAbout() {
        JOptionPane.showMessageDialog(
            this,
            "Sol-Flow v1.0\n\nA modern Java Swing application\nBuilt with love and creativity",
            "About Sol-Flow",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
