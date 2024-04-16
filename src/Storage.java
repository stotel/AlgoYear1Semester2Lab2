import java.io.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Storage implements IGrouping, Serializable {
    private static String name = "Storage";
    private HashMap<String, ProductGroup> Groups;

    public HashMap<String, ProductGroup> getGroups() {
        return Groups;
    }
    private static Storage INSTANCE;

    private Storage() {
        //this.name = name;
        Groups = new HashMap<>();
    }

    public static Storage getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Storage();
        }

        return INSTANCE;
    }

    public void appendElement(String name,String description) {
        try{
            if(Groups.containsKey(name)){
                throw new SameNameException();
            }
            Groups.remove(name);
            Groups.put(name,new ProductGroup(name,description));
        }catch (SameNameException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeElement(String name) {
        try{
            if(!Groups.containsKey(name)){
                throw new NoElementException();
            }
            Groups.remove(name);
        }catch (NoElementException e){
            System.out.println(e.getMessage());
        }
    }
    public void redactElement(String name) {

    }
    public ProductGroup getElement(String name){
        return Groups.get(name);
    }

    public void serialize() throws IOException {
        saveToFile();
        for(String key:Groups.keySet()){
            Groups.get(key).saveToFile();
        }
    }
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(name));
        writer.write(this.toString());

        writer.close();
    }

    @Override
    public void clear() {
        Groups = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Storage" +
                "Name = " + name + '\n' +
                "Groups=" +'\n' + Groups.keySet().stream().map(x ->"    " + Groups.get(x).toString() +'\n').collect(Collectors.joining())
                ;
    }
}
