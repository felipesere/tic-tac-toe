package de.fesere.tictactoe.model;

public enum Diagonal {
    TOP_LEFT,
    BOTTOM_LEFT;


    public int counter(int i) {
        if (this == TOP_LEFT) {
           return i;
        } else {
           return 2-i;
        }
    }

}
