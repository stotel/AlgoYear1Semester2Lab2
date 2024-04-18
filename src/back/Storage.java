package back;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class Storage implements IGrouping, Serializable {
    private static String name = "Storage";
    private HashMap<String, ProductGroup> Groups;

    public HashMap<String, ProductGroup> getGroups() {
        return Groups;
    }
    private static Storage INSTANCE;

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    private MainFrame frame;

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
            //Groups.remove(name);
            GroupTableModel.addGroup(name, description);
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
            GroupTableModel.removeGroup(name);
            Groups.remove(name);
        }catch (NoElementException e){
            System.out.println(e.getMessage());
        }
    }
    public void redactElement(String name,String newName,String newDescription) {
        HashMap<String, Product> h = Groups.get(name).getProducts();
        //you can just change group, it is covered todo @Orest
        removeElement(name);
        appendElement(newName,newDescription);
        Groups.get(newName).setProducts(h);
    }
    public ProductGroup getElement(String name){
        return Groups.get(name);
    }
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(name+"SERIALIZED"));
        writer.write(this.Groups.keySet().stream().map(x->x+'%'+Groups.get(x).getDescription()+'\n').collect(Collectors.joining()));
        clearSavingDirectory();
        this.Groups.keySet().stream().forEach(x-> {
            try {
                Groups.get(x).saveToFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.close();
    }
    public  void clearSavingDirectory(){
        File folder = new File("GroupsContents");

        // Check if the specified path is a directory
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        // Delete the file
                        if (file.delete()) {
                            System.out.println("Deleted file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete file: " + file.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println("Specified path is not a directory.");
        }
    }
    public void loadFromFile() throws IOException {
        //Storage sto = new Storage();
        clear();
        BufferedReader reader = new BufferedReader(new FileReader(name+"SERIALIZED"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] LineArr = line.split("%");
            getInstance().appendElement(LineArr[0],LineArr[1]);
        }
        for(String i: getInstance().Groups.keySet()){
            getInstance().Groups.get(i).loadFromFile();
        }
        ProductTableModel.init();
        //return INSTANCE;
    }
    public Product findProduct(String name){
        for(String i:Groups.keySet()){
            for(String j:Groups.get(i).getProducts().keySet()){
                Product p = Groups.get(i).getProducts().get(j);
                if(Objects.equals(p.getName(), name)){
                    return p;
                }
            }
        }
        return null;
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

    public  boolean isProductWithNamePresent(String pName){
        for(String i:Groups.keySet()){
            for(String j:Groups.get(i).getProducts().keySet()){
                Product p = Groups.get(i).getProducts().get(j);
                if(Objects.equals(p.getName(), pName)){
                    return true;
                }
            }
        }
        return false;
    }

    public void removeProduct(String name) {
        for(String i:Groups.keySet()){
            for(String j:Groups.get(i).getProducts().keySet()){
                Product p = Groups.get(i).getProducts().get(j);
                if(Objects.equals(p.getName(), name)){
                    Groups.get(i).removeElement(name);
                    break;
                }
            }
        }
    }
}
