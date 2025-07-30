package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class Move {
    private final int row;
    private final int column;
    private final Cell value;
    private final MoveType type;

    public Move(final int row, final int column, final Cell value, MoveType type) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getValue() {
        return value;
    }

    public MoveType getType(){
        return type;
    }

    @Override
    public String toString() {
        return "row=" + row + ", column=" + column + ", value=" + value;
    }
}
