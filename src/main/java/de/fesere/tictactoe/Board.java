package de.fesere.tictactoe;

import java.util.List;

public interface Board {
    List<Line> getLines();

    Board mark(Move move, Marker marker);

    List<Move> getPossibleMoves();

    boolean isFinished();

    boolean hasWinner();

    boolean hasDraw();

    List<Line> getRows();


    public interface Line {
        boolean isEmpty();
        Marker getMarker(int index);
        boolean hasWinner();
    }
}
