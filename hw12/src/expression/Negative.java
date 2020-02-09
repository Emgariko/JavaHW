package expression;

public class Negative implements CommonExpression {
    CommonExpression expression;

    public Negative(CommonExpression exp) {
        expression = exp;
    }

    @Override
    public int evaluate(int x) {
        return -expression.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return -expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x, y, z);
    }

    @Override
    public String toString() { // may be i should change it
        StringBuilder res = new StringBuilder("-");
        res.append("(");
        res.append(expression.toString());
        res.append(")");
        return res.toString();
    }
}
