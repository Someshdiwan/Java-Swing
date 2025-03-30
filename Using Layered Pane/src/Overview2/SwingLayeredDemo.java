package Overview2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame extends JFrame implements ActionListener {
    JRadioButton r1, r2, r3;
    JLayeredPane lp;
    JLabel l1, l2, l3, l4;

    MyFrame() {
        super("Layered Demo");

        // Setup radio buttons
        r1 = new JRadioButton("Red");
        r2 = new JRadioButton("Green");
        r3 = new JRadioButton("Blue", true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        r1.addActionListener(this);
        r2.addActionListener(this);
        r3.addActionListener(this);

        // Setup labels with background colors and bounds
        l1 = new JLabel("Red", SwingConstants.CENTER);
        l1.setBounds(10, 10, 200, 200);
        l1.setBackground(Color.red);
        l1.setOpaque(true);

        l2 = new JLabel("Green", SwingConstants.CENTER);
        l2.setBounds(50, 50, 250, 250);
        l2.setBackground(Color.green);
        l2.setOpaque(true);

        l3 = new JLabel("Blue", SwingConstants.CENTER);
        l3.setBounds(100, 100, 300, 300);
        l3.setBackground(Color.blue);
        l3.setOpaque(true);

        l4 = new JLabel("b", SwingConstants.CENTER);
        l4.setBounds(50, 50, 70, 70);
        l4.setBackground(Color.black);
        l4.setOpaque(true);

        // Setup layered pane with a null layout (absolute positioning)
        lp = new JLayeredPane();
        lp.setPreferredSize(new Dimension(400, 400));
        lp.add(l1, new Integer(0));
        lp.add(l2, new Integer(1));
        lp.add(l3, new Integer(2));
        lp.add(l4, new Integer(4));

        // Panel for radio buttons
        JPanel p = new JPanel();
        p.add(r1);
        p.add(r2);
        p.add(r3);
        add(p, BorderLayout.NORTH);
        add(lp, BorderLayout.CENTER);

        // Add mouse motion listener to the layered pane (not the frame)
        lp.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                l4.setLocation(x, y);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Change the layer of l4 based on the selected radio button.
        if (r1.isSelected()) {
            lp.setLayer(l4, 1);
        } else if (r2.isSelected()) {
            lp.setLayer(l4, 2);
        } else if (r3.isSelected()) {
            lp.setLayer(l4, 3);
        }
    }
}

public class SwingLayeredDemo {
    public static void main(String[] args) {
        // Ensure the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyFrame f = new MyFrame();
                f.setSize(800, 800);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
            }
        });
    }
}