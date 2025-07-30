package expression.exceptions;

public class OverflowException extends CalculationException {
    public OverflowException(String message) {
        super(message);
    }

    public OverflowException(int a, int b, char op, char c) {
        super(a + " " + op + " " + b + " " + c + " " + (c == '>' ? "Integer.MAX_VALUE" : "Integer.MIN_VALUE"));
    }

    protected static void checkAdd(int a, int b) {
        if (a >= 0 && b >= 0 && b > Integer.MAX_VALUE - a) {
            throw new OverflowException(a, b, '+', '>');
        } else if (a <= 0 && b <= 0 && b < Integer.MIN_VALUE - a) {
            throw new OverflowException(a, b, '+', '<');
        }
    }

    protected static void checkSub(int a, int b) {
        if (a >= 0 && b <= 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException(a, b, '-', '>');
        } else if (a <= 0 && b >= 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException(a, b, '-', '<');
        }
    }

    public static void checkMul(int a, int b) {
        if ((a > 0 && b > 0 && b > Integer.MAX_VALUE / a) || (a < 0 && b < 0 && b < Integer.MAX_VALUE / a)) {
            throw new OverflowException(a, b, '*', '>');
        } else if ((a > 0 && b < 0 && b < Integer.MIN_VALUE / a) || (a < 0 && b > 0 && a != -1 && b > Integer.MIN_VALUE / a)) {
            throw new OverflowException(a, b, '*', '<');
        }
    }

    public static void checkDiv(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException(a, b, '/', '>');
        }
    }

    protected static void checkNeg(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("-(" + a + ") > Integer.MAX_VALUE");
        }
    }
}
