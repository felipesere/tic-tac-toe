package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.ui.UI;

import java.util.List;

public class Human extends BasePlayer {

    private final  UI userInterface;

    public Human(Marker marker, UI userInterface) {
        super(marker);

        this.userInterface = userInterface;
    }

    @Override
    public Board performMove(Board board) {
        userInterface.displayBoard(board);

        List<Move> possibleMoves = board.getPossibleMoves();
        userInterface.displayMoves(board);
        Move move = selectMove(possibleMoves);

        return board.applyMove(move, marker);
    }

    private Move selectMove(List<Move> possibleMoves) {
        int choice = userInterface.getSelectedMove(possibleMoves);

        return possibleMoves.get(choice);
    }
}