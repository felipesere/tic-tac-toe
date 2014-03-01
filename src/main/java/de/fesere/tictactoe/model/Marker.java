package de.fesere.tictactoe.model;

public enum Marker {
    EMPTY,
    X,
    O;

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
