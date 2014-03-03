package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.GameFinishedNotifier;
import de.fesere.tictactoe.Player;

public class UINotifier implements GameFinishedNotifier {
    UI userInterface;

    public UINotifier(UI userInterface) {
        this.userInterface = userInterface;
    }


    @Override
    public void notifyFinished(Board board, Player player) {
        if (board.hasWinner()) {
            userInterface.showWinner(player.getName());
        } else {
            userInterface.showDraw();
        }
        userInterface.displayBoard(board);
    }
}
