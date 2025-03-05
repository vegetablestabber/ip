package error;

public class InvalidArgCountException extends AppException {
    public InvalidArgCountException(int actualArgCount, int expectedArgCount) {
        super("Not enough arguments (actual: " + actualArgCount
            + ", expected: " + expectedArgCount + ").");
    }
}
