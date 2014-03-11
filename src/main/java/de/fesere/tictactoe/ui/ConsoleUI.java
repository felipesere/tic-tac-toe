package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.Board.Line;

import java.io.*;
import java.util.List;

public class ConsoleUI implements UI {

    private final BufferedReader reader;
    private final PrintStream printer;

    protected ConsoleUI(InputStream input, OutputStream output) {
        reader = new BufferedReader(new InputStreamReader(input));
        printer = new PrintStream(output);
    }

    public ConsoleUI() {
      this(System.in, System.out);
    }

    @Override
    public void displayBoard(Board board) {
        for(Line row : board.getRows()){
            printRow(row);
        }
    }

    private void printRow(Line row) {
        printer.println("[" + row.getMarker(0) + "][" + row.getMarker(1) + "][" + row.getMarker(2) + "]");
    }

    @Override
    public void displayMoves(List<Move> moves) {
        for (int i= 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            printer.println("Move (" + i + ") :" + move.getRow() + ", " + move.getColumn());
        }
    }

    @Override
    public int getSelectedMove(List<Move> possibleMoves) {
        printer.println("Your choice: ");

        int upperLimit = possibleMoves.size();
        while(true)  {
            String input = readInput();
            int choice = extract(input);
            if(isValid(choice, upperLimit)) {
               return choice;
            }
            else {
                printer.println("Please try again");
            }
        }
    }

    private boolean isValid(int choice, int upperLimit) {
        return 0 <= choice && choice < upperLimit;
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
        printer.println("Player " + player.getName() + " has won");
    }

    @Override
    public void showDraw() {
        printer.println("It was a draw");
    }
}
