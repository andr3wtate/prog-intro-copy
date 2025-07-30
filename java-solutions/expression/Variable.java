package expression;

import java.util.Map;
import java.util.NoSuchElementException;

public class Variable implements CommonExp {
    private final String variable;

    public Variable(final String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluateD(Map<String, Double> variables) throws NoSuchElementException {
        Double val = variables.get(variable);
        if (val == null) {
            throw new NoSuchElementException();
        }
        return val;
    }

    @Override
    public int evaluate(int x, int y, int z) throws NoSuchElementException {
        return switch (variable.charAt(variable.length() - 1)) {
            case 'x' -> x;
            case 'y' -> y;
            case 'z' -> z;
            default -> throw new NoSuchElementException();
        };
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable c) {
            return this.variable.equals(c.variable);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }
}
