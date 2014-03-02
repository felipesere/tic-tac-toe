package de.fesere.tictactoe.board;

import de.fesere.tictactoe.Marker;

public class Line {

    private Marker[] marks = new Marker[3];

    public Line(Marker first, Marker middle, Marker last) {
       marks[0] = first;
       marks[1] = middle;
       marks[2] = last;
    }

    public Line(Marker[] markers) {
        this(markers[0], markers[1], markers[2]);
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
        return marks[0].isMarked() &&
                marks[0] == marks[1] &&
                marks[1] == marks[2];
    }
}
