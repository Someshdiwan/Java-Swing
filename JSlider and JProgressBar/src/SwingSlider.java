import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * This class demonstrates the usage of a JSlider component.
 *
 * For more details on JSlider, see the official documentation:
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/javax/swing/JSlider.html">JSlider Documentation</a>
 */

class MyFrame extends JFrame implements ChangeListener {
    JSlider sl;
    JPanel p1, p2;
    JProgressBar pb;

    int w = 50;

    MyFrame() {
        sl = new JSlider(0, 100, 50); //Orientation and its is Constructor.
        sl.setMajorTickSpacing(10);
        sl.setMinorTickSpacing(1);

        sl.setPaintTicks(true);
        sl.setPaintLabels(true);

        pb = new JProgressBar();
        pb.setString("50%");
        pb.setStringPainted(true);

        pb = new JProgressBar();
        pb.setString("50%");
        pb.setStringPainted(true);
        pb.setStringPainted(true);
        //pb.setIndeterminate(true);

        //Need a panel. and creating an object of panel we need drawing.
        //drawing was done paint method of component. in AWT
        //But in swing, the method name is paintComponent(Takes parameter graphic g)
        //Override a method and use anonymous class for JPanel.
        p1 = new JPanel() {
            public void paintComponent(Graphics g) {
                // Clear the panel before drawing the oval.
                super.paintComponent(g);
                g.drawOval(200, 200, w, w);
            }
        };

        //Create a second panel and a progress bar in that panel.
        p2 = new JPanel();
        p2.add(pb);

        add(sl, BorderLayout.NORTH);

        add(p1, BorderLayout.CENTER);

        add(p2, BorderLayout.SOUTH);

        sl.addChangeListener(this);
    }

    //Now we have to handel a EventHandler.
    //add a event handler on the slider bar so i change the circle and progress bar aka slider.
    @Override
    public void stateChanged(ChangeEvent e) {
        //Get the value from a slider.
        w = sl.getValue();
        //Once's the 'w' is changed then i should repaint the panel.
        p1.repaint(); //redraw the circle with different width and height.

        //In progress bar string is 0% then we should change the string.
        pb.setString(w+"%");

        //change the value of the progress bar.
        pb.setValue(w);
    }
}

public class SwingSlider {
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(500,500);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}