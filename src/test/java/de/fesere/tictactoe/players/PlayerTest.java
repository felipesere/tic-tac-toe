package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.ui.UI;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {

    private Board board;

    @Before
    public void setup() {
        board = new ArrayBoard();
    }

    @Test
        public void test_performingMoveAddsMarker_forHuman() {
        UI dummyUI = createDummyUI();
        Player player = new Human(Marker.X, dummyUI);

        int beforePossibleMoves = getNumberOfPossibleMoves(board);
        Board markedBoard = player.performMove(board);

        int afterPossibleMoves = getNumberOfPossibleMoves(markedBoard);
        assertThat(afterPossibleMoves, is(beforePossibleMoves-1));
    }

    @Test
    public void test_performingMoveAddsMarker_forAI() {
        Player player = new RandomAI(Marker.X);

        int beforePossibleMoves = getNumberOfPossibleMoves(board);
        Board markedBoard = player.performMove(board);

        int afterPossibleMoves = getNumberOfPossibleMoves(markedBoard);
        assertThat(afterPossibleMoves, is(beforePossibleMoves-1));
    }

    private UI createDummyUI() {
        return new UI() {

            @Override
            public void displayBoard(Board board) {
            }

            @Override
            public void displayMoves(List<Move> moves) {
            }

            @Override
            public int getSelectedMove(List<Move> moves) {
                return new Random().nextInt(moves.size());
            }

            @Override
            public void showWinner(Player player) {
            }

            @Override
            public void showDraw() {
            }
        };
    }

    private int getNumberOfPossibleMoves(Board board) {
        return board.getPossibleMoves().size();
    }
}
