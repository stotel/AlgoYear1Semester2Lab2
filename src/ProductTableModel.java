import javax.swing.table.DefaultTableModel;
import java.util.Set;

public class ProductTableModel extends DefaultTableModel{
    static ProductTableModel instance;
    public static ProductTableModel getInstance() {
        return instance;
    }
    public ProductTableModel(){
        super(null,
                new String[]{"Name", "Group", "Manufacturer", "Price", "Quantity"});
        instance = this;
    }

    public static void init(){
        new ProductTableModel();
        //fill all products
        Storage st = Storage.getInstance();
        for(String groupName : st.getGroups().keySet()){
            for(String i: st.getElement(groupName).getProducts().keySet()){
                Product p = Storage.getInstance().getElement(groupName).getProducts().get(i);
                instance.addRow(new String[]{p.getName(),groupName,p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
            }
        }
        // todo @Orest

    }

    public static void removeProduct(){

    }

    public static void addProduct(){

    }

    public static void editProduct(){

    }

    // work with product(buy/sell)
    public static void updateProduct(){

    }
}
