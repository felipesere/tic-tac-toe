package de.fesere.tictactoe;

import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.players.HumanPlayer;
import de.fesere.tictactoe.players.UnbeatableAIPlayer;
import de.fesere.tictactoe.ui.ConsoleUI;
import de.fesere.tictactoe.ui.UI;
import de.fesere.tictactoe.ui.UINotifier;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;

public class Main {

    public static void main(String[] args) {
        UI userInterface = new ConsoleUI();

        Player firstPlayer = new HumanPlayer(X, userInterface);
        Player secondPlayer =  new UnbeatableAIPlayer(O);
        Board board = new ArrayBoard();
        GameFinishedNotifier notifyAfterFinish = new UINotifier(userInterface);


        TicTacToe ticTacToe = new TicTacToe(board, firstPlayer, secondPlayer);

        ticTacToe.play(notifyAfterFinish);

    }

}
