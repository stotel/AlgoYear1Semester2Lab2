import javax.lang.model.element.Element;

public class NoElementException extends Exception{
    public NoElementException() {
        super("This element does not exist!");
    }

    // Constructor that takes a custom message and a cause
    public NoElementException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that takes only a cause
    public NoElementException(Throwable cause) {
        super("This element does not exist!",cause);
    }
}