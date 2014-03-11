package de.fesere.tictactoe;

import de.fesere.tictactoe.board.ArrayBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.fesere.tictactoe.Marker.NONE;

public class BoardBuilder {

    private Board board = new ArrayBoard();
    private int rowIndex = 0;


    public BoardBuilder() {
    }

    public BoardBuilder row(String row) {
        Marker[] markers = getMarkerFromString(row);
        BoardBuilder builder = applyMarkersOnRow(rowIndex, markers);
        builder.rowIndex++;
        return builder;
    }

    private Marker[] getMarkerFromString(String row) {
        Pattern pattern = Pattern.compile("(\\[.?\\])");
        Matcher m = pattern.matcher(row);
        Marker[] marker = new Marker[3];
        for (int i = 0; i < 3; i++) {
            if (m.find()) {
                marker[i] = convertToMarker(m.group());
            }
        }
        return marker;

    }

    private Marker convertToMarker(String group) {
        if (group.contains("X")) {
            return Marker.X;
        }
        if (group.contains("O")) {
            return Marker.O;
        }
        return NONE;

    }


    private BoardBuilder applyMarkersOnRow(int row, Marker... marker) {
        for (int i = 0; i < 3; i++) {
            Move move = new Move(row, i);
            board = board.mark(move, marker[i]);
        }
        return this;
    }

    public Board build() {
        return board;
    }
}
