package de.fesere.tictactoe;

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


    @Override
    public String toString() {
        switch (this) {
            case NONE : return " ";
            case  X   : return "X";
            case  O   : return "O";
        }

        return "Type did not match!";
    }
}
