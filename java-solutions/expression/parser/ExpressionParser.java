package expression.parser;

import expression.*;

import java.util.List;
import java.util.Map;

public class ExpressionParser implements TripleParser {
    private static final List <List <String>> operations = List.of(
            List.of("+", "-"),
            List.of("*", "/"),
            List.of("**", "//")
    );

    @Override
    public TripleExpression parse(final String expression) {
        return new Parser(new StringSource(expression)).parseExp(0);
    }

    private static class Parser extends BaseParser {

        public Parser(final CharSource expression) {
            super(expression);
        }

        private CommonExp parseExp(final int priority) {
            if (priority == 3) {
                return parsePrim();
            }
            skipWhitespace();
            CommonExp left = parseExp(priority + 1);
            boolean found;
            do {
                found = false;
                for (final String s : operations.get(priority)) {
                    if (test(s)) {
                        left = buildBinary(take(s.length()), left, parseExp(priority + 1));
                        found = true;
                        break;
                    }
                }
            } while (found);
            return left;
        }

        private CommonExp parsePrim() {
            skipWhitespace();
            CommonExp exp = parseInner();
            while (test('²') || test('³')) {
                exp = buildUnary(take(), exp);
                skipWhitespace();
            }
            return exp;
        }

        private CommonExp parseInner() {
            skipWhitespace();
            if (take('-')) {
                if (isNumber()) {
                    return parseNumber(true);
                } else {
                    return new Negate(parsePrim());
                }
            } else if (take('(')) {
                return parseBrackets();
            } else if (isLetter()) {
                return new Variable(parseVariable());
            } else {
                return parseNumber(false);
            }
        }

        private CommonExp buildUnary(final char op, final CommonExp exp) {
            return switch (op) {
                case '²' -> new Square(exp);
                case '³' -> new Cube(exp);
                default -> throw new AssertionError("Incorrect operation");
            };
        }

        private CommonExp buildBinary(final String op, final CommonExp left, final CommonExp right) {
            return switch (op) {
                case "+" -> new Add(left, right);
                case "-" -> new Subtract(left, right);
                case "*" -> new Multiply(left, right);
                case "/" -> new Divide(left, right);
                case "**" -> new Power(left, right);
                case "//" -> new Log(left, right);
                default -> throw new AssertionError("Incorrect operation");
            };
        }

        private String parseVariable() {
            final StringBuilder ans = new StringBuilder();
            while (isLetter()) {
                ans.append(take());
            }
            skipWhitespace();
            return ans.toString();
        }

        private CommonExp parseBrackets() {
            final CommonExp ans = parseExp(0);
            take();
            skipWhitespace();
            return ans;
        }

        private CommonExp parseNumber(final boolean minus) {
            final StringBuilder val = new StringBuilder(minus ? "-" : "");
            while (isNumber()) {
                val.append(take());
            }
            skipWhitespace();
            return new Const(Integer.parseInt(val.toString()));
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(getCur())) {
                take();
            }
        }
    }
}
