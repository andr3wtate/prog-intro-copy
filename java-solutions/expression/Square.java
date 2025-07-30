package expression;

public class Square extends UnaryOperation {
    public Square(CommonExp operand) {
        super(operand);
    }

    @Override
    public int operation(int a) {
        return a * a;
    }

    @Override
    public double operation(double a) {
        return a * a;
    }

    @Override
    public String getLeftOperation() {
        return "";
    }

    @Override
    public String getRightOperation() {
        return "²";
    }

    @Override
    public int getPriority() {
        return 20;
    }
}
