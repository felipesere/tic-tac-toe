package de.fesere.tictactoe;

import de.fesere.tictactoe.board.Diagonal;
import de.fesere.tictactoe.board.Line;

import java.util.List;

public interface Board {
    List<Line> getLines();

    Line getRow(int rowIndex);

    List<Line> getRows();

    Line getColumn(int columnIndex);

    Line getDiagonal(Diagonal diagonal);

    Board mark(Move move, Marker marker);

    List<Move> getPossibleMoves();

    boolean hasWinner();

    boolean hasDraw();
}
