package expression;

import java.util.Map;
import java.util.NoSuchElementException;

public abstract class UnaryOperation implements CommonExp {
    private final CommonExp operand;

    protected UnaryOperation(CommonExp operand) {
        this.operand = operand;
    }

    abstract public int operation(int a);
    abstract public double operation(double a);
    abstract public String getLeftOperation();
    abstract public String getRightOperation();

    @Override
    public int evaluate(int x) {
        return operation(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operation(operand.evaluate(x, y, z));
    }

    @Override
    public double evaluateD(Map<String, Double> variables) throws NoSuchElementException {
        return operation(operand.evaluateD(variables));
    }

    @Override
    public String toMiniString() {
        if (!operand.isOperation() && this.getPriority() <= operand.getPriority()) {
            return this.getLeftOperation() + (this.getLeftOperation().equals("-") ? " " : "")
                    + operand.toMiniString() + this.getRightOperation();
        } else {
            return this.getLeftOperation() + "(" + operand.toMiniString() + ")" + this.getRightOperation();
        }
    }

    @Override
    public String toString() {
        return this.getLeftOperation() + "(" + operand.toString() + ")" + this.getRightOperation();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnaryOperation c) {
            return this.getClass().equals(c.getClass()) && this.operand.equals(c.operand);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return operand.hashCode() * 277 + this.getClass().hashCode();
    }
}
