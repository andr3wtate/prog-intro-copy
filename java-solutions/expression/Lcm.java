package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;
import expression.exceptions.UnsupportedOperation;

public class Lcm extends BinaryOperation {
    public Lcm(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        int gcd = Gcd.gcd(a, b);
        DivisionByZeroException.checkDivByZero(a, gcd);
        OverflowException.checkDiv(a, gcd);
        a /= Gcd.gcd(a, b);
        OverflowException.checkMul(a, b);
        return a * b;
    }

    @Override
    public double operation(double a, double b) {
        throw new UnsupportedOperation("lcm operation can't be applied to double values");
    }

    @Override
    public String getOperation() {
        return "lcm";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }
}
