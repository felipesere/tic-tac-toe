package de.fesere.tictactoe;

import java.util.List;
import java.util.Map;

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

        boolean hasWinner();

        List<Marker> getMarkers();
    }
}
