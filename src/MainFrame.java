import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.util.stream.Stream;

public class MainFrame extends JFrame {
    JToolBar toolBar;
    JButton button;
    JPanel currentPanel, groupPanel, itemPanel;
    DefaultTableModel groupTableModel, itemTableModel;
    JTable groupTable, itemTable;

    public MainFrame() {
        setTitle("Storage manager");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        init();

        /*addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuPanel.setVisible(true);
                setToolTipText("Click to show menu");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPanel.setVisible(false);
                setToolTipText(null);
            }
        });
         */
    }

    void init(){
        createToolBar();
        createButton();
        groupTableModel = new DefaultTableModel(null,
                new String[]{"Name"});
        itemTableModel = new DefaultTableModel(null,
                new String[]{"Name", "Group", "Manufacturer", "Price", "Quantity"});

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        jp1.add(toolBar);
        jp2.add(button);
        add(jp1, BorderLayout.NORTH);
        setGroupView();
        add(jp2, BorderLayout.SOUTH);
    }

    public void setGroupView() {
        if(groupPanel == null)
            groupPanel = getGroupPanel();
        setPanel(groupPanel);
    }

    public void setItemView(){
        if(itemPanel == null)
            itemPanel = getItemPanel();
        setPanel(itemPanel);
    }

    private void setPanel(JPanel newPanel) {
        if(currentPanel != null)
            getContentPane().remove(currentPanel);
        currentPanel = newPanel;
        getContentPane().add(currentPanel, BorderLayout.CENTER);
        revalidate();     // idk
        repaint();        // idk
    }



    //----------------------------------------------------------------------------------------------------------------------------
    public JPanel getGroupPanel() {
        JTable table = getTable(groupTableModel);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Create JPanel for table view
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public JPanel getItemPanel(){
        //todo
        return null;
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

    void createToolBar(){
        toolBar = new JToolBar();

        // Create two buttons
        JButton button1 = new JButton("Groups");
        JButton button2 = new JButton("Items");

        // Add buttons to the toolbar
        toolBar.add(Box.createHorizontalGlue());         // to get buttons to the center
        toolBar.add(button1);
        toolBar.add(button2);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.setFloatable(false);
    }


    void createButton(){
        MainFrame frame = this;
        JButton add = new JButton("Add Group");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGroupFrame.createAndShow(frame);
            }
        });
        button = add;
    }

    public static MainFrame createAndShowGUI() {
        //Create and set up the window.
        MainFrame frame = new MainFrame();

        //Create and set up the content pane.
        //SimpleTableDemo newContentPane = new SimpleTableDemo();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    /**
     * Add entry to groups table, no check for distinct/empty
     * @param name
     * */
    public void addGroupEntry(String name) {
        groupTableModel.addRow(new String[]{name});
    }

    public void editGroupEntry(int row, String newName) {
        //int selectedRow = table.getSelectedRow();

        //if (row != -1) {
            groupTableModel.setValueAt(newName, row, 0);
        //} else {
            //JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        //}
    }

    public void deleteGroupEntry(int row) {
        //int selectedRow = table.getSelectedRow();

        //if (selectedRow != -1) {
            groupTableModel.removeRow(row);
        //} else {
        //    JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        //}
    }

    public void deleteGroupEntry(String name) {
        //int selectedRow = table.getSelectedRow();

        //if (selectedRow != -1) {
        groupTableModel.removeRow(getGroupEntryRow(name));
        //} else {
        //    JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        //}
    }

    int getGroupEntryRow(String name){
        int ind = 0;
        for (String s: getGroupEntries()) {
            if(s.equals(name))
                return ind;
            else
                ind++;
        }
        return -1;
    }

    String getGroupEntry(int row){
        return (String)groupTableModel.getValueAt(row, 0);
    }

    public String[] getGroupEntries(){
        String[] entries = new String[groupTableModel.getDataVector().capacity()];
        int i = 0;
        for (Object a: groupTableModel.getDataVector()) {
            Vector<String> v = (Vector<String>)a;
            entries[i++] = v.get(0);
        }
        return entries;
    }
}