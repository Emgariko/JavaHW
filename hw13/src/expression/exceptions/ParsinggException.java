package expression.exceptions;

import expression.parser.ExpressionParser;

public class ParsinggException extends ExpressionException {
    public ParsinggException(String msg, int pos) {
        super("Parsing error at " + Integer.toString(pos) + ", " + msg + ".");
    }
}
