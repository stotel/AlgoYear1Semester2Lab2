package Models;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import javax.swing.table.DefaultTableModel;

public class GroupTableModel extends DefaultTableModel {
    static GroupTableModel instance;
    public static GroupTableModel getInstance() {
        return instance;
    }
    public GroupTableModel(){
        super(null,
                new String[]{"Name"});
    }

    public static void init(){
        instance = new GroupTableModel();
        // todo @Orest
    }

    public static void removeGroup(String name){
        Storage.getInstance().removeElement(name);
        for(int i = 0;i<instance.getRowCount();i++){
            //System.out.println(instance.getValueAt(i,0));
            if(instance.getValueAt(i,0).equals(name)){
                instance.removeRow(i);
            }
        }
    }

    public static void addGroup(){

    }

    public static void editGroup(){

    }
}
