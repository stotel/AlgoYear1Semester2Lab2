package Models;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class GroupTableModel extends DefaultTableModel {
    static GroupTableModel instance = new GroupTableModel();;
    public static GroupTableModel getInstance() {
        return instance;
    }
    public GroupTableModel(){
        super(null,
                new String[]{"Name","Description"});
    }

    /**
     * Names only
     * // todo
     */
    public static String[] getGroups(){
        String[] entries = new String[instance.getDataVector().capacity()];
        int i = 0;
        for (Object a: GroupTableModel.getInstance().getDataVector()) {
            Vector<String> v = (Vector<String>)a;
            entries[i++] = v.get(0);
        }
        return entries;
    }

    //-------------------------------------------------------------------------------------
    public static void addGroup(String name, String desc){
        instance.addRow(new String[]{name, desc});
        //it is ok. Storage.getInstance().appendElement(name,desc);
    }

    public static void removeGroup(String name){
        //it is ok. Storage.getInstance().removeElement(name);
        for(int i = 0;i<instance.getRowCount();i++){
            //System.out.println(instance.getValueAt(i,0));
            if(instance.getValueAt(i,0).equals(name)){
                instance.removeRow(i);
            }
        }
    }
    public static void editGroup(String oldName, String newName, String newDesc){
        for(int i = 0;i<instance.getRowCount();i++){
            //System.out.println(instance.getValueAt(i,0));
            if(instance.getValueAt(i,0).equals(oldName)){
                instance.setValueAt(newName, i, 0);
                instance.setValueAt(newDesc, i, 1);
                Storage.getInstance().redactElement(oldName, newName, newDesc);
            }
        }
    }
}
