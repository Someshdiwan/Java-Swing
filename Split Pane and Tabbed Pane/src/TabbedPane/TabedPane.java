package TabbedPane;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame extends JFrame implements ListSelectionListener
{
    JSplitPane sp;
    JList list;
    JLabel lbl;

    MyFrame()
    {
        super("Split Demo");

        //Data and its is also called as Model.
        String cols[]={"RED","GREEN","BLUE","YELLOW","MAGENTA","ORANGE","BLACK"};

        list=new JList(cols); //Passing array of list. Data and model and component on screen is List.
        //View

        //Handel Event on the list.
        list.setSelectedIndex(0);

        list.addListSelectionListener(this);

        JScrollPane p1 = new JScrollPane(list);

        lbl = new JLabel(" ");
        lbl.setOpaque(true);

        lbl.setBackground(Color.RED); //Setting default color of the Label Red.
        JScrollPane p2 = new JScrollPane(lbl);

        JTabbedPane tp = new JTabbedPane();
        tp.addTab("Colors",p1);
        tp.addTab("Label",p2);
        //Able to communicate between the Across the tabs.
        //change the tab action will be changed into the another tab.
        add(tp);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String str = (String)list.getSelectedValue();

        switch (str)
        {
            case "RED": lbl.setBackground(Color.RED);
                break;
            case "GREEN": lbl.setBackground(Color.GREEN);
                break;
            case "BLUE": lbl.setBackground(Color.BLUE);
                break;
            case "YELLOW": lbl.setBackground(Color.YELLOW);
                break;
            case "MAGENTA": lbl.setBackground(Color.MAGENTA);
                break;
            case "ORANGE": lbl.setBackground(Color.orange);
                break;
            case "BLACK": lbl.setBackground(Color.BLACK);
                break;
        }
    }
}
public class TabedPane {
    public static void main(String[] args)
    {
            MyFrame f = new MyFrame();
            f.setSize(800,800);
            f.setVisible(true);

            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}