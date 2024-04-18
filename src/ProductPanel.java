import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductPanel extends JPanel {
    public static JPanel getProductPanel(String groupName) {
        ProductTableModel productTableModel = ProductTableModel.getInstance();
        //if(groupName != null) // it means we need to _filter_ products only from this group (create _new_ productTableModel)
        // todo @Orest

        //----------------------------------------------------------------------------------------------
        JTable table = MainFrame.getTable(productTableModel);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = (String)table.getValueAt(table.getSelectedRow(), 0);
                System.out.println(name);
                ProductActionChooser.createAndShow(name);
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
}