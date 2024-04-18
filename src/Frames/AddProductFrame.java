package Frames;

import Models.GroupTableModel;
import Models.ProductTableModel;
import back.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductFrame extends JFrame {

    JTextField t1,groupTextField,t2,t3,t4,t5;
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
        t1 = new JTextField();
        groupTextField = new JTextField(groupName);
        t2 = new JTextField();
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
        JPanel jp1 = new JPanel(new GridLayout(6,2));
        jp1.add(new JLabel("Name: "));
        jp1.add(t1);
        jp1.add(new JLabel("Group:"));
        jp1.add(groupTextField);
        jp1.add(new JLabel("Description: "));
        jp1.add(t2);
        jp1.add(new JLabel("Manufacturer: "));
        jp1.add(t3);
        jp1.add(new JLabel("Price: "));
        jp1.add(t4);
        jp1.add(new JLabel("Quantity: "));
        jp1.add(t5);

        add(jp1, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }
    void tryAdd(){
        String group = groupTextField.getText();
        if(Storage.getInstance().getGroups().get(group) != null)
            ProductTableModel.addProduct(t1.getText(), groupTextField.getText(),t3.getText(),t4.getText(),t5.getText());
        else JOptionPane.showMessageDialog(null, "There is no such group, as \""+group+"\"");

    }
    public static void createAndShow(JFrame base, String groupName){
        AddProductFrame fr = new AddProductFrame(base, groupName);
        fr.setVisible(true);
    }
}
