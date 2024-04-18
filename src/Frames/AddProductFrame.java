package Frames;

import Models.GroupTableModel;
import Models.ProductTableModel;
import back.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductFrame extends JFrame {

    JLabel l1,l2,l3,l5,l4;
    JTextField t1,t2,t3,t4,t5;
    JButton button;
    String groupName;
    public AddProductFrame(JFrame base, String groupName){
        this.groupName = groupName;
        setTitle("Add product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 600);
        init();
        setLocationRelativeTo(base);
    }
    void init(){
        l1 = new JLabel("Name: ");
        l2 = new JLabel("Group: ");
        l3 = new JLabel("Manufacturer: ");
        l4 = new JLabel("Price: ");
        l5 = new JLabel("Quantity: ");
        t1 = new JTextField();
        t2 = new JTextField(groupName);
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryAdd();
                dispose();
            }
        });

        setLayout(new BorderLayout());
        JPanel jp1 = new JPanel(new GridLayout(5,2));
        jp1.add(l1);
        jp1.add(t1);
        jp1.add(l2);
        jp1.add(t2);
        jp1.add(l3);
        jp1.add(t3);
        jp1.add(l4);
        jp1.add(t4);
        jp1.add(l5);
        jp1.add(t5);

        add(jp1, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }
    void tryAdd(){
        String group = t2.getText();
        if(Storage.getInstance().getGroups().get(group) != null)
            ProductTableModel.addProduct(t1.getText(), t2.getText(),t3.getText(),t4.getText(),t5.getText());
        else JOptionPane.showMessageDialog(null, "There is no such group, as \""+group+"\"");

    }
    public static void createAndShow(JFrame base, String groupName){
        AddProductFrame fr = new AddProductFrame(base, groupName);
        fr.setVisible(true);
    }
}
