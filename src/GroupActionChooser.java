import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GroupActionChooser extends JFrame {
    public GroupActionChooser(String name) {
        // Set the title of the window
        setTitle(name);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons
        JButton deleteButton = new JButton("Delete");
        JButton addButton = new JButton("Add");
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
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);

        // Add components to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

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

        // Disable the window's close button
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Make the window visible
        setVisible(true);
    }
}