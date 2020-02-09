package expression;

public class Divide extends AbstractOperation {
    public Divide(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    public int makeOperation(int c, int d) {
        return c / d;
    }

    @Override
    public String operationSym() {
        return "/";
    }
    @Override
    protected double makeOperation(double c, double d) {
        return c / d;
    }
}
