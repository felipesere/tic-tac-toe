package de.fesere.tictactoe.board;

public enum Diagonal {
    TOP_LEFT,
    BOTTOM_LEFT;


    public int counter(int i) {
        if (this == TOP_LEFT) {
           return i;
        } else {
            // for counting bottom up
           return 2-i;
        }
    }

}
