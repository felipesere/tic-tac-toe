package de.fesere.tictactoe;

import java.util.List;

public interface UI {

    void displayBoard(Board board);

    void displayMoves(List<Move> moves);

    int getSelectedMove(List<Move> moves);

    void showWinner(Player player);
}
