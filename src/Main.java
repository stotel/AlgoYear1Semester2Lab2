import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MainFrame.createAndShowGUI();
        //
        Storage MainStorage = Storage.getInstance();
        MainStorage.setFrame(MainFrame.getInstance());
        MainStorage.appendElement("groceries","products for general consumption in food");
        MainStorage.appendElement("building supplies","products for use in construction");
        Product product1 = new Product("Product 1", "Description 1", "Manufacturer 1", 10, 20.0);
        Product product2 = new Product("Product 2", "Description 2", "Manufacturer 2", 20, 30.0);
        Product product3 = new Product("Product 3", "Description 3", "Manufacturer 3", 30, 40.0);
        Product product4 = new Product("Product 4", "Description 4", "Manufacturer 4", 40, 50.0);
        Product product5 = new Product("Product 5", "Description 5", "Manufacturer 5", 50, 60.0);
        Product product6 = new Product("Product 6", "Description 6", "Manufacturer 6", 60, 70.0);
        Product product7 = new Product("Product 7", "Description 7", "Manufacturer 7", 70, 80.0);
        Product product8 = new Product("Product 8", "Description 8", "Manufacturer 8", 80, 90.0);
        Product product9 = new Product("Product 9", "Description 9", "Manufacturer 9", 90, 100.0);
        Product product10 = new Product("Product 10", "Description 10", "Manufacturer 10", 100, 110.0);
        MainStorage.getGroups().get("groceries").getProducts().put(product1.getName(), product1);
        MainStorage.getGroups().get("groceries").getProducts().put(product2.getName(), product2);
        MainStorage.getGroups().get("groceries").getProducts().put(product3.getName(), product3);
        MainStorage.getGroups().get("groceries").getProducts().put(product4.getName(), product4);
        MainStorage.getGroups().get("groceries").getProducts().put(product5.getName(), product5);
        MainStorage.getGroups().get("groceries").getProducts().put(product6.getName(), product6);
        MainStorage.getGroups().get("groceries").getProducts().put(product7.getName(), product7);
        MainStorage.getGroups().get("building supplies").getProducts().put(product8.getName(), product8);
        MainStorage.getGroups().get("building supplies").getProducts().put(product9.getName(), product9);
        MainStorage.getGroups().get("building supplies").getProducts().put(product10.getName(), product10);
        //MainStorage.removeElement("groceries");
        System.out.println(MainStorage);
        MainStorage.serialize();
        System.out.println("Hello world!");
    }
}