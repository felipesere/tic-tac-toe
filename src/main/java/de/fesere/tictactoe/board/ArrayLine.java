package de.fesere.tictactoe.board;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;

public class ArrayLine implements Board.Line {

    private final Marker[] marks;

    public ArrayLine(Marker[] markers) {
        this.marks = markers;
    }

    public boolean isEmpty() {
        for(Marker mark : marks) {
            if(!mark.isNone()) {
                return false;
            }
        }

        return true;
    }

    public Marker getMarker(int index) {
        return marks[index];
    }

    public boolean hasWinner() {
        return marks[0].isMarked() && allMarkingsEqual();
    }

    private boolean allMarkingsEqual() {
        Marker reference = marks[0];
        for(Marker marker : marks) {
            if(reference != marker) {
                return  false;
            }
        }
        return true;
    }
}