package de.fesere.tictactoe.model;

public class Line {

    private Marker [] marks = new Marker[3];

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
           if(!mark.isEmpty()) {
               return false;
           }
       }

        return true;
    }

    public Marker getMarker(int index) {
       return marks[index];
    }
}
