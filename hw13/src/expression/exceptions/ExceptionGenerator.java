package expression.exceptions;

import expression.parser.TokenType;

import java.util.Map;

public class ExceptionGenerator {

    public static void checkOperation(int a, int b, String type) {
        switch (type) {
            case "MUL":
                if (a == Integer.MIN_VALUE && b == -1|| a != 0 && b!= 0 && (a * b) / b != a) {
                    throw new OverflowMultiplyException(Integer.toString(a) + " * " + Integer.toString(b));
                }
                break;
            case "DIV":
                if (a == Integer.MIN_VALUE && b== -1) {
                    throw new OverflowDivideExcpetpion(Integer.toString(a) + " / " + Integer.toString(b));
                }
                if (b== 0) {
                    String msg = new String(Integer.toString(a) + " / " + Integer.toString(b));
                    throw new DivisionByZeroException(msg);
                }
                break;
            case "ADD":
                int res = a + b;
                if (b > 0 && res < a || b < 0 && res > a) {
                    throw new OverflowAddException(Integer.toString(a) + " + " + Integer.toString(b));
                }
                break;
            case "SUB":
                res = a - b;
                if (b > 0 && res > a || b < 0 && res < a) {
                    throw new OverflowSubstractException(Integer.toString(a) + " - " + Integer.toString(b));
                }
                break;
            case "NEG":
                if (a == Integer.MIN_VALUE) {
                    throw new OverflowNegateExcpetpion("-(" + Integer.toString(a) + ")");
                }
                break;
            case "LOG2":
                if (a <= 0) {
                    throw new LogByNonPositiveException("Log2(" + Integer.toString(a) + ")");
                }
                break;
            case "POW2":
                if (a < 0) {
                    throw new PowByNegativeException("Pow2(" + Integer.toString(a) + ")");
                }
        }
    }
}
