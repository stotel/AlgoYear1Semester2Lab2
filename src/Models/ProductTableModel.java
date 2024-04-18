package Models;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ProductTableModel extends DefaultTableModel{
    static ProductTableModel instance;
    static List<ProductPanel> ProductPanels = new LinkedList<>();
    public static ProductTableModel getInstance() {
        return instance;
    }
    public ProductTableModel(){
        super(null,
                new String[]{"Name", "Group", "Manufacturer", "Price", "Quantity"});
    }

    public static void init(){
        instance = new ProductTableModel();
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
    }

    public static void addProduct(){

    }

    public static void editProduct(){

    }

    // work with product(buy/sell)
    public static void updateProduct(){

    }
}
