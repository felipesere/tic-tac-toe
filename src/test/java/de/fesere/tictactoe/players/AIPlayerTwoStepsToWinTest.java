package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Player;
import org.junit.Test;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;

public class AIPlayerTwoStepsToWinTest {

    Player player = new UnbeatableAIPlayer(X);
    Player opponent = new UnbeatableAIPlayer(O);

    @Test
    public void testShouldwin3() {

        Board initial = new BoardBuilder()
                .row1("[X][X][O]")
                .row2("[ ][ ][ ]")
                .row3("[ ][ ][O]").build();

        Board result = player.performMove(initial);

        result = opponent.performMove(result);

        System.out.println(result);
    }
}
