package de.fesere.tictactoe;

import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.exceptions.InvalidGameConfigurationException;
import de.fesere.tictactoe.players.Human;
import de.fesere.tictactoe.players.UnbeatableAI;
import de.fesere.tictactoe.ui.ConsoleUI;
import de.fesere.tictactoe.ui.UI;
import de.fesere.tictactoe.ui.UINotifier;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;

public class Main {

    public static void main(String[] args) {
        UI userInterface = new ConsoleUI();

        Player firstPlayer = new Human(X, userInterface);
        Player secondPlayer =  new UnbeatableAI(O);
        validatePlayers(firstPlayer, secondPlayer);

        Board board = new ArrayBoard();
        GameFinishedNotifier notifyAfterFinish = new UINotifier(userInterface);

        TicTacToe ticTacToe = new TicTacToe(board, firstPlayer, secondPlayer);
        ticTacToe.play(notifyAfterFinish);
    }

    private static void validatePlayers(Player firstPlayer, Player secondPlayer) {
        if(firstPlayer.getMarker() == secondPlayer.getMarker()) {
            throw new InvalidGameConfigurationException("Players must have different markers!");
        }
    }
}
