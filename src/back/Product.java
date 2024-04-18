package back;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;

public class Product {
    private ProductGroup group;
    private String name;
    private String description;
    private String manufacturer;
    private int quantityInStock;
    private double pricePerUnit;

    // Constructor
    public Product(ProductGroup group,String name, String description, String manufacturer, int quantityInStock, double pricePerUnit) {
        this.group = group;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.quantityInStock = quantityInStock;
        this.pricePerUnit = pricePerUnit;
    }

    // Getters and Setters
    public void setProduct(String n, String man, double p, int q) {
        setName(n);
        setManufacturer(man);
        setPricePerUnit(p);
        setQuantityInStock(q);
    }

    public void setGroup(ProductGroup group){
        this.group = group;
    }
    public String getName() {
        return name;
    }

    public ProductGroup getGroup() {
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    // Method to calculate total price based on quantity

    public double calculateTotalPrice(int quantity) {
        return quantity * pricePerUnit;
    }
    // toString method to represent object as string

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", pricePerUnit=" + pricePerUnit;
    }
}
