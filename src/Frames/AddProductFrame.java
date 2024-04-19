package Frames;

import Models.GroupTableModel;
import Models.ProductTableModel;
import back.Product;
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
                if(tryAdd())
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
    boolean tryAdd() {
        String group = groupTextField.getText();
        if(t1.getText().isEmpty()||groupTextField.getText().isEmpty()||t2.getText().isEmpty()){
            new ErrorFrame("you can not leave fields empty");
            return false;
        }
        if (Storage.getInstance().getGroups().get(group) == null) {
            new ErrorFrame("There is no such group, as \"" + group + "\"");
            return false;
        }
        if(Storage.getInstance().isProductWithNamePresent(t1.getText())){
            new ErrorFrame("Product with the same name already exists");
            return false;
        }
        if(t1.getText().contains("%")||groupTextField.getText().contains("%")||t2.getText().contains("%")||t3.getText().contains("%")||t4.getText().contains("%")||t5.getText().contains("%")){
            new ErrorFrame("'%' is an illegal symbol");
            return false;
        }
        try {
            Double.parseDouble(t4.getText());
        } catch (NumberFormatException z) {
            new ErrorFrame("The string \"" + t4.getText() + "\" cannot be converted to a double.");
            return false;
        }
        try {
            Integer.parseInt(t5.getText());
        } catch (NumberFormatException z) {
            new ErrorFrame("The string \"" + t5.getText() + "\" cannot be converted to an int.");
            return false;
        }
        if (Double.parseDouble(t4.getText())<0||Integer.parseInt(t5.getText())<0){
            new ErrorFrame("Those numeral values cant be negative");
            return false;
        }
        ProductTableModel.addProduct(t1.getText(), groupTextField.getText(), t2.getText(), t3.getText(), t4.getText(), t5.getText());
        return true;
    }
    public static void createAndShow(JFrame base, String groupName){
        AddProductFrame fr = new AddProductFrame(base, groupName);
        fr.setVisible(true);
    }
}