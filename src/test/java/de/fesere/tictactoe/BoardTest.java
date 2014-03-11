package de.fesere.tictactoe;

import de.fesere.tictactoe.Board.Line;
import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.exceptions.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class BoardTest {
    private Board board;

    @Before
    public void setup() {
        board = new ArrayBoard();
    }

    @Test
    public void emptyBoardHasEmptyLines() {
       for(Line line : board.getLines()) {
           assertThat(line.isEmpty(), is(true));
       }
    }


    @Test
    public void markTopCenterAffectsBothRowAndColumn() {

        Board updatedBoard = board.mark(new Move(0,1), X);

        List<Line> lines = keepNonEmptyLines(updatedBoard.getLines());
        assertThat(lines, hasSize(2));
    }


    @Test
    public void markTopLeftAffectsBothRowAndColumnAndDiagonal() {
        Board updatedBoard = board.mark(new Move(0,0), X);

        List<Line> lines = keepNonEmptyLines(updatedBoard.getLines());
        assertThat(lines, hasSize(3));
    }

    @Test
    public void markCenterAndCheckForAll() {
        Board updatedBoard = board.mark(new Move(1,1), X);

        List<Line> lines = keepNonEmptyLines(updatedBoard.getLines());
        assertThat(lines, hasSize(4));
    }

    @Test
    public void emptyBoardHasNinePossibleMoves() {
        List<Move> possibleMoves =  board.getPossibleMoves();
        assertThat(possibleMoves, hasSize(9));
    }

    @Test
    public void markingReducesNumberOfPossibleMoves() {
        Board updatedBoard = board.mark(new Move(0, 0), O);
        List<Move> possibleMoves = updatedBoard.getPossibleMoves();
        assertThat(possibleMoves, hasSize(8));


        List<Move> oldPossibleMoves =  board.getPossibleMoves();
        assertThat(oldPossibleMoves, hasSize(9));
    }

    @Test(expected = InvalidMoveException.class)
    public void canNotMarkPreviouslyMarkedBoard() {
        Move move = new Move(1,1);
        Board updatedBoard = board.mark(move, X);
        updatedBoard.mark(move, O);
    }


    @Test
    public void emptyBoardHasNoWinner() {
        assertThat(board.hasWinner(), is(false));
    }

    @Test
    public void threeInRowHasWinner() {
        Board result = new BoardBuilder().row("[X][X][X]").build();

        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void threeInColumnHasWinner() {
        Board result = new BoardBuilder().row("[X][][]")
                                         .row("[X][][]")
                                         .row("[X][][]").build();

        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void threeInDiagonalHasWinner() {
        Board result = new BoardBuilder().row("[X][ ][ ]")
                                         .row("[ ][X][ ]")
                                         .row("[ ][ ][X]").build();
        assertThat(result.hasWinner(), is(true));
    }



    private List<Line> keepNonEmptyLines(List<Line> lines) {
        List<Line> result = new LinkedList<>();
        for(Line line : lines) {
            if(!line.isEmpty()) {
                result.add(line);
            }
        }

        return result;
    }
}
