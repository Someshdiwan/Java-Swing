package Overivew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame extends JFrame implements ActionListener {
    JLayeredPane lp;
    JRadioButton r1, r2, r3;
    JLabel l1, l2, l3, l4;

    MyFrame() {
        super("Layered Demo");

        // Create radio buttons and group them
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

        // Panel for radio buttons
        JPanel p1 = new JPanel();
        p1.add(r1);
        p1.add(r2);
        p1.add(r3);

        // Create labels with colors, text centered, and absolute positions
        l1 = new JLabel("Red", SwingConstants.CENTER);
        l1.setBounds(10, 10, 200, 200);
        l1.setBackground(Color.red);
        l1.setOpaque(true);

        l2 = new JLabel("Green", SwingConstants.CENTER);
        l2.setBounds(50, 50, 250, 250);
        l2.setBackground(Color.green);
        l2.setOpaque(true);

        l3 = new JLabel("Blue", SwingConstants.CENTER);
        l3.setBounds(70, 70, 300, 300);
        l3.setBackground(Color.blue);
        l3.setOpaque(true);

        l4 = new JLabel("b", SwingConstants.CENTER);
        l4.setBounds(100, 100, 150, 150);
        l4.setBackground(Color.black);
        l4.setOpaque(true);

        // Create a layered pane with null layout for absolute positioning
        lp = new JLayeredPane();
        lp.setLayout(null);
        lp.setPreferredSize(new Dimension(400, 400));
        lp.add(l1, Integer.valueOf(0));
        lp.add(l2, Integer.valueOf(1));
        lp.add(l3, Integer.valueOf(2));
        lp.add(l4, Integer.valueOf(3));

        // Add components to the frame
        add(p1, BorderLayout.NORTH);
        add(lp, BorderLayout.CENTER);

        // Add mouse motion listener to the layered pane for proper coordinate reference
        lp.addMouseMotionListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent me) {
                l4.setLocation(me.getX(), me.getY());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Change the layer of l4 based on the selected radio button
        if(r1.isSelected())
            lp.setLayer(l4, 1);
        else if(r2.isSelected())
            lp.setLayer(l4, 2);
        else if(r3.isSelected())
            lp.setLayer(l4, 3);
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