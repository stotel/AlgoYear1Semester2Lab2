package Frames;

import Models.ProductTableModel;
import back.Product;
import back.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductFrame extends JFrame {

    JTextField t1,groupTextField,t2,t3,t4,t5;
    JButton button;
    Product product;
    public EditProductFrame(Product pr){
        product = pr;
        setTitle("Edit product "+pr.getName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 600);
        init(pr.getName());
        setLocationRelativeTo(null);
    }
    void init(String pName){
        t1 = new JTextField(product.getName());
        groupTextField = new JTextField(product.getGroup().getName());
        t2 = new JTextField(product.getDescription());
        t3 = new JTextField(product.getManufacturer());
        t4 = new JTextField(String.valueOf(product.getPricePerUnit()));
        t5 = new JTextField(String.valueOf(product.getQuantityInStock()));
        button = new JButton("Edit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(t1.getText().isEmpty()||t2.getText().isEmpty()){
                    new ErrorFrame("you can not leave fields empty");
                    return;
                }
                if(Storage.getInstance().isProductWithNamePresent(t1.getText())){
                    new ErrorFrame("product with the same name already exists");
                    return;
                }
                if(t1.getText().contains("%")||t2.getText().contains("%")||t3.getText().contains("%")||t4.getText().contains("%")||t5.getText().contains("%")){
                    new ErrorFrame("'%' is an illegal symbol");
                    return;
                }
                try {
                    Double.parseDouble(t4.getText());
                } catch (NumberFormatException z) {
                    new ErrorFrame("The string \"" + t4.getText() + "\" cannot be converted to a double.");
                    return;
                }
                try {
                    Integer.parseInt(t5.getText());
                } catch (NumberFormatException z) {
                    new ErrorFrame("The string \"" + t5.getText() + "\" cannot be converted to an int.");
                    return;
                }
                tryEdit(pName);
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
    void tryEdit(String pName){
        String group = groupTextField.getText();
        if(Storage.getInstance().getGroups().get(group) != null) {
            ProductTableModel.removeProduct(pName);
            ProductTableModel.addProduct(t1.getText(), groupTextField.getText(), t3.getText(), t4.getText(), t5.getText());
        }
        else JOptionPane.showMessageDialog(null, "There is no such group, as \""+group+"\"");

    }
    public static void createAndShow(Product pr){
        EditProductFrame fr = new EditProductFrame(pr);
        fr.setVisible(true);
    }
}
