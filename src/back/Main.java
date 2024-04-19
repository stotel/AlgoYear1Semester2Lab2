package back;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import java.io.IOException;

public class Main {
    /**
     * ABBABJHBHABHBAHBABHBA
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Storage MainStorage = Storage.getInstance();
        MainStorage.loadFromFile();
        MainFrame.createAndShowGUI();
    }
}