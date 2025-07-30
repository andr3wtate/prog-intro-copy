package game;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;
    private final int playerId;

    public HumanPlayer(final PrintStream out, final Scanner in, final int playerId) {
        this.out = out;
        this.in = in;
        this.playerId = playerId;
    }

    public HumanPlayer(final int playerId) {
        this(System.out, new Scanner(System.in), playerId);
    }

    public int getId() {
        return playerId;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        //Board b = (Board) position;
        //b.makeMove(new Move(0, 0, Cell.X, MoveType.DEFAULT));
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int row = 0, col = 0;
            try {
                String s = in.nextLine();
                if (s.equals("I give up")) {
                    return new Move(0, 0, cell, MoveType.GIVE_UP);
                }
                else if (s.equals("How about a draw")) {
                    return new Move(0, 0, cell, MoveType.ASK_FOR_DRAW);
                }
                Scanner parser = new Scanner(s);
                row = parser.nextInt();
                col = parser.nextInt();
                final Move move = new Move(row, col, cell, MoveType.DEFAULT);
                if (position.isValid(move)) {
                    parser.close();
                    return move;
                }
                out.println("Move " + move + " is invalid");
            }
            catch (NoSuchElementException e) {
                System.out.println("Incorrect input");
            }
        }
    }

    public boolean askForDraw(int id) {
        System.out.println("Player " + id + " are you agree for a draw?" + System.lineSeparator() + "Enter Y/N");
        String s;
        while (true) {
            s = in.nextLine();
            if (s.equals("Y")) {
                return true;
            } else if (s.equals("N")) {
                return false;
            } else {
                System.out.println("Please enter Y/N");
            }
        }
    }
}
