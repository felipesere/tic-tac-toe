package de.fesere.tictactoe;

public class ScriptedPlayer implements Player {

    private final Marker marker;
    private final  Move[] sequence;
    private int moveNumber = 0;

    public ScriptedPlayer(Marker marker, Move ... sequence) {
        this.marker = marker;
        this.sequence = sequence;
    }

    @Override
    public Board performMove(Board board) {

        Board result = board.applyMove(sequence[moveNumber], marker);
        moveNumber++;

        return result;
    }

    @Override
    public String getName() {
        return "ScriptedPlayer";
    }

    @Override
    public Marker getMarker() {
        return marker;
    }
}
