package expression;

public class Add extends AbstractOperation {
    public Add(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int getPriority() {
        return 1;
    }
    @Override
    public int makeOperation(int c, int d) {
        return c + d;
    }

    @Override
    public String operationSym() {
        return "+";
    }
    @Override
    protected double makeOperation(double c, double d) {
        return c + d;
    }
}
