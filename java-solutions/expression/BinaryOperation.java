package expression;

import java.util.Map;
import java.util.NoSuchElementException;

public abstract class BinaryOperation implements CommonExp {
    private final CommonExp operand1;
    private final CommonExp operand2;

    protected BinaryOperation(CommonExp operand1, CommonExp operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    abstract public int operation(int a, int b);

    abstract public double operation(double a, double b);

    abstract public String getOperation();

    @Override
    public int evaluate(int x) {
        return operation(operand1.evaluate(x), operand2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operation(operand1.evaluate(x, y, z), operand2.evaluate(x, y, z));
    }

    @Override
    public double evaluateD(Map<String, Double> variables) throws NoSuchElementException {
        return operation(operand1.evaluateD(variables), operand2.evaluateD(variables));
    }

    @Override
    public String toMiniString() {
        StringBuilder ans = new StringBuilder();
        if (operand1.isOperation() && operand1.getPriority() < this.getPriority()) {
            ans.append("(").append(operand1.toMiniString()).append(")");
        } else {
            ans.append(operand1.toMiniString());
        }
        ans.append(" ").append(getOperation()).append(" ");
        if (operand2.isOperation() && (operand2.getPriority() < this.getPriority()
                || operand2.getPriority() == this.getPriority() && (!this.isCommutative() || operand2.isAffectedByRounding()
                || this.isStrictlyAssociative() != operand2.isStrictlyAssociative()))) {
            ans.append("(").append(operand2.toMiniString()).append(")");
        } else {
            ans.append(operand2.toMiniString());
        }
        return ans.toString();
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " " + getOperation() + " " + operand2.toString() + ")";
    }

    @Override
    public boolean isOperation() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BinaryOperation c) {
            return this.getClass().equals(c.getClass()) && this.operand1.equals(c.operand1) && this.operand2.equals(c.operand2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return operand1.hashCode() * 277 * 277 + operand2.hashCode() * 277 + this.getClass().hashCode();
    }
}
