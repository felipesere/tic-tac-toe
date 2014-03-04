package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.BoardBuilder;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.players.RandomAI;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class ConsoleUITest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final Board board = new ArrayBoard();


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

        InputStream input = sequenceTypedByUser("12", "3");
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
        Board board =new BoardBuilder().row1("[O][ ][ ]")
                                       .row2("[ ][X][ ]")
                                       .row3("[ ][ ][O]").build();


        UI userInterface = new ConsoleUI(emptyInput(), outputStream);
        userInterface.displayBoard(board);

        assertThat(outputStream.toString(), is("[O][ ][ ]\n[ ][X][ ]\n[ ][ ][O]\n"));
    }


    @Test
    public void testPrintsAllPossibleMoves() {
        Board board = new ArrayBoard();

        UI userInterface = new ConsoleUI(emptyInput(), outputStream);
        userInterface.displayMoves(board.getPossibleMoves());

        assertThat(linesPrinted(), is(9));
    }

    @Test
    public void testPrintsWinner() {
        UI userInterface = new ConsoleUI(emptyInput(), outputStream);
        userInterface.showWinner(new RandomAI(Marker.X));

        assertThat(linesPrinted(), is(1));
    }

    @Test
    public void testPrintsDraw() {
        UI userInterface = new ConsoleUI(emptyInput(), outputStream);
        userInterface.showDraw();

        assertThat(linesPrinted(), is(1));
    }



    private int linesPrinted() {
        return outputStream.toString().split("\n").length;
    }


    public InputStream sequenceTypedByUser(String... values) {

        String result = StringUtils.join(values, "\n");

        try {
            return new ByteArrayInputStream(result.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            fail("Encoding was not supported!");
            throw new RuntimeException("Is never thrown due to fail()");
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
