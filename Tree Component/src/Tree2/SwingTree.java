package Tree2;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

class MyFrame extends JFrame implements TreeSelectionListener {
    JTree tree;
    JLabel label;

    MyFrame() {
        super("File Tree Demo");

        // Define the root directory for the file tree.
        String rootPath = "C://Users//somes//Downloads//Java-Swing";
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootPath);
        File rootFile = new File(rootPath);

        // Build the tree: list files and directories under the given path.
        if (rootFile.exists() && rootFile.isDirectory()) {
            for (File file : rootFile.listFiles()) {
                if (file.isDirectory()) {
                    DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(file.getName());
                    // For each subdirectory, add its files as children.
                    for (File subfile : file.listFiles()) {
                        dirNode.add(new DefaultMutableTreeNode(subfile.getName()));
                    }
                    root.add(dirNode);
                } else {
                    root.add(new DefaultMutableTreeNode(file.getName()));
                }
            }
        }
        // Create the JTree using the node structure.
        tree = new JTree(root);
        tree.addTreeSelectionListener(this);
        JScrollPane sp = new JScrollPane(tree);

        // Label to display file details.
        label = new JLabel("No file selected");

        setLayout(new BorderLayout());
        add(sp, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        // Get the tree path and convert it to string.
        TreePath tp = e.getPath();
        // The tree path is formatted like "[root, node1, node2, ...]". Use StringTokenizer to parse it.
        StringTokenizer tokenizer = new StringTokenizer(tp.toString(), "[,]");
        StringBuilder fullPath = new StringBuilder();

        // Reconstruct the file path from the tokens.
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                if (fullPath.length() == 0) {
                    // The first token is the complete root path.
                    fullPath.append(token);
                } else {
                    fullPath.append(File.separator).append(token);
                }
            }
        }
        // Now, fullPath contains the complete file path.
        File selectedFile = new File(fullPath.toString());
        String details;
        if (selectedFile.exists()) {
            if (selectedFile.isDirectory()) {
                details = "Directory: " + selectedFile.getAbsolutePath();
            } else {
                details = "File: " + selectedFile.getAbsolutePath() +
                        " | Size: " + selectedFile.length() + " bytes";
            }
        } else {
            details = "Path does not exist: " + fullPath.toString();
        }
        label.setText(details);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyFrame f = new MyFrame();
            f.setSize(500, 500);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        });
    }
}