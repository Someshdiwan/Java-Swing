package SwingSpinner;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


class MyFrame extends JFrame implements ActionListener,ListSelectionListener,ChangeListener
{
    JComboBox cb;
    JList list;
    JSpinner sp1,sp2;
    JTextField tf;

    MyFrame()
    {
        super("Spinner Demo");

        //Data and model and view = MVC.
        String countries[]={"India","US","UK","Canada","Australia"};
        String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
        String days[]={"Monday","Tuesday","Wednesday","Thursday","Firday","Saturday","Sunday"};

        cb=new JComboBox(countries);
/*      JComboBox is component. but it is containing data and that data coming from a list of array and this dara
        is called as model.
        and it will be shown inside the combo box and that combo box  is visual component so, it is view and
        when you click on the  combo box something should happen that handling event, so we called as controller.
        so activity is called as controller and the visual thing as view and list is called as the Data.
        it is the MVC Model view controller so swing support MVC Architecture.
*/

        list=new JList(months);
        list.setVisibleRowCount(5);
        tf=new JTextField(15);

        sp1=new JSpinner(new SpinnerNumberModel(1,1,31,1));
        sp2=new JSpinner(new SpinnerListModel(days));

        setLayout(new FlowLayout());
        add(cb);
        add(sp1);
        add(sp2);
        add(new JScrollPane(list));
        add(tf);

        cb.addActionListener(this);
        list.addListSelectionListener(this);
        sp1.addChangeListener(this);
        sp2.addChangeListener(this);

    }

    public void actionPerformed(ActionEvent ae)
    {
        tf.setText((String)cb.getSelectedItem());
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        tf.setText((String)list.getSelectedValue());
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        if(e.getSource()==sp1)
            tf.setText(sp1.getValue()+"");
        else
            tf.setText((String)sp2.getValue());
    }
}

public class SwingSpinner
{
    public static void main(String[] args)
    {
        MyFrame f=new MyFrame();
        f.setSize(300,300);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}