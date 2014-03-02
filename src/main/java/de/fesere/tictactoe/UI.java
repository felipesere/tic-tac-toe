package de.fesere.tictactoe;

import java.util.List;

public interface UI {

    void displayBoard(Board board);

    void displayMoves(List<Move> moves);

    int getSelectedMove();

    void showWinner(Player player);
}
