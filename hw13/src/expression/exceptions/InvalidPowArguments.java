package expression.exceptions;

public class InvalidPowArguments extends ExpressionException {
    public InvalidPowArguments(String msg) {
        super("Invalid arguments in " + msg);
    }
}
