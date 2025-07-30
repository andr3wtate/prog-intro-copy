package expression.exceptions;

import expression.BinaryOperation;
import expression.CommonExp;

import static java.util.Collections.swap;

public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b) {
        OverflowException.checkMul(a, b);
        return a * b;
    }

    @Override
    public double operation(double a, double b) {
        return a * b;
    }

    @Override
    public String getOperation() {
        return "*";
    }

    @Override
    public int getPriority() {
        return 20;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }
}
