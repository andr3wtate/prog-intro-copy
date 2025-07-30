package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    private final int playerId;

    SequentialPlayer(final int playerId) {
        this.playerId = playerId;
    }

    public int getId() {
        return playerId;
    }

    @Override
    public Move move(final Position position, final Cell cell) throws IllegalStateException {
        //Board board = (Board) position;
        //board.makeMove(new Move(0, 0, Cell.X));
        for (int r = 0; r < position.getRowSize(); r++) {
            for (int c = 0; c < position.getColSize(); c++) {
                final Move move = new Move(r, c, cell, MoveType.DEFAULT);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }

    public boolean askForDraw(int no) {
        return true;
    }
}
