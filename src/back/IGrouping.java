package back;

import Models.*;
import Panels.*;
import Choosers.*;
import Frames.*;
import java.io.IOException;

public interface IGrouping {
    /*public void appendElement();
    public void removeElement();
    public void redactElement();*/

    public void saveToFile() throws IOException;
    public void clear();
}
