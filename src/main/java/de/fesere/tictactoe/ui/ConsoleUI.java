package de.fesere.tictactoe.ui;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.Player;
import de.fesere.tictactoe.UI;
import de.fesere.tictactoe.board.Line;

import java.io.*;
import java.util.List;

public class ConsoleUI implements UI {

    InputStream inputStream;
    PrintStream outputStream;

    protected ConsoleUI(InputStream input, PrintStream output) {
        inputStream = input;
        outputStream = output;
    }

    public ConsoleUI() {
      this(System.in, System.out);
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
            outputStream.println("Move (" + i + ") :" + move.getRow() + ", " + move.getColumn());
        }
    }

    @Override
    public int getSelectedMove() {
        outputStream.println("Your choice: ");
        BufferedReader scanner = new BufferedReader(new InputStreamReader(inputStream));
        int choice;
        try {
            String input = scanner.readLine();
            choice = Integer.parseInt(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return choice;
    }

    @Override
    public void showWinner(Player player) {
        outputStream.println("Player " + player + " has won");
    }

    private void printRow(Line row) {
        outputStream.println("[" + row.getMarker(0) + "][" + row.getMarker(1) + "][" + row.getMarker(2) + "]");
    }
}
