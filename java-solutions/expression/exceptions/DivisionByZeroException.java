package expression.exceptions;

public class DivisionByZeroException extends CalculationException {
    public DivisionByZeroException(String message) {
        super(message);
    }

    public static void checkDivByZero(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException(a + "/" + b);
        }
    }
}
