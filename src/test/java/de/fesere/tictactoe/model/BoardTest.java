package de.fesere.tictactoe.model;

import org.junit.Before;
import org.junit.Test;

import static de.fesere.tictactoe.model.Diagonal.BOTTOM_LEFT;
import static de.fesere.tictactoe.model.Diagonal.TOP_LEFT;
import static de.fesere.tictactoe.model.Marker.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    Board Board;

    @Before
    public void setup() {
        Board = new ArrayBoard();
    }

    @Test
    public void emptyBoardHasEmptyLines() {
       for(Line line : Board.getLines()) {
           assertThat(line.isEmpty(), is(true));
       }
    }


    @Test
    public void markTopCenterAffectsBothRowAndColumn() {
        Board updatedBoard = Board.mark(0, 1, X);

        Line firstRow = updatedBoard.getRow(0);
        assertThat(firstRow.getMarker(1), is(equalTo(X)));

        Line secondColumn = updatedBoard.getColumn(1);
        assertThat(secondColumn.getMarker(0), is(equalTo(X)));
    }

    @Test
    public void markTopLeftAffectsBothRowAndColumnAndDiagonal() {
        Board updatedBoard = Board.mark(0, 0, X);

        Line firstRow = updatedBoard.getRow(0);
        assertThat(firstRow.getMarker(0), is(equalTo(X)));

        Line firstColumn = updatedBoard.getColumn(0);
        assertThat(firstColumn.getMarker(0), is(equalTo(X)));

        Line firstDiagonal = updatedBoard.getDiagonal(TOP_LEFT);
        assertThat(firstDiagonal.getMarker(0), is(equalTo(X)));
    }

    @Test
    public void markCenter() {
        Board updatedBoard = Board.mark(1, 1, X);

        Line firstRow = updatedBoard.getRow(1);
        assertThat(firstRow.getMarker(1), is(equalTo(X)));
        assertThat(firstRow.isEmpty(), is(false));


        Line firstColumn = updatedBoard.getColumn(1);
        assertThat(firstColumn.getMarker(1), is(equalTo(X)));
        assertThat(firstColumn.isEmpty(), is(false));

        Line firstDiagonal = updatedBoard.getDiagonal(BOTTOM_LEFT);
        assertThat(firstDiagonal.getMarker(1), is(equalTo(X)));
        assertThat(firstDiagonal.isEmpty(), is(false));


        Line secondDiagonal = updatedBoard.getDiagonal(TOP_LEFT);
        assertThat(secondDiagonal.getMarker(1), is(equalTo(X)));
        assertThat(secondDiagonal.isEmpty(), is(false));
    }
}
