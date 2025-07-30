package expression;

import expression.exceptions.CalculationException;

public class SquareExponent extends UnaryOperation {
    public SquareExponent(CommonExp operand) {
        super(operand);
    }

    @Override
    public int operation(int a) {
        if (a <= 0) {
            throw new CalculationException("Variable a = ".concat(String.valueOf(a)).concat(" must be > 0 for a^a operation."));
        }
        return Power.binPow(a, a, true);
    }

    @Override
    public double operation(double a) {
        return a * a;
    }

    @Override
    public String getLeftOperation() {
        return "²";
    }

    @Override
    public String getRightOperation() {
        return "";
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
