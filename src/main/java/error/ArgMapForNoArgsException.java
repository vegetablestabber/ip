package error;

public class ArgMapForNoArgsException extends AppException {
    public ArgMapForNoArgsException() {
        super("Cannot return argument map for empty required argument array.");
    }
}
