import java.io.IOException;

public interface IGrouping {
    /*public void appendElement();
    public void removeElement();
    public void redactElement();*/

    public void saveToFile() throws IOException;
    public void clear();
}
