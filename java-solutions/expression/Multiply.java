package expression;

public class Multiply extends BinaryOperation {
    public Multiply(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b){
        return a * b;
    }

    @Override
    public double operation(double a, double b){
        return a * b;
    }

    @Override
    public String getOperation() {
        return "*";
    }

    @Override
    public int getPriority() {
        return 20;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }
}
