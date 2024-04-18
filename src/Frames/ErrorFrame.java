package Frames;

import javax.swing.*;

public class ErrorFrame extends JFrame {
        public ErrorFrame(String message){
            setTitle("Error");

            // Create a JLabel to indicate the error message
            JLabel errorLabel = new JLabel(message, SwingConstants.CENTER);

            // Set the font and foreground color to make it noticeable
            errorLabel.setFont(errorLabel.getFont().deriveFont(12.0f));
            errorLabel.setForeground(java.awt.Color.RED);

            // Add the error label to the frame
            add(errorLabel);

            // Set frame properties
            setSize(300, 100);
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Center the frame on the screen
            setVisible(true);
        }
        // Create a JFrame
}
