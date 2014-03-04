package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidGameConfigurationException;


public class TicTacToe {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private Board board;

    public TicTacToe(Board board, Player firstPlayer, Player secondPlayer) {
        validatePlayers(firstPlayer, secondPlayer);
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.board = board;
    }

    private void validatePlayers(Player firstPlayer, Player secondPlayer) {
        if(firstPlayer.getMarker() == secondPlayer.getMarker()) {
            throw new InvalidGameConfigurationException("Players must have different markers!");
        }

    }

    public void play(GameFinishedNotifier notifier) {

        Player player = secondPlayer;
        while (isNotFinished(board)) {
            player = otherPlayer(player);
            board = player.performMove(board);
        }

        notifier.notifyFinished(board, player);
    }

    private static boolean isNotFinished(Board board) {
        return !board.hasWinner() && !board.hasDraw();
    }

    private Player otherPlayer(Player player) {
        if(player != firstPlayer) {
            return firstPlayer;
        }
        else {
            return secondPlayer;
        }

    }
}
