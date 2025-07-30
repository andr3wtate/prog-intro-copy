package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;

import java.util.List;

public class ExpressionParser implements TripleParser {

    private static final List <List <String>> operations = List.of(
            List.of("gcd", "lcm"),
            List.of("+", "-"),
            List.of("*", "/"),
            List.of("**", "//")
    );

    @Override
    public TripleExpression parse(final String expression) throws ParsingException {
        Parser p = new Parser(new StringSource(expression));
        return p.parse();
    }

    private static class Parser extends BaseParser {
        public Parser(final CharSource expression) {
            super(expression);
        }

        private CommonExp parse() throws ParsingException {
            CommonExp exp = parseExp(0, true);
            if (!eof()) {
                skipWhitespace();
                if (test(')')) {
                    throw new IncorrectParenthesisException("No opening parenthesis for parenthesis at index " + getPos());
                } else if (isNumber()) {
                    throw new IncorrectNumberException("Spaces in number at index " + getPos());
                } else {
                    throw new IncorrectIdentifierException("Unexpected symbol at index "+ getPos());
                }
            }
            return exp;
        }

        private CommonExp parseExp(int priority, boolean isLeft) throws ParsingException {
            if (priority == 4) {
                return parsePrim(isLeft);
            }
            skipWhitespace();
            CommonExp left = parseExp(priority + 1, isLeft);
            boolean fl;
            do {
                fl = false;
                for (String s : operations.get(priority)) {
                    if (test(s)) {
                        String op = take(s.length());
                        if (priority == 0 && isNumber()) {
                            throw new IncorrectIdentifierException("Between numbers and variables or functions gcd/lcm must be al least one whitespace");
                        }
                        left = buildBinary(op, left, parseExp(priority + 1, false));
                        fl = true;
                    }
                }
            } while (fl);
            return left;
        }

        private CommonExp parsePrim(boolean isLeft) throws ParsingException {
            skipWhitespace();
            CommonExp exp = parseInner(isLeft);
            while (test('²') || test('³')) {
                exp = buildUnary(take(), exp);
                skipWhitespace();
            }
            return exp;
        }

        private CommonExp parseInner(boolean isLeft) throws ParsingException {
            skipWhitespace();
            if (take('-')) {
                if (isNumber()) {
                    return parseNumber(true);
                } else {
                    return new CheckedNegate(parsePrim(isLeft));
                }
            } else if (take('(')) {
                return parseBrackets();
            } else if (isLetter()) {
                return new Variable(parseVariable());
            } else if (isNumber()) {
                return parseNumber(false);
            } else if (take('²')) {
                return new SquareExponent(parsePrim(isLeft));
            } else if (take('³')) {
                return new CubeExponent(parsePrim(isLeft));
            }
            if (test('[') || test('{')) {
                throw new IncorrectParenthesisException("Mismatched open parenthesis: ".concat(String.valueOf(take())).concat(" . Use ("));
            }
            if (isLeft) {
                throw new IncorrectArgumentsException("First argument expected at index ".concat(String.valueOf(getPos())));
            } else {
                if (test('+') || test('-') || test('*') || test('/') || test("gcd") || test("lcm")) {
                    throw new IncorrectArgumentsException("Middle operand expected at index ".concat(String.valueOf(getPos())));
                } else {
                    throw new IncorrectArgumentsException("Second operand expected at index ".concat(String.valueOf(getPos())));
                }
            }
        }

        private CommonExp buildUnary(char op, CommonExp exp) {
            return switch (op) {
                case '²' -> new Square(exp);
                case '³' -> new Cube(exp);
                default -> throw new AssertionError();
            };
        }

        private CommonExp buildBinary(String op, CommonExp left, CommonExp right) {
            return switch (op) {
                case "+" -> new CheckedAdd(left, right);
                case "-" -> new CheckedSubtract(left, right);
                case "*" -> new CheckedMultiply(left, right);
                case "/" -> new CheckedDivide(left, right);
                case "**" -> new Power(left, right);
                case "//" -> new Log(left, right);
                case "gcd" -> new Gcd(left, right);
                case "lcm" -> new Lcm(left, right);
                default -> throw new AssertionError();
            };
        }

        private String parseVariable() throws ParsingException {
            final StringBuilder ans = new StringBuilder();
            while (isLetter()) {
                ans.append(take());
            }
            if (ans.charAt(ans.length() - 1) != 'x' && ans.charAt(ans.length() - 1) != 'y' && ans.charAt(ans.length() - 1) != 'z') {
                if (ans.toString().equals("gcd") || ans.toString().equals("lcm")) {
                    throw new IncorrectArgumentsException("Not enough arguments in function ".concat(ans.toString()));
                } else {
                    throw new IncorrectIdentifierException("Incorrect identifier: ".concat(ans.toString()).concat(". Last character must be x or y or z"));
                }
            }
            skipWhitespace();
            return ans.toString();
        }

        private CommonExp parseBrackets() throws ParsingException {
            final CommonExp ans = parseExp(0, true);
            char c = take();
            if (c != ')') {
                if (c == ']' || c == '}') {
                    throw new IncorrectParenthesisException("Mismatched closing parenthesis: " + c + " . Use (");
                } else {
                    throw new IncorrectParenthesisException("Expected ')', found " + c);
                }
            }
            skipWhitespace();
            return ans;
        }

        private CommonExp parseNumber(final boolean minus) throws ParsingException {
            final StringBuilder val = new StringBuilder(minus ? "-" : "");
            while (isNumber()) {
                val.append(take());
            }
            if (isLetter()) {
                throw new IncorrectIdentifierException("Between numbers and variables or functions gcd/lcm must be al least one whitespace");
            }
            skipWhitespace();
            try {
                return new Const(Integer.parseInt(val.toString()));
            } catch (NumberFormatException e) {
                throw new OverflowException(e.getMessage());
            }
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(getCur())) {
                take();
            }
        }
    }
}
