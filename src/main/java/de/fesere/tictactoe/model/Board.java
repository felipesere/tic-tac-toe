package de.fesere.tictactoe.model;

import java.util.List;

public interface Board {
    List<Line> getLines();

    Line getRow(int rowIndex);

    Line getColumn(int columnIndex);

    Line getDiagonal(Diagonal diagonal);

    Board mark(int rowIndex, int columnIndex, Marker marker);
}
