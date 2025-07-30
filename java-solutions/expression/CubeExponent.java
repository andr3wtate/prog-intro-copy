package expression;

import expression.exceptions.CalculationException;

public class CubeExponent extends UnaryOperation {
    public CubeExponent(CommonExp operand) {
        super(operand);
    }

    @Override
    public int operation(int a) {
        if (a <= 0) {
            throw new CalculationException("Variable a = ".concat(String.valueOf(a)).concat(" must be > 0 for a^(a^a) operation."));
        }
        return Power.binPow(a, Power.binPow(a, a, true), true);
    }

    @Override
    public double operation(double a) {
        return a * a * a;
    }

    @Override
    public String getLeftOperation() {
        return "³";
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
