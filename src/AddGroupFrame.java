import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGroupFrame extends JFrame {

    public AddGroupFrame(JFrame base){
        setTitle("Add group");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        //setLayout(new BorderLayout());
        add(new JLabel("Name: "));
        add(new JTextField());
        add(new JLabel("Description: "));
        add(new JTextField());
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //todo
            }
        });
        add(addButton);
        setLocationRelativeTo(base);
        setAlwaysOnTop(true);
    }
    public static void createAndShow(JFrame base){
        AddGroupFrame fr = new AddGroupFrame(base);
        base.setEnabled(false);
        fr.setVisible(true);

    }
}
