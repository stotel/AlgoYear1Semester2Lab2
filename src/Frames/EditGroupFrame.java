package Frames;

import Models.GroupTableModel;
import back.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
                if(t1.getText().isEmpty()||t2.getText().isEmpty()){
                    new ErrorFrame("you can not leave fields empty");
                    return;
                }
                if(Storage.getInstance().getGroups().containsKey(t1.getText())&& !Objects.equals(t1.getText(), groupName)){
                    new ErrorFrame("group with the same name already exists");
                    return;
                }
                if(t1.getText().contains("%")||t2.getText().contains("%")){
                    new ErrorFrame("'%' is an illegal symbol");
                    return;
                }
                GroupTableModel.editGroup(groupName, t1.getText(), t2.getText());
                dispose();
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
    boolean tryEdit(){
        if(t1.getText().contains("%")||t2.getText().contains("%")){
            new ErrorFrame("'%' is an illegal symbol");
            return false;
        }
        GroupTableModel.editGroup(groupName, t1.getText(), t2.getText());
        return true;
    }
    public static void createAndShow(JFrame base, String gname){
        EditGroupFrame fr = new EditGroupFrame(base, gname);
        fr.setVisible(true);
    }
}
