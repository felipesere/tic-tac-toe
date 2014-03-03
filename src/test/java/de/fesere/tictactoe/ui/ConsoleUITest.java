package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.UI;
import de.fesere.tictactoe.board.ArrayBoard;
import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsoleUITest {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Board board = new ArrayBoard();


    @Test
    public void testGetSelectedMoveWithValidInput() {

        InputStream input = sequenceTypedByUser("3");
        UI userInterface = new ConsoleUI(input, outputStream);

        int result = userInterface.getSelectedMove(board.getPossibleMoves());
        assertThat(result, is(3));
    }

    @Test
    public void testGetSelectedMoveWithInvalidInput_NotANumber_followedByNumber() {

        InputStream input = sequenceTypedByUser("abc", "3");
        UI userInterface = new ConsoleUI(input, outputStream);

        int result = userInterface.getSelectedMove(board.getPossibleMoves());
        assertThat(result, is(3));
    }

    @Test
    public void testGetSelectedMoveWithInvalidInput_NotANumber_negativeNumber() {

        InputStream input = sequenceTypedByUser("-12", "3");
        UI userInterface = new ConsoleUI(input, outputStream);

        int result = userInterface.getSelectedMove(board.getPossibleMoves());
        assertThat(result, is(3));
    }

    @Test
    public void testGetSelectedMoveWithInvalidInput_NotANumber_largerThanNumberOfChoices() {
        Board board = new ArrayBoard();

        InputStream input = sequenceTypedByUser("9", "3");
        UI userInterface = new ConsoleUI(input, outputStream);

        int result = userInterface.getSelectedMove(board.getPossibleMoves());
        assertThat(result, is(3));
    }


    @Test
    public void testPrintEmptyBoard() throws UnsupportedEncodingException {
        UI userInterface = new ConsoleUI(emptyInput(), outputStream);
        userInterface.displayBoard(new ArrayBoard());

        assertThat(outputStream.toString(), is("[ ][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]\n"));
    }

    @Test
    public void testPrintBoardWithMarkers() throws UnsupportedEncodingException {
        Board board = new ArrayBoard();
        board = board.mark(new Move(0,0), Marker.O);
        board = board.mark(new Move(1,1), Marker.X);
        board = board.mark(new Move(2,2), Marker.O);


        UI userInterface = new ConsoleUI(emptyInput(), outputStream);
        userInterface.displayBoard(board);

        assertThat(outputStream.toString(), is("[O][ ][ ]\n[ ][X][ ]\n[ ][ ][O]\n"));
    }


    public InputStream sequenceTypedByUser(String... values) {

        String result = StringUtils.join(values, "\n");

        try {
            return new ByteArrayInputStream(result.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Encoding was not supported!");
            throw new RuntimeException();
        }
    }

    public InputStream emptyInput() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }


}
