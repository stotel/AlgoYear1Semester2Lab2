package Choosers;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductActionChooser extends ActionChooser{
    public ProductActionChooser(String name) {
        super(name);
    }

    @Override
    void init() {
        // Create buttons
        JButton deleteButton = new JButton("Delete");
        JButton workButton = new JButton("Work");
        JButton editButton = new JButton("Edit");

        // Create text area
        JTextArea textArea = new JTextArea(Storage.getInstance().findProduct(name).getDescription());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        // Add buttons to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(deleteButton);
        buttonPanel.add(workButton);
        buttonPanel.add(editButton);

        // Add components to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Add action listeners to buttons
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Delete button is clicked
                ProductTableModel.removeProduct(name);//  todo @Orest
                //System.out.println(name);
                dispose();
            }
        });

        workButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Add button is clicked
                // todo
                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Edit button is clicked
                // todo
                dispose();
            }
        });
    }

    public static void createAndShow(String productName){
        ProductActionChooser pc = new ProductActionChooser(productName);
        pc.setVisible(true);
    }
}
