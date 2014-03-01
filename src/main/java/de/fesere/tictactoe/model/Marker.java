package de.fesere.tictactoe.model;

public enum Marker {
    NONE,
    X,
    O;

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isMarked() {
        return !isNone();
    }
}
