package de.fesere.tictactoe;

public interface Player {

    Board performMove(Board board);

    String getName();

    Marker getMarker();
}