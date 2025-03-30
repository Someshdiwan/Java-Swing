package InternalFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class demonstrates the use of a JInternalFrame in a Swing application.
 *
 * A JInternalFrame is a lightweight component that provides features similar
 * to a native window but resides inside a container (like a JDesktopPane).
 *
 * For more information, see the official JInternalFrame documentation:
 * <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JInternalFrame.html">JInternalFrame Documentation</a>
 */

class MyInternalFrame extends JInternalFrame
{
    /*
    Why i extended from JInternalFrame,
    I want that internal frame to have its own menu
    and also text Area and text area inside JScrollFrame.
    */

    static int count = 0;
    JTextArea ta;
    JScrollPane sp;

    MyInternalFrame()
    {
        super("Document"+(++count),true,true,true,true);
        //Document 1 Document 2 ....Like this

        ta=new JTextArea();
        sp=new JScrollPane(ta);

        add(sp);

        //Created a menu bar and save it.
        JMenuBar mb = new JMenuBar();

        //Single menu. File
        JMenu file = new JMenu("File");
        JMenuItem m1 = new JMenuItem("Save");

        file.add(m1);
        mb.add(file);
        setJMenuBar(mb);

        setSize(300,300);
        setLocation(50,50);
        setVisible(true);
    }

}

class MyFrame extends JFrame implements ActionListener
{
    JDesktopPane jp;
    MyFrame()
    {
        super("Internal Pane Demo");
        jp=new JDesktopPane();
        setContentPane(jp);

        JMenuBar mb = new JMenuBar();
        JMenu d = new JMenu("Document");
        JMenuItem m1 = new JMenuItem("New");

        d.add(m1);
        mb.add(d);
        setJMenuBar(mb);

        m1.addActionListener((ActionListener) this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyInternalFrame mi = new MyInternalFrame();
        jp.add(mi);
    }
}

public class SwingInternalFrame {
    public static void main(String[] args) {
        MyFrame mf = new MyFrame();
        mf.setSize(800,800);
        mf.setVisible(true);

        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}