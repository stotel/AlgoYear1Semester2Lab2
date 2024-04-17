import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.util.stream.Stream;

public class MainFrame extends JFrame {
    JToolBar toolBar;
    DefaultTableModel tableModel;
    JTable table;

    public MainFrame() {
        setTitle("Storage manager");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createToolBar();

        createTable();

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);


        // Add components to main frame
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

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

    void createTable(){
        // Create a table with a model
        tableModel = new DefaultTableModel(new String[][]{{"Meat"}, {"Veggies"}, {"Grains"}},
                new String[]{"Name"});

        table = new JTable(tableModel);
        // Setting the JTable non-editable
        table.setDefaultEditor(Object.class, null);

        // Set table headers
        table.getColumnModel().getColumn(0).setHeaderValue("Groups");
        //table.getColumnModel().getColumn(1).setHeaderValue("Manufacturer");
        //table.getColumnModel().getColumn(2).setHeaderValue("Price of 1");
    }

    /**
     * Add entry to groups table, no check for distinct/empty
     * @param name
     * */
    public void addGroupEntry(String name) {
        tableModel.addRow(new String[]{name});
    }

    public void editGroupEntry(int row, String newName) {
        //int selectedRow = table.getSelectedRow();

        //if (row != -1) {
            tableModel.setValueAt(newName, row, 0);
        //} else {
            //JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        //}
    }

    public void deleteGroupEntry(int row) {
        //int selectedRow = table.getSelectedRow();

        //if (selectedRow != -1) {
            tableModel.removeRow(row);
        //} else {
        //    JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        //}
    }

    public void deleteGroupEntry(String name) {
        //int selectedRow = table.getSelectedRow();

        //if (selectedRow != -1) {
        tableModel.removeRow(getGroupEntryRow(name));
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
        return (String)tableModel.getValueAt(row, 0);
    }

    public String[] getGroupEntries(){
        String[] entries = new String[tableModel.getDataVector().capacity()];
        int i = 0;
        for (Object a: tableModel.getDataVector()) {
            Vector<String> v = (Vector<String>)a;
            entries[i++] = v.get(0);
        }
        return entries;
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

    public static void createAndShowGUI() {
        //Create and set up the window.
        MainFrame frame = new MainFrame();

        //Create and set up the content pane.
        //SimpleTableDemo newContentPane = new SimpleTableDemo();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /*JFileChooser fc;
    JTextArea textArea;
    public MainFrame(){
        super("File reader");
        this.setSize(1000,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);           //  fit text to width of JTextArea (no horizontal scrolling)
        textArea.setWrapStyleWord(true);      //
        JButton b1 = new JButton("Open file");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openFile();
            }
        });
        add(new JScrollPane(textArea));
        add(b1, BorderLayout.SOUTH);
    }

    //choose file and read to JTextArea
    void openFile(){
        fc = new JFileChooser(".");         // this directory
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {        // if chosen
            try (BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()))) {
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                textArea.setText(sb.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading the file", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    */
}