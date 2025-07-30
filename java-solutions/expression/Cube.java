package expression;

public class Cube extends UnaryOperation {
    public Cube(CommonExp operand) {
        super(operand);
    }

    @Override
    public int operation(int a) {
        return a * a * a;
    }

    @Override
    public double operation(double a) {
        return a * a * a;
    }

    @Override
    public String getLeftOperation() {
        return "";
    }

    @Override
    public String getRightOperation() {
        return "³";
    }

    @Override
    public int getPriority() {
        return 20;
    }
}
