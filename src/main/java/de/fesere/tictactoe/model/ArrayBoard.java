package de.fesere.tictactoe.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayBoard implements Board {

    Marker[][] rows = new Marker[3][3];

    public ArrayBoard() {
        initializeRows();
    }

    public ArrayBoard(ArrayBoard arrayBoard, int markedRow, int markedColumn, Marker marker) {
        duplicateBoard(arrayBoard);
        rows[markedRow][markedColumn] = marker;
    }

    private void duplicateBoard(ArrayBoard arrayBoard) {
        for(int rowIndex = 0; rowIndex < 3; rowIndex++) {
            rows[rowIndex] = Arrays.copyOf(arrayBoard.rows[rowIndex], 3);
        }
    }

    private void initializeRows() {
        for (int i = 0; i < rows.length; i++) {
            initilizeRow(i);
        }
    }

    private void initilizeRow(int rowIndex) {
        for (int i = 0; i < rows[rowIndex].length; i++) {
            rows[rowIndex][i] = Marker.EMPTY;
        }
    }

    @Override
    public List<Line> getLines() {
        List<Line> lines = new LinkedList<Line>();
        for(int i = 0; i < 3; i++) {
            lines.add(getRow(i));
            lines.add(getColumn(i));
        }

        for(Diagonal diagonal : Diagonal.values()) {
            lines.add(getDiagonal(diagonal));
        }

        return lines;
    }

    @Override
    public Line getRow(int rowIndex) {
        return new Line(rows[rowIndex]);
    }

    @Override
    public Line getColumn(int columnIndex) {
        Marker[] columnMarkers = getColumnMarkers(columnIndex);
        return new Line(columnMarkers);
    }

    private Marker[] getColumnMarkers(int columnIndex) {
        Marker[] result = new Marker[3];
        for (int i = 0; i < 3; i++) {
            result[i] = rows[i][columnIndex];
        }

        return result;
    }

    @Override
    public Line getDiagonal(Diagonal diagonal) {
        Marker[] diagonalMarkers = getDiagonalMarkers(diagonal);

        return new Line(diagonalMarkers);
    }

    private Marker[] getDiagonalMarkers(Diagonal diagonal) {
        Marker[] diagonalMarkers = new Marker[3];
        for (int i = 0; i < 3; i++) {
            diagonalMarkers[i] = rows[diagonal.counter(i)][i];
        }
        return diagonalMarkers;
    }

    @Override
    public Board mark(int rowIndex, int columnIndex, Marker marker) {
        return new ArrayBoard(this, rowIndex, columnIndex, marker);
    }
}


