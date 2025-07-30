import java.io.*;
import java.lang.StringBuilder;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.function.IntPredicate;

public class MyScanner {
    private Reader in;
    private char[] buffer;
    private int pointer, lineNumb, curLength;
    private char prLast;

    public MyScanner(final int bufferSize) throws IOException {
        in = new InputStreamReader(System.in);
        buffer = new char[bufferSize];
        prLast = 0;
        getBuffer();
    }

    public MyScanner(final String input, final int bufferSize) throws IOException {
        in = new InputStreamReader(new FileInputStream(input));
        buffer = new char[bufferSize];
        prLast = 0;
        getBuffer();
    }

    private boolean checkLetter(final int x) {
        return Character.isLetter(x) || Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'';
    }

    private boolean checkInt(final int x) {
        return Character.getType(x) == Character.DECIMAL_DIGIT_NUMBER || x == '-';
    }

    private boolean checkSep() {
        return buffer[pointer] == '\r'
                || buffer[pointer] == '\n' && ((pointer != 0 ? buffer[pointer - 1] : prLast) != '\r');
    }

    private boolean getBuffer() throws IOException {
        if (curLength == -1) {
            return false;
        }
        pointer = 0;
        curLength = in.read(buffer);
        if (curLength <= 0) {
            curLength = -1;
            this.close();
            return false;
        }
        return true;
    }

    public boolean hasNextChar() throws IOException {
        if (curLength == -1) {
            return false;
        }
        if (pointer != curLength) {
            return true;
        }
        prLast = buffer[pointer - 1];
        return getBuffer();
    }

    private boolean hasNext(final IntPredicate f) throws IOException {
        // :NOTE: a\rb
        while (hasNextChar() && !f.test(buffer[pointer])) {
            if (checkSep()) {
                lineNumb++;
            }
            pointer++;
        }
        // :NOTE: --1
        // :NOTE: '-'
        return hasNextChar();
    }

    public boolean hasNextInt() throws IOException {
        return hasNext(this::checkInt);
    }

    public boolean hasNextWord() throws IOException {
        return hasNext(this::checkLetter);
    }

    public boolean hasNextIntWord() throws IOException {
        return hasNext(smb -> checkLetter(smb) | checkInt(smb));
    }

    private String read(final IntPredicate f) throws IOException {
        final StringBuilder ans = new StringBuilder();
        while (hasNextChar() && f.test(buffer[pointer])) {
            ans.append(buffer[pointer++]);
        }
        return ans.toString();
    }

    public int nextInt() throws NoSuchElementException, IOException {
        if (!hasNextInt()) {
            throw new NoSuchElementException("You closed input or reached EOF");
        }
        final String s = read(this::checkInt);
        if (hasNextChar() && buffer[pointer] == 'o' || buffer[pointer] == 'O') {
            long ansf = Integer.parseUnsignedInt(s.substring(0, s.length()), 8);
            if (ansf >= (1L << 31)) {
                ansf -= (1L << 32);
            }
            return (int)ansf;
        }
        return Integer.parseInt(s);
    }

    public String nextWord() throws NoSuchElementException, IOException {
        if (!hasNextWord()) {
            throw new NoSuchElementException("You closed input or reached EOF");
        }
        return read(this::checkLetter);
    }

    public String nextIntWord() throws NoSuchElementException, IOException {
        if (!hasNextIntWord()) {
            throw new NoSuchElementException("You closed input or reached EOF");
        }
        return read(smb -> checkLetter(smb) | checkInt(smb));
    }

    public int getLineNo() {
        return lineNumb;
    }

    public void close() {
        try {
            in.close();
            curLength = -1;
        }
        catch (final IOException e){
            System.err.println(e.getMessage());
        }
    }
}
