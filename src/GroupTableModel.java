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

    public static void removeGroup(){

    }

    public static void addGroup(){

    }

    public static void editGroup(){

    }
}
