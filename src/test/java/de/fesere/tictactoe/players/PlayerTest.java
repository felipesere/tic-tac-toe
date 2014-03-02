package de.fesere.tictactoe.players;

import de.fesere.tictactoe.*;
import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.players.HumanPlayer;
import de.fesere.tictactoe.players.RandomAIPlayer;
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
        Player player = new HumanPlayer(Marker.X, dummyUI);

        int beforePossibleMoves = getNumberOfPossibleMoves(board);
        Board markedBoard = player.performMove(board);

        int afterPossibleMoves = getNumberOfPossibleMoves(markedBoard);
        assertThat(afterPossibleMoves, is(beforePossibleMoves-1));
    }

    @Test
    public void test_performingMoveAddsMarker_forAI() {
        Player player = new RandomAIPlayer(Marker.X);

        int beforePossibleMoves = getNumberOfPossibleMoves(board);
        Board markedBoard = player.performMove(board);

        int afterPossibleMoves = getNumberOfPossibleMoves(markedBoard);
        assertThat(afterPossibleMoves, is(beforePossibleMoves-1));
    }

    private UI createDummyUI() {
        return new UI() {

            int choice = -1;

            @Override
            public void use(Board board) {
            }

            @Override
            public void displayBoard(Board board) {
            }

            @Override
            public void displayMoves(List<Move> moves) {
                choice = new Random().nextInt(moves.size());

            }

            @Override
            public int getSelectedMove() {
                return choice;
            }

            @Override
            public void showWinner(Player player) {
            }
        };
    }

    private int getNumberOfPossibleMoves(Board board) {
        return board.getPossibleMoves().size();
    }




}
