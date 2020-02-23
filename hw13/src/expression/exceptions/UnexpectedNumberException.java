package expression.exceptions;

public class UnexpectedNumberException extends ParsingException {
    public UnexpectedNumberException(int pos) {
        super("Unexpected number", pos);
    }
}
