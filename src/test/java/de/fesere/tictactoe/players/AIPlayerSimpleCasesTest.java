package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Player;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AIPlayerSimpleCasesTest {

    Player player = new UnbeatableAIPlayer(Marker.X);

    @Test
    public void shouldWinColumn() {

        Board initial = new BoardBuilder()
                .row1("[O][X][O]")
                .row2("[X][X][O]")
                .row3("[O][ ][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.getRow(2).getMarker(1), is(Marker.X));
    }

    @Test
    public void testShouldwinDiagonal() {

        Board initial = new BoardBuilder()
                .row1("[X][O][O]")
                .row2("[O][X][O]")
                .row3("[X][ ][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.getRow(2).getMarker(2), is(Marker.X));
    }

    @Test
    public void testShouldwin3() {

        Board initial = new BoardBuilder()
                .row1("[X][O][O]")
                .row2("[ ][X][O]")
                .row3("[X][ ][ ]").build();

        Board result = player.performMove(initial);
        assertThat(result.getRow(1).getMarker(0), is(Marker.X));
    }

    @Test
    public void testShouldDraw() {

        Board initial = new BoardBuilder()
                .row1("[X][O][O]")
                .row2("[O][X][X]")
                .row3("[X][ ][O]").build();

        Board result = player.performMove(initial);
        assertThat(result.getRow(2).getMarker(1), is(Marker.X));
    }
}