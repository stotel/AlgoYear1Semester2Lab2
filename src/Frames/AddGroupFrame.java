package Frames;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGroupFrame extends JFrame {

    JLabel l1,l2;
    JTextField t1,t2;
    JButton button;
    String groupName;
    public AddGroupFrame(JFrame base, String groupName){
        this.groupName = groupName;
        setTitle("Add group");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        init();
        setLocationRelativeTo(base);
    }
    void init(){
        l1 = new JLabel("Name: ");
        l2 = new JLabel("Description: ");
        t1 = new JTextField();
        t2 = new JTextField();
        button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //todo
            }
        });

        setLayout(new BorderLayout());
        JPanel jp1 = new JPanel(new GridLayout(2,2));
        jp1.add(l1);
        jp1.add(t1);
        jp1.add(l2);
        jp1.add(t2);

        add(jp1, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }
    public static void createAndShow(JFrame base, String groupName){
        AddGroupFrame fr = new AddGroupFrame(base, groupName);
        fr.setVisible(true);
    }
}
