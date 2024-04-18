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
import java.util.Set;
import java.util.Vector;

public class ProductTableModel extends DefaultTableModel{
    String groupName;
    static ProductTableModel instance;
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

    public void clear(){
        int rows = getRowCount();
        for(int i = 0; i < rows; i++) {
            removeRow(0);
        }
    }
    public void update(){
        clear();
        for(String i:Storage.getInstance().getGroups().get(groupName).getProducts().keySet()){
            Product p =Storage.getInstance().getGroups().get(groupName).getProducts().get(i);
            addRow(new String[]{p.getName(),groupName,p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
        }
    }
    public static void init(){
        instance = new ProductTableModel(null);
        //fill all products
        //Storage st = Storage.getInstance();
        for(String i : Storage.getInstance().getGroups().keySet()){
            for(String j: Storage.getInstance().getElement(i).getProducts().keySet()){
                Product p = Storage.getInstance().getElement(i).getProducts().get(j);
                instance.addRow(new String[]{p.getName(),i,p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
                //System.out.println(instance.getRowCount());
            }
        }
        //System.out.println(instance.getRowCount());
        // todo @Orest

    }

    public static void removeProduct(String name){
        Storage.getInstance().removeProduct(name);
        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //System.out.println(instance.getRowCount());
        for(int i = 0;i<instance.getRowCount();i++){
            System.out.println(instance.getValueAt(i,0));
            if(instance.getValueAt(i,0).equals(name)){
                //System.out.println("found");
                instance.removeRow(i);
            }
        }
        updateAll();
    }

    public static void addProduct(){
        // todo @Orest
        updateAll();
    }

    public static void updateAll(){
        instance.fireTableChanged(new TableModelEvent(instance));
    }

    public static void editProduct(){
        //todo @Orest
        updateAll();
    }

    // work with product(buy/sell)
    public static void updateProduct(){
        //todo @Orest
        updateAll();
    }
}
