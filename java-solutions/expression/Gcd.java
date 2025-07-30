package expression;

import expression.exceptions.UnsupportedOperation;

public class Gcd extends BinaryOperation {
    public Gcd(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    protected static int gcd(int a, int b) {
        if (b == 0) {
            return a > 0 ? a : -a;
        }
        return gcd(b, a % b);
    }

    @Override
    public int operation(int a, int b) {
        return gcd(a, b);
    }

    @Override
    public double operation(double a, double b) {
        throw new UnsupportedOperation("gcd operation can't be applied to double values");
    }

    @Override
    public String getOperation() {
        return "gcd";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public boolean isStrictlyAssociative() {
        return true;
    }
}
