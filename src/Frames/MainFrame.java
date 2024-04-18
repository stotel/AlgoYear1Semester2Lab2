package Frames;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;
import java.util.stream.Stream;

public class MainFrame extends JFrame {
    static MainFrame instance;
    JToolBar toolBar;
    JButton button;
    JPanel currentPanel, groupPanel, productPanel;

    public static MainFrame getInstance(){
        return instance;
    }

    public MainFrame() {
        setTitle("Storage manager");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        init();
        instance = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Storage.getInstance().saveToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    void init(){
        createToolBar();
        createButton();

        GroupTableModel.init();       // todo: change location
        ProductTableModel.init();     //

        setGroupView();
    }


    //----------------------------------------------------------------------------------------------------------------------------
    JPanel getGroupPanel() {
        return GroupPanel.getGroupPanel();
    }
    JPanel getProductPanel(){
        return ProductPanel.getProductPanel(null);
    }
    void setGroupView() {
        if(groupPanel == null)
            groupPanel = getGroupPanel();
        setPanel(groupPanel);

        JPanel jp2 = new JPanel();
        jp2.add(button);
        add(jp2, BorderLayout.SOUTH);
    }

    void setProductView(){
        if(productPanel == null)
            productPanel = getProductPanel();
        setPanel(productPanel);
    }

    void setPanel(JPanel newPanel) {
        getContentPane().removeAll();
        currentPanel = newPanel;
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(currentPanel, BorderLayout.CENTER);
        revalidate();     // idk
        repaint();        // idk
    }

    void createToolBar(){
        toolBar = new JToolBar();

        // Create two buttons
        JButton groupB = new JButton("Groups");
        JButton productB = new JButton("Products");

        groupB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setGroupView();
            }
        });
        productB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setProductView();
            }
        });


        // Add buttons to the toolbar
        toolBar.add(Box.createHorizontalGlue());         // to get buttons to the center
        toolBar.add(groupB);
        toolBar.add(productB);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.setFloatable(false);
    }

    void createButton(){
        MainFrame frame = this;
        JButton add = new JButton("Add Group");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGroupFrame.createAndShow(frame, null);
            }
        });
        button = add;
    }

    public static JTable getTable(DefaultTableModel tm){
        JTable tb = new JTable(tm);
        tb.setDefaultEditor(Object.class, null);
        return tb;
    }

    public static void createAndShowGUI() {
        MainFrame frame = new MainFrame();

        //Create and set up the content pane.
        //SimpleTableDemo newContentPane = new SimpleTableDemo();
        //newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

}