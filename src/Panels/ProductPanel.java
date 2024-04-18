package Panels;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
//import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductPanel extends JPanel {
    static String groupName;
    static JTextField searchField;
    public static JPanel getProductPanel(String grName) {
        groupName = grName;
        ProductTableModel model = ProductTableModel.getInstance();
        if(groupName != null)
            model = new ProductTableModel(groupName);

        double totalPrice = model.totalPrice();
        //----------------------------------------------------------------------------------------------
        JTable table = MainFrame.getTable(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = (String)table.getValueAt(table.getSelectedRow(), 0);
                ProductActionChooser.createAndShow(name);
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Create JPanel for table view
        JPanel jp = new JPanel(new GridLayout(2,2));
        JButton search = new JButton("Search");
        JButton add = new JButton("Add product");
        searchField = new JTextField();
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tryFind();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddProductFrame.createAndShow(null, groupName);
            }
        });

        JLabel label = new JLabel(Double.toString(totalPrice));
        ProductTableModel finalModel = model;
        ProductTableModel.getInstance().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent) {
                label.setText(String.valueOf(finalModel.totalPrice()));
            }
        });

        jp.add(new JLabel("Total price: "));
        jp.add(label);
        jp.add(search);
        jp.add(searchField);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.NORTH);
        panel.add(jp, BorderLayout.CENTER);
        panel.add(add, BorderLayout.SOUTH);

        return panel;
    }

    static void tryFind(){
        String target = searchField.getText();
        if(Storage.getInstance().findProduct(target) != null)
            ProductActionChooser.createAndShow(target);
        else new ErrorFrame("There is no such product, as \""+target+"\"");
    }
}
