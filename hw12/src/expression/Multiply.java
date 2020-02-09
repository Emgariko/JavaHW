package expression;

public class Multiply extends AbstractOperation {
    public Multiply(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int getPriority() {
        return 2;
    }
    @Override
    public int makeOperation(int c, int d) {
        return c * d;
    }

    @Override
    public String operationSym() {
        return "*";
    }

    @Override
    protected double makeOperation(double c, double d) {
        return c * d;
    }

}
