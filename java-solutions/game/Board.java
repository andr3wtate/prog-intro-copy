package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board extends Position {
    Position getPosition();
    Cell getTurn();
    Result makeMove(Move move);
}