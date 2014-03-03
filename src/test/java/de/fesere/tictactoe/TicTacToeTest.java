package de.fesere.tictactoe;

import de.fesere.tictactoe.exceptions.InvalidGameConfigurationException;
import de.fesere.tictactoe.players.BoardBuilder;
import de.fesere.tictactoe.players.RandomAIPlayer;
import org.junit.Test;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TicTacToeTest {

    Player firstPlayer = new RandomAIPlayer(X);
    Player secondPlayer =  new RandomAIPlayer(O);

    @Test(expected = InvalidGameConfigurationException.class)
    public void throwExceptionIfSameMarkingForBothPlayers(){
        new TicTacToe(null, new RandomAIPlayer(X), new RandomAIPlayer(X));
    }


    @Test
    public void correctNotificationOfPlayerXWinning() {
        Board board = new BoardBuilder().row1("[X][X][X]")
                                        .row2("[X][O][O]")
                                        .row3("[O][O][X|").build();

        TicTacToe ticTacToe = new TicTacToe(board,firstPlayer, secondPlayer);
        ticTacToe.play(new GameFinishedNotifier() {
            @Override
            public void notifyFinished(Board board, Player player) {
                assertThat(board.hasWinner(), is(true));
                assertThat(player, is(firstPlayer));
            }
        });
    }

    @Test
    public void correctNotificationOfDraw() {
        Board board = new BoardBuilder().row1("[O][X][X]")
                                        .row2("[X][O][O]")
                                        .row3("[O][X][X]").build();

        TicTacToe ticTacToe = new TicTacToe(board,firstPlayer, secondPlayer);
        ticTacToe.play(new GameFinishedNotifier() {
            @Override
            public void notifyFinished(Board board, Player player) {
                assertThat(board.hasDraw(), is(true));
            }
        });
    }

    @Test
    public void correctNotificationAfterSingleMove() {
        Board board = new BoardBuilder().row1("[O][X][X]")
                                        .row2("[X][O][O]")
                                        .row3("[ ][X][X]").build();

        TicTacToe ticTacToe = new TicTacToe(board,firstPlayer, secondPlayer);
        ticTacToe.play(new GameFinishedNotifier() {
            @Override
            public void notifyFinished(Board board, Player player) {
                assertThat(board.hasWinner(), is(true));
            }
        });
    }
}
