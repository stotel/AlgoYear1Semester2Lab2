import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class groupProductsViewer extends JFrame {
    DefaultTableModel productTableModel;
    JPanel currentPanel,productPanel;
    groupProductsViewer(String groupName){
        setTitle("Storage manager");
        setSize(800, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        productTableModel = new DefaultTableModel(null,
                new String[]{"Name", "Group", "Manufacturer", "Price", "Quantity"});
        for(String i:Storage.getInstance().getElement(groupName).getProducts().keySet()){
            Product p = Storage.getInstance().getElement(groupName).getProducts().get(i);
            productTableModel.addRow(new String[]{p.getName(),groupName,p.getManufacturer(),String.valueOf(p.getPricePerUnit()),String.valueOf(p.getQuantityInStock())});
        }
        setProductView();
        setVisible(true);
    }
    public void setProductView() {
        if(productPanel == null)
            productPanel = getProductPanel();
        setPanel(productPanel);
    }
    private void setPanel(JPanel newPanel) {
        if(currentPanel != null)
            getContentPane().remove(currentPanel);
        currentPanel = newPanel;
        getContentPane().add(currentPanel, BorderLayout.CENTER);
        revalidate();     // idk
        repaint();        // idk
    }
    public JPanel getProductPanel() {
        JTable table = getTable(productTableModel);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = (String)table.getValueAt(table.getSelectedRow(),0);
                System.out.println(name);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Create JPanel for table view
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
    JTable getTable(DefaultTableModel tm){
        // Create a table with a model
        JTable tb = new JTable(tm);
        // Setting the JTable non-editable
        tb.setDefaultEditor(Object.class, null);

        // Set table headers
        //tb.getColumnModel().getColumn(0).setHeaderValue("Groups");
        //table.getColumnModel().getColumn(1).setHeaderValue("Manufacturer");
        //table.getColumnModel().getColumn(2).setHeaderValue("Price of 1");
        return tb;
    }
}
