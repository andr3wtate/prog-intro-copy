package game;

import java.util.Arrays;
import java.util.Map;

public class CurPosition implements Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.INVALID, '#'
    );

    private final int m, n;
    int empty;
    final Cell[][] cells;
    Cell turn;

    public CurPosition(int m, int n, boolean isDiamond) {
        if (!isDiamond) {
            this.empty = m * n;
        }
        else {
            m = 2 * m - 1;
            n = 2 * n - 1;
            this.empty = n + n * (n - 1) / 2;
        }
        this.m = m;
        this.n = n;
        this.cells = new Cell[m][n];
        this.turn = Cell.X;
        for (int i = 0; i < this.m; i++) {
            if (!isDiamond) {
                Arrays.fill(cells[i], Cell.E);
            }
            else {
                for (int j = 0; j < n; j++) {
                    if (j < Math.abs(i - n / 2) || j >= n - Math.abs(i - n / 2)) {
                        cells[i][j] = Cell.INVALID;
                    }
                    else {
                        cells[i][j] = Cell.E;
                    }
                }
            }
        }
    }

    public CurPosition(int m, int n, Cell turn, Cell[][] cells) {
        this.m = m;
        this.n = n;
        this.turn = turn;
        this.cells = cells;
    }

    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    public void updCell(Move move) {
        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;
    }

    public int getRowSize() {
        return m;
    }

    public int getColSize() {
        return n;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        int lenm = String.valueOf(m).length(), lenn = String.valueOf(n).length();
        sb.append(" ".repeat(lenm));
        for (int c = 0; c < n; c++) sb.append(String.valueOf(c)).append(' ');
        for (int r = 0; r < m; r++) {
            sb.append(System.lineSeparator()).append(' ').append(r);
            sb.append(" ".repeat(lenm - String.valueOf(r).length()));
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(getCell(r, c)));
                sb.append(" ".repeat(String.valueOf(c).length()));
            }
        }
        return sb.toString();
    }
}
