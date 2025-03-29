import javax.swing.*;
import java.awt.*;

class MyFrame1 extends JFrame
{
    MyFrame1(){
        JTextField tf = new JTextField(20);
        JButton b = new JButton("Click");
        setLayout(new FlowLayout());
        add(tf);
        add(b);

        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem o = new JMenuItem("Open");
        JMenuItem c = new JMenuItem("Close");
        file.add(o);
        file.add(c);

        mb.add(file);
        setJMenuBar(mb);
    }
}
public class SwingDemo {
    public static void main(String[] args) {
        MyFrame1 f = new MyFrame1();
        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();

        for (UIManager.LookAndFeelInfo info : plafs) {
            System.out.println(info.getName());
        }

        f.setSize(500,500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(UIManager.getLookAndFeel());
    }
}