package expression.exceptions;

import expression.BinaryOperation;
import expression.CommonExp;

public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b) {
        OverflowException.checkSub(a, b);
        return a - b;
    }

    @Override
    public double operation(double a, double b){
        return a - b;
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
