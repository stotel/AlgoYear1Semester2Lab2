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
                new String[]{"Name", "Group", "Manufacturer", "Price", "Quantity"});
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
                addRow(new String[]{p.getName(),groupName,p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
            }
        }
        catch (Exception e){

        }
    }
    public static void init(){
        for(String i : Storage.getInstance().getGroups().keySet()){
            for(String j: Storage.getInstance().getElement(i).getProducts().keySet()){
                Product p = Storage.getInstance().getElement(i).getProducts().get(j);
                instance.addRow(new String[]{p.getName(),i,p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
            }
        }
    }
    public static void updateAll(){
        instance.fireTableChanged(new TableModelEvent(instance));
    }
    static int indexOf(String name){
        for(int i = 0;i<instance.getRowCount();i++){
            //System.out.println(instance.getValueAt(i,0));
            if(instance.getValueAt(i,0).equals(name)){
                return i;
            }
        }
        return -1;
    }

    public double totalPrice(){
        double sum = 0;
        for(int i = 0; i < getRowCount(); i++){
            sum += Double.parseDouble(((String)getValueAt(i,3))) * Double.parseDouble((String)getValueAt(i,4));
        }
        //System.out.println("Total price = "+sum);
        return sum;
    }
    public static void removeProduct(String name){
        Storage.getInstance().removeProduct(name);
        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //System.out.println(instance.getRowCount());
        instance.removeRow(indexOf(name));
        updateAll();
    }
    public static void addProduct(String n, String g, String man, String p, String q){
        double price = Double.parseDouble(p);
        int quantity = Integer.parseInt(q);
        Storage.getInstance().getGroups().get(g).appendElement(n,g,man,quantity,price);
        instance.addRow(new String[]{n,g,man,p,q});
        updateAll();
    }
    public static void editProduct(String oldName, String n, String g, String man, String p, String q){
        double price = Double.parseDouble(p);
        int quantity = Integer.parseInt(q);

        ProductGroup gr = Storage.getInstance().findProduct(oldName).getGroup();
        gr.removeElement(oldName);
        Storage.getInstance().getElement(g).appendElement(n,null,man,quantity,price);      // todo
        int ind = indexOf(oldName);
        instance.setValueAt(n,ind,0);
        instance.setValueAt(g,ind,1);
        instance.setValueAt(man,ind,2);
        instance.setValueAt(p,ind,3);
        instance.setValueAt(q,ind,4);
        //instance.setValueAt(null,ind,5); todo test
        updateAll();
    }
    public static void tradeProduct(String name, int add){
        int ind = indexOf(name);
        //String st = getInstance().getValueAt(ind,4).toString();
        int val = Integer.parseInt(getInstance().getValueAt(ind,4).toString());
        val += add;
        instance.setValueAt(val, ind, 4);
        Storage.getInstance().findProduct(name).setQuantityInStock(val);
        updateAll();
    }
}
