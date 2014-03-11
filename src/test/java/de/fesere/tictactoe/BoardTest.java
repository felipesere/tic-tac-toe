package de.fesere.tictactoe;

import de.fesere.tictactoe.Board.Line;
import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.exceptions.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;
import static de.fesere.tictactoe.board.Diagonal.BOTTOM_LEFT;
import static de.fesere.tictactoe.board.Diagonal.TOP_LEFT;
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

        Line firstRow = updatedBoard.getRow(0);
        assertThat(firstRow.getMarker(1), is(X));

        Line secondColumn = updatedBoard.getColumn(1);
        assertThat(secondColumn.getMarker(0), is(X));
    }

    @Test
    public void markTopLeftAffectsBothRowAndColumnAndDiagonal() {
        Board updatedBoard = board.mark(new Move(0,0), X);

        Line firstRow = updatedBoard.getRow(0);
        assertThat(firstRow.getMarker(0), is(X));

        Line firstColumn = updatedBoard.getColumn(0);
        assertThat(firstColumn.getMarker(0), is(X));

        Line firstDiagonal = updatedBoard.getDiagonal(TOP_LEFT);
        assertThat(firstDiagonal.getMarker(0), is(X));
    }

    @Test
    public void markCenterAndCheckForAll() {
        Board updatedBoard = board.mark(new Move(1,1), X);

        Line firstRow = updatedBoard.getRow(1);
        assertThat(firstRow.getMarker(1), is(X));
        assertThat(firstRow.isEmpty(), is(false));


        Line firstColumn = updatedBoard.getColumn(1);
        assertThat(firstColumn.getMarker(1), is(X));
        assertThat(firstColumn.isEmpty(), is(false));

        Line firstDiagonal = updatedBoard.getDiagonal(BOTTOM_LEFT);
        assertThat(firstDiagonal.getMarker(1), is(X));
        assertThat(firstDiagonal.isEmpty(), is(false));


        Line secondDiagonal = updatedBoard.getDiagonal(TOP_LEFT);
        assertThat(secondDiagonal.getMarker(1), is(X));
        assertThat(secondDiagonal.isEmpty(), is(false));
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
        Board result = new BoardBuilder().row1("[X][X][X]").build();

        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void threeInColumnHasWinner() {
        Board result = new BoardBuilder().row1("[X][][]")
                                         .row2("[X][][]")
                                         .row3("[X][][]").build();

        assertThat(result.hasWinner(), is(true));
    }

    @Test
    public void threeInDiagonalHasWinner() {
        Board result = new BoardBuilder().row1("[X][ ][ ]")
                                         .row2("[ ][X][ ]")
                                         .row3("[ ][ ][X]").build();
        assertThat(result.hasWinner(), is(true));
    }
}
