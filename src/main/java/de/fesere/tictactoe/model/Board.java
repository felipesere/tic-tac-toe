package de.fesere.tictactoe.model;

import java.util.LinkedList;
import java.util.List;

import static de.fesere.tictactoe.model.Marker.EMPTY;

public class Board {

    List<Line> rows = new LinkedList<Line>();
    List<Line> columns = new LinkedList<Line>();
    List<Line> diagonals = new LinkedList<Line>();

    public Board() {
        for(int i = 0; i < 3; i++) {
            rows.add(new Line(EMPTY, EMPTY, EMPTY));
            columns.add(new Line(EMPTY, EMPTY, EMPTY));
        }

        for(int i = 0; i < 3; i++) {
            diagonals.add(new Line(EMPTY, EMPTY, EMPTY));
        }
    }


    public Line getRow(int rowIndex) {
      return rows.get(rowIndex);
    }

    public Line getColumn(int columnIndex) {
        return columns.get(columnIndex);
    }

    public Line getDiagonal(int diagonalIndex) {
        return diagonals.get(diagonalIndex);
    }

    public Board mark(int rowIndex, int columnIndex, Marker marker) {
      Line row = rows.get(rowIndex);
      row.mark(columnIndex, marker);

      Line column = columns.get(columnIndex);
      column.mark(rowIndex, marker);

      return this;
    }
}
