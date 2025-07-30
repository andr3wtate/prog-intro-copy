package expression;

import expression.exceptions.OverflowException;

public class Power extends BinaryOperation {
    public Power(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    protected static int binPow(int a, int n, boolean needCheck) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 1) {
            int b = binPow(a, n - 1, needCheck);
            if (needCheck) {
                OverflowException.checkMul(a, b);
            }
            return a * b;
        } else {
            int b = binPow(a, n / 2, needCheck);
            if (needCheck) {
                OverflowException.checkMul(b, b);
            }
            return b * b;
        }
    }

    @Override
    public int operation(int a, int b) {
        return b >= 0 ? binPow(a, b, false) : 1;
    }

    @Override
    public double operation(double a, double b) {
        return Math.pow(a, b);
    }

    @Override
    public String getOperation() {
        return "**";
    }

    @Override
    public int getPriority() {
        return 30;
    }
}
