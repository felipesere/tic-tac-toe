package de.fesere.tictactoe.model;

public class Move {
    private final int row;
    private final int column;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Move)){
            return false;
        }
        Move other = (Move) obj;
        return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
        return 37 + 5 * row + 89 * column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
