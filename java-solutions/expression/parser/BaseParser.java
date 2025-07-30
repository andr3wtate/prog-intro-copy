package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected String take(final int cnt) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            ans.append(take());
        }
        return ans.toString();
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean test(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (source.nextInCnt(i) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    protected int getPos() {
        return source.getPos();
    }

    protected boolean isNumber() {
        return Character.isDigit(ch);
    }

    protected boolean isLetter() {
        return Character.isLetter(ch);
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected char getCur() {
        return ch;
    }

    protected boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }
}
