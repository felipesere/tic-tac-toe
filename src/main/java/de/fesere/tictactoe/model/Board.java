package de.fesere.tictactoe.model;

import java.util.Arrays;

public class Board {

    Marker[][] rows = new Marker[3][3];

    public Board() {
        initializeRows();
    }

    public Board(Board board, int markedRow, int markedColumn, Marker marker) {
        duplicateBoard(board);
        rows[markedRow][markedColumn] = marker;
    }

    private void duplicateBoard(Board board) {
        for(int rowIndex = 0; rowIndex < 3; rowIndex++) {
            rows[rowIndex] = Arrays.copyOf(board.rows[rowIndex], 3);
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


    public Line getRow(int rowIndex) {
        return new Line(rows[rowIndex]);
    }

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

    public Board mark(int rowIndex, int columnIndex, Marker marker) {
        return new Board(this, rowIndex, columnIndex, marker);
    }
}
