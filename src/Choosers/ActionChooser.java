package Choosers;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import back.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionChooser extends JFrame {
    String name;
    public ActionChooser(String name) {
        this.name = name;
        // Set the title of the window
        setTitle(name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        init();

        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    void init(){

    }
    public static void createAndShow(String name){

    }
}
