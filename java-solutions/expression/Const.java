package expression;

import java.util.Map;

public class Const implements CommonExp {
    private final long value;
    private final boolean isDouble;

    public Const(final int value) {
        this.value = value;
        this.isDouble = false;
    }

    public Const(final double value) {
        this.value = Double.doubleToLongBits(value);
        this.isDouble = true;
    }

    @Override
    public int evaluate(int x) {
        return (int) value;
    }

    @Override
    public double evaluateD(Map<String, Double> variables) {
        return Double.longBitsToDouble(value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) value;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public String toString() {
        return isDouble ? String.valueOf(Double.longBitsToDouble(value)) : String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Const c) {
            return this.value == c.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return isDouble ? Double.valueOf(value).hashCode() : Integer.valueOf(Math.toIntExact(value)).hashCode();
    }
}