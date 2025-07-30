package expression.exceptions;

import expression.CommonExp;
import expression.UnaryOperation;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(CommonExp operand) {
        super(operand);
    }

    @Override
    public int operation(int a) {
        OverflowException.checkNeg(a);
        return -a;
    }

    @Override
    public double operation(double a) {
        return -a;
    }

    @Override
    public String getLeftOperation() {
        return "-";
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
