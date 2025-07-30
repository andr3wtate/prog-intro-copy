package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player player1, player2;
    private int id1, id2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        this.id1 = player1.getId();
        this.id2 = player2.getId();
    }

    public int play(Board board) {
        if (player1.getId() == -1) {
            return id2;
        }
        if (player2.getId() == -1) {
            return id1;
        }
        while (true) {
            final int result1 = move(board, id1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, id2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final Board board, final int no) {
        final Player playerMove = no == id1 ? player1 : player2;
        final Player playerWait = no == id1 ? player2 : player1;
        try {
            Move move = playerMove.move(board.getPosition(), board.getTurn());
            if (move.getType() == MoveType.ASK_FOR_DRAW) {
                boolean isDraw = playerWait.askForDraw(no == id1 ? id2 : id1);
                if (isDraw) {
                    log("Draw");
                    return 0;
                } else {
                    move = playerMove.move(board.getPosition(), board.getTurn());
                    if (move.getType() == MoveType.ASK_FOR_DRAW) {
                        System.out.println("You can't ask for draw again on this turn, so you lose");
                        log("Player " + no + " lose");
                        return no == id1 ? id2 : id1;
                    }
                }
            }
            if (move.getType() == MoveType.GIVE_UP) {
                log("Player " + no + " lose");
                return no == id1 ? id2 : id1;
            }
            final Result result = board.makeMove(move);
            log("Player " + no + " move: " + move);
            log("Position:\n" + board);
            if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lose");
                return no == id1 ? id2 : id1;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else {
                return -1;
            }
        }
        catch (IllegalStateException e) {
            System.out.println("There is no correct moves left");
            return no == id1 ? id2 : id1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
