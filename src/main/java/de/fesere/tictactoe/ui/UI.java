package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.Player;

import java.util.List;

public interface UI {

    void displayBoard(Board board);

    void displayMoves(List<Move> moves);

    int getSelectedMove(List<Move> moves);

    void showWinner(Player player);

    void showDraw();
}
