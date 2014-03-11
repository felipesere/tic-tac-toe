package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;

import java.util.List;
import java.util.Random;

public class RandomAI extends BasePlayer {

    public RandomAI(Marker marker) {
        super(marker);
    }

    @Override
    public Board performMove(Board board) {
        List<Move> possibleMoves = board.getPossibleMoves();
        int choice = new Random().nextInt(possibleMoves.size());
        Move move =  possibleMoves.get(choice);
        return board.applyMove(move, marker);
    }
}
