package de.fesere.tictactoe.model;

import de.fesere.tictactoe.model.board.Diagonal;
import de.fesere.tictactoe.model.board.Line;

import java.util.List;
import java.util.Set;

public interface Board {
    List<Line> getLines();

    Line getRow(int rowIndex);

    Line getColumn(int columnIndex);

    Line getDiagonal(Diagonal diagonal);

    Board mark(Move move, Marker marker);

    Set<Move> getPossibleMoves();
}
