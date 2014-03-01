package de.fesere.tictactoe.model;

import static de.fesere.tictactoe.model.Marker.*;

public class Line {

    private  Marker first;
    private  Marker middle;
    private  Marker last;

    public Line(Marker first, Marker middle, Marker last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    public boolean isEmpty() {
        return first == EMPTY &&
                middle == EMPTY &&
                last == EMPTY;
    }

    public Marker getMarker(int index) {
        switch (index){
            case 0 : return first;
            case 1 : return middle;
            case 2 : return last;
            default: throw new IllegalArgumentException("Index must be 0,1,2");
        }
    }

    public void mark(int index, Marker marker) {
        switch (index){
            case 0 : first = marker;
                     break;
            case 1 : middle = marker;
                    break;
            case 2 : last = marker;
                    break;
            default: throw new IllegalArgumentException("Index must be 0,1,2");
        }
    }
}
