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
        Move move = selectMove(possibleMoves);
        return board.mark(move, marker);
    }

    private Move selectMove(List<Move> possibleMoves) {
        userInterface.displayMoves(possibleMoves);
        int choice = userInterface.getSelectedMove(possibleMoves);

        return possibleMoves.get(choice);
    }
}