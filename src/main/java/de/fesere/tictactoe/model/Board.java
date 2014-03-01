package de.fesere.tictactoe.model;

public class Board {

    Marker[][] rows = new Marker[3][3];

    public Board() {
        initializeRows();
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
        rows[rowIndex][columnIndex] = marker;
        return this;
    }
}
