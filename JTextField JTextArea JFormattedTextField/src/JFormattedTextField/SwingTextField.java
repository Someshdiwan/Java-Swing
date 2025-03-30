package JFormattedTextField;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.*;
import java.util.*;

class MyFrame extends JFrame {
    MyFrame() {

        // Set a layout manager so that multiple components can be seen.
        setLayout(new FlowLayout());

        //Formated only for the numbers and date etc.
        JFormattedTextField tf2 = new JFormattedTextField();

        tf2.setColumns(15);
        tf2.setValue(new Date());
        add(tf2);

        //You can restrict a text field a particular formatted.
        //When ever you do a number formating you have to follow these steps.

        //NumberFormat nf = NumberFormat.getInstance();
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

        NumberFormatter nft = new NumberFormatter(nf);
        nft.setMaximum(10000);
        //Don't allow a Invalid Char.
        nft.setAllowsInvalid(false);

        JFormattedTextField tf3 = new JFormattedTextField(nft);
        tf3.setColumns(15);
        tf3.setValue(0);
        add(tf3);
    }
}

public class SwingTextField {
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(500,500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}