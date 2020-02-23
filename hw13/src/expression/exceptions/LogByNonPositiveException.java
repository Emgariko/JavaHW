package expression.exceptions;

public class LogByNonPositiveException extends ExpressionException{
    public LogByNonPositiveException(String msg) {
        super("Log by non positive in " + msg);
    }
}
