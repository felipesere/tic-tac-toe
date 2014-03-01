package de.fesere.tictactoe.model;

import org.junit.Test;

import static de.fesere.tictactoe.model.Marker.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    Board board = new Board();

    @Test
    public void emptyBoardHasOnlyEmptyRows() {
        for(int i = 0; i < 3; i++) {
            Line row = board.getRow(i);
            assertIsEmpty(row);
        }
    }


    @Test
    public void emptyBoardHasOnlyEmptyColumns() {
        for(int i = 0; i < 3; i++) {
            Line column = board.getColumn(i);
            assertIsEmpty(column);
        }
    }

    @Test
    public void emptyBoardHasOnlyEmptyDiagonals() {
        for(int i = 0; i < 2; i++) {
            Line column = board.getDiagonal(i);
            assertIsEmpty(column);
        }
    }

    @Test
    public void markTopCenterAffectsBothRowAndColumn() {
        Board updatedBoard = board.mark(0, 1, X);

        Line firstRow = updatedBoard.getRow(0);
        assertThat(firstRow.getMarker(1), is(equalTo(X)));

        Line secondColumn = updatedBoard.getColumn(1);
        assertThat(secondColumn.getMarker(0), is(equalTo(X)));
    }



    public void assertIsEmpty(Line line) {
        assertThat(line.isEmpty(), is(true));
    }
}
