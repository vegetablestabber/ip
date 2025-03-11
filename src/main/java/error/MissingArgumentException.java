package error;

public class MissingArgumentException extends AppException {

    public MissingArgumentException(String arg) {
        super("'" + arg + "' argument missing.");
    }

    public MissingArgumentException() {
        super("No parameter provided.");
    }

}
