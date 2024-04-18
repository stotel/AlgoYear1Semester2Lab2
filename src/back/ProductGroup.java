package back;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;

import java.io.*;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductGroup implements IGrouping, Serializable {
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String name = "Storage";
    private String description;

    public String getDescription() {
        return description;
    }

    private HashMap<String, Product> Products;

    public void setProducts(HashMap<String, Product> products) {
        Products = products;
    }

    public HashMap<String, Product> getProducts() {
        return Products;
    }

    public ProductGroup(String name, String description) {
        this.name = name;
        this.description=description;
        Products = new HashMap<>();
    }

    public void appendElement(String name, String description, String manufacturer, int quantityInStock, double pricePerUnit) {
        Products.put(name,new Product(name, description, manufacturer, quantityInStock, pricePerUnit));
    }
    public void removeElement(String name) {
       Products.remove(name);
    }
    public void redactElement(String name) {
        Product redactedProduct = Products.get(name);
        //to do redaction logic
    }

    @Override
    public void clear() {

    }
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("GroupsContents/"+name+"SERIALIZED"));
        writer.write(this.getProducts().keySet().stream().map(x->x+'%'+getProducts().get(x).getDescription()+'%'+getProducts().get(x).getManufacturer()+'%'+getProducts().get(x).getQuantityInStock()+'%'+getProducts().get(x).getPricePerUnit()+'\n').collect(Collectors.joining()));

        writer.close();
    }
    public void loadFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("GroupsContents/"+name+"SERIALIZED"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] LineArr = line.split("%");
            appendElement(LineArr[0],LineArr[1],LineArr[2],Integer.parseInt(LineArr[3]),Double.parseDouble(LineArr[4]));
        }
    }

    @Override
    public String toString() {
        return "ProductGroup" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", Products=" + '\n' + Products.keySet().stream().map(x ->"        " + Products.get(x).toString() + '\n').collect(Collectors.joining())
                ;
    }
}
