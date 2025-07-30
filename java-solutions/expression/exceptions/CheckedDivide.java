package expression.exceptions;

import expression.BinaryOperation;
import expression.CommonExp;

public class CheckedDivide extends BinaryOperation {
    public CheckedDivide(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b) {
        DivisionByZeroException.checkDivByZero(a, b);
        OverflowException.checkDiv(a, b);
        return a / b;
    }

    @Override
    public double operation(double a, double b){
        return a / b;
    }

    @Override
    public String getOperation() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 20;
    }

    @Override
    public boolean isAffectedByRounding() {
        return true;
    }
}
