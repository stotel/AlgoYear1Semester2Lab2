package Models;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class ProductTableModel extends DefaultTableModel{
    String groupName;
    static ProductTableModel instance = new ProductTableModel(null);
    public static ProductTableModel getInstance() {
        return instance;
    }
    public ProductTableModel(String groupName){
        super(null,
                new String[]{"Name", "Group", "Description", "Manufacturer", "Price", "Quantity"});
        this.groupName = groupName;

        if(groupName != null){
            ProductTableModel model = this;
            instance.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent tableModelEvent) {
                    model.update();
                }
            });
            ProductTableModel.updateAll();
        }
    }



    // work with product(buy/sell)
    //--------------------------------------------------------------------------------------------------
    public void clear(){
        int rows = getRowCount();
        for(int i = 0; i < rows; i++) {
            removeRow(0);
        }
    }

    public void update(){
        clear();
        try {
            for(String i:Storage.getInstance().getGroups().get(groupName).getProducts().keySet()){
                Product p =Storage.getInstance().getGroups().get(groupName).getProducts().get(i);
                addRow(new String[]{p.getName(),groupName,p.getDescription(),p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
            }
        }
        catch (Exception e){
        }
    }
    public static void init(){
        for(String i : Storage.getInstance().getGroups().keySet()){
            for(String j: Storage.getInstance().getElement(i).getProducts().keySet()){
                Product p = Storage.getInstance().getElement(i).getProducts().get(j);
                instance.addRow(new String[]{p.getName(),i,p.getDescription(),p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
            }
        }
    }
    public static void updateAll(){
        instance.fireTableChanged(new TableModelEvent(instance));
    }
    static int indexOf(String name){
        for(int i = 0;i<instance.getRowCount();i++){
            if(instance.getValueAt(i,0).equals(name)){
                return i;
            }
        }
        return -1;
    }

    public double totalPrice(){
        double sum = 0;
        for(int i = 0; i < getRowCount(); i++){
            sum += Double.parseDouble((String.valueOf(getValueAt(i,4)))) * Double.parseDouble(String.valueOf(getValueAt(i,5)));
        }
        return sum;
    }
    public static void removeProduct(String name){
        Storage.getInstance().removeProduct(name);
        instance.removeRow(indexOf(name));
        updateAll();
    }
    public static void addProduct(String n, String g, String d, String man, String p, String q){
        double price = Double.parseDouble(p);
        int quantity = Integer.parseInt(q);
        Storage.getInstance().getGroups().get(g).appendElement(n,d,man,quantity,price);
        instance.addRow(new String[]{n,g,d,man,p,q});
        updateAll();
    }
    public static void editProduct(String oldName, String n, String g, String d, String man, String p, String q){
        double price = Double.parseDouble(p);
        int quantity = Integer.parseInt(q);

        ProductGroup gr = Storage.getInstance().findProduct(oldName).getGroup();
        gr.removeElement(oldName);
        Storage.getInstance().getElement(g).appendElement(n,d,man,quantity,price);
        int ind = indexOf(oldName);
        instance.setValueAt(n,ind,0);
        instance.setValueAt(g,ind,1);
        instance.setValueAt(d,ind,2);
        instance.setValueAt(man,ind,3);
        instance.setValueAt(p,ind,4);
        instance.setValueAt(q,ind,5);
        updateAll();
    }
    public static void tradeProduct(String name, int add){
        int ind = indexOf(name);
//<<<<<<< HEAD
        //String st = getInstance().getValueAt(ind,4).toString();
        //int val = Integer.parseInt(getInstance().getValueAt(ind,4).toString());
//=======
        int val = Integer.parseInt(String.valueOf(instance.getValueAt(ind,5)));
//>>>>>>> 20d8d2a62587e726bb20959565c10b3e81751f10
        val += add;
        instance.setValueAt(val, ind, 5);
        Storage.getInstance().findProduct(name).setQuantityInStock(val);
        updateAll();
    }
}
