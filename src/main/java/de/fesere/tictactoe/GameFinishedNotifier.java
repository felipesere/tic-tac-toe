package de.fesere.tictactoe;

public interface GameFinishedNotifier {

    void notifyFinished(Board board, Player lastPlayer);
}
