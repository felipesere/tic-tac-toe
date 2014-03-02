package de.fesere.tictactoe.board;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.InvalidMoveException;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;

import java.util.*;

import static de.fesere.tictactoe.Marker.NONE;

public class ArrayBoard implements Board {

    Marker[][] rows = new Marker[3][3];

    public ArrayBoard() {
        initializeRows();
    }

    public ArrayBoard(ArrayBoard arrayBoard, Move move, Marker marker) {
        duplicateBoard(arrayBoard);
        rows[move.getRow()][move.getColumn()] = marker;
    }

    private void duplicateBoard(ArrayBoard arrayBoard) {
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
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
            rows[rowIndex][i] = NONE;
        }
    }

    @Override
    public List<Line> getLines() {
        List<Line> lines = new LinkedList<Line>();
        for (int i = 0; i < 3; i++) {
            lines.add(getRow(i));
            lines.add(getColumn(i));
        }

        for (Diagonal diagonal : Diagonal.values()) {
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
    public Board mark(Move move, Marker marker) {
        if(isMarked(move)) {
            throw new InvalidMoveException(move, " Is already taken");
        }

        return new ArrayBoard(this, move,  marker);
    }

    private boolean isMarked(Move move) {
        return rows[move.getRow()][move.getColumn()].isMarked();
    }

    @Override
    public List<Move> getPossibleMoves() {
        List<Move> moves = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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

    private boolean hasWinningRows() {
        for(int i = 0; i < 3; i++) {
           Line line = getRow(i);
           if(line.hasWinner()) {
               return true;
           }
        }

        return false;
    }

    private boolean hasWinningColumns() {
        for(int i = 0; i < 3; i++) {
            Line line = getColumn(i);
            if(line.hasWinner()) {
                return true;
            }
        }

        return false;
    }

    private boolean hasWinningDiagonals() {
        for(Diagonal diagonal : Diagonal.values()) {
            Line line = getDiagonal(diagonal);
            if(line.hasWinner()) {
                return true;
            }
        }

        return false;
    }
}

