package Frames;

import back.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGroupFrame extends AddGroupFrame{

    public EditGroupFrame(JFrame base, String groupName) {
        super(base, groupName);
        setTitle("Edit group \""+groupName+"\"");
    }

    @Override
    void init(){
        l1 = new JLabel("Name: ");
        l2 = new JLabel("Description: ");
        t1 = new JTextField(groupName);
        t2 = new JTextField(Storage.getInstance().getElement(groupName).getDescription());
        button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Storage.getInstance().redactElement(groupName,t1.getText(),t2.getText());
                dispose(); //todo
            }
        });

        setLayout(new BorderLayout());
        JPanel jp1 = new JPanel(new GridLayout(2,2));
        jp1.add(l1);
        jp1.add(t1);
        jp1.add(l2);
        jp1.add(t2);

        add(jp1, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }

    public static void createAndShow(JFrame base, String gname){
        EditGroupFrame fr = new EditGroupFrame(base, gname);
        fr.setVisible(true);
    }
}
