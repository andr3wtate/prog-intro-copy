package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    char next();
    char nextInCnt(int cnt);
    IllegalArgumentException error(String message);
    int getPos();
}
