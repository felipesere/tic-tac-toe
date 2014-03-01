package de.fesere.tictactoe.model;

public class Line {

    private static final int SIZE = 3;
    private Marker [] marks = new Marker[SIZE];

    public Line(Marker first, Marker middle, Marker last) {
       marks[0] = first;
       marks[1] = middle;
       marks[2] = last;
    }

    public boolean isEmpty() {
       for(Marker mark : marks) {
           if(!mark.isEmpty()) {
               return false;
           }
       }

        return true;
    }

    public Marker getMarker(int index) {
       return marks[index];
    }

    public void mark(int index, Marker marker) {
       marks[index] = marker;
    }
}
