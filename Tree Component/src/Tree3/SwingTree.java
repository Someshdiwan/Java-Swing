package Tree3;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;

class MyFrame extends JFrame implements TreeSelectionListener {
    private JTree tree;
    private JLabel label;
    private static final String DUMMY = "Loading...";

    public MyFrame() {
        super("File Tree Demo");
        setLayout(new BorderLayout());

        // Create menu bar for dynamic root selection
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Action listener to open file chooser
        openItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDir = chooser.getSelectedFile();
                setNewRoot(selectedDir);
            }
        });

        // Initialize with a default root directory
        File defaultRoot = new File(System.getProperty("user.home")); // or "C://Users//somes//Downloads//Java-Swing"
        DefaultMutableTreeNode root = createNode(defaultRoot);
        populateChildren(root);

        // Set up the JTree
        tree = new JTree(root);
        tree.addTreeSelectionListener(this);
        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                if (node.getChildCount() == 1 && DUMMY.equals(((DefaultMutableTreeNode) node.getFirstChild()).getUserObject())) {
                    node.removeAllChildren();
                    populateChildren(node);
                    ((DefaultTreeModel) tree.getModel()).reload(node);
                }
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                // No action needed on collapse
            }
        });

        // Add components to frame
        JScrollPane scrollPane = new JScrollPane(tree);
        label = new JLabel("No file selected");
        add(scrollPane, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }

    // Helper method to create a tree node
    private DefaultMutableTreeNode createNode(File file) {
        return new DefaultMutableTreeNode(new FileWrapper(file));
    }

    // Populate immediate children of a node
    private void populateChildren(DefaultMutableTreeNode node) {
        FileWrapper wrapper = (FileWrapper) node.getUserObject();
        File dir = wrapper.getFile();
        if (dir.isDirectory() && dir.listFiles() != null) {
            for (File file : dir.listFiles()) {
                DefaultMutableTreeNode childNode = createNode(file);
                if (file.isDirectory()) {
                    childNode.add(new DefaultMutableTreeNode(DUMMY));
                }
                node.add(childNode);
            }
        }
    }

    // Set a new root directory dynamically
    private void setNewRoot(File rootDir) {
        if (rootDir != null && rootDir.isDirectory()) {
            DefaultMutableTreeNode newRoot = createNode(rootDir);
            populateChildren(newRoot);
            tree.setModel(new DefaultTreeModel(newRoot));
            tree.expandPath(new TreePath(newRoot.getPath()));
        } else {
            JOptionPane.showMessageDialog(this, "Invalid directory selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        if (path != null) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            FileWrapper wrapper = (FileWrapper) selectedNode.getUserObject();
            File selectedFile = wrapper.getFile();
            String details = selectedFile.isDirectory()
                    ? "Directory: " + selectedFile.getAbsolutePath()
                    : "File: " + selectedFile.getAbsolutePath() + " | Size: " + selectedFile.length() + " bytes";
            label.setText(details);
        }
    }

    // Wrapper class to manage File objects and display names
    static class FileWrapper {
        private final File file;

        public FileWrapper(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        @Override
        public String toString() {
            return file.getName();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyFrame frame = new MyFrame();
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}