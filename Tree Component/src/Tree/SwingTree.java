package Tree;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.StringTokenizer;

class MyFrame extends JFrame implements TreeSelectionListener
{
    JTree tree;
    JLabel label;

    MyFrame()
    {
        super("Tree Demo");
        //valid path must be given
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("C://Users//somes//Downloads//Java-Swing");
        File f=new File("C://Users//somes//Downloads//Java-Swing");

        for(File x: f.listFiles())
        {
            if(x.isDirectory())
            {
                DefaultMutableTreeNode temp=new DefaultMutableTreeNode(x.getName());
                for(File y:x.listFiles())
                {
                    temp.add(new DefaultMutableTreeNode(x.getName()));
                }
                root.add(temp);
            }
            else
                root.add(new DefaultMutableTreeNode(x.getName()));
        }

        tree=new JTree(root);
        label=new JLabel("No Files Selected");

        tree.addTreeSelectionListener(this);
        JScrollPane sp=new JScrollPane(tree);

        add(sp,BorderLayout.CENTER);
        add(label,BorderLayout.SOUTH);

    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        label.setText(e.getPath().toString());
    }
}

public class SwingTree
{
    public static void main(String[] args)
    {
        MyFrame f=new MyFrame();
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}