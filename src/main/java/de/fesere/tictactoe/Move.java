package de.fesere.tictactoe;

public class Move {
    private final int row;
    private final int column;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
