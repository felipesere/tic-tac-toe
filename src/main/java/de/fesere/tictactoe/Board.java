package de.fesere.tictactoe;

import java.util.List;

public interface Board {
    List<Line> getLines();

    Board applyMove(Move move, Marker marker);

    List<Move> getPossibleMoves();

    List<Line> getRows();

    boolean isFinished();

    boolean hasWinner();

    boolean hasDraw();

    public interface Line {
        boolean isEmpty();
        Marker getMarker(int index);
        boolean hasWinner();
    }
}
