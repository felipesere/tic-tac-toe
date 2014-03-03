package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.board.ArrayBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.fesere.tictactoe.Marker.NONE;

public class BoardBuilder {

    private Board board = new ArrayBoard();

    public BoardBuilder() {
    }

    public BoardBuilder row1(String row) {
        Marker [] markers = getMarkerFromString(row);
        return applyMarkersOnRow(0, markers);
    }

    private Marker[] getMarkerFromString(String row) {
        Pattern pattern = Pattern.compile("(\\[.?\\])");
        Matcher m = pattern.matcher(row);
        Marker [] marker = new Marker[3];
        for(int i = 0; i<3; i++) {
            m.find();
            marker[i] =convertToMarker(m.group());
        }
        return marker;

    }

    private Marker convertToMarker(String group) {
        if(group.contains("X")) {
            return Marker.X;
        }
        if(group.contains("O")) {
            return Marker.O;
        }
        return NONE;

    }


    private BoardBuilder applyMarkersOnRow(int row, Marker ... marker) {
        for(int i =0; i < 3; i++) {
            Move move = new Move(row, i);
            board = board.mark(move, marker[i]);
        }
        return this;
    }

    public BoardBuilder row2(String row) {
        Marker [] markers = getMarkerFromString(row);
        return applyMarkersOnRow(1, markers);
    }

    public BoardBuilder row3(String row) {
        Marker [] markers = getMarkerFromString(row);
        return applyMarkersOnRow(2, markers);
    }

    public Board build() {
        return board;
    }
}
