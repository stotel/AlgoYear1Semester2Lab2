package Choosers;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import back.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GroupActionChooser extends ActionChooser {
    public GroupActionChooser(String name) {
        super(name);
    }

    @Override
    void init(){
        // Create buttons
        JButton deleteButton = new JButton("Delete");
        JButton viewButton = new JButton("View");
        JButton editButton = new JButton("Edit");

        // Create text area
        JTextArea textArea = new JTextArea(Storage.getInstance().getElement(name).getDescription());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        // Add buttons to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);

        // Add components to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Add action listeners to buttons
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Delete button is clicked
                Storage.getInstance().removeElement(name);
                //GroupTableModel.removeGroup(); todo @Orest
                dispose();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Add button is clicked
                ProductFrame.createAndShow(MainFrame.getInstance(), name);
                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Edit button is clicked
                //todo
                dispose();
            }
        });
    }
    public static void createAndShow(String name){
        GroupActionChooser gc = new GroupActionChooser(name);
        gc.setVisible(true);
    }
}