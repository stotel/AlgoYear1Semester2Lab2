import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GroupActionChooser extends JFrame {
    public GroupActionChooser(String name) {
        // Set the title of the window
        setTitle(name);

        // Set the size of the window
        setSize(200, 300);

        // Set the layout manager for the window
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        GridLayout layout = new GridLayout(3, 1);
        setLayout(layout);
        // Create buttons
        JButton deleteButton = new JButton("Delete");
        //deleteButton.setSize(200,100);
        //deleteButton.setAlignmentX(0);
        JButton addButton = new JButton("Add");
        //addButton.setAlignmentX(0);
        //addButton.setSize(200,100);
        JButton editButton = new JButton("Edit");
        //editButton.setAlignmentX(0);
        //editButton.setSize(200,100);

        // Add action listeners to buttons
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Delete button is clicked
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Add button is clicked
                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the window when Edit button is clicked
                dispose();
            }
        });

        // Add buttons to the window
        add(deleteButton);
        add(addButton);
        add(editButton);

        // Disable the window's close button
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Make the window visible
        setVisible(true);
    }
}