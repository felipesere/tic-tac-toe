package de.fesere.tictactoe;

import de.fesere.tictactoe.players.RandomAI;
import org.junit.Test;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TicTacToeTest {

    private Player firstPlayer = new RandomAI(X);
    private Player secondPlayer =  new RandomAI(O);

    @Test
    public void testGameLoopAlternatesCorrectly() {
        Board board = new BoardBuilder().row("[O][ ][X]")
                                        .row("[X][ ][O]")
                                        .row("[ ][X][X]").build();

        firstPlayer = new ScriptedPlayer(X, new Move(0,1), new Move(2,0));
        secondPlayer = new ScriptedPlayer(O, new Move(1,1));

        TicTacToe ticTacToe = new TicTacToe(board,firstPlayer, secondPlayer);
        ticTacToe.play(new GameFinishedNotifier() {
            @Override
            public void notifyFinished(Board board, Player player) {
                assertThat(board.hasWinner(), is(true));
            }
        });
    }


    @Test
    public void correctNotificationOfPlayerXWinning() {
        Board board = new BoardBuilder().row("[X][ ][X]")
                                        .row("[X][O][O]")
                                        .row("[O][O][X]").build();

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
        Board board = new BoardBuilder().row("[O][X][X]")
                                        .row("[X][O][O]")
                                        .row("[O][X][X]").build();

        TicTacToe ticTacToe = new TicTacToe(board,firstPlayer, secondPlayer);
        ticTacToe.play(new GameFinishedNotifier() {
            @Override
            public void notifyFinished(Board board, Player player) {
                assertThat(board.hasDraw(), is(true));
            }
        });
    }
}
