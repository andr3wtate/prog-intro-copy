package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard extends CurPosition implements Board {
    private final int k;

    public TicTacToeBoard(int m, int n, int k, boolean isDiamond) {
        super(m, n, isDiamond);
        this.k = k;
    }

    @Override
    public Position getPosition() {
        return new CurPosition(getRowSize(), getColSize(), turn, cells);
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    // count
    private int Count(final Move move, final int dx,  final  int dy) {
        int cnt = 0;
        for (int i = move.getRow(), j = move.getColumn(); i >= 0 && i < getRowSize() &&
                j >= 0 && j <= getColSize() && getCell(i, j) == turn; i += dx, j += dy) {
            cnt++;
            if (cnt == k) {
                return cnt;
            }
        }
        return cnt;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        updCell(move);
        int[] dx = {0, 1, 1, 1};
        int[] dy = {1, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            if (Count(move, dx[i], dy[i]) + Count(move, -dx[i], -dy[i]) - 1 >= k) {
                return Result.WIN;
            }
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }
}
