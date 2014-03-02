package de.fesere.tictactoe;

import java.util.List;

public interface UI {

    void use(Board board);

    void displayBoard(Board board);

    void displayMoves(List<Move> moves);

    int getSelectedMove();

    void showWinner(Player player);
}
