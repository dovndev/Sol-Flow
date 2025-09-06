package com.solflow.ui.components;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Status bar component that displays application status and information
 */
public class StatusBar extends JPanel {
    
    private JLabel statusLabel;
    private JLabel timeLabel;
    private JLabel memoryLabel;
    private JProgressBar progressBar;
    private Timer updateTimer;
    
    public StatusBar() {
        initializePanel();
        createComponents();
        layoutComponents();
        startUpdateTimer();
    }
    
    private void initializePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        setPreferredSize(new Dimension(0, 25));
    }
    
    private void createComponents() {
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        
        timeLabel = new JLabel();
        timeLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        updateTime();
        
        memoryLabel = new JLabel();
        memoryLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        updateMemoryInfo();
        
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressBar.setPreferredSize(new Dimension(100, 20));
        progressBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
    }
    
    private void layoutComponents() {
        // Left side - status message and progress bar
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(statusLabel, BorderLayout.CENTER);
        leftPanel.add(progressBar, BorderLayout.EAST);
        
        // Right side - time and memory info
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.add(memoryLabel);
        rightPanel.add(createSeparator());
        rightPanel.add(timeLabel);
        
        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
    
    private JLabel createSeparator() {
        JLabel separator = new JLabel(" | ");
        separator.setForeground(Color.GRAY);
        return separator;
    }
    
    private void startUpdateTimer() {
        updateTimer = new Timer(1000, e -> {
            updateTime();
            updateMemoryInfo();
        });
        updateTimer.start();
    }
    
    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        String timeString = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        timeLabel.setText(timeString);
    }
    
    private void updateMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        double usedMB = usedMemory / (1024.0 * 1024.0);
        double totalMB = totalMemory / (1024.0 * 1024.0);
        
        String memoryString = String.format("Memory: %.1f/%.1f MB", usedMB, totalMB);
        memoryLabel.setText(memoryString);
    }
    
    public void setStatus(String status) {
        statusLabel.setText(status);
        
        // Create a timer to clear the status after 5 seconds
        Timer clearTimer = new Timer(5000, e -> {
            if (statusLabel.getText().equals(status)) {
                statusLabel.setText("Ready");
            }
        });
        clearTimer.setRepeats(false);
        clearTimer.start();
    }
    
    public void setStatus(String status, int duration) {
        statusLabel.setText(status);
        
        if (duration > 0) {
            Timer clearTimer = new Timer(duration, e -> {
                if (statusLabel.getText().equals(status)) {
                    statusLabel.setText("Ready");
                }
            });
            clearTimer.setRepeats(false);
            clearTimer.start();
        }
    }
    
    public void showProgress(int value, int maximum) {
        progressBar.setMaximum(maximum);
        progressBar.setValue(value);
        progressBar.setVisible(true);
        
        if (value >= maximum) {
            Timer hideTimer = new Timer(2000, e -> progressBar.setVisible(false));
            hideTimer.setRepeats(false);
            hideTimer.start();
        }
    }
    
    public void hideProgress() {
        progressBar.setVisible(false);
    }
    
    public void showIndeterminateProgress(String message) {
        setStatus(message);
        progressBar.setIndeterminate(true);
        progressBar.setVisible(true);
    }
    
    public void hideIndeterminateProgress() {
        progressBar.setIndeterminate(false);
        progressBar.setVisible(false);
        setStatus("Ready");
    }
    
    public void setTemporaryStatus(String status, Color color, int duration) {
        Color originalColor = statusLabel.getForeground();
        statusLabel.setForeground(color);
        statusLabel.setText(status);
        
        Timer resetTimer = new Timer(duration, e -> {
            statusLabel.setForeground(originalColor);
            statusLabel.setText("Ready");
        });
        resetTimer.setRepeats(false);
        resetTimer.start();
    }
    
    public void showSuccessMessage(String message) {
        setTemporaryStatus(message, new Color(0, 150, 0), 3000);
    }
    
    public void showErrorMessage(String message) {
        setTemporaryStatus(message, Color.RED, 5000);
    }
    
    public void showWarningMessage(String message) {
        setTemporaryStatus(message, new Color(255, 140, 0), 4000);
    }
    
    // Cleanup method to stop the timer when the component is no longer needed
    public void dispose() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
}
