package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import org.junit.Test;

import static de.fesere.tictactoe.Marker.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UnbeatableAIPlayerTest {

    UnbeatableAIPlayer player = new UnbeatableAIPlayer(X);


    @Test
    public void test_directWin() {
        Board initial = new BoardBuilder().row1("[X][X][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void test_avoidDirectDefeat() {
        Board initial = new BoardBuilder().row1("[O][O][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.getRow(0).getMarker(2), is(X));
    }

    @Test
    public  void test_XshouldWinDirectly() {
        Board initial = new BoardBuilder().row1("[O][X][ ]")
                                          .row2("[ ][X][ ]")
                                          .row3("[O][ ][ ]").build();

        Board result = player.performMove(initial);

        assertThat(result.getRow(2).getMarker(1), is(X));
        assertThat(result.hasWinner(), is(true));
    }
}
