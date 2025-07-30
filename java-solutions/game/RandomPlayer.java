package game;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;
    private final int playerId;

    public RandomPlayer(final Random random, final int playerId) {
        this.random = random;
        this.playerId = playerId;
    }

    public RandomPlayer(final int playerId) {
        this(new Random(), playerId);
    }

    public int getId() {
        return playerId;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(position.getRowSize());
            int c = random.nextInt(position.getColSize());
            final Move move = new Move(r, c, cell, MoveType.DEFAULT);
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    public boolean askForDraw(int no) {
        return true;
    }
}
