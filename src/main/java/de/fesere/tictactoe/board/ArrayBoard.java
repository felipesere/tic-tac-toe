package de.fesere.tictactoe.board;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.exceptions.InvalidMoveException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static de.fesere.tictactoe.Marker.NONE;

public class ArrayBoard implements Board {
    private static final int SIZE = 3;

    Marker[][] rows = new Marker[SIZE][SIZE];

    public ArrayBoard() {
        initializeRows();
    }

    public ArrayBoard(ArrayBoard arrayBoard, Move move, Marker marker) {
        duplicateBoard(arrayBoard);
        rows[move.getRow()][move.getColumn()] = marker;
    }

    private void duplicateBoard(ArrayBoard arrayBoard) {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            rows[rowIndex] = Arrays.copyOf(arrayBoard.rows[rowIndex], SIZE);
        }
    }

    private void initializeRows() {
        for (int i = 0; i < SIZE; i++) {
            initilizeRow(i);
        }
    }

    private void initilizeRow(int rowIndex) {
        for (int i = 0; i < SIZE; i++) {
            rows[rowIndex][i] = NONE;
        }
    }

    @Override
    public List<Line> getLines() {
        List<Line> lines = new LinkedList<>();
        lines.addAll(getRows());
        lines.addAll(getColumns());
        lines.addAll(getDiagonals());
        return lines;
    }

    @Override
    public Line getRow(int rowIndex) {
        return new Line(rows[rowIndex]);
    }

    @Override
    public List<Line> getRows() {
        List<Line> rows = new LinkedList<>();
        for(int i=0; i < SIZE; i++) {
            rows.add(getRow(i));
        }

        return rows;
    }

    public List<Line> getColumns() {
        List<Line> rows = new LinkedList<>();
        for(int i=0; i < SIZE; i++) {
            rows.add(getColumn(i));
        }

        return rows;
    }

    public List<Line> getDiagonals() {
        List<Line> diagonals = new LinkedList<>();
        for (Diagonal diagonal : Diagonal.values()) {
            diagonals.add(getDiagonal(diagonal));
        }
        return diagonals;
    }

    @Override
    public Line getColumn(int columnIndex) {
        Marker[] columnMarkers = getColumnMarkers(columnIndex);
        return new Line(columnMarkers);
    }

    private Marker[] getColumnMarkers(int columnIndex) {
        Marker[] result = new Marker[SIZE];
        for (int i = 0; i < SIZE; i++) {
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
        Marker[] result = new Marker[SIZE];
        for (int i = 0; i < SIZE; i++) {
            result[i] = rows[diagonal.counter(i)][i];
        }
        return result;
    }

    @Override
    public Board mark(Move move, Marker marker) {
        if(isMarked(move)) {
            throw new InvalidMoveException(" Is already taken");
        }

        return new ArrayBoard(this, move,  marker);
    }

    private boolean isMarked(Move move) {
        return rows[move.getRow()][move.getColumn()].isMarked();
    }

    @Override
    public List<Move> getPossibleMoves() {
        List<Move> moves = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(rows[i][j].isNone()) {
                    moves.add(new Move(i,j));
                }
            }
        }
        return moves;
    }

    @Override
    public boolean hasWinner() {
        return hasWinningRows() || hasWinningColumns() || hasWinningDiagonals();
    }

    @Override
    public boolean hasDraw() {
        return !hasWinner() && everythingMarked();
    }

    private boolean everythingMarked() {
       return getPossibleMoves().size() == 0;
    }

    private boolean hasWinner(List<Line> lines) {
        for(Line line : lines) {
            if(line.hasWinner()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWinningRows() {
      return hasWinner(getRows());
    }

    private boolean hasWinningColumns() {
        return hasWinner(getColumns());
    }

    private boolean hasWinningDiagonals() {
        return hasWinner(getDiagonals());
    }
}


