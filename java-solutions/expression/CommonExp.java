package expression;

public interface CommonExp extends TripleExpression, Expression, DoubleMapExpression {
    default boolean isOperation() {
        return false;
    }
    default int getPriority() {
        return Integer.MAX_VALUE;
    }
    default boolean isCommutative() {
        return false;
    }
    default boolean isAffectedByRounding() {
        return false;
    }
    default boolean isStrictlyAssociative() { return false; }
}
