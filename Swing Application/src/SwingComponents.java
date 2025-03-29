import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple Swing application demonstrating the use of JComponent.
 * <p>
 * This class showcases how to create a basic Swing application.
 * For further details on JComponent and its functionality, refer to the
 * official Java documentation:
 * <ul>
 *   <li><a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JComponent.html">JComponent Documentation</a></li>
 * </ul>
 * </p>
 *
 * @see javax.swing.JComponent
 */

class MyFrame extends JFrame implements ActionListener {
    JLabel l;
    JButton b;
    int count =0;

    MyFrame()
    {
        super("Swing Application");
        setLayout(new FlowLayout());

        l=new JLabel("Clicked "+count+" Times");
        b=new JButton("Click to increase the count");

        add(l);
        add(b);
        add(new JButton("Cancel"));

        b.addActionListener(this);

        //Setting a default button for a Frane that is RootPain.
        getRootPane().setDefaultButton(b);
        //b.setIcon(new ImageIcon("C:\\Users\\somes\\Downloads\\Java-Swing\\Swing Application\\src\\click.png"));

        l.setToolTipText("Counter"); //Tool Tip help.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        l.setText("Clicked "+count+" Times");
    }
}

public class SwingComponents {
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(500,50);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //In AWT We're handling Window Listener to handling CLosing.
        //to stop the application manually

    }
}