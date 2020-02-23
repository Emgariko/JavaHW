package expression.exceptions;

public abstract class OverflowException extends ExpressionException {
    public OverflowException(String msg) {
        super("Overflow in " + msg + " operation.");
    }
}
