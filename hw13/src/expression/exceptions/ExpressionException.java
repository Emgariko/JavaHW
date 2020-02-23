package expression.exceptions;

public abstract class ExpressionException extends RuntimeException{
    public ExpressionException(String msg) {
        super(msg);
    }
}
