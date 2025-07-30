package expression;

public class Log extends BinaryOperation {
    public Log(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b) {
        if (a < 0 || b <= 0) {
            return 0;
        } else if (a == 0) {
            return Integer.MIN_VALUE;
        } else if (b == 1) {
            return a == 1 ? 0 : Integer.MAX_VALUE;
        }
        int ans = 0;
        while (a > 0) {
            a /= b;
            ans++;
        }
        return ans - 1;
    }

    @Override
    public double operation(double a, double b) {
        return Math.log(a) / Math.log(b);
    }

    @Override
    public String getOperation() {
        return "//";
    }

    @Override
    public int getPriority() {
        return 30;
    }
}
