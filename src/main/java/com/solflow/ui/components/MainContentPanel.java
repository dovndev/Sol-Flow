package com.solflow.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main content panel that displays the primary application content
 */
public class MainContentPanel extends JPanel {
    
    private JTabbedPane tabbedPane;
    private JTextArea welcomeTextArea;
    
    public MainContentPanel() {
        initializePanel();
        createComponents();
        layoutComponents();
        setupEventHandlers();
    }
    
    private void initializePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Content Area"));
    }
    
    private void createComponents() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        
        // Create welcome tab
        createWelcomeTab();
        
        // Create sample tabs
        createSampleTabs();
    }
    
    private void createWelcomeTab() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        
        // Welcome header
        JLabel welcomeLabel = new JLabel("Welcome to Sol-Flow", JLabel.CENTER);
        welcomeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Welcome content
        welcomeTextArea = new JTextArea();
        welcomeTextArea.setText(
            "Sol-Flow - Modern Java Swing Application\\n\\n" +
            "Features:\\n" +
            "• Modern and clean user interface\\n" +
            "• Project explorer with file tree\\n" +
            "• Tabbed content area\\n" +
            "• Dark/Light theme support\\n" +
            "• Comprehensive menu system\\n" +
            "• Status bar with real-time updates\\n\\n" +
            "Getting Started:\\n" +
            "1. Use the File menu to create or open projects\\n" +
            "2. Explore the project structure in the side panel\\n" +
            "3. Switch between light and dark themes in the View menu\\n" +
            "4. Check the status bar for application updates\\n\\n" +
            "This is a fully functional Java Swing application template\\n" +
            "that you can extend with your own features and functionality."
        );
        welcomeTextArea.setEditable(false);
        welcomeTextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        welcomeTextArea.setMargin(new Insets(10, 10, 10, 10));
        welcomeTextArea.setLineWrap(true);
        welcomeTextArea.setWrapStyleWord(true);
        
        JScrollPane welcomeScrollPane = new JScrollPane(welcomeTextArea);
        welcomeScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        // Action buttons panel
        JPanel buttonPanel = createActionButtonsPanel();
        
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.add(welcomeScrollPane, BorderLayout.CENTER);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        tabbedPane.addTab("Welcome", welcomePanel);
    }
    
    private JPanel createActionButtonsPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton newProjectButton = new JButton("Create New Project");
        newProjectButton.setPreferredSize(new Dimension(150, 35));
        newProjectButton.addActionListener(e -> showNewProjectDialog());
        
        JButton openProjectButton = new JButton("Open Project");
        openProjectButton.setPreferredSize(new Dimension(150, 35));
        openProjectButton.addActionListener(e -> openProject());
        
        JButton settingsButton = new JButton("Settings");
        settingsButton.setPreferredSize(new Dimension(150, 35));
        settingsButton.addActionListener(e -> showSettings());
        
        buttonPanel.add(newProjectButton);
        buttonPanel.add(openProjectButton);
        buttonPanel.add(settingsButton);
        
        return buttonPanel;
    }
    
    private void createSampleTabs() {
        // Text Editor Tab
        createTextEditorTab();
        
        // Data View Tab
        createDataViewTab();
        
        // Chart Tab
        createChartTab();
    }
    
    private void createTextEditorTab() {
        JTextArea textArea = new JTextArea();
        textArea.setText("// Sample Java code\\npublic class Example {\\n    public static void main(String[] args) {\\n        System.out.println(\\"Hello, Sol-Flow!\\");\\n    }\\n}");
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        textArea.setTabSize(4);
        
        JScrollPane textScrollPane = new JScrollPane(textArea);
        tabbedPane.addTab("Code Editor", textScrollPane);
    }
    
    private void createDataViewTab() {
        String[] columnNames = {"ID", "Name", "Type", "Status", "Date"};
        Object[][] data = {
            {1, "Project Alpha", "Development", "Active", "2024-01-15"},
            {2, "Project Beta", "Testing", "Pending", "2024-02-20"},
            {3, "Project Gamma", "Production", "Complete", "2024-03-10"},
            {4, "Project Delta", "Planning", "Active", "2024-04-05"},
            {5, "Project Epsilon", "Development", "Active", "2024-05-12"}
        };
        
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane tableScrollPane = new JScrollPane(table);
        tabbedPane.addTab("Data View", tableScrollPane);
    }
    
    private void createChartTab() {
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        
        JLabel chartLabel = new JLabel("Chart Visualization Area", JLabel.CENTER);
        chartLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        chartLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        
        // Create a simple visual representation
        JPanel visualPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int width = getWidth();
                int height = getHeight();
                
                // Draw a simple bar chart
                g2d.setColor(new Color(70, 130, 180));
                g2d.fillRect(50, height - 150, 40, 100);
                g2d.fillRect(120, height - 200, 40, 150);
                g2d.fillRect(190, height - 120, 40, 70);
                g2d.fillRect(260, height - 180, 40, 130);
                g2d.fillRect(330, height - 160, 40, 110);
                
                // Draw labels
                g2d.setColor(Color.BLACK);
                g2d.drawString("Q1", 60, height - 20);
                g2d.drawString("Q2", 130, height - 20);
                g2d.drawString("Q3", 200, height - 20);
                g2d.drawString("Q4", 270, height - 20);
                g2d.drawString("Q5", 340, height - 20);
            }
        };
        
        chartPanel.add(chartLabel, BorderLayout.NORTH);
        chartPanel.add(visualPanel, BorderLayout.CENTER);
        
        tabbedPane.addTab("Charts", chartPanel);
    }
    
    private void layoutComponents() {
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        // Add tab change listener if needed
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex >= 0) {
                String tabTitle = tabbedPane.getTitleAt(selectedIndex);
                System.out.println("Switched to tab: " + tabTitle);
            }
        });
    }
    
    public void showNewProjectDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "New Project", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Project Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Project Type:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Java Application", "Web Application", "Desktop Application"});
        formPanel.add(typeCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        JTextField locationField = new JTextField(20);
        formPanel.add(locationField, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton createButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");
        
        createButton.addActionListener(e -> {
            String projectName = nameField.getText().trim();
            if (!projectName.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Project '" + projectName + "' created successfully!");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Please enter a project name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private void openProject() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String projectPath = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(this, "Opening project: " + projectPath);
        }
    }
    
    private void showSettings() {
        JDialog settingsDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Settings", true);
        settingsDialog.setSize(500, 400);
        settingsDialog.setLocationRelativeTo(this);
        
        JTabbedPane settingsTabs = new JTabbedPane();
        
        // General settings
        JPanel generalPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        generalPanel.add(new JLabel("Theme:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> themeCombo = new JComboBox<>(new String[]{"Light", "Dark", "System"});
        generalPanel.add(themeCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        generalPanel.add(new JLabel("Font Size:"), gbc);
        gbc.gridx = 1;
        JSpinner fontSpinner = new JSpinner(new SpinnerNumberModel(12, 8, 24, 1));
        generalPanel.add(fontSpinner, gbc);
        
        settingsTabs.addTab("General", generalPanel);
        
        // Editor settings
        JPanel editorPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0; gbc.gridy = 0;
        editorPanel.add(new JLabel("Tab Size:"), gbc);
        gbc.gridx = 1;
        JSpinner tabSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 8, 1));
        editorPanel.add(tabSpinner, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        JCheckBox wordWrapCheck = new JCheckBox("Word Wrap");
        editorPanel.add(wordWrapCheck, gbc);
        
        settingsTabs.addTab("Editor", editorPanel);
        
        settingsDialog.add(settingsTabs);
        settingsDialog.setVisible(true);
    }
    
    public void addNewTab(String title, Component component) {
        tabbedPane.addTab(title, component);
        tabbedPane.setSelectedComponent(component);
    }
    
    public void closeCurrentTab() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex > 0) { // Don't close the welcome tab
            tabbedPane.removeTabAt(selectedIndex);
        }
    }
}
