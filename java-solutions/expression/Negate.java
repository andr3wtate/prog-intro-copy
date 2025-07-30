package expression;

public class Negate extends UnaryOperation {
    public Negate(CommonExp operand) {
        super(operand);
    }

    @Override
    public int operation(int a) {
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
        return 10;
    }
}
