package Frames;

import back.*;
import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductFrame extends JFrame {
    String groupName;

    ProductFrame(JFrame base, String gName){
        groupName = gName;
        setTitle("Products of "+groupName);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(ProductPanel.getProductPanel(gName));

        setLocationRelativeTo(base);
    }

    public static void createAndShow(JFrame base, String gName){
        ProductFrame gpr = new ProductFrame(base, gName);
        gpr.setVisible(true);
    }
}
