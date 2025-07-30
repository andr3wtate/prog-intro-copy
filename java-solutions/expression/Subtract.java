package expression;

public class Subtract extends BinaryOperation {
    public Subtract(CommonExp operand1, CommonExp operand2) {
        super(operand1, operand2);
    }

    @Override
    public int operation(int a, int b){
        return a - b;
    }

    @Override
    public double operation(double a, double b){
        return a - b;
    }

    @Override
    public String getOperation() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
