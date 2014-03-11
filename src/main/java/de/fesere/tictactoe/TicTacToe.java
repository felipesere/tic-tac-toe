package de.fesere.tictactoe;

public class TicTacToe {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private Board board;

    public TicTacToe(Board board, Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.board = board;
    }

    public void play(GameFinishedNotifier notifier) {

        Player player = secondPlayer;
        while (!board.isFinished()) {
            player = otherPlayer(player);
            board = player.performMove(board);
        }

        notifier.notifyFinished(board, player);
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
