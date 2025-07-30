package expression;

public class Main {
    public static void main(String[] arg) {
        System.out.println(new Subtract(
                new Multiply(new Variable("x"), new Variable("x")),
                new Add(new Multiply(new Const(2), new Variable("x")), new Const(1))).evaluate(Integer.valueOf(arg[0])));
    }
}
