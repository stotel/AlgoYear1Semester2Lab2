package back;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductGroup implements IGrouping, Serializable {
    private String name = "Storage";
    private String description;

    public String getDescription() {
        return description;
    }

    private HashMap<String, Product> Products;

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
        BufferedWriter writer = new BufferedWriter(new FileWriter(name));
        writer.write(this.toString());

        writer.close();
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
