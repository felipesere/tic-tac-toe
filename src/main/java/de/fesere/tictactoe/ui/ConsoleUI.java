package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.UI;
import de.fesere.tictactoe.board.Line;

import java.io.*;
import java.util.List;

public class ConsoleUI implements UI {

    BufferedReader reader;
    PrintStream printer;
    private Board board;

    protected ConsoleUI(InputStream input, OutputStream output) {
        reader = new BufferedReader(new InputStreamReader(input));
        printer = new PrintStream(output);
    }

    public ConsoleUI() {
      this(System.in, System.out);
    }

    @Override
    public void use(Board board) {
        this.board = board;
    }

    @Override
    public void displayBoard(Board board) {
        for(int i = 0; i < 3; i++) {
            Line row = board.getRow(i);
            printRow(row);
        }
    }

    @Override
    public void displayMoves(List<Move> moves) {
        for (int i= 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            printer.println("Move (" + i + ") :" + move.getRow() + ", " + move.getColumn());
        }
    }

    @Override
    public int getSelectedMove() {
        printer.println("Your choice: ");

        int choice = -1;
        while(isInvalidInput(choice))  {
            String input = readInput();
            choice = extract(input);
        }
        return choice;
    }

    private boolean isInvalidInput(int choice) {
        return choice < 0 ||  board.getPossibleMoves().size() <= choice ;
    }

    private int extract(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private String readInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void showWinner(Player player) {
        printer.println("Player " + player + " has won");
    }

    private void printRow(Line row) {
        printer.println("[" + row.getMarker(0) + "][" + row.getMarker(1) + "][" + row.getMarker(2) + "]");
    }
}
