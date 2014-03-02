package de.fesere.tictactoe;

public enum Marker {
    X,
    O,
    NONE;

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isMarked() {
        return !isNone();
    }


    @Override
    public String toString() {
        if(this== X) {
            return "X";
        }
        if(this==O){
            return "O";
        }
        return " ";
    }
}
