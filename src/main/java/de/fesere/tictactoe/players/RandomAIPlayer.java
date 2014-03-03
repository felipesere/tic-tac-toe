package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.Player;

import java.util.List;
import java.util.Random;

public class RandomAIPlayer implements Player {

    private Marker marker;

    public RandomAIPlayer(Marker marker) {
        this.marker = marker;
    }

    @Override
    public Board performMove(Board board) {
        List<Move> possibleMoves = board.getPossibleMoves();
        int choice = new Random().nextInt(possibleMoves.size());
        Move move =  possibleMoves.get(choice);
        return board.mark(move, marker);
    }

    @Override
    public String getName() {
        return "Random AI";
    }

    @Override
    public Marker getMarker() {
        return marker;
    }
}
