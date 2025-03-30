package OpenFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

class MyFrame extends JFrame implements ActionListener {
    JToolBar tb;
    JButton b1, b2, b3, b4, b5, b6, b7;
    JTextArea ta;

    MyFrame() {
        super("Tool Bar");
        tb = new JToolBar();

        b1 = new JButton(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\JMenuBar and JToolBar 1 interface\\images\\open 1.png"));
        b2 = new JButton(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\JMenuBar and JToolBar 1 interface\\images\\save 2.png"));
        b3 = new JButton(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\JMenuBar and JToolBar 1 interface\\images\\add 2.png"));
        b4 = new JButton(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\JMenuBar and JToolBar 1 interface\\images\\cut 2.png"));
        b5 = new JButton(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\JMenuBar and JToolBar 1 interface\\images\\copy 2.png"));
        b6 = new JButton(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\JMenuBar and JToolBar 1 interface\\images\\split 2.png"));
        b7 = new JButton(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\JMenuBar and JToolBar 1 interface\\images\\close 2.png"));

        tb.add(b1);
        tb.add(b2);
        tb.add(b3);
        tb.add(new JToolBar.Separator());
        tb.add(b4);
        tb.add(b5);
        tb.add(b6);
        tb.add(b7);

        ta = new JTextArea();
        JScrollPane sp = new JScrollPane(ta);

        add(tb, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);

        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("file");

        JMenuItem m1 = new JMenuItem("save");
        JMenuItem m2 = new JMenuItem("open");
        m2.setMnemonic(KeyEvent.VK_O); //Key Short-cut for menu

        file.add(m1);
        file.addSeparator(); //separate save and open.
        file.add(m2);

        mb.add(file);
        setJMenuBar(mb);

        b1.setActionCommand("open");
        b1.addActionListener(this);

        m2.addActionListener(this); //Action listener method call hoga.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        File f = fc.getSelectedFile();
        //open the file save the file read the file. You have to need FileIo Streams.
        try {
            FileInputStream fi = new FileInputStream(f);
            byte b[] = new byte[fi.available()];
            fi.read(b);
            String str = new String(b);

            ta.setText(str);
            fi.close();
        } catch (Exception o) {
        }
    }
}

public class SwingMenuTool {
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(800,800);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}