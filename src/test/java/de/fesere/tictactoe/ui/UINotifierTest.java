package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.BoardBuilder;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.players.RandomAI;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;

public class UINotifierTest {
    UI mockUI = Mockito.mock(UI.class);

    @Test
    public void testNotifiyWinner() {
        Board board =new BoardBuilder().row1("[O][X][ ]")
                                       .row2("[ ][X][ ]")
                                       .row3("[ ][X][O]").build();


        UINotifier notifier = new UINotifier(mockUI);
        notifier.notifyFinished(board, new RandomAI(Marker.X));

        Mockito.verify(mockUI).showWinner(any(Player.class));
        Mockito.verify(mockUI).displayBoard(any(Board.class));
    }

    @Test
    public void testNotifiyDraw() {
        Board board =new BoardBuilder().row1("[O][X][O]")
                                       .row2("[O][X][X]")
                                       .row3("[X][O][O]").build();


        UINotifier notifier = new UINotifier(mockUI);
        notifier.notifyFinished(board, new RandomAI(Marker.X));

        Mockito.verify(mockUI).showDraw();
        Mockito.verify(mockUI).displayBoard(any(Board.class));
    }
}
