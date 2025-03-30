package Tree1;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.StringTokenizer;

class MyFrame extends JFrame implements TreeSelectionListener {
    JTree t;
    JLabel l;

    MyFrame() {
        DefaultMutableTreeNode drive = new DefaultMutableTreeNode("C://Users//somes//Downloads//Java-Swing");

        //drive.add(new DefaultMutableTreeNode(new Button("Ok")));
        File f1 = new File("C://Users//somes//Downloads//Java-Swing");

        for (File x : f1.listFiles()) {
            if (x.isDirectory()) {
                DefaultMutableTreeNode temp = new DefaultMutableTreeNode(x.getName());
                for (File y : x.listFiles())
                    temp.add(new DefaultMutableTreeNode(y.getName()));
                drive.add(temp);
            } else
                drive.add(new DefaultMutableTreeNode(x.getName()));
        }
        t = new JTree(drive);
        JScrollPane jp = new JScrollPane(t);
        l = new JLabel("No Files Selected");

        t.addTreeSelectionListener(this);

        add(jp, BorderLayout.NORTH);
        add(l, BorderLayout.SOUTH);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath tp = e.getPath();

        //DefaultMutableTreeNode tn=(DefaultMutableTreeNode)e.getSource();
        StringTokenizer sz = new StringTokenizer(tp.toString(), ",]");
        String str = "";
        while (sz.hasMoreTokens())
            str = sz.nextToken();
        l.setText(str);
    }
}

public class SwingTree {
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}