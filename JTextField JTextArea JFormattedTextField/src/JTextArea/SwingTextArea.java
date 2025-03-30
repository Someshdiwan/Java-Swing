package JTextArea;

import javax.swing.*;
import java.awt.*;

class MyTextAreaFrame extends JFrame {
    MyTextAreaFrame() {
        // Set a layout manager so that the text area is displayed properly.
        setLayout(new FlowLayout());

        // Create a JTextArea with 30 rows and 30 columns.
        JTextArea ta = new JTextArea(30, 30);
        // Set initial text for the text area.
        ta.setText("Enter your text here...");

        // Optionally, add the JTextArea to a scroll pane.
        JScrollPane sp = new JScrollPane(ta);
        add(sp);
    }
}

public class SwingTextArea {
    public static void main(String[] args) {
        MyTextAreaFrame f = new MyTextAreaFrame();
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}