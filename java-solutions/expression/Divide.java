package expression;

public class Divide extends BinaryOperation {
    public Divide(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b){
        return a / b;
    }

    @Override
    public double operation(double a, double b){
        return a / b;
    }

    @Override
    public String getOperation() {
        return "/";
    }

    @Override
    public int getPriority() {
        return 20;
    }

    @Override
    public boolean isAffectedByRounding() {
        return true;
    }
}
