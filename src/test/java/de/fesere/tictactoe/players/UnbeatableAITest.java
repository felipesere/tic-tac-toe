package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.BoardBuilder;
import de.fesere.tictactoe.Player;
import org.junit.Test;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UnbeatableAITest {

    private final UnbeatableAI player = new UnbeatableAI(X);

    @Test
    public void testSimpleDirectWin() {
        Board initial = new BoardBuilder().row("[X][X][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void testAvoidDirectDefeat() {
        Board initial = new BoardBuilder().row("[O][O][ ]").build();

        Board result = player.performMove(initial);
        Player opponent = new UnbeatableAI(O);
        result = opponent.performMove(result);

        assertThat(result.hasWinner(), is(false));
    }

    @Test
    public void testShouldWinDirectlyWithColumn() {
        Board initial = new BoardBuilder()
                .row("[O][X][O]")
                .row("[X][X][O]")
                .row("[O][ ][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void testShouldWinDirectlyWithDiagonal() {
        Board initial = new BoardBuilder()
                .row("[X][O][O]")
                .row("[O][X][O]")
                .row("[X][ ][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void testShouldwinDirectlyRatherThanDefend() {
        Board initial = new BoardBuilder()
                .row("[X][O][O]")
                .row("[ ][X][O]")
                .row("[X][ ][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public  void test_XshouldWinDirectlyAtTheBottomCenter() {
        Board initial = new BoardBuilder().row("[O][X][ ]")
                                          .row("[ ][X][ ]")
                                          .row("[O][ ][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.hasWinner(), is(true));
    }
}
