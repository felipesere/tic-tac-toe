package de.fesere.tictactoe.players;

import de.fesere.tictactoe.*;

import java.util.List;

public class HumanPlayer implements Player {

    private final Marker marker;
    private UI userInterface;

    public HumanPlayer(Marker marker, UI userInterface) {
        if(marker.isNone()) {
            throw new IllegalArgumentException("Marker must be either X or O");
        }
        this.marker = marker;
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