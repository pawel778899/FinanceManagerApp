package exception;

public class NoSuchOptionException extends Exception { // Wyjątek kontrolowany - trzeba go obsłużyć, nie można go zignorować

    public NoSuchOptionException(String message) {
        super(message);
    }
}
