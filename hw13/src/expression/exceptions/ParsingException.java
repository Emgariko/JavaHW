package expression.exceptions;

public abstract class ParsingException extends Exception {
    public ParsingException(String msg, int pos) {
        super("Parsing error at " + Integer.toString(pos) + ", " + msg + ".");
    }
}
