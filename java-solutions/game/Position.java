package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Position {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
    void updCell(Move move);
    int getRowSize();
    int getColSize();
}
