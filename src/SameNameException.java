public class SameNameException extends Exception{
        public SameNameException() {
            super("You already have option with this name!");
        }

        // Constructor that takes a custom message and a cause
        public SameNameException(String message, Throwable cause) {
            super(message, cause);
        }

        // Constructor that takes only a cause
        public SameNameException(Throwable cause) {
            super("You already have option with this name!",cause);
    }
}
