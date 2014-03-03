package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidGameConfigurationException;

import static de.fesere.tictactoe.Marker.X;

public class TicTacToe {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private Board board;

    public TicTacToe(Board board, Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        validatePlayers(firstPlayer, secondPlayer);
        this.board = board;
    }

    private void validatePlayers(Player firstPlayer, Player secondPlayer) {
        if(firstPlayer.getMarker() == secondPlayer.getMarker()) {
            throw new InvalidGameConfigurationException("Players must have different markers!");
        }

    }

    public void play(GameFinishedNotifier notifier) {
        Player player = getStartingPlayer();
        while (isNotFinished(board)) {
            board = player.performMove(board);
            player = otherPlayer(player);
        }

        notifier.notifyFinished(board, player);

    }

    private Player getStartingPlayer() {
        if(firstPlayer.getMarker() == X) {
            return firstPlayer;
        }
        else {
            return secondPlayer;
        }
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
