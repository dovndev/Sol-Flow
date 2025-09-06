package com.solflow.ui.components;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Side panel component with navigation tree and project explorer
 */
public class SidePanel extends JPanel {
    
    private JTree navigationTree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;
    
    public SidePanel() {
        initializePanel();
        createComponents();
        layoutComponents();
        setupEventHandlers();
    }
    
    private void initializePanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 0));
        setBorder(BorderFactory.createTitledBorder("Project Explorer"));
    }
    
    private void createComponents() {
        createNavigationTree();
    }
    
    private void createNavigationTree() {
        rootNode = new DefaultMutableTreeNode("Sol-Flow Project");
        
        // Create sample tree structure
        DefaultMutableTreeNode srcNode = new DefaultMutableTreeNode("src");
        DefaultMutableTreeNode mainNode = new DefaultMutableTreeNode("main");
        DefaultMutableTreeNode javaNode = new DefaultMutableTreeNode("java");
        DefaultMutableTreeNode comNode = new DefaultMutableTreeNode("com");
        DefaultMutableTreeNode solflowNode = new DefaultMutableTreeNode("solflow");
        
        DefaultMutableTreeNode uiNode = new DefaultMutableTreeNode("ui");
        DefaultMutableTreeNode componentsNode = new DefaultMutableTreeNode("components");
        DefaultMutableTreeNode utilNode = new DefaultMutableTreeNode("util");
        
        // Build tree structure
        rootNode.add(srcNode);
        srcNode.add(mainNode);
        mainNode.add(javaNode);
        javaNode.add(comNode);
        comNode.add(solflowNode);
        
        solflowNode.add(uiNode);
        solflowNode.add(utilNode);
        uiNode.add(componentsNode);
        
        // Add some sample files
        solflowNode.add(new DefaultMutableTreeNode("SolFlowApplication.java"));
        uiNode.add(new DefaultMutableTreeNode("MainFrame.java"));
        componentsNode.add(new DefaultMutableTreeNode("SidePanel.java"));
        componentsNode.add(new DefaultMutableTreeNode("MainContentPanel.java"));
        componentsNode.add(new DefaultMutableTreeNode("StatusBar.java"));
        utilNode.add(new DefaultMutableTreeNode("ThemeManager.java"));
        
        treeModel = new DefaultTreeModel(rootNode);
        navigationTree = new JTree(treeModel);
        navigationTree.setRootVisible(true);
        navigationTree.setShowsRootHandles(true);
        
        // Expand all nodes by default
        for (int i = 0; i < navigationTree.getRowCount(); i++) {
            navigationTree.expandRow(i);
        }
    }
    
    private void layoutComponents() {
        JScrollPane treeScrollPane = new JScrollPane(navigationTree);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(treeScrollPane, BorderLayout.CENTER);
        
        // Add toolbar at the top
        JToolBar toolbar = createToolbar();
        add(toolbar, BorderLayout.NORTH);
    }
    
    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        
        JButton refreshButton = new JButton("🔄");
        refreshButton.setToolTipText("Refresh project tree");
        refreshButton.addActionListener(e -> refreshTree());
        
        JButton newFolderButton = new JButton("📁");
        newFolderButton.setToolTipText("New folder");
        newFolderButton.addActionListener(e -> createNewFolder());
        
        JButton newFileButton = new JButton("📄");
        newFileButton.setToolTipText("New file");
        newFileButton.addActionListener(e -> createNewFile());
        
        toolbar.add(refreshButton);
        toolbar.addSeparator();
        toolbar.add(newFolderButton);
        toolbar.add(newFileButton);
        
        return toolbar;
    }
    
    private void setupEventHandlers() {
        navigationTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode selectedNode = 
                        (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();
                    if (selectedNode != null) {
                        handleNodeDoubleClick(selectedNode);
                    }
                }
            }
        });
    }
    
    private void handleNodeDoubleClick(DefaultMutableTreeNode node) {
        String nodeName = node.toString();
        if (nodeName.endsWith(".java")) {
            // Simulate opening a file
            System.out.println("Opening file: " + nodeName);
            // Here you would typically open the file in the main content area
        }
    }
    
    private void refreshTree() {
        treeModel.reload();
        System.out.println("Project tree refreshed");
    }
    
    private void createNewFolder() {
        String folderName = JOptionPane.showInputDialog(
            this,
            "Enter folder name:",
            "New Folder",
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (folderName != null && !folderName.trim().isEmpty()) {
            DefaultMutableTreeNode selectedNode = 
                (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();
            if (selectedNode == null) {
                selectedNode = rootNode;
            }
            
            DefaultMutableTreeNode newFolder = new DefaultMutableTreeNode(folderName.trim());
            selectedNode.add(newFolder);
            treeModel.reload();
            System.out.println("Created new folder: " + folderName);
        }
    }
    
    private void createNewFile() {
        String fileName = JOptionPane.showInputDialog(
            this,
            "Enter file name:",
            "New File",
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (fileName != null && !fileName.trim().isEmpty()) {
            DefaultMutableTreeNode selectedNode = 
                (DefaultMutableTreeNode) navigationTree.getLastSelectedPathComponent();
            if (selectedNode == null) {
                selectedNode = rootNode;
            }
            
            DefaultMutableTreeNode newFile = new DefaultMutableTreeNode(fileName.trim());
            selectedNode.add(newFile);
            treeModel.reload();
            System.out.println("Created new file: " + fileName);
        }
    }
    
    public void addFileToTree(String fileName, DefaultMutableTreeNode parent) {
        if (parent == null) {
            parent = rootNode;
        }
        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(fileName);
        parent.add(fileNode);
        treeModel.reload();
    }
}
